package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.VipRecharge;
import com.manager.system.mapper.RechargeMapper;
import com.manager.system.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/9/6
 */
@Service
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    private RechargeMapper rechargeMapper;

    @Override
    public Integer saveVipRecharge(VipRecharge vipRecharge) {
        return rechargeMapper.saveVipRecharge(vipRecharge);
    }

    @Override
    public List findVipRecharge() {
        return rechargeMapper.findVipRecharge();
    }

    @Override
    public Integer updateVipRecharge(VipRecharge vipRecharge) {
        return rechargeMapper.updateVipRecharge(vipRecharge);
    }
}
