package com.manager.system.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.RechargeOrder;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.uuid.IdUtils;
import com.manager.system.mapper.RechargeOrderMapper;
import com.manager.system.service.RechargeOrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 充值订单查询
 *
 * @author sieGuang 2021/09/10
 */
@Service
public class RechargeOrderServiceImpl implements RechargeOrderService {

    @Autowired
    private RechargeOrderMapper rechargeOrderMapper;

    @Override
    public Map getRechargeOrderList(RechargeOrder rechargeOrder) {
        // 充值次数
        int rechargeNum = 0;
        // 充值成功次数
        int rechargeRechargeNum = 0;
        // 充值成功总金额
        BigDecimal rechargeRechargeAmount = new BigDecimal(0);
        // 放回参数
        Map result = new HashMap();
        // 设置分页数据
        PageHelper.startPage(rechargeOrder.getPage(), rechargeOrder.getSize(),
                rechargeOrder.getOrderByColumn() + " " + rechargeOrder.getIsAsc());

        List<RechargeOrder> list = rechargeOrderMapper.getRechargeOrderList(rechargeOrder);

        PageInfo<RechargeOrder> pageInfo = new PageInfo<RechargeOrder>(list);
        result.put("page", rechargeOrder.getPage());
        result.put("size", rechargeOrder.getSize());
        result.put("total", pageInfo.getTotal());

        // 只处理分页的数据
        list = pageInfo.getList();

        // 左下角的哪几个字段
        if (CollectionUtils.isNotEmpty(list)) {

            rechargeNum = list.size();

            List<RechargeOrder> temp1 = list.stream().filter(f -> "1".equals(f.getPaymentStatus())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(temp1)) {
                rechargeRechargeNum = temp1.size();
            }

            for (RechargeOrder order : list) {
                rechargeRechargeAmount = rechargeRechargeAmount.add(order.getRechargeAmount());
            }

            // 查询系统赠送页面的多放回几个字段
            if (rechargeOrder != null && ("5".equals(rechargeOrder.getRechargeType()))) {

                // 人工充值次数
                Integer artificialRechargeNum = 0;
                // 人工充值金额
                BigDecimal artificialRechargeAmount = new BigDecimal(0);
                // 充值扣值次数
                int rechargeDeductionNum = 0;
                // 人工充值金额
                BigDecimal rechargeDeductionAmount = new BigDecimal(0);
                // 彩金加款次数
                int jackpotRechargeNum = 0;
                // 彩金加款金额
                BigDecimal jackpotRechargeAmount = new BigDecimal(0);
                // 彩金扣除次数
                int jackpotDeductionNum = 0;
                // 彩金扣除金额
                BigDecimal jackpotDeductionAmount = new BigDecimal(0);

                for (RechargeOrder order : list) {
                    if ("1".equals(order.getOperateType())) {
                        artificialRechargeNum++;
                        artificialRechargeAmount = artificialRechargeAmount.add(order.getRechargeAmount());
                    } else if ("2".equals(order.getOperateType())) {
                        rechargeDeductionNum++;
                        rechargeDeductionAmount = rechargeDeductionAmount.add(order.getRechargeAmount());
                    } else if ("3".equals(order.getOperateType())) {
                        jackpotRechargeNum++;
                        jackpotRechargeAmount = jackpotRechargeAmount.add(order.getRechargeAmount());
                    } else if ("4".equals(order.getOperateType())) {
                        jackpotDeductionNum++;
                        jackpotDeductionAmount = jackpotDeductionAmount.add(order.getRechargeAmount());
                    }
                }
                result.put("artificialRechargeNum", artificialRechargeNum);
                result.put("artificialRechargeAmount", artificialRechargeAmount);
                result.put("rechargeDeductionNum", rechargeDeductionNum);
                result.put("rechargeDeductionAmount", rechargeDeductionAmount);
                result.put("jackpotRechargeNum", jackpotRechargeNum);
                result.put("jackpotRechargeAmount", jackpotRechargeAmount);
                result.put("jackpotDeductionNum", jackpotDeductionNum);
                result.put("jackpotDeductionAmount", jackpotDeductionAmount);
            }
        }

        result.put("rows", list);
        result.put("rechargeNum", rechargeNum);
        result.put("rechargeRechargeNum", rechargeRechargeNum);
        result.put("rechargeRechargeAmount", rechargeRechargeAmount);
        return result;
    }

    @Override
    public Integer addRechargeOrder(RechargeOrder rechargeOrder) {
        rechargeOrder.setOrderNumber(IdUtils.getOrderId());
        rechargeOrder.setAdminUserId(String.valueOf(SecurityUtils.getUserId()));
        rechargeOrder.setTid(ManagerConfig.getTid());
        return rechargeOrderMapper.addRechargeOrder(rechargeOrder);
    }

    @Override
    public Integer editRechargeOrder(RechargeOrder rechargeOrder) {
        return rechargeOrderMapper.editRechargeOrder(rechargeOrder);
    }

    @Override
    public Integer uidIsPresent(int uid,int tid) {
        return rechargeOrderMapper.uidIsPresent(uid,tid);
    }

    @Override
    public Integer selectRechargeGive(int i) {
        if (i == 1 || i == 2) {
            return rechargeOrderMapper.selectRechargeGive(i);
        }
        return 0;
    }

    @Override
    public Integer selectJinMonthGive() {
        return rechargeOrderMapper.selectJinMonthGive();
    }

    @Override
    public Integer selectYinMonthGive() {
        return rechargeOrderMapper.selectYinMonthGive();
    }

    @Override
    public Map getRechargeAmount(String monthlyCardType) {
        return rechargeOrderMapper.getRechargeAmount(monthlyCardType);
    }

    @Override
    public List<RechargeOrder> export(RechargeOrder rechargeOrder) {
        List<RechargeOrder> list = rechargeOrderMapper.getRechargeOrderList(rechargeOrder);
        return list;
    }

}
