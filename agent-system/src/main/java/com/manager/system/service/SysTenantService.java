package com.manager.system.service;

import com.manager.common.core.domain.entity.SysTenant;

import java.util.List;

public interface SysTenantService {

    List list(SysTenant sysTenant);

    List channelList(SysTenant sysTenant);

    List selectTenants(String tid, String type);

    List selectAllTenant();

    List buildTree(List list);

    int insertSelective(SysTenant record);

    int updateByPrimaryKeySelective(SysTenant record);
}
