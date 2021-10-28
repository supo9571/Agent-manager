package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.SysBlack;
import com.manager.common.core.domain.entity.SysBlackInfo;
import com.manager.common.core.domain.model.UserLock;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.http.HttpUtils;
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


    @Override
    public List getSysBlackInfos(SysBlackInfo sysBlackInfo) {
        return sysBlackMapper.getSysBlackInfos(sysBlackInfo);
    }

    @Override
    public void readSysBlackInfos(int id) {
        sysBlackMapper.readSysBlackInfos(id);
    }

    @Autowired
    private ManagerConfig managerConfig;

    @Override
    public void sealSysBlackInfos(int id) {
        sysBlackMapper.sealSysBlackInfos(id, SecurityUtils.getUsername());
        SysBlackInfo sysBlackInfo = sysBlackMapper.getSysBlackInfo(id);
        JSONObject param = new JSONObject();
        param.put("cmd", "forbidden");
        param.put("reason", "");
        param.put("uid", sysBlackInfo.getUid());
        HttpUtils.sendPost(managerConfig.getDomain() + managerConfig.getChangeCoins(),
                "data=" + param.toJSONString());
        UserLock userLock = new UserLock();
        userLock.setUid(sysBlackInfo.getUid()+"");
        userLock.setLockDay(0);
        userLock.setCreateBy("系统");
        userLock.setLockType(0);
        userLock.setRemark("黑名单策略");
        sysBlackMapper.saveUserLock(userLock);
        sysBlackMapper.saveUserLockLog(userLock);
    }
}
