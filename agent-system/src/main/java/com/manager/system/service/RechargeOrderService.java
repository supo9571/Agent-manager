package com.manager.system.service;

import com.manager.common.core.domain.entity.Pay;
import com.manager.common.core.domain.entity.RechargeOrder;
import com.manager.common.core.domain.entity.VipRecharge;

import java.util.List;
import java.util.Map;

/**
 * 充值订单查询
 * @author sieGuang 2021/09/10
 */
public interface RechargeOrderService {

    /**
     * 查询充值数据
     */
    Map getRechargeOrderList(RechargeOrder rechargeOrder);

    /**
     * 增加充值数据
     * @param rechargeOrder 参数
     */
    Integer addRechargeOrder(RechargeOrder rechargeOrder);

    /**
     * 确认充值、取消充值
     * @param rechargeOrder
     */
    Integer editRechargeOrder(RechargeOrder rechargeOrder);

    Integer selectRechargeGive(int i);

    Integer selectJinMonthGive();

    Integer selectYinMonthGive();
}
