package com.manager.system.mapper;

import com.manager.common.core.domain.entity.ConfigAgent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * andy 2021/9/7
 */
@Mapper
public interface ConfigAgentMapper {
    /**
     * 查询
     */
    List getConfigAgentList(@Param("tid") Integer tid);

    /**
     * 新增
     * @param configAgent
     * @return
     */
    Integer saveConfigAgent(ConfigAgent configAgent);

    /**
     * 修改
     * @param configAgent
     * @return
     */
    Integer upConfigAgent(ConfigAgent configAgent);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delConfigAgent(long id);

    /**
     * 根据主键查对象
     * @param id
     * @return
     */
    ConfigAgent getConfigAgentById(long id);

    /**
     * 推广链接域名配置
     * @param promotionDomain
     * @return
     */
    Integer upPromotionDomainToAll(@Param("promotionDomain") String promotionDomain,@Param("tid") Integer tid);
}
