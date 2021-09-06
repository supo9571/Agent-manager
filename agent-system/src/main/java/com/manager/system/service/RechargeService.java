package com.manager.system.service;

import com.manager.common.core.domain.entity.BankRecharge;
import com.manager.common.core.domain.entity.MonthRecharge;
import com.manager.common.core.domain.entity.OnlineRecharge;
import com.manager.common.core.domain.entity.VipRecharge;

import java.util.List;

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

    Integer saveBankRecharge(BankRecharge bankRecharge);

    List findBankRecharge(BankRecharge bankRecharge);

    Integer updateBankRecharge(BankRecharge bankRecharge);

    Integer saveMonthRecharge(MonthRecharge monthRecharge);

    List findMonthRecharge(MonthRecharge monthRecharge);

    Integer updateMonthRecharge(MonthRecharge monthRecharge);
}
