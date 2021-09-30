package com.manager.system.mapper;

import com.manager.common.core.domain.entity.HorseRaceLamp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 跑马灯配置
 *
 * @author sieGuang 2021/09/21
 */
@Mapper
public interface HorseRaceLampMapper {

    /**
     * 添加
     */
    Integer addHorseRaceLamp(HorseRaceLamp horseRaceLamp);

    /**
     * 查询
     */
    List<HorseRaceLamp> listHorseRaceLamp(HorseRaceLamp horseRaceLamp);

    /**
     * 编辑
     */
    Integer editHorseRaceLamp(HorseRaceLamp horseRaceLamp);

    /**
     * 通过id删除当前数据
     */
    @Delete("delete from sys_horse_race_lamp where id = #{id}")
    Integer delHorseRaceLamp(@Param("id") String id);


}
