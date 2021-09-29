package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author marvin 2021/9/29
 */
@FeignClient(value = "data-manager")
public interface AgentService {

    @PostMapping(value = "/data/agent/list")
    AjaxResult getAgents(@RequestParam("tid")Integer tid,@RequestParam("uid")String uid,@RequestParam("page")Integer page,
                         @RequestParam("size")Integer size,@RequestParam("orderByColumn")String orderByColumn,@RequestParam("isAsc")String isAsc);
}
