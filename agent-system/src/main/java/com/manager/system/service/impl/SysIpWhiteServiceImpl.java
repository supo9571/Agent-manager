package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.SysIpWhite;
import com.manager.common.exception.CustomException;
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
    public void addIpWhite(long tid, Long userId, String ips,long createUserId,String userName) {
        if(userId==null || userId ==0){
            //根据userName 查询id
            userId = sysUserMapper.selectUserIdByUserName(userName);
            if(userId==null || userId ==0) throw new CustomException("用户名错误！");
        }
        List<SysIpWhite> list = new ArrayList<>();
        List<String> ipList = Arrays.asList(ips.split(","));
        Long finalUserId = userId;
        ipList.forEach(ip->{
            list.add(new SysIpWhite(tid, finalUserId,createUserId,ip));
        });
        sysIpWhiteMapper.insertIpWhite(list);
    }

    @Override
    public void delIpWhite(long id) {
        sysIpWhiteMapper.delIpWhite(id);
    }

    @Override
    public List selectIpWhiteList(String tid, String userId, String ip, String userName) {
        return sysIpWhiteMapper.selectIpWhiteList(tid,userId,ip,userName);
    }

    @Override
    public String selectIpByUserId(String userId) {
        StringBuffer userIp = new StringBuffer();
        List ips = sysIpWhiteMapper.selectIpByUserId(userId);
        ips.forEach(ip -> userIp.append(((Map)ip).get("ip")+","));
        return userIp.length()>1?userIp.substring(0,userIp.length()-1):"";
    }
}
