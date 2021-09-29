package com.manager.web.controller.agent;

import com.manager.common.core.domain.AjaxResult;
import com.manager.openFegin.AgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/9/29
 */
@RestController
@Api(tags = "代理查询")
@RequestMapping("/data/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;
    /**
     * 获取牌局记录 列表
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "查询牌局记录列表")
    @GetMapping("/list")
    public AjaxResult list(Integer tid,String uid,Integer page,Integer size,String orderByColumn,String isAsc) {
        return agentService.getAgents(tid, uid, page, size, orderByColumn, isAsc);
    }
}
