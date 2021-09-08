package com.manager.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manager.common.core.domain.entity.Activity;
import com.manager.system.mapper.ActivityMapper;
import com.manager.system.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/7
 */
@Service
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
        });
        String reslitStr = jsonObject.toJSONString();
        reslitStr = reslitStr.replaceAll(":"," = ");
        reslitStr = reslitStr.replaceAll("\" =","] =");
        reslitStr = reslitStr.replaceAll("\"","[");
        result.put("activity_new.lua","return "+reslitStr);
        return result.toJSONString();
    }

    /**
     * 根据type 转 名称
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
            default:
                return "";
        }
    }
}
