package com.manager.controller.system;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysUser;
import com.manager.common.core.domain.model.LoginUser;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.ServletUtils;
import com.manager.framework.web.service.TokenService;
import com.manager.system.service.SysIpWhiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/ip")
@Api(tags = "IP管理")
public class SysIpController extends BaseController {

    @Autowired
    private SysIpWhiteService sysIpWhiteService;

    /**
     * 添加白名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:add')")
    @ApiOperation(value = "添加白名单")
    @Log(title = "添加白名单", businessType = BusinessType.INSERT)
    @GetMapping("/white/add")
    public AjaxResult add(Long userId,String userName, String ips,String type) {
        sysIpWhiteService.addIpWhite(userId, userName, ips,type);
        return success();
    }

    /**
     * 删除白名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:delete')")
    @ApiOperation(value = "删除白名单")
    @Log(title = "删除白名单", businessType = BusinessType.DELETE)
    @GetMapping("/white/delete")
    public AjaxResult delete(long id) {
        sysIpWhiteService.delIpWhite(id);
        return success();
    }

    /**
     * 查询白名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:list')")
    @ApiOperation(value = "查询白名单")
    @GetMapping("/white/list")
    public AjaxResult list(String userId, String ip, String userName) {
        startPage();
        List list = sysIpWhiteService.selectIpWhiteList(userId, ip, userName);
        return AjaxResult.success("查询白名单成功", getDataTable(list));
    }

}
