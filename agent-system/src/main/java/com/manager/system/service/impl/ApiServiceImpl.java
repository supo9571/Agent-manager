package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.system.mapper.ApiMapper;
import com.manager.system.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author marvin 2021/9/7
 */
@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiMapper apiMapper;
    @Override
    public JSONObject getMonthConfig() {
        JSONObject result = new JSONObject();
        Map map = apiMapper.getMonthConfig();
        result.put("code","200");
        result.put("result",map);
        return result;
    }

    @Override
    public JSONObject getBookConfig(String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        return null;
    }
}
