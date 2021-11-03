package com.manager.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/11/3
 */
@Mapper
public interface PlayerMapper {

    @Select("select uid, `day`,sub_num subNum, other_num otherNum," +
            "(select sum(team_num) from agent_popularize_day where d.day>=`day` and uid=d.uid ) sumTeamNum," +
            " (select sum(sub_num) from agent_popularize_day where d.day>=`day` and uid=d.uid ) sumSubNum," +
            "(select sum(other_num) from agent_popularize_day where d.day>=`day` and uid=d.uid ) sumOtherNum " +
            "from agent_popularize_day d where d.uid = #{uid}")
    List<Map> getPopularizes(@Param("uid") String uid);
}
