package com.manager.web.controller.news;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SystemNotice;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUploadUtils;
import com.manager.system.service.SystemNoticeService;
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
 * 系统公告配置
 * @author sieGuang 2021/09/18
 */
@RestController
@RequestMapping("/news/systemNotice")
@Api(tags = "系统公告配置")
@Slf4j
public class SystemNoticeController extends BaseController {

    @Autowired
    private SystemNoticeService systemNoticeService;

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:addSystemNotice')")
    @ApiOperation(value = "添加系统公告")
    @Log(title = "添加系统公告", businessType = BusinessType.INSERT)
    @PostMapping("/addSystemNotice")
    public AjaxResult addSystemNotice(@RequestBody SystemNotice systemNotice) {
        systemNotice.setCreateBy(SecurityUtils.getUsername());
        systemNotice.setTid(ManagerConfig.getTid());
        Integer i = systemNoticeService.addSystemNotice(systemNotice);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:listSystemNotice')")
    @ApiOperation(value = "查询系统公告列表")
    @PostMapping("/listSystemNotice")
    public AjaxResult listSystemNotice(@RequestBody SystemNotice systemNotice) {
        startPage();
        systemNotice.setTid(ManagerConfig.getTid());
        List list = systemNoticeService.listSystemNotice(systemNotice);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 编辑
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editSystemNotice')")
    @ApiOperation(value = "编辑系统公告")
    @Log(title = "编辑系统公告", businessType = BusinessType.UPDATE)
    @PostMapping("/editSystemNotice")
    public AjaxResult editSystemNotice(@RequestBody SystemNotice systemNotice) {
        systemNotice.setTid(ManagerConfig.getTid());
        systemNotice.setUpdateBy(SecurityUtils.getUsername());
        Integer i = systemNoticeService.editSystemNotice(systemNotice);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:delSystemNotice')")
    @ApiOperation(value = "删除系统公告")
    @Log(title = "删除系统公告", businessType = BusinessType.DELETE)
    @PostMapping("/delSystemNotice")
    public AjaxResult delSystemNotice(String id) {
        Integer i = systemNoticeService.delSystemNotice(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 上传图片
     */
    @Log(title = "上传图片", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadPicture")
    public AjaxResult uploadPicture(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String avatar = FileUploadUtils.upload(ManagerConfig.getAvatarPath(), file);

            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", avatar);
            return ajax;
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

}
