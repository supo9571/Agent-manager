package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.HorseRaceLamp;
import com.manager.system.mapper.HorseRaceLampMapper;
import com.manager.system.service.HorseRaceLampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 跑马灯配置
 * @author sieGuang 2021/09/21
 */
@Service
public class HorseRaceLampServiceImpl implements HorseRaceLampService {

    @Autowired
    private HorseRaceLampMapper horseRaceLampMapper;

    @Override
    public Integer addHorseRaceLamp(HorseRaceLamp horseRaceLamp) {
        return horseRaceLampMapper.addHorseRaceLamp(horseRaceLamp);
    }

    @Override
    public List listHorseRaceLamp(HorseRaceLamp horseRaceLamp) {
        return horseRaceLampMapper.listHorseRaceLamp(horseRaceLamp);
    }

    @Override
    public Integer editHorseRaceLamp(HorseRaceLamp horseRaceLamp) {
        return horseRaceLampMapper.editHorseRaceLamp(horseRaceLamp);
    }

    @Override
    public Integer delHorseRaceLamp(String id) {
        return horseRaceLampMapper.delHorseRaceLamp(id);
    }

}
