package com.manager.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manager.common.core.domain.entity.Activity;
import com.manager.common.utils.StringUtils;
import com.manager.system.mapper.ActivityMapper;
import com.manager.system.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/7
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public Integer saveActivity(Activity activity) {
        return activityMapper.saveActivity(activity);
    }

    /**
     * 判断 活动时间 是否重叠
     * @param activity
     * @return
     */
    @Override
    public Integer checkActivityTime(Activity activity) {
        return activityMapper.checkActivityTime(activity);
    }

    @Override
    public List findActivity(Activity activity) {
        return activityMapper.findActivity(activity);
    }

    @Override
    public Integer updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public String getActivityConfig() {
        List<Map> list = activityMapper.getActivityConfig();
        JSONObject result = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        list.forEach(map -> {
            if(jsonObject.getString(String.valueOf(map.get("ac_type")))==null && !"113114".equals(String.valueOf(map.get("ac_type")))){
                JSONObject acInfo = JSONObject.parseObject(JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteClassName));
                acInfo.put("ac_name",getNameByType(acInfo.getInteger("ac_type")));
                acInfo.put("ac_content",JSONObject.parseObject(String.valueOf(map.get("ac_content"))));
                jsonObject.put(String.valueOf(acInfo.get("ac_type")),acInfo);
            } else if("113114".equals(String.valueOf(map.get("ac_type")))){
                setRechargeGive(map,jsonObject);
            }
        });

        jsonObject.put("116",getMonthConfig());
        String resultStr = StringUtils.jsonToLua(jsonObject);
        result.put("activity_new.lua","return {"+resultStr+"}");
        return result.toJSONString();
    }

    /**
     * 根据 活动 Type 转 活动名称
     */
    private String getNameByType(Integer type){
        switch (type){
            case 113114:
                return "充值红包";
            case 113:
                return "每日充值红包";
            case 114:
                return "连续充值红包";
            case 109:
                return "每日首充";
            case 3:
                return "首充返利";
            case 122:
                return "流水返利";
            case 115:
                return "全民推广";
            case 112:
                return "摇钱树";
            case 111:
                return "救济金";
           case 116:
                return "月卡活动";
            default:
                return "未知活动";
        }
    }

    /**
     * 获取月卡 活动
     * @return
     */
    private JSONObject getMonthConfig(){
        //月卡活动信息
        Map map = activityMapper.getMonthConfig();
        JSONObject month = new JSONObject();
        month.put("ac_name",getNameByType(116));
        month.put("ac_begin_time","2021-01-01 00:00:00");//房贷开始时间
        month.put("ac_end_time","2050-12-31 00:00:00");//房贷结束时间
        month.put("ac_type",116);
        month.put("sort_index",116);
        month.put("open_state",true);
        JSONArray monthArr = new JSONArray();
        JSONObject monthObj = new JSONObject();//银卡
        monthObj.put("card_id","1");
        monthObj.put("card_name","至尊银卡");
        Integer yprice = (int)map.get("yinRecharge")*10000;
        Integer ypay = (int)map.get("yinRechargeGive")*10000;
        Double yd = Double.valueOf(String.valueOf(map.get("yinRechargeGiveDay")))*10000;
        Integer ycoin = yd.intValue();
        Integer ytotal = (ypay+ycoin*30)/yprice*100;
        monthObj.put("price",yprice);//充值金额
        monthObj.put("pay_reward_coin",ypay);//立即获得
        monthObj.put("reward_coin",ycoin);//每日赠送
        monthObj.put("total_value",ytotal);//收益率

        JSONObject monthObj2 = new JSONObject();//金卡
        monthObj2.put("card_id","2");
        monthObj2.put("card_name","至尊金卡");
        Integer jprice = (int)map.get("jinRecharge")*10000;
        Integer jpay = (int)map.get("jinRechargeGive")*10000;
        Double jd = Double.valueOf(String.valueOf(map.get("jinRechargeGiveDay")))*10000;
        Integer jcoin = jd.intValue();
        Integer jtotal = (jpay+jcoin*30)/jprice*100;
        monthObj2.put("price",jprice);//充值金额
        monthObj2.put("pay_reward_coin",jpay);//立即获得
        monthObj2.put("reward_coin",jcoin);//每日赠送
        monthObj2.put("total_value",jtotal);//收益率

        monthArr.add(monthObj);
        monthArr.add(monthObj2);
        month.put("ac_content",monthArr);
        return month;
    }

    /**
     * 获取 充值红包 配置
     */
    private void setRechargeGive(Map map ,JSONObject jsonObject){
        JSONObject acInfo = JSONObject.parseObject(String.valueOf(map.get("ac_content")));
        JSONObject level = new JSONObject();// 连续充值红包
        level.put("ac_name",getNameByType(114));
        level.put("ac_begin_time",String.valueOf(map.get("ac_begin_time")));
        level.put("ac_end_time",String.valueOf(map.get("ac_end_time")));
        level.put("ac_type",114);
        level.put("sort_index",map.get("sort_index"));
        level.put("open_state",map.get("open_state"));

        JSONObject levelContent = new JSONObject();
        levelContent.put("level",acInfo.get("level"));
        level.put("ac_content",levelContent);
        jsonObject.put("114",level);

        JSONObject random = new JSONObject();// 每日红包奖励
        random.put("ac_name",getNameByType(113));
        random.put("ac_begin_time",String.valueOf(map.get("ac_begin_time")));
        random.put("ac_end_time",String.valueOf(map.get("ac_end_time")));
        random.put("ac_type",113);
        random.put("sort_index",map.get("sort_index"));
        random.put("open_state",map.get("open_state"));

        JSONObject randomContent = new JSONObject();
        JSONObject randomArea = new JSONObject();
        JSONArray jsonArray = acInfo.getJSONArray("random_area");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject randomInfo = new JSONObject();
            if(i==0){
                randomInfo.put("random_begin",1);
                randomInfo.put("random_end",jsonArray.getJSONObject(i).getInteger("random"));
            }else {
                Integer beginRandom = randomArea.getJSONObject(i+"").getInteger("random_end");
                randomInfo.put("random_begin",beginRandom+1);
                randomInfo.put("random_end",jsonArray.getJSONObject(i).getInteger("random")+beginRandom);
            }
            randomInfo.put("coin",jsonArray.getJSONObject(i).get("coin"));
            randomArea.put(i+1+"",randomInfo);
        }
        randomContent.put("random_area",randomArea);
        random.put("ac_content",randomContent);
        jsonObject.put("113",random);
    }
}
