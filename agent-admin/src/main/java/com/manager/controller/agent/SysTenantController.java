package com.manager.controller.agent;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysTenant;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.SysTenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author jason
 * @date 2021-09-24
 */
@RestController
@RequestMapping("/system/tenant")
@Api(tags = "总代渠道管理")
public class SysTenantController extends BaseController {

    @Resource
    private SysTenantService sysTenantService;

    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @ApiOperation(value = "总代列表")
    @GetMapping("/list")
    public AjaxResult list(SysTenant sysTenant) {
        startPage();
        sysTenant.setTType(1);
        List list = sysTenantService.list(sysTenant);
        return AjaxResult.success(getDataTable(list));
    }

    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @ApiOperation(value = "总代新增")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysTenant sysTenant) {
        int count = sysTenantService.getTenantCount(sysTenant.getTId());
        if (count > 0) {
            return AjaxResult.error("渠道号已存在！");
        }
        sysTenant.setCreateTime(new Date());
        sysTenant.setCreateBy(SecurityUtils.getUsername());
        sysTenant.setParentId(String.valueOf(ManagerConfig.getTid()));
        sysTenant.setTenant(ManagerConfig.getTid());
        sysTenant.setTType(1);
        return AjaxResult.success(sysTenantService.insertSelective(sysTenant));
    }

    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @ApiOperation(value = "总代修改")
    @PostMapping("/update")
    public AjaxResult update(@RequestBody SysTenant sysTenant) {
        sysTenant.setUpdateTime(new Date());
        sysTenant.setUpdateBy(SecurityUtils.getUsername());
        return AjaxResult.success(sysTenantService.updateByPrimaryKeySelective(sysTenant));
    }

    @PreAuthorize("@ss.hasPermi('system:tenant:channel:list')")
    @ApiOperation(value = "渠道列表")
    @GetMapping("/channel/list")
    public AjaxResult channelList(SysTenant sysTenant) {
        startPage();
        sysTenant.setTType(2);
        List list = sysTenantService.channelList(sysTenant);
        return AjaxResult.success(getDataTable(list));
    }

    @PreAuthorize("@ss.hasPermi('system:tenant:channel:list')")
    @ApiOperation(value = "渠道新增")
    @PostMapping("/channel/add")
    public AjaxResult channelAdd(@RequestBody SysTenant sysTenant) {
        if (sysTenant.getParentId() == null) {
            return AjaxResult.error("所属总代号不能为空");
        }
        int count = sysTenantService.getTenantCount(sysTenant.getTId());
        if (count > 0) {
            return AjaxResult.error("渠道号已存在！");
        }
        sysTenant.setCreateTime(new Date());
        sysTenant.setCreateBy(SecurityUtils.getUsername());
        sysTenant.setTenant(ManagerConfig.getTid());
        sysTenant.setTType(2);
        return AjaxResult.success(sysTenantService.insertSelective(sysTenant));
    }

    @PreAuthorize("@ss.hasPermi('system:tenant:channel:list')")
    @ApiOperation(value = "渠道修改")
    @PostMapping("/channel/update")
    public AjaxResult channelUpdate(@RequestBody SysTenant sysTenant) {
        sysTenant.setUpdateTime(new Date());
        sysTenant.setUpdateBy(SecurityUtils.getUsername());
        return AjaxResult.success(sysTenantService.updateByPrimaryKeySelective(sysTenant));
    }


}
