package com.manager.system.service.impl;


import com.manager.common.annotation.DataSource;
import com.manager.common.core.domain.entity.Pay;
import com.manager.common.core.domain.entity.RechargeOrder;
import com.manager.common.enums.DataSourceType;
import com.manager.system.mapper.RechargeOrderMapper;
import com.manager.system.service.RechargeOrderService;
import lombok.Data;
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
 * @author sieGuang 2021/09/10
 */
@Service
@DataSource(DataSourceType.SLAVE)
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

        List<RechargeOrder> list = rechargeOrderMapper.getRechargeOrderList(rechargeOrder);
        if(CollectionUtils.isNotEmpty(list)){
            List<RechargeOrder> temp1 = list.stream().filter(f -> "1".equals(f.getPaymentStatus())).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(temp1)){
                rechargeRechargeNum = temp1.size();
            }

            for (RechargeOrder order : list) {
                System.out.println(order.getRechargeAmount());
                rechargeRechargeAmount.add(order.getRechargeAmount());
            }
            rechargeNum = list.size();
        }

        Map result = new HashMap();
        result.put("list",list);
        result.put("rechargeNum",rechargeNum);
        result.put("rechargeRechargeNum",rechargeRechargeNum);
        result.put("rechargeRechargeAmount",rechargeRechargeAmount);
        return result;
    }

}
