package com.manager.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.system.mapper.ApiMapper;
import com.manager.system.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        String phoneType = JSONObject.parseObject(data).getString("phone_type");
        if("ios".equals(phoneType)){
            phoneType = "1";
        }else if("Android".equals(phoneType)){
            phoneType = "2";
        }else {
            phoneType = "3";
        }
        Integer vip = JSONObject.parseObject(data).getInteger("vip_level");
        List<Map> list = apiMapper.selectConfigPay();
        JSONArray jsonArray = new JSONArray();
        JSONObject result = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            JSONObject payInfo = new JSONObject(list.get(i));
            Integer payType = payInfo.getInteger("pay_type");
            if(payType==1){//vip充值
                List<Map> payList = apiMapper.selectVipconfig(payInfo.getInteger("id"));
                payList.forEach(map -> map.put("btn", Arrays.asList(String.valueOf(map.get("btn")).split(","))));
                payInfo.put("pay_list",payList);
            }else if(payType==3){//银行卡充值
                List<Map> payList = apiMapper.selectBankconfig(payInfo.getInteger("id"),vip);
                payInfo.put("pay_list",getBankInfo(payList));
            }else{//线上充值
                List<Map> payList = apiMapper.selectOnlineconfig(payInfo.getInteger("id"),vip,phoneType);
                payList.forEach(map -> map.put("btn", Arrays.asList(String.valueOf(map.get("btn")).split(","))));
                payInfo.put("pay_list",payList);
            }
            payInfo.remove("pay_type");
            jsonArray.add(payInfo);
        }
        result.put("onRspPayList",jsonArray);
        return result;
    }

    private List<JSONObject> getBankInfo(List<Map> list){
        List<JSONObject> result = new ArrayList<>();
        list.forEach(m->{
            JSONObject jsonObject = new JSONObject(m);
            if(2==jsonObject.getInteger("jump_type")){
                jsonObject.remove("url");
                String bankValue = "["+jsonObject.getString("bank_value")+"]";
                JSONArray jsonArray = JSONArray.parseArray(bankValue);
                int i =new Random().nextInt(100)+1;
                for (int l = 0; l < jsonArray.size(); l++) {
                    JSONObject json = jsonArray.getJSONObject(l);
                    if(Integer.valueOf(json.getInteger("bank_rate"))>=i){
                        jsonObject.put("bank_name",json.get("bank_name"));
                        jsonObject.put("bank_card_num",json.get("bank_card_num"));
                        jsonObject.put("bank_user_name",json.get("bank_user_name"));
                        break;
                    }
                    i = i-Integer.valueOf(json.getInteger("bank_rate"));
                }
            }
            jsonObject.remove("bank_value");
            jsonObject.put("btn",Arrays.asList(jsonObject.getString("btn").split(",")));
            result.add(jsonObject);
        });
        return result;
    }
}
