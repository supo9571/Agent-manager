package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author jason
 * @date 2021-09-23
 */
@Data
public class AgentGeneralChannel extends BaseEntity {
    private Long id;

    private String agentCode;

    private String channelName;

    private String channelCode;

    private String remark;

    private String unionStatistics;

    private String channelUrl1;

    private String channelUrl2;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

}
