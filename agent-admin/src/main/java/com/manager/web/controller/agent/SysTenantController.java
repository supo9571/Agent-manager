package com.manager.web.controller.agent;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysTenant;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.SysTenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author jason
 * @date 2021-09-24
 */
@RestController
@RequestMapping("/system/tenant")
@Api(tags = "总代渠道管理")
public class SysTenantController {

    @Autowired
    private SysTenantService sysTenantService;

    @ApiOperation(value = "总代列表")
    @GetMapping("/list")
    public AjaxResult selectTenants(SysTenant sysTenant) {
        return AjaxResult.success(sysTenantService.list(sysTenant));
    }

    @ApiOperation(value = "总代新增")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysTenant sysTenant) {
        sysTenant.setCreateTime(new Date());
        sysTenant.setCreateBy(SecurityUtils.getUsername());
        sysTenant.setParentId(ManagerConfig.getTid());
        return AjaxResult.success(sysTenantService.insertSelective(sysTenant));
    }

    @ApiOperation(value = "总代修改")
    @PostMapping("/update")
    public AjaxResult update(@RequestBody SysTenant sysTenant) {
        sysTenant.setUpdateTime(new Date());
        sysTenant.setUpdateBy(SecurityUtils.getUsername());
        return AjaxResult.success(sysTenantService.updateByPrimaryKeySelective(sysTenant));
    }

}
