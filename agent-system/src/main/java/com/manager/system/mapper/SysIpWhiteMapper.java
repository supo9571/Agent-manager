package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysIpWhite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysIpWhiteMapper {

    void insertIpWhite(List<SysIpWhite> list);

    @Delete("delete from sys_ip_white where id = #{id}")
    void delIpWhite(@Param("id") long id);

    List selectIpWhiteList(@Param("userId") String userId, @Param("ip") String ip, @Param("userName") String userName,@Param("tenant")Integer tenant);

    List selectIpByUserId(@Param("userId") Object userId,@Param("tenant")Integer tenant);
}
