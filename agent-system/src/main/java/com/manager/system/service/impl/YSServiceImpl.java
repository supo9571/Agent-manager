package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.Ysinfo;
import com.manager.system.mapper.YSMapper;
import com.manager.system.service.YSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/10/23
 */
@Service
public class YSServiceImpl implements YSService {

    @Autowired
    private YSMapper ysMapper;
    @Override
    public Ysinfo selectByName(String username) {
        return ysMapper.selectByName(username);
    }

    @Override
    public List getOrder(String uid, String beginTime, String endTime, Integer ysid) {
        return ysMapper.getOrder(uid,beginTime,endTime,ysid);
    }

    @Override
    public List getReport(String beginTime, String endTime, Integer ysid) {
        return ysMapper.getReport(beginTime,endTime,ysid);
    }
}
