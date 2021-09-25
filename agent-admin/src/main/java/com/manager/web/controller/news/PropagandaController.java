package com.manager.web.controller.news;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Propaganda;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUploadUtils;
import com.manager.system.service.PropagandaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 宣传活动配置
 * @author sieGuang 2021/09/25
 */
@RestController
@RequestMapping("/news/systemNotice")
@Api(tags = "系统公告配置")
@Slf4j
public class PropagandaController extends BaseController {

    @Autowired
    private PropagandaService propagandaService;

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:news:addPropaganda')")
    @ApiOperation(value = "添加宣传活动")
    @Log(title = "添加宣传活动告", businessType = BusinessType.INSERT)
    @PostMapping("/addPropaganda")
    public AjaxResult addPropaganda(@RequestBody Propaganda propaganda) {
        propaganda.setCreateBy(SecurityUtils.getUsername());
        propaganda.setTid(ManagerConfig.getTid());
        Integer i = propagandaService.addPropaganda(propaganda);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:news:listPropaganda')")
    @ApiOperation(value = "查询宣传活动列表")
    @PostMapping("/listPropaganda")
    public AjaxResult listPropaganda(@RequestBody Propaganda propaganda) {
        startPage(propaganda.getPage(),propaganda.getSize(),propaganda.getOrderByColumn(),propaganda.getIsAsc());
        propaganda.setTid(ManagerConfig.getTid());
        List list = propagandaService.listPropaganda(propaganda);
        return AjaxResult.success(getDataTable(list,propaganda.getPage(),propaganda.getSize()));
    }

    /**
     * 编辑
     */
    @PreAuthorize("@ss.hasPermi('system:news:editPropaganda')")
    @ApiOperation(value = "编辑宣传活动")
    @Log(title = "编辑宣传活动", businessType = BusinessType.UPDATE)
    @PostMapping("/editPropaganda")
    public AjaxResult editPropaganda(@RequestBody Propaganda propaganda) {
        propaganda.setTid(ManagerConfig.getTid());
        propaganda.setUpdateBy(SecurityUtils.getUsername());
        Integer i = propagandaService.editPropaganda(propaganda);
        return i>0?AjaxResult.success():AjaxResult.error();
    }


    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:news:delPropaganda')")
    @ApiOperation(value = "删除宣传活动")
    @Log(title = "删除宣传活动", businessType = BusinessType.DELETE)
    @PostMapping("/delPropaganda")
    public AjaxResult delPropaganda(String id) {
        Integer i = propagandaService.delPropaganda(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
