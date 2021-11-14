package com.manager.system.service;

import com.manager.common.core.domain.entity.ConfigRegisterConstraint;

/**
 * @author jason
 * @date 2021-11-06
 */
public interface ConfigRegisterConstraintService {

    ConfigRegisterConstraint getConfigRegisterConstraint(Integer tid);

    int saveConfigRegisterConstraint(ConfigRegisterConstraint configRegisterConstraint);

}
