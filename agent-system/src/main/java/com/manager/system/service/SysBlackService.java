package com.manager.system.service;

import com.manager.common.core.domain.entity.SysBlack;
import com.manager.common.core.domain.entity.SysBlackInfo;

import java.util.List;

/**
 * @author marvin 2021/8/25
 */
public interface SysBlackService {
    int insertBlack(SysBlack sysBlack);

    List getSysBlacks(SysBlack sysBlack);

    void deleteSysBlack(Integer id);

    List getSysBlackInfos(SysBlackInfo sysBlackInfo);

    void readSysBlackInfos(int id);

    void sealSysBlackInfos(int id);
}
