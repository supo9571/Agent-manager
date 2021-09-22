package com.manager.web.controller.news;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.PersonalMail;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.PersonalMailService;
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
 * 个人邮箱配置
 * @author sieGuang 2021/09/20
 */
@RestController
@RequestMapping("/news/personalMail")
@Api(tags = "个人邮件配置")
public class PersonalMailController extends BaseController {

    @Autowired
    private PersonalMailService personalMailService;

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:addPersonalMail')")
    @ApiOperation(value = "添加个人邮箱")
    @Log(title = "添加个人邮箱", businessType = BusinessType.INSERT)
    @PostMapping("/addPersonalMail")
    public AjaxResult addPersonalMail(@RequestBody PersonalMail personalMail) {
        personalMail.setCreateBy(SecurityUtils.getUsername());
        personalMail.setTid(ManagerConfig.getTid());
        Integer i = personalMailService.addPersonalMail(personalMail);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:listPersonalMail')")
    @ApiOperation(value = "查询个人邮箱列表")
    @PostMapping("/listPersonalMail")
    public AjaxResult listPersonalMail(@RequestBody PersonalMail personalMail) {
        startPage();
        personalMail.setTid(ManagerConfig.getTid());
        List list = personalMailService.listPersonalMail(personalMail);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 编辑
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editPersonalMail')")
    @ApiOperation(value = "编辑个人邮箱")
    @Log(title = "编辑个人邮箱", businessType = BusinessType.UPDATE)
    @PostMapping("/editPersonalMail")
    public AjaxResult editPersonalMail(@RequestBody PersonalMail personalMail) {
        personalMail.setTid(ManagerConfig.getTid());
        personalMail.setUpdateBy(SecurityUtils.getUsername());
        Integer i = personalMailService.editPersonalMail(personalMail);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:delPersonalMail')")
    @ApiOperation(value = "删除个人邮箱")
    @Log(title = "删除个人邮箱", businessType = BusinessType.DELETE)
    @PostMapping("/delPersonalMail")
    public AjaxResult delPersonalMail(String id) {
        Integer i = personalMailService.delPersonalMail(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
