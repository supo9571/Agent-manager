package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.Ysinfo;
import com.manager.common.utils.http.HttpUtils;
import com.manager.system.mapper.YSMapper;
import com.manager.system.service.YSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return ysMapper.selectByName(username);
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
        //充值
        JSONObject param = new JSONObject();
        param.put("cmd", "addcoins");
        param.put("reason", 100070);
        param.put("type", 1);//1=加金币,2=加流水
        param.put("value", amount*10000);
        param.put("uid", uid);
        //操作 用户金币
        String result = HttpUtils.sendPost(managerConfig.getReportDomain(),"data=" + param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            //修改银商余额
            ysMapper.updateYSAmount(ysid,amount*10000);
            //添加充值订单
            //TODO
            //添加额度记录

            //添加 备注信息
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
}
