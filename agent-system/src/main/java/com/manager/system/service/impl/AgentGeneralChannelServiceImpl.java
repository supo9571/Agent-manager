package com.manager.system.service.impl;

import com.manager.common.annotation.DataSource;
import com.manager.common.enums.DataSourceType;
import com.manager.system.service.AgentGeneralChannelService;
import org.springframework.stereotype.Service;

/**
 * @author jason
 * @date 2021-09-23
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class AgentGeneralChannelServiceImpl implements AgentGeneralChannelService {



}