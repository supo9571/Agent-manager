package com.manager.system.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author marvin 2021/9/7
 */
public interface ApiService {
    JSONObject getMonthConfig();

    JSONObject getBookConfig(String data);
}
