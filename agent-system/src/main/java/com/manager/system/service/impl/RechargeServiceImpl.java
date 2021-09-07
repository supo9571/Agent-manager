package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.*;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.mapper.RechargeMapper;
import com.manager.system.service.RechargeService;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 添加 银行卡充值 配置
     * @param bankRecharge
     * @return
     */
    @Override
    public Integer saveBankRecharge(BankRecharge bankRecharge) {
        return rechargeMapper.saveBankRecharge(bankRecharge);
    }

    @Override
    public List findBankRecharge(BankRecharge bankRecharge) {
        return rechargeMapper.findBankRecharge(bankRecharge);
    }

    @Override
    public Integer updateBankRecharge(BankRecharge bankRecharge) {
        return rechargeMapper.updateBankRecharge(bankRecharge);
    }

    /**
     * 添加月卡
     * @param monthRecharge
     * @return
     */
    @Override
    public Integer saveMonthRecharge(MonthRecharge monthRecharge) {
        return rechargeMapper.saveMonthRecharge(monthRecharge);
    }

    @Override
    public List findMonthRecharge(MonthRecharge monthRecharge) {
        return rechargeMapper.findMonthRecharge(monthRecharge);
    }

    @Override
    public Integer updateMonthRecharge(MonthRecharge monthRecharge) {
        return rechargeMapper.updateMonthRecharge(monthRecharge);
    }

    /**
     * 添加 银商
     * @param ysinfo
     * @return
     */
    @Override
    public Integer saveYsinfo(Ysinfo ysinfo) {
        ysinfo.setPassword(SecurityUtils.encryptPassword(ysinfo.getPassword()));
        return rechargeMapper.saveYsinfo(ysinfo);
    }

    @Override
    public List findYsinfo() {
        return rechargeMapper.fingYsinfo();
    }

    @Override
    public Integer updateYsinfo(Ysinfo ysinfo) {
        if (StringUtils.isNotBlank(ysinfo.getPassword())) ysinfo.setPassword(SecurityUtils.encryptPassword(ysinfo.getPassword()));
        return rechargeMapper.updateYsinfo(ysinfo);
    }

    @Override
    public List getYsOption() {
        return rechargeMapper.getYsOption();
    }
}
