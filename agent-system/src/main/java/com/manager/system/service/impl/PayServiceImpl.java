package com.manager.system.service.impl;


import com.manager.common.annotation.DataSource;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.Pay;
import com.manager.common.enums.DataSourceType;
import com.manager.system.mapper.PayMapper;
import com.manager.system.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商城页签管理
 * @author sieGuang 2021/09/07
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

    @Override
    public List getPayList() {
        return payMapper.getPayList(ManagerConfig.getTid());
    }

    @Override
    public int editPay(Pay pay) {
        pay.setTid(ManagerConfig.getTid());
        return payMapper.editPay(pay);
    }

}
