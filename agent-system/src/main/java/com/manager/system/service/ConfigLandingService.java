package com.manager.system.service;

import com.manager.common.core.domain.entity.ConfigLanding;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 * @author jason
 * @date 2021-09-20
 */
public interface ConfigLandingService {

    Map getConfigLanding(@Param("tid") Integer tid);

    ConfigLanding getId(@Param("id") Integer id);

    int editConfigLanding(ConfigLanding configLanding);

    int addConfigLanding(ConfigLanding configLanding);
}
