package com.manager.system.service.impl;

import com.manager.common.annotation.DataSource;
import com.manager.common.core.domain.entity.SysTenantChannelStatistics;
import com.manager.common.enums.DataSourceType;
import com.manager.system.mapper.SysTenantChannelStatisticsMapper;
import com.manager.system.service.SysTenantChannelStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jason
 * @date 2021-09-30
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class SysTenantChannelStatisticsServiceImpl implements SysTenantChannelStatisticsService {

    @Resource
    private SysTenantChannelStatisticsMapper mapper;

    @Override
    public List<SysTenantChannelStatistics> list(SysTenantChannelStatistics statistics) {
        return mapper.list(statistics);
    }
}
