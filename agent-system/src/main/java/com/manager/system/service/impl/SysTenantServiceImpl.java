package com.manager.system.service.impl;

import com.manager.common.annotation.DataSource;
import com.manager.common.core.domain.entity.SysDept;
import com.manager.common.core.domain.entity.SysTenant;
import com.manager.common.enums.DataSourceType;
import com.manager.common.utils.StringUtils;
import com.manager.system.mapper.SysTenantMapper;
import com.manager.system.service.SysTenantService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@DataSource(DataSourceType.SLAVE)
public class SysTenantServiceImpl implements SysTenantService {

    @Resource
    private SysTenantMapper sysTenantMapper;

    @Override
    public List list(SysTenant sysTenant) {
        List<Map> list = sysTenantMapper.list(sysTenant);
        if (!CollectionUtils.isEmpty(list)) {
            String pName = sysTenantMapper.getPlatformName();
            list.forEach(map -> {
                map.put("pName", pName);
                List<Map> userList = sysTenantMapper.selectUserList(String.valueOf(map.get("tid")));
                if (!CollectionUtils.isEmpty(list)) {
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
        return sysTenantMapper.selectAllTenant();
    }

    @Override
    public List buildTree(List list) {
        List returnList = new ArrayList();
        List<Long> tempList = new ArrayList();
        list.forEach(l -> tempList.add((Long) ((Map) l).get("tid")));

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
    private List getChildList(List<SysDept> list, Map t) {
        List tlist = new ArrayList<SysDept>();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map n = (Map) it.next();
            if (StringUtils.isNotNull(n.get("parentId")) && (Long) n.get("parentId") == (Long) t.get("tid")) {
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
