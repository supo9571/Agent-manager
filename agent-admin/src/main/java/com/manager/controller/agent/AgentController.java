package com.manager.controller.agent;

import com.manager.common.config.ManagerConfig;
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
     * 代理列表
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "代理列表")
    @GetMapping("/list")
    public AjaxResult list(String uid, String agentId, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getAgents(ManagerConfig.getTid(), uid, agentId, page, size, orderByColumn, isAsc);
    }

    /**
     * 每日收益
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "每日收益")
    @GetMapping("/day")
    public AjaxResult day(String uid, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getCommissionDays(uid, page, size, orderByColumn, isAsc);
    }

    /**
     * 领取记录
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "领取记录")
    @GetMapping("/cash")
    public AjaxResult cash(String uid, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getCashs(uid, page, size, orderByColumn, isAsc);
    }

    /**
     * 推广记录
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "推广记录")
    @GetMapping("/popularize")
    public AjaxResult popularize(String uid, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getPopularizes(uid, page, size, orderByColumn, isAsc);
    }
}
