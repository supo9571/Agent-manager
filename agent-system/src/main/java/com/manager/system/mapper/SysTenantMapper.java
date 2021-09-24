package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysTenant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysTenantMapper {

    List selectTenants(@Param("tid") String tId,@Param("tType") String tType);

    List selectAllTenant();

    List list(SysTenant record);

    int deleteByPrimaryKey(String tId);

    int insert(SysTenant record);

    int insertSelective(SysTenant record);

    SysTenant selectByPrimaryKey(String tId);

    int updateByPrimaryKeySelective(SysTenant record);

    int updateByPrimaryKey(SysTenant record);
}
