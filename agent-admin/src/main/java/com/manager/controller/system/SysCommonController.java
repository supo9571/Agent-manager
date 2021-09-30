package com.manager.controller.system;

import com.manager.common.core.domain.AjaxResult;
import com.manager.system.service.SysTenantService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页
 *
 * @author marvin
 */
@RestController
@RequestMapping("/system/common")
@Api(tags = "系统公共接口")
public class SysCommonController {

    @Autowired
    SysTenantService sysTenantService;

    /**
     * 管理范围 级联列表
     */

    @ApiOperation(value = "管理范围级联列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", dataType = "String", value = "父级平台Id"),
            @ApiImplicitParam(name = "type", dataType = "String", required = true, value = "列表类型 0平台 1总代 2渠道")
    })
    @GetMapping("/tenant")
    public AjaxResult selectTenants(String tid, String type) {
        return AjaxResult.success(sysTenantService.selectTenants(tid, type));
    }

    @ApiOperation(value = "管理范围树结构")
    @GetMapping("/tenantTree")
    public AjaxResult tenantTree() {
        List list = sysTenantService.selectAllTenant();
        return AjaxResult.success(sysTenantService.buildTree(list));
    }

}
