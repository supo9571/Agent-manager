package com.manager.controller.game;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.ConfigAgent;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.ConfigAgenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 全民代理配置
 * @author andy
 * @date 21:47 2021/9/7
 **/
@Api(tags = "全民代理配置")
@RestController
@RequestMapping(value = "/config/agent")
public class ConfigAgentController extends BaseController {

    @Autowired
    private ConfigAgenService configAgenService;

    /**
     * 全民代理配置列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:agent:configAgenList')")
    @ApiOperation(value = "全民代理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Inetger",required =true),
            @ApiImplicitParam(name = "size", value = "每页条数", dataType = "Inetger",required =true)
    })
    @PostMapping("/configAgenList")
    public AjaxResult getConfigAgenList(Integer page,Integer size){
        startPage();
        List list=configAgenService.getConfigAgentList();
        return AjaxResult.success("查询成功",getDataTable(list));
    }

    /**
     * 新增
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:agent:saveConfigAgent')")
    @ApiOperation(value = "全民代理新增")
    @Log(title = "新增全民代理",businessType = BusinessType.INSERT)
    @PostMapping("/saveConfigAgent")
    public AjaxResult saveConfigAgent(@RequestBody ConfigAgent configAgent){
        configAgent.setTid(ManagerConfig.getTid());
        int i=configAgenService.saveConfigAgent(configAgent);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 修改
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:agent:upConfigAgent')")
    @ApiOperation(value = "全民代理修改")
    @Log(title = "修改全民代理",businessType = BusinessType.UPDATE)
    @PostMapping("/upConfigAgent")
    public AjaxResult upConfigAgent(@RequestBody ConfigAgent configAgent){
        int i=configAgenService.upConfigAgent(configAgent);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 删除
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:agent:delConfigAgent')")
    @ApiOperation(value = "全民代理删除")
    @Log(title = "删除全民代理",businessType = BusinessType.DELETE)
    @PostMapping("/delConfigAgent")
    public AjaxResult delConfigAgent(String id){
        int i=configAgenService.delConfigAgent(Integer.valueOf(id));
        return i>0?AjaxResult.success():AjaxResult.error();
    }


    /**
     * 根据ID查对象
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:agent:getConfigAgentById')")
    @ApiOperation(value = "查出某个全民代理")
    @PostMapping("/getConfigAgentById")
    public AjaxResult getConfigAgentById(String id){
        ConfigAgent ca = configAgenService.getConfigAgentById(Integer.valueOf(id));
        return AjaxResult.success(ca);
    }

    /**
     * 推广链接域名配置
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:agent:upPromotionDomainToAll')")
    @ApiOperation(value = "推广链接域名配置")
    @PostMapping("/upPromotionDomainToAll")
    public AjaxResult upPromotionDomainToAll(String promotionDomain){
        int i=configAgenService.upPromotionDomainToAll(promotionDomain);
        return i>0?AjaxResult.success():AjaxResult.error();
    }
}
