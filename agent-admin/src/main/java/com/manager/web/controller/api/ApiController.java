package com.manager.web.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.manager.system.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/9/7
 * 客服端 调用接口
 */
@RestController
@Api(tags = "客服端接口")
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    private ApiService apiService;
    /**
     * 月卡接口
     */
    @ApiOperation(value = "月卡接口")
    @PostMapping("/pay/get_month_mark")
    public JSONObject month(){
        return apiService.getMonthConfig();
    }

    /**
     * 充值 配置接口
     */
    @ApiOperation(value = "充值配置信息接口")
    @PostMapping("/pay/get_book_mark")
    public JSONObject book(String data){
        return apiService.getBookConfig(data);
    }

}
