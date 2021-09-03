package com.manager.system.service;

import com.manager.common.core.domain.entity.SysBlack;

import java.util.List;

/**
 * @author marvin 2021/8/25
 */
public interface SysBlackService {
    int insertBlack(SysBlack sysBlack);

    List getSysBlacks(SysBlack sysBlack);

    void deleteSysBlack(Integer id);
}
