package com.manager.system.mapper;

import com.manager.common.core.domain.entity.AgentGeneralChannel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jason
 * @date 2021-09-23
 */
@Mapper
public interface AgentGeneralChannelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgentGeneralChannel record);

    int insertSelective(AgentGeneralChannel record);

    AgentGeneralChannel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentGeneralChannel record);

    int updateByPrimaryKey(AgentGeneralChannel record);
}
