package com.manager.system.service;

import com.manager.common.core.domain.entity.ConfigAgent;

import java.util.List;

public interface ConfigAgenService {

    /**
     * 查询
     *
     * @return
     */
    List getConfigAgentList();

    /**
     * 新增
     *
     * @param configAgent
     * @return
     */
    Integer saveConfigAgent(ConfigAgent configAgent);

    /**
     * 修改
     *
     * @param configAgent
     * @return
     */
    Integer upConfigAgent(ConfigAgent configAgent);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Integer delConfigAgent(long id);

    /**
     * 根据主键查对象
     *
     * @param id
     * @return
     */
    ConfigAgent getConfigAgentById(long id);

    /**
     * 推广链接域名配置
     *
     * @param promotionDomain
     * @return
     */
    Integer upPromotionDomainToAll(String promotionDomain);
}
