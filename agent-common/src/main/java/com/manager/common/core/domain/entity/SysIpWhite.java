package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 白名单 sys_ip_while
 *
 * @author marvin
 */
@Data
public class SysIpWhite extends BaseEntity {
    private long id;

    private long userId;

    private String ip;

    private String type;

}
