package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysTenantStatistics;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author jason
 * @date 2021-09-28
 */
@Mapper
public interface SysTenantStatisticsMapper {

    SysTenantStatistics getSysTenantStatistics(SysTenantStatistics record);

    int deleteByPrimaryKey(String tId);

    int insert(SysTenantStatistics record);

    int insertSelective(SysTenantStatistics record);

    SysTenantStatistics selectByPrimaryKey(String tId);

    int updateByPrimaryKeySelective(SysTenantStatistics record);

    int updateByPrimaryKey(SysTenantStatistics record);

}
