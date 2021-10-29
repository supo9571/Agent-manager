package com.manager.controller.system;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysBlack;
import com.manager.common.core.domain.entity.SysBlackInfo;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.SysBlackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/black")
@Api(tags = "黑名单管理")
public class SysBlackController extends BaseController {

    @Autowired
    private SysBlackService sysBlackService;

    /**
     * 添加黑名单策略
     */
    @PreAuthorize("@ss.hasPermi('system:black:add')")
    @ApiOperation(value = "添加黑名单策略")
    @Log(title = "添加黑名单策略", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(SysBlack sysBlack) {
        sysBlack.setCreateBy(SecurityUtils.getUsername());
        return toAjax(sysBlackService.insertBlack(sysBlack));
    }

    /**
     * 查询黑名单策略
     */
    @PreAuthorize("@ss.hasPermi('system:black:add')")
    @ApiOperation(value = "查询黑名单策略")
    @PostMapping("/list")
    public AjaxResult list(SysBlack sysBlack) {
        startPage();
        List list = sysBlackService.getSysBlacks(sysBlack);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 删除黑名单策略
     */
    @PreAuthorize("@ss.hasPermi('system:black:add')")
    @ApiOperation(value = "删除黑名单策略")
    @Log(title = "删除黑名单策略", businessType = BusinessType.DELETE)
    @GetMapping("/del")
    public AjaxResult del(Integer id) {
        sysBlackService.deleteSysBlack(id);
        return AjaxResult.success();
    }


    /**
     * 查询黑名单执行结果
     */
    @PreAuthorize("@ss.hasPermi('system:black:list')")
    @ApiOperation(value = "查询黑名单执行结果")
    @PostMapping("/info")
    public AjaxResult info(SysBlackInfo sysBlackInfo) {
        startPage();
        List list = sysBlackService.getSysBlackInfos(sysBlackInfo);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 黑名单执行结果 已读
     */
    @PreAuthorize("@ss.hasPermi('system:black:list')")
    @ApiOperation(value = "已读黑名单执行结果")
    @PostMapping("/read")
    public AjaxResult read(int id) {
        sysBlackService.readSysBlackInfos(id);
        return AjaxResult.success();
    }

    /**
     * 黑名单执行结果 封停
     */
    @PreAuthorize("@ss.hasPermi('system:black:list')")
    @ApiOperation(value = "封停黑名单执行结果")
    @PostMapping("/seal")
    public AjaxResult seal(int id) {
        sysBlackService.sealSysBlackInfos(id);
        return AjaxResult.success();
    }
}
