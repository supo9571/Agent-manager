package com.manager.system.service.impl;

import com.manager.common.annotation.DataSource;
import com.manager.common.core.domain.entity.ConfigLanding;
import com.manager.common.enums.DataSourceType;
import com.manager.system.mapper.ConfigLandingMapper;
import com.manager.system.mapper.ConsumerMapper;
import com.manager.system.service.ConfigLandingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jason
 * @date 2021-09-20
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class ConfigLandingServiceImpl implements ConfigLandingService {

    @Resource
    private ConfigLandingMapper configLandingMapper;

    @Override
    public Map getConfigLanding(Integer tid) {
        return configLandingMapper.getConfigLandingInfo(tid);
    }

    @Override
    public ConfigLanding getId(Integer id) {
        return configLandingMapper.getById(id);
    }

    @Override
    public int editConfigLanding(ConfigLanding configLanding) {
        return configLandingMapper.editConfigLanding(configLanding);
    }

    @Override
    public int addConfigLanding(ConfigLanding configLanding) {
        return configLandingMapper.addConfigLanding(configLanding);
    }

}
