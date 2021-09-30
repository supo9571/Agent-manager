package com.manager.controller.news;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.HorseRaceLamp;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.HorseRaceLampService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 跑马灯配置
 *
 * @author sieGuang 2021/09/21
 */
@RestController
@RequestMapping("/news/horseRaceLamp")
@Api(tags = "跑马灯配置")
public class HorseRaceLampController extends BaseController {

    @Autowired
    private HorseRaceLampService horseRaceLampService;

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:news:addHorseRaceLamp')")
    @ApiOperation(value = "添加跑马灯配置")
    @Log(title = "添加跑马灯配置", businessType = BusinessType.INSERT)
    @PostMapping("/addHorseRaceLamp")
    public AjaxResult addHorseRaceLamp(@RequestBody HorseRaceLamp horseRaceLamp) {
        horseRaceLamp.setCreateBy(SecurityUtils.getUsername());
        horseRaceLamp.setTid(ManagerConfig.getTid());
        Integer i = horseRaceLampService.addHorseRaceLamp(horseRaceLamp);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:news:listHorseRaceLamp')")
    @ApiOperation(value = "查询跑马灯配置列表")
    @PostMapping("/listHorseRaceLamp")
    public AjaxResult listHorseRaceLamp(@RequestBody HorseRaceLamp horseRaceLamp) {
        startPage(horseRaceLamp.getPage(), horseRaceLamp.getSize(), horseRaceLamp.getOrderByColumn(), horseRaceLamp.getIsAsc());
        horseRaceLamp.setTid(ManagerConfig.getTid());
        List list = horseRaceLampService.listHorseRaceLamp(horseRaceLamp);
        return AjaxResult.success(getDataTable(list, horseRaceLamp.getPage(), horseRaceLamp.getSize()));
    }

    /**
     * 编辑
     */
    @PreAuthorize("@ss.hasPermi('system:news:editHorseRaceLamp')")
    @ApiOperation(value = "编辑跑马灯配置")
    @Log(title = "编辑跑马灯配置", businessType = BusinessType.UPDATE)
    @PostMapping("/editHorseRaceLamp")
    public AjaxResult editHorseRaceLamp(@RequestBody HorseRaceLamp horseRaceLamp) {
        horseRaceLamp.setTid(ManagerConfig.getTid());
        horseRaceLamp.setUpdateBy(SecurityUtils.getUsername());
        Integer i = horseRaceLampService.editHorseRaceLamp(horseRaceLamp);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:news:delHorseRaceLamp')")
    @ApiOperation(value = "删除跑马灯配置")
    @Log(title = "删除跑马灯配置", businessType = BusinessType.DELETE)
    @PostMapping("/delHorseRaceLamp")
    public AjaxResult delHorseRaceLamp(String id) {
        Integer i = horseRaceLampService.delHorseRaceLamp(id);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

}
