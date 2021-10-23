package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Ysinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author marvin 2021/10/23
 */
@Mapper
public interface YSMapper {
    @Select("select id,password,username,ip_white_list ipWhiteList, " +
            "google_check googleCheck,google_key googleKey from config_ys where username = #{username} and status=1")
    Ysinfo selectByName(@Param("username") String username);

    List getOrder(@Param("uid") String uid,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("ysid") Integer ysid);

    List getReport(@Param("beginTime")String beginTime,@Param("endTime") String endTime,@Param("ysid") Integer ysid);
}
