package com.manager.web.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.core.controller.BaseController;
import com.manager.system.service.ApiService;
import com.manager.system.service.ConfigAgenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sieGuang 2021/9/9
 * 客服端 调用接口
 */
@RestController
@Api(tags = "代理推广界面")
@RequestMapping("/api/v1/agentv2")
public class ActingController extends BaseController {

    @Autowired
    private ConfigAgenService configAgenService;

    /**
     * 基础信息接口
     */
    @ApiOperation(value = "基础信息接口")
    @PostMapping("/info")
    public JSONObject info(){

        JSONObject result = new JSONObject();
        String uid = getHeader("uid"); // uid
        String promotionDomain = "";

        List<Map> list = configAgenService.getConfigAgentList();
        if(CollectionUtils.isNotEmpty(list)){
            promotionDomain = (String)list.get(0).get("promotionDomain");
        }
        result.put("reCommissionRuleList",list);
        result.put("spread_url",promotionDomain);
        result.put("uid",uid);
        return result;
    }

}
