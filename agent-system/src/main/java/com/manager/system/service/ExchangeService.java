package com.manager.system.service;

import com.manager.common.core.domain.entity.Exchange;
import com.manager.common.core.domain.entity.Pay;

import java.util.List;

/**
 * 提现信息管理
 * @author sieGuang 2021/09/07
 */
public interface ExchangeService {

    /**
     * 查询
     */
    List getExchangeList();

    /**
     * 编辑
     * @param exchange 需要修改的内容
     */
    int editExchange(Exchange exchange);

    /**
     * 编辑
     * @param settingsType 基础类型 1保留金额  2打码倍数 3提现总次数
     * @param value 值
     */
    int editSettingsExchange(int settingsType,String value);

}
