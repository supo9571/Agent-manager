package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 黑名单 sys_ip_Black
 *
 * @author marvin
 */
@Data
public class SysIpBlack extends BaseEntity {
    private long id;

    private long deptId;

    private long userId;

    private String ip;

    private long createUserId;

    private String creatTime;

    public SysIpBlack() {
    }

    public SysIpBlack(long deptId, long userId, long createUserId, String ip) {
        this.deptId = deptId;
        this.userId = userId;
        this.ip = ip;
        this.createUserId = createUserId;
    }
}
