package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Pay;
import com.manager.common.core.domain.entity.RechargeOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 充值订单查询
 * @author sieGuang 2021/09/10
 */
@Mapper
public interface RechargeOrderMapper {

    /**
     * 查询
     */
    List<RechargeOrder> getRechargeOrderList(RechargeOrder rechargeOrder);

}
