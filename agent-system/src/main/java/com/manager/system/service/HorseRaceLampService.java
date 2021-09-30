package com.manager.system.service;

import com.manager.common.core.domain.entity.HorseRaceLamp;

import java.util.List;


/**
 * 跑马灯配置
 *
 * @author sieGuang 2021/09/21
 */
public interface HorseRaceLampService {

    /**
     * 添加
     */
    Integer addHorseRaceLamp(HorseRaceLamp horseRaceLamp);

    /**
     * 查询
     */
    List listHorseRaceLamp(HorseRaceLamp horseRaceLamp);

    /**
     * 编辑
     */
    Integer editHorseRaceLamp(HorseRaceLamp horseRaceLamp);

    /**
     * 通过id删除当前数据
     */
    Integer delHorseRaceLamp(String id);

}
