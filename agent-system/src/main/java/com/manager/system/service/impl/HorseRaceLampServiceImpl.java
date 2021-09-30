package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.HorseRaceLamp;
import com.manager.common.utils.DateUtils;
import com.manager.system.mapper.HorseRaceLampMapper;
import com.manager.system.service.HorseRaceLampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return horseRaceLampMapper.listHorseRaceLamp(horseRaceLamp);
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

}
