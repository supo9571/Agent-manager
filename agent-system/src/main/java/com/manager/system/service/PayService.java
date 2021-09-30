package com.manager.system.service;

import com.manager.common.core.domain.entity.Pay;

import java.util.List;

/**
 * 商城页签管理
 *
 * @author sieGuang 2021/09/07
 */
public interface PayService {

    /**
     * 查询
     */
    List getPayList();

    /**
     * 编辑
     *
     * @param pay 需要修改的内容
     */
    int editPay(Pay pay);

}
