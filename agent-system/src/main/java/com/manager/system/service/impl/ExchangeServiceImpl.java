package com.manager.system.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Exchange;
import com.manager.common.utils.http.HttpUtils;
import com.manager.system.mapper.ExchangeMapper;
import com.manager.system.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 提现信息管理
 *
 * @author sieGuang 2021/09/07
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Autowired
    private ManagerConfig managerConfig;
    @Override
    public List<Map> getExchangeList() {
        return exchangeMapper.getExchangeList(ManagerConfig.getTid());
    }

    @Override
    public int editExchange(Exchange exchange) {
        return exchangeMapper.editExchange(exchange);
    }

    @Override
    public int editSettingsExchange(int settingsType, String value) {
        int i = exchangeMapper.editSettingsExchange(settingsType, value, ManagerConfig.getTid());
        //发送配置
        JSONObject param = new JSONObject();
        List exchangeConfig = exchangeMapper.getExchangeConfig();
        param.put("exchange.json", new JSONArray(exchangeConfig).toJSONString());
        HttpUtils.sendPost(managerConfig.getGameDomain(), "data=" + param.toJSONString());
        return i;
    }

}
