package com.manager.system.mapper;

import com.manager.common.core.domain.entity.OnlineRecharge;
import com.manager.common.core.domain.entity.VipRecharge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/6
 */
@Mapper
public interface RechargeMapper {

    Integer saveVipRecharge(VipRecharge vipRecharge);

    List findVipRecharge();

    Integer updateVipRecharge(VipRecharge vipRecharge);

    Integer saveOnlineRecharge(OnlineRecharge onlineRecharge);

    @Select("select id payId,cname payName from config_pay where pay_type = '2' ")
    List<Map> getOnlinePays();

    List findOnlineRecharge(OnlineRecharge onlineRecharge);

    Integer updateOnlineRecharge(OnlineRecharge onlineRecharge);
}
