package com.manager.system.service;

import com.manager.common.core.domain.AjaxResult;

/**
 * 游戏端服务
 * @author sieGuang 2021/11/09
 */
public interface ReportAgentService {

    /**
     * 给用户加减金币
     * @param cmd   addcoins 加金币、reducecoins 减金币
     * @param value 增加值
     * @param uid   用户uid
     * @param saveflag 0=操作大厅的金币,1=操作保险箱,默认0
     * @param reason 100070=vip充值  100071=金卡月卡充值 100072=银卡月卡充值 100073=银行卡充值
     * @return
     */
    AjaxResult editCoinsGm(String cmd, Long value, Integer uid, Integer saveflag, Integer reason);

}
