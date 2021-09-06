package com.manager.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysTenantMapper {

    List selectTenants(@Param("tid") String tId,@Param("tType") String tType);

    List selectAllTenant();
}
