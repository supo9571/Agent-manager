package com.manager.system.service;

import com.manager.common.core.domain.entity.Pay;
import com.manager.common.core.domain.entity.RechargeOrder;

import java.util.List;
import java.util.Map;

/**
 * 充值订单查询
 * @author sieGuang 2021/09/10
 */
public interface RechargeOrderService {

    /**
     * 查询
     */
    Map getRechargeOrderList(RechargeOrder rechargeOrder);

}
