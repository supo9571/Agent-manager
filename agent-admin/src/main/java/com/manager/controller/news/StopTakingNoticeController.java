package com.manager.controller.news;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.StopTakingNotice;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.StopTakingNoticeService;
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
 * 停服公告
 *
 * @author sieGuang 2021/09/27
 */
@RestController
@RequestMapping("/news/stopTakingNotice")
@Api(tags = "停服公告")
public class StopTakingNoticeController extends BaseController {

    @Autowired
    private StopTakingNoticeService stopTakingNoticeService;

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:news:addStopTakingNotice')")
    @ApiOperation(value = "添加停服公告")
    @Log(title = "添加停服公告", businessType = BusinessType.INSERT)
    @PostMapping("/addStopTakingNotice")
    public AjaxResult addStopTakingNotice(@RequestBody StopTakingNotice stopTakingNotice) {
        stopTakingNotice.setCreateBy(SecurityUtils.getUsername());
        stopTakingNotice.setTid(ManagerConfig.getTid());
        Integer i = stopTakingNoticeService.addStopTakingNotice(stopTakingNotice);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:news:listStopTakingNotice')")
    @ApiOperation(value = "查询停服公告列表")
    @PostMapping("/listStopTakingNotice")
    public AjaxResult listStopTakingNotice(@RequestBody StopTakingNotice stopTakingNotice) {
        startPage(stopTakingNotice.getPage(), stopTakingNotice.getSize(),
                stopTakingNotice.getOrderByColumn(), stopTakingNotice.getIsAsc());
        stopTakingNotice.setTid(ManagerConfig.getTid());
        List list = stopTakingNoticeService.listStopTakingNotice(stopTakingNotice);
        return AjaxResult.success(getDataTable(list, stopTakingNotice.getPage(), stopTakingNotice.getSize()));
    }

    /**
     * 编辑
     */
    @PreAuthorize("@ss.hasPermi('system:news:editStopTakingNotice')")
    @ApiOperation(value = "编辑停服公告")
    @Log(title = "编辑停服公告", businessType = BusinessType.UPDATE)
    @PostMapping("/editStopTakingNotice")
    public AjaxResult editStopTakingNotice(@RequestBody StopTakingNotice stopTakingNotice) {
        stopTakingNotice.setTid(ManagerConfig.getTid());
        stopTakingNotice.setUpdateBy(SecurityUtils.getUsername());
        Integer i = stopTakingNoticeService.editStopTakingNotice(stopTakingNotice);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }


    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:news:delStopTakingNotice')")
    @ApiOperation(value = "删除停服公告")
    @Log(title = "删除停服公告", businessType = BusinessType.DELETE)
    @PostMapping("/delStopTakingNotice")
    public AjaxResult delStopTakingNotice(String id) {
        Integer i = stopTakingNoticeService.delStopTakingNotice(id);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

}
