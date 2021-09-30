package com.manager.system.service;

import java.util.List;

public interface SysIpWhiteService {
    void addIpWhite(long tid, Long userId, String ips, long createUserId, String userName);

    void delIpWhite(long id);

    List selectIpWhiteList(String tid, String userId, String ip, String userName);

    String selectIpByUserId(String userId);
}
