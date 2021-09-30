package com.manager.system.service.impl;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.ConfigAgent;
import com.manager.system.mapper.ConfigAgentMapper;
import com.manager.system.service.ConfigAgenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LV
 * @date 22:13 2021/9/7
 * @return
 **/
@Service
public class ConfigAgentServiceImpl implements ConfigAgenService {

    @Autowired
    private ConfigAgentMapper configAgentMapper;


    @Override
    public List getConfigAgentList() {
        return configAgentMapper.getConfigAgentList(ManagerConfig.getTid());
    }

    @Override
    public Integer saveConfigAgent(ConfigAgent configAgent) {
        return configAgentMapper.saveConfigAgent(configAgent);
    }

    @Override
    public Integer upConfigAgent(ConfigAgent configAgent) {
        return configAgentMapper.upConfigAgent(configAgent);
    }

    @Override
    public Integer delConfigAgent(long id) {
        return configAgentMapper.delConfigAgent(id);
    }

    @Override
    public ConfigAgent getConfigAgentById(long id) {
        return configAgentMapper.getConfigAgentById(id);
    }

    @Override
    public Integer upPromotionDomainToAll(String promotionDomain) {
        return configAgentMapper.upPromotionDomainToAll(promotionDomain,ManagerConfig.getTid());
    }


}
