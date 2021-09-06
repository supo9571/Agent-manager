package com.manager.system.service;

import com.manager.common.core.domain.entity.VipRecharge;

import java.util.List;

/**
 * @author marvin 2021/9/6
 */
public interface RechargeService {
    Integer saveVipRecharge(VipRecharge vipRecharge);

    List findVipRecharge();

    Integer updateVipRecharge(VipRecharge vipRecharge);
}
