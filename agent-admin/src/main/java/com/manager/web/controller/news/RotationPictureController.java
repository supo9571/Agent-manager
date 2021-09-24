package com.manager.web.controller.news;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.RotationPicture;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.RotationPictureService;
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
 * 轮播图配置
 * @author sieGuang 2021/09/23
 */
@RestController
@RequestMapping("/news/rotationPicture")
@Api(tags = "轮播图配置")
public class RotationPictureController extends BaseController {

    @Autowired
    private RotationPictureService rotationPictureService;

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:news:addRotationPicture')")
    @ApiOperation(value = "添加轮播图")
    @Log(title = "添加轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/addRotationPicture")
    public AjaxResult addRotationPicture(@RequestBody RotationPicture rotationPicture) {
        rotationPicture.setCreateBy(SecurityUtils.getUsername());
        rotationPicture.setTid(ManagerConfig.getTid());
        Integer i = rotationPictureService.addRotationPicture(rotationPicture);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:news:listRotationPicture')")
    @ApiOperation(value = "查询轮播图列表")
    @PostMapping("/listRotationPicture")
    public AjaxResult listRotationPicture(@RequestBody RotationPicture rotationPicture) {
        startPage(rotationPicture.getPage(),rotationPicture.getSize(),
                rotationPicture.getOrderByColumn(),rotationPicture.getIsAsc());
        rotationPicture.setTid(ManagerConfig.getTid());
        List list = rotationPictureService.listRotationPicture(rotationPicture);
        return AjaxResult.success(getDataTable(list,rotationPicture.getPage(),rotationPicture.getSize()));
    }

    /**
     * 编辑
     */
    @PreAuthorize("@ss.hasPermi('system:news:editSystemNotice')")
    @ApiOperation(value = "编辑轮播图")
    @Log(title = "编辑轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/editSystemNotice")
    public AjaxResult editRotationPicture(@RequestBody RotationPicture rotationPicture) {
        rotationPicture.setTid(ManagerConfig.getTid());
        rotationPicture.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rotationPictureService.editRotationPicture(rotationPicture);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:news:delRotationPicture')")
    @ApiOperation(value = "删除轮播图")
    @Log(title = "删除轮播图", businessType = BusinessType.DELETE)
    @PostMapping("/delRotationPicture")
    public AjaxResult delRotationPicture(String id) {
        Integer i = rotationPictureService.delRotationPicture(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
