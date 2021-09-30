package com.manager.system.service.impl;

import com.manager.common.annotation.DataSource;
import com.manager.common.core.domain.entity.SysTenantStatistics;
import com.manager.common.enums.DataSourceType;
import com.manager.system.mapper.SysTenantStatisticsMapper;
import com.manager.system.service.SysTenantStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jason
 * @date 2021-09-28
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class SysTenantStatisticsServiceImpl implements SysTenantStatisticsService {

    @Resource
    private SysTenantStatisticsMapper mapper;

    @Override
    public SysTenantStatistics getSysTenantStatistics(SysTenantStatistics sysTenantStatistics) {
        return mapper.getSysTenantStatistics(sysTenantStatistics);
    }


}
