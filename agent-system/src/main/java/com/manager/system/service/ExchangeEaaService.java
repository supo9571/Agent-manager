package com.manager.system.service;

import com.manager.common.core.domain.entity.ExchangeEaa;

import java.util.List;
import java.util.Map;

/**
 * 提现审批
 * @author sieGuang 2021/09/16
 */
public interface ExchangeEaaService {

    /**
     * 查询提现审批数据
     */
    Map getExchangeEaaList(ExchangeEaa exchangeEaa);

    /**
     * 玩家打码信息查询
     */
    List getAddMosaicPlayerList(int uid);

    /**
     * 导出
     */
    List export(ExchangeEaa exchangeEaa);

    /**
     * 修改
     */
    int editExchangeEaaList(ExchangeEaa exchangeEaa);

    /**
     * 获取打款方式下拉框
     */
    List getTransferMode();
}
