package com.manager.system.service;

import java.util.List;

public interface SysTenantService {

    List selectTenants(String tid, String type);

    List selectAllTenant();

    List buildTree(List list);
}
