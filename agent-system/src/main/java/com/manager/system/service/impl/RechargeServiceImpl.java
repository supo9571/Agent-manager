package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.OnlineRecharge;
import com.manager.common.core.domain.entity.VipRecharge;
import com.manager.system.mapper.RechargeMapper;
import com.manager.system.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/9/6
 * 充值 配置service
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


    /**
     * 获取在线充值 下拉列表
     * @return
     */
    public List getOnlinePays() {
        return rechargeMapper.getOnlinePays();
    }

    /**
     * 添加 线上充值 配置信息
     * @param onlineRecharge
     * @return
     */
    @Override
    public Integer saveOnlineRecharge(OnlineRecharge onlineRecharge) {
        return  rechargeMapper.saveOnlineRecharge(onlineRecharge);
    }

    /**
     * 查询线上充值 配置
     * @param onlineRecharge
     * @return
     */
    @Override
    public List findOnlineRecharge(OnlineRecharge onlineRecharge) {
        return rechargeMapper.findOnlineRecharge(onlineRecharge);
    }

    /**
     * 修改线上充值 配置
     * @param onlineRecharge
     * @return
     */
    @Override
    public Integer updateOnlineRecharge(OnlineRecharge onlineRecharge) {
        return rechargeMapper.updateOnlineRecharge(onlineRecharge);
    }
}
