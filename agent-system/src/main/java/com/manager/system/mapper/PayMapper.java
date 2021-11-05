package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 商城页签管理
 *
 * @author sieGuang 2021/09/07
 */
@Mapper
public interface PayMapper {

    /**
     * 查询
     */
    @Select("select id,sort,cname,recharge_give as rechargeGive,status," +
            "pay_type as payType from config_pay where tid = #{tid} ORDER BY sort ASC")
    List<Map> getPayList(@Param("tid") Integer tid);

    /**
     * 编辑
     *
     * @param pay 需要修改的内容
     */
    int editPay(Pay pay);

}
