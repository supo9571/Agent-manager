package com.manager.controller;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author marvin 2021/9/6
 */
@RestController
@RequestMapping("/ys/manager")
@Api(tags = "银商系统管理")
public class YSController extends BaseController {

    @Autowired
    private RechargeService rechargeService;

    /**
     * 银商后台登录
     */
    @ApiOperation(value = "银商后台登录")
    @GetMapping("/login")
    public AjaxResult login(String username, String password, String googleCode, HttpServletRequest httpRequest) {
        //TODO
        httpRequest.getSession().setAttribute(YSID,100005);
        return AjaxResult.success();
    }

    /**
     * 银商后台登出
     */
    @ApiOperation(value = "银商后台登录")
    @GetMapping("/loginout")
    public AjaxResult loginout(HttpServletRequest httpRequest) {
        httpRequest.getSession().removeAttribute(YSID);
        return AjaxResult.success();
    }
    /**
     * 额度明细
     */
    @ApiOperation(value = "额度明细")
    @GetMapping("/quota")
    public AjaxResult quota(Integer type, HttpServletRequest httpRequest){
        if(checkLogin(httpRequest)){
            return AjaxResult.error("请先登录");
        }
        startPage();
        Integer ysid = (Integer) httpRequest.getSession().getAttribute(YSID);
        List list = rechargeService.ysquota(ysid,type);
        return AjaxResult.success(getDataTable(list));
    }
}
