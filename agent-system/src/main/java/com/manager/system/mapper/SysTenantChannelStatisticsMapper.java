package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysTenantChannelStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jason
 * @date 2021-09-30
 */
@Mapper
public interface SysTenantChannelStatisticsMapper {

    List<SysTenantChannelStatistics> list(SysTenantChannelStatistics statistics);

    int deleteByPrimaryKey(String key);

    int insert(SysTenantChannelStatistics record);

    int insertSelective(SysTenantChannelStatistics record);

    SysTenantChannelStatistics selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(SysTenantChannelStatistics record);

    int updateByPrimaryKey(SysTenantChannelStatistics record);

}
