package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysTenant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysTenantMapper {

    @Select("select t_name from sys_tenant where parent_id = 0")
    String getPlatformName();

    @Select("SELECT t_id as tid,t_name as tName from sys_tenant ")
    List<SysTenant> allList();

    List selectTenants(@Param("tid") String tId,@Param("tType") String tType);

    List selectAllTenant();

    List list(SysTenant record);

    List channelList(SysTenant record);

    List selectUserList(@Param("tid") String tId);

    int deleteByPrimaryKey(String tId);

    int insert(SysTenant record);

    int insertSelective(SysTenant record);

    SysTenant selectByPrimaryKey(String tId);

    int updateByPrimaryKeySelective(SysTenant record);

    int updateByPrimaryKey(SysTenant record);
}
