package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.SysBlack;
import com.manager.system.mapper.SysBlackMapper;
import com.manager.system.service.SysBlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/8/25
 */
@Service
public class SysBlackServiceImpl implements SysBlackService {
    @Autowired
    private SysBlackMapper sysBlackMapper;
    @Override
    public int insertBlack(SysBlack sysBlack) {
        return sysBlackMapper.insertBlack(sysBlack);
    }

    @Override
    public List getSysBlacks(SysBlack sysBlack) {
        return sysBlackMapper.getSysBlacks(sysBlack);
    }

    @Override
    public void deleteSysBlack(Integer id) {
        sysBlackMapper.deleteSysBlack(id);
    }
}
