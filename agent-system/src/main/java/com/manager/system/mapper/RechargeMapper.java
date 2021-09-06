package com.manager.system.mapper;

import com.manager.common.core.domain.entity.VipRecharge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author marvin 2021/9/6
 */
@Mapper
public interface RechargeMapper {

    Integer saveVipRecharge(VipRecharge vipRecharge);

    List findVipRecharge();

    Integer updateVipRecharge(VipRecharge vipRecharge);
}
