package com.manager.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.HorseRaceLamp;
import com.manager.common.utils.DateUtils;
import com.manager.system.mapper.HorseRaceLampMapper;
import com.manager.system.service.HorseRaceLampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 跑马灯配置
 *
 * @author sieGuang 2021/09/21
 */
@Service
public class HorseRaceLampServiceImpl implements HorseRaceLampService {

    @Autowired
    private HorseRaceLampMapper horseRaceLampMapper;

    @Override
    public Integer addHorseRaceLamp(HorseRaceLamp horseRaceLamp) {
        // 当前时间 > 开始时间
        if (DateUtils.dateCompare(horseRaceLamp.getBeginTime())) {
            // 当前时间 < 结束数据  = 在线
            // 当前时间 > 结束数据  = 下线（else）
            if (!(DateUtils.dateCompare(horseRaceLamp.getEndTime()))) {
                horseRaceLamp.setState("2");
            } else {
                horseRaceLamp.setState("3");
            }
        } else {
            // 其它 = 待发送
            horseRaceLamp.setState("1");
        }
        return horseRaceLampMapper.addHorseRaceLamp(horseRaceLamp);
    }

    @Override
    public List listHorseRaceLamp(HorseRaceLamp horseRaceLamp) {
        List<HorseRaceLamp> list = horseRaceLampMapper.listHorseRaceLamp(horseRaceLamp);
        // 处理状态展示
        list.forEach(l ->{
            // 当前时间 > 开始时间
            if (DateUtils.dateCompare(l.getBeginTime())) {
                // 当前时间 < 结束数据  = 在线
                // 当前时间 > 结束数据  = 下线（else）
                if (!(DateUtils.dateCompare(l.getEndTime()))) {
                    l.setState("2");
                } else {
                    l.setState("3");
                }
            } else {
                // 其它 = 待发送
                l.setState("1");
            }
        });
        return list;
    }

    @Override
    public Integer editHorseRaceLamp(HorseRaceLamp horseRaceLamp) {
        // 当前时间 > 开始时间
        if (DateUtils.dateCompare(horseRaceLamp.getBeginTime())) {
            // 当前时间 < 结束数据  = 在线
            // 当前时间 > 结束数据  = 下线（else）
            if (!(DateUtils.dateCompare(horseRaceLamp.getEndTime()))) {
                horseRaceLamp.setState("2");
            } else {
                horseRaceLamp.setState("3");
            }
        } else {
            // 其它 = 待发送
            horseRaceLamp.setState("1");
        }
        return horseRaceLampMapper.editHorseRaceLamp(horseRaceLamp);
    }

    @Override
    public Integer delHorseRaceLamp(String id) {
        return horseRaceLampMapper.delHorseRaceLamp(id);
    }

    @Override
    public String getHorseRaceLamp() {
        List<Map> horseRaceLampList = horseRaceLampMapper.getHorseRaceLamp();
        JSONObject result = new JSONObject();
        JSONObject horseRaceLamp = new JSONObject();
        for (int i = 0; i < horseRaceLampList.size(); i++) {
            Map map = horseRaceLampList.get(i);
            String s_time = (String) map.get("s_time");
            String de_time = (String) map.get("de_time");
            String[] s = s_time.split(":");
            String[] d = de_time.split(":");
            int sTime = Integer.valueOf(s[0])*60*60+Integer.valueOf(s[1])*60+Integer.valueOf(s[2]);
            int deTime = Integer.valueOf(d[0])*60*60+Integer.valueOf(d[1])*60+Integer.valueOf(d[2]);
            map.put("sTime",sTime);
            map.put("de_time",deTime);
            horseRaceLamp.put(String.valueOf(map.get("id")), new JSONObject(map));
        }
        result.put("system_notice.json", horseRaceLamp.toJSONString());
        return result.toJSONString();
    }

}
