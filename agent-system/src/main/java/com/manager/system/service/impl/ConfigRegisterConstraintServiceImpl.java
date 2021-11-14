package com.manager.system.service.impl;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.ConfigRegisterConstraint;
import com.manager.system.mapper.ConfigRegisterConstraintMapper;
import com.manager.system.service.ConfigRegisterConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jason
 * @date 2021-11-06
 */
@Service
public class ConfigRegisterConstraintServiceImpl implements ConfigRegisterConstraintService {

    @Resource
    private ConfigRegisterConstraintMapper mapper;


    @Override
    public ConfigRegisterConstraint getConfigRegisterConstraint(Integer tid) {
        return mapper.getConfigRegisterConstraint(tid);
    }

    @Override
    public int saveConfigRegisterConstraint(ConfigRegisterConstraint configRegisterConstraint) {
        ConfigRegisterConstraint constraint = getConfigRegisterConstraint(ManagerConfig.getTid());
        configRegisterConstraint.setTid(ManagerConfig.getTid());
        int result;
        if (constraint == null) {
            result = mapper.saveConfigRegisterConstraint(configRegisterConstraint);
        } else {
            result = mapper.updateConfigRegisterConstraint(configRegisterConstraint);
        }
        return result;
    }
}
