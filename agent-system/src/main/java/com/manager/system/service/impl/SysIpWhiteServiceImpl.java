package com.manager.system.service.impl;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.SysIpWhite;
import com.manager.common.exception.CustomException;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.mapper.SysIpWhiteMapper;
import com.manager.system.mapper.SysUserMapper;
import com.manager.system.service.SysIpWhiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SysIpWhiteServiceImpl implements SysIpWhiteService {

    @Autowired
    private SysIpWhiteMapper sysIpWhiteMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void addIpWhite(Long userId, String userName, String ips, String type) {
        if (userId == null || userId == 0) {
            //根据userName 查询id
            userId = sysUserMapper.selectUserIdByUserName(userName);
            if (userId == null || userId == 0) throw new CustomException("用户名不存！");
        }
        List<String> ipList = Arrays.asList(ips.split(","));
        Integer ipCount = sysUserMapper.selectUserIps(userId);
        if (ipCount+ipList.size()>=100) throw new CustomException("ip数超出限制！");

        List<SysIpWhite> list = new ArrayList<>();
        for (int i = 0; i < ipList.size(); i++) {
            SysIpWhite sysIpWhite = new SysIpWhite();
            sysIpWhite.setUserId(userId);
            sysIpWhite.setCreateBy(SecurityUtils.getUsername());
            sysIpWhite.setIp(ipList.get(i));
            sysIpWhite.setType(type);
            list.add(sysIpWhite);
        }
        sysIpWhiteMapper.insertIpWhite(list);
    }

    @Override
    public void delIpWhite(long id) {
        sysIpWhiteMapper.delIpWhite(id);
    }

    @Override
    public List selectIpWhiteList(String userId, String ip, String userName) {
        return sysIpWhiteMapper.selectIpWhiteList(userId, ip, userName,ManagerConfig.getTid());
    }

    @Override
    public String selectIpByUserId(String userId) {
        StringBuffer userIp = new StringBuffer();
        List ips = sysIpWhiteMapper.selectIpByUserId(userId,ManagerConfig.getTid());
        ips.forEach(ip -> userIp.append(((Map) ip).get("ip") + ","));
        return userIp.length() > 1 ? userIp.substring(0, userIp.length() - 1) : "";
    }
}
