package com.manager.system.service;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/6
 */
public interface RechargeService {
    Integer saveVipRecharge(VipRecharge vipRecharge);

    List findVipRecharge();

    Integer updateVipRecharge(VipRecharge vipRecharge);

    Integer saveOnlineRecharge(OnlineRecharge onlineRecharge);

    List getOnlinePays();

    List findOnlineRecharge(OnlineRecharge onlineRecharge);

    Integer updateOnlineRecharge(OnlineRecharge onlineRecharge);

    Integer getBankRechargeId(BankRecharge bankRecharge);

    Integer saveBankRecharge(BankRecharge bankRecharge);

    List findBankRecharge(BankRecharge bankRecharge);

    Integer updateBankRecharge(BankRecharge bankRecharge);

    Integer saveMonthRecharge(MonthRecharge monthRecharge);

    List findMonthRecharge(MonthRecharge monthRecharge);

    Integer updateMonthRecharge(MonthRecharge monthRecharge);

    Integer saveYsinfo(Ysinfo ysinfo);

    List findYsinfo();

    Integer updateYsinfo(Ysinfo ysinfo);

    List getYsOption();

    void ysRecharge(Integer ysid, Long amount);

    List ysquota(Integer ysid,Integer type,String beginTime,String endTime);

    Map ysquotaCount(Integer ysid, Integer type, String beginTime, String endTime);

    List ysreport(Integer ysid, String ysname, String beginTime, String endTime, Long transferInMin, Long transferInMax, Long transferOutMin, Long transferOutMax);

    BigDecimal ysCount(Integer ysid);

    List getMark(Integer uid, String beginTime, String endTime);

    List getBlack(Integer uid, String beginTime, String endTime);
}
