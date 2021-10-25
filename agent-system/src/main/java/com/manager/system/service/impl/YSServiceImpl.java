package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.RechargeOrder;
import com.manager.common.core.domain.entity.YsQuota;
import com.manager.common.core.domain.entity.Ysinfo;
import com.manager.common.utils.http.HttpUtils;
import com.manager.common.utils.uuid.IdUtils;
import com.manager.system.mapper.RechargeMapper;
import com.manager.system.mapper.RechargeOrderMapper;
import com.manager.system.mapper.YSMapper;
import com.manager.system.service.YSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/23
 */
@Service
public class YSServiceImpl implements YSService {

    @Autowired
    private YSMapper ysMapper;
    @Override
    public Ysinfo selectByName(String username) {
        return ysMapper.selectByName(username,ManagerConfig.getTid());
    }

    @Override
    public List getOrder(String uid, String beginTime, String endTime, Integer ysid) {
        return ysMapper.getOrder(uid,beginTime,endTime,ysid);
    }

    @Override
    public Map getOrderCount(String uid, String beginTime, String endTime, Integer ysid) {
        return ysMapper.getOrderCount(uid,beginTime,endTime,ysid);
    }

    @Override
    public List getReport(String beginTime, String endTime, Integer ysid) {
        return ysMapper.getReport(beginTime,endTime,ysid);
    }

    @Autowired
    private ManagerConfig managerConfig;

    @Autowired
    private RechargeOrderMapper rechargeOrderMapper;

    @Autowired
    private RechargeMapper rechargeMapper;

    @Override
    @Transactional
    public String recharge(Integer ysid, Integer uid, Long amount, String name, String bank) {
        //查询银商余额
        Long ysAmount = ysMapper.getYsAmount(ysid);
        if(ysAmount<amount*1000){
            return "账号额度不足，请充值。";
        }
        //判断uid是否合法
        if(ysMapper.checkUid(uid, ManagerConfig.getTid())<1){
            return "用户id不合法，请确认后重试。";
        }
        Integer rechargeGive = rechargeOrderMapper.selectRechargeGive(1);
        BigDecimal bigGive = new BigDecimal(amount).multiply(new BigDecimal(rechargeGive)).divide(new BigDecimal(100));
        Long give = bigGive.longValue();
        //充值
        JSONObject param = new JSONObject();
        param.put("cmd", "addcoins");
        param.put("reason", 100070);
        param.put("type", 1);//1=加金币,2=加流水
        param.put("value", (amount+give)*10000);
        param.put("uid", uid);
        //操作 用户金币
        String result = HttpUtils.sendPost(managerConfig.getReportDomain(),"data=" + param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            Long curr = resultJson.getLong("curr")/10000;//用户余额
            //修改银商余额
            ysMapper.updateYSAmount(ysid,amount*10000);
            //添加充值订单
            RechargeOrder rechargeOrder = new RechargeOrder();
            rechargeOrder.setUid(uid);
            rechargeOrder.setAfterOrderMoney(new BigDecimal(curr));
            rechargeOrder.setBeforeOrderMoney(new BigDecimal(curr-amount-give));
            rechargeOrder.setRechargeAmount(new BigDecimal(amount));
            rechargeOrder.setExCoins(new BigDecimal(give));
            rechargeOrder.setRemark(name+" "+bank);
            rechargeOrder.setOrderNumber(IdUtils.getOrderId());
            rechargeOrder.setAdminUserId(ysid+"");
            rechargeOrder.setTid(ManagerConfig.getTid());
            rechargeOrder.setRechargeType("1");
            rechargeOrderMapper.addRechargeOrder(rechargeOrder);
            //添加额度记录
            YsQuota ysQuota = rechargeMapper.findYsinfoById(ysid);
            ysQuota.setType(2);
            ysQuota.setValue(amount*10000);
            ysQuota.setAmount((ysQuota.getAmount()-amount*10000));
            rechargeMapper.addYsQuotaInfo(ysQuota);
            //添加 备注信息
            if (ysMapper.selectExchangeName(uid, name) < 1) {
                ysMapper.saveMark(uid,name,bank,ManagerConfig.getTid());
            }
        }

        return "操作成功";
    }

    @Override
    public String risk(Integer ysid, Integer uid, Long amount, String name, String bank) {
        //查询银商余额
        Long ysAmount = ysMapper.getYsAmount(ysid);
        if(ysAmount<amount*1000){
            return "账号额度不足，请充值。";
        }
        //判断uid是否合法
        if(ysMapper.checkUid(uid, ManagerConfig.getTid())<1){
            return "用户id不合法，请确认。";
        }
        //是否为黑名单用户
        if (ysMapper.checkBlack(name, bank, ManagerConfig.getTid()) > 0) {
            return "该备注信息为黑名单用户。";
        }
        //判断 姓名是否一致
        if (ysMapper.selectExchangeName(uid, name) < 1) {
            return "备注姓名与玩家绑定姓名不符。";
        }
        return "请确认：对玩家ID【"+uid+"】进行手动充值，充值金额为【"+amount+"】元？";
    }

    @Override
    public List getMark(Integer uid, String beginTime, String endTime) {
        return ysMapper.getMark(uid,beginTime,endTime,ManagerConfig.getTid());
    }

    @Override
    public List getBlack(Integer uid, String beginTime, String endTime) {
        return ysMapper.getBlack(uid,beginTime,endTime,ManagerConfig.getTid());
    }

    @Override
    public Long getYsAmount(Integer ysid) {
        return ysMapper.getYsAmount(ysid);
    }


}
