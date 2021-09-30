package com.manager.controller.agent;

import com.manager.common.core.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason
 * @date 2021-09-23
 */
@Api(tags = "渠道列表")
@RestController
@RequestMapping(value = "/agent/general/channel")
public class AgentGeneralChannelController extends BaseController {

//    //    @PreAuthorize("@ss.hasPermi('system:agent:generalList')")
//    @ApiOperation(value = "总代渠道管理列表")
//    @PostMapping("/list")
//    public AjaxResult list(AgentGeneral agentGeneral) {
//        startPage();
//        List list = agentGeneralService.list(agentGeneral);
//        return AjaxResult.success("查询成功", getDataTable(list));
//    }
//
//
//    @ApiOperation(value = "总代新增")
//    @PostMapping("/add")
//    public AjaxResult add(AgentGeneral agentGeneral) {
//        agentGeneral.setTid(ManagerConfig.getTid());
//        agentGeneral.setCreateTime(new Date());
//        agentGeneral.setCreateBy(SecurityUtils.getUsername());
//        return AjaxResult.success(agentGeneralService.insertSelective(agentGeneral));
//    }
//
//    @ApiOperation(value = "总代修改")
//    @PostMapping("/update")
//    public AjaxResult update(AgentGeneral agentGeneral) {
//        agentGeneral.setUpdateTime(new Date());
//        agentGeneral.setUpdateBy(SecurityUtils.getUsername());
//        return AjaxResult.success(agentGeneralService.updateByPrimaryKeySelective(agentGeneral));
//    }

}
