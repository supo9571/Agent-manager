package com.manager.system.service.impl;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.SysTenant;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.StringUtils;
import com.manager.system.mapper.SysTenantMapper;
import com.manager.system.service.SysTenantService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysTenantServiceImpl implements SysTenantService {

    @Resource
    private SysTenantMapper sysTenantMapper;

    @Override
    public List list(SysTenant sysTenant) {
        List<Map> list = sysTenantMapper.list(sysTenant);
        if (!CollectionUtils.isEmpty(list)) {
            String pName = sysTenantMapper.getPlatformName(ManagerConfig.getTid());
            list.forEach(map -> {
                map.put("pName", pName);
                List<Map> userList = sysTenantMapper.selectUserList(String.valueOf(map.get("tid")));
                if (!CollectionUtils.isEmpty(userList)) {
                    for (int i = 0; i < userList.size(); i++) {
                        Map userMap = userList.get(i);
                        String account = userMap.get("nickName") + "(" + userMap.get("userName") + ")";
                        map.put("account" + (i + 1), account);
                    }
                }
            });
        }
        return list;
    }

    /**
     * 获取所有平台list转map
     *
     * @return
     */
    private Map<String, String> getTenantMap() {
        List<SysTenant> list = sysTenantMapper.allList();
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        return list.stream().collect(Collectors.toMap(SysTenant::getTId, SysTenant::getTName));
    }

    @Override
    public List channelList(SysTenant sysTenant) {
        List<Map> list = sysTenantMapper.channelList(sysTenant);
        if (!CollectionUtils.isEmpty(list)) {
            Map<String, String> tenantMap = getTenantMap();
            list.forEach(map -> {
                if (tenantMap.get(map.get("parentId")) != null) {
                    map.put("pName", tenantMap.get(map.get("parentId")) + "(" + map.get("parentId") + ")");
                }
                List<Map> userList = sysTenantMapper.selectUserList(String.valueOf(map.get("tid")));
                if (!CollectionUtils.isEmpty(userList)) {
                    for (int i = 0; i < userList.size(); i++) {
                        Map userMap = userList.get(i);
                        String account = userMap.get("nickName") + "(" + userMap.get("userName") + ")";
                        map.put("account" + (i + 1), account);
                    }
                }
            });
        }
        return list;
    }

    @Override
    public List selectTenants(String tid, String tType) {
        return sysTenantMapper.selectTenants(tid, tType);
    }

    @Override
    public List selectAllTenant() {
        return sysTenantMapper.selectAllTenant(SecurityUtils.getTid());
    }

    @Override
    public List buildTree(List list) {
        List returnList = new ArrayList();
        List<String> tempList = new ArrayList();
        list.forEach(l -> tempList.add((String) ((Map) l).get("tid")));

        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Map map = (Map) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(map.get("parentId"))) {
                recursionFn(list, map);
                returnList.add(map);
            }
        }
        return returnList;
    }

    @Override
    public int insertSelective(SysTenant record) {
        return sysTenantMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysTenant record) {
        return sysTenantMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int getTenantCount(String tid) {
        return sysTenantMapper.getTenantCount(tid);
    }

    /**
     * 递归列表
     */
    private void recursionFn(List list, Map map) {
        // 得到子节点列表
        List<Map> childList = getChildList(list, map);
        map.put("child", childList);
        for (Map tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List getChildList(List list, Map t) {
        List tlist = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map n = (Map) it.next();
            if (StringUtils.isNotNull(n.get("parentId")) && n.get("parentId").equals(t.get("tid"))) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List list, Map t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }


}
