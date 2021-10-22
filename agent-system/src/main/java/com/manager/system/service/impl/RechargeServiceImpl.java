package com.manager.system.service.impl;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.*;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.mapper.RechargeMapper;
import com.manager.system.service.RechargeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return rechargeMapper.findVipRecharge(ManagerConfig.getTid());
    }

    @Override
    public Integer updateVipRecharge(VipRecharge vipRecharge) {
        return rechargeMapper.updateVipRecharge(vipRecharge);
    }


    /**
     * 获取在线充值 下拉列表
     *
     * @return
     */
    public List getOnlinePays() {
        return rechargeMapper.getOnlinePays(ManagerConfig.getTid());
    }

    /**
     * 添加 线上充值 配置信息
     *
     * @param onlineRecharge
     * @return
     */
    @Override
    public Integer saveOnlineRecharge(OnlineRecharge onlineRecharge) {
        return rechargeMapper.saveOnlineRecharge(onlineRecharge);
    }

    /**
     * 查询线上充值 配置
     *
     * @param onlineRecharge
     * @return
     */
    @Override
    public List findOnlineRecharge(OnlineRecharge onlineRecharge) {
        return rechargeMapper.findOnlineRecharge(onlineRecharge);
    }

    /**
     * 修改线上充值 配置
     *
     * @param onlineRecharge
     * @return
     */
    @Override
    public Integer updateOnlineRecharge(OnlineRecharge onlineRecharge) {
        return rechargeMapper.updateOnlineRecharge(onlineRecharge);
    }

    /**
     * 添加 银行卡充值 配置
     *
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
     *
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
     *
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
        return rechargeMapper.fingYsinfo(ManagerConfig.getTid());
    }

    @Override
    public Integer updateYsinfo(Ysinfo ysinfo) {
        if (StringUtils.isNotBlank(ysinfo.getPassword()))
            ysinfo.setPassword(SecurityUtils.encryptPassword(ysinfo.getPassword()));
        return rechargeMapper.updateYsinfo(ysinfo);
    }

    @Override
    public List getYsOption() {
        return rechargeMapper.getYsOption(ManagerConfig.getTid());
    }

    @Override
    @Transactional
    public void ysRecharge(Integer ysid, Long amount) {
        //添加 额度
        rechargeMapper.ysRecharge(ysid,amount*10000);
        //查询 银商 额度信息
        YsQuota ysQuota = rechargeMapper.findYsinfoById(ysid);
        ysQuota.setType(1);
        ysQuota.setAmount(amount*10000);
        //添加 额度记录
        rechargeMapper.addYsQuotaInfo(ysQuota);
    }

    @Override
    public List ysquota(Integer ysid,Integer type) {
        return rechargeMapper.getYsQuotaInfo(ysid,type);
    }
}
