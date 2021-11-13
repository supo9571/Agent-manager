package com.manager.system.service;

import java.util.List;

public interface SysIpWhiteService {
    void addIpWhite(Long userId, String userName, String ips, String type);

    void delIpWhite(long id);

    List selectIpWhiteList(String userId, String ip, String userName);

    String selectIpByUserId(String userId);
}
