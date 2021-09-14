package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Pay;
import com.manager.common.core.domain.entity.RechargeOrder;
import com.manager.common.core.domain.entity.VipRecharge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    Integer addRechargeOrder(RechargeOrder rechargeOrder);

    Integer editRechargeOrder(RechargeOrder rechargeOrder);

    @Select("select recharge_give from config_pay where pay_type = #{payType} and status = '1' limit 0,1")
    Integer selectRechargeGive(@Param("payType") int payType);

    @Select("select jin_recharge_give from config_month_recharge where status = '1' limit 0,1")
    Integer selectJinMonthGive();

    @Select("select yin_recharge_give from config_month_recharge where status = '1' limit 0,1")
    Integer selectYinMonthGive();
}
