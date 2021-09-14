package com.manager.system.service.impl;


import com.manager.common.annotation.DataSource;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.Exchange;
import com.manager.common.enums.DataSourceType;
import com.manager.system.mapper.ExchangeMapper;
import com.manager.system.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 提现信息管理
 * @author sieGuang 2021/09/07
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Override
    public List<Map> getExchangeList() {
        return exchangeMapper.getExchangeList(ManagerConfig.getTid());
    }

    @Override
    public int editExchange(Exchange exchange) {
        return exchangeMapper.editExchange(exchange);
    }

    @Override
    public int editSettingsExchange(int settingsType,String value){
        return exchangeMapper.editSettingsExchange(settingsType,value,ManagerConfig.getTid());
    }

}