package com.manager.controller;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Ysinfo;
import com.manager.common.exception.CustomException;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.google.GoogleAuth;
import com.manager.system.service.RechargeService;
import com.manager.system.service.YSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/6
 */
@RestController
@RequestMapping("/ys/manager")
@Api(tags = "银商系统管理")
public class YSController extends BaseController {

    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private YSService ysService;
    /**
     * 银商后台登录
     */
    @ApiOperation(value = "银商后台登录")
    @GetMapping("/login")
    public AjaxResult login(String username, String password, String googleCode, HttpServletRequest httpRequest) {
        Ysinfo ysinfo = ysService.selectByName(username);
        if(ysinfo==null || !SecurityUtils.matchesPassword(password,ysinfo.getPassword())){
            throw new CustomException("账号密码错误");
        }
        //验证google验证码
        if ("1".equals(ysinfo.getGoogleCheck()) && !GoogleAuth.isPattern(ysinfo.getGoogleKey(), googleCode)) {
            throw new CustomException("google验证码错误");
        }
        httpRequest.getSession().setAttribute(YSID,ysinfo.getId());
        return AjaxResult.success();
    }

    /**
     * 银商后台登出
     */
    @ApiOperation(value = "银商后台登出")
    @GetMapping("/logout")
    public AjaxResult logout(HttpServletRequest httpRequest) {
        httpRequest.getSession().removeAttribute(YSID);
        return AjaxResult.success();
    }

    /**
     * 充值订单
     */
    @ApiOperation(value = "充值订单")
    @GetMapping("/order")
    public AjaxResult order(String uid, String beginTime, String endTime,HttpServletRequest httpRequest){
        if(checkLogin(httpRequest)){
            throw new CustomException("登录信息失效，请登录后访问",401);
        }
        startPage();
        Integer ysid = (Integer) httpRequest.getSession().getAttribute(YSID);
        List list = ysService.getOrder(uid,beginTime,endTime,ysid);
        Map count = ysService.getOrderCount(uid,beginTime,endTime,ysid);
        Map result = new HashMap();
        result.put("list", getDataTable(list));
        result.put("count", count);
        return AjaxResult.success("查询成功", result);
    }

    /**
     * 统计报表
     */
    @ApiOperation(value = "统计报表")
    @GetMapping("/report")
    public AjaxResult report(String beginTime, String endTime,HttpServletRequest httpRequest){
        if(checkLogin(httpRequest)){
            throw new CustomException("登录信息失效，请登录后访问",401);
        }
        startPage();
        Integer ysid = (Integer) httpRequest.getSession().getAttribute(YSID);
        List list = ysService.getReport(beginTime,endTime,ysid);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 额度明细
     */
    @ApiOperation(value = "额度明细")
    @GetMapping("/quota")
    public AjaxResult quota(Integer type,String beginTime, String endTime, HttpServletRequest httpRequest){
        if(checkLogin(httpRequest)){
            throw new CustomException("登录信息失效，请登录后访问",401);
        }
        startPage();
        Integer ysid = (Integer) httpRequest.getSession().getAttribute(YSID);
        List list = rechargeService.ysquota(ysid,type,beginTime,endTime);
        Map count = rechargeService.ysquotaCount(ysid,type,beginTime,endTime);
        Map result = new HashMap();
        result.put("list", getDataTable(list));
        result.put("count", count);
        return AjaxResult.success("查询成功", result);
    }

    /**
     * 用户充值 风险提示
     */
    @ApiOperation(value = "用户充值")
    @GetMapping("/risk")
    public AjaxResult risk(Integer uid, Long amount, String name, String bank, HttpServletRequest httpRequest) {
        if(checkLogin(httpRequest)){
            throw new CustomException("登录信息失效，请登录后访问",401);
        }
        Integer ysid = (Integer) httpRequest.getSession().getAttribute(YSID);
        String msg = ysService.risk(ysid,uid,amount,name,bank);
        return AjaxResult.success(msg);
    }

    /**
     * 用户充值
     */
    @ApiOperation(value = "用户充值")
    @GetMapping("/recharge")
    public AjaxResult recharge(Integer uid, Long amount, String name, String bank, HttpServletRequest httpRequest) {
        if(checkLogin(httpRequest)){
            throw new CustomException("登录信息失效，请登录后访问",401);
        }
        Integer ysid = (Integer) httpRequest.getSession().getAttribute(YSID);
        String msg = ysService.recharge(ysid,uid,amount,name,bank);
        return AjaxResult.success(msg);
    }

    /**
     * 备注姓名库
     */
    @ApiOperation(value = "备注姓名库")
    @GetMapping("/name")
    public AjaxResult name(Integer uid, String beginTime, String endTime, HttpServletRequest httpRequest) {
        if(checkLogin(httpRequest)){
            throw new CustomException("登录信息失效，请登录后访问",401);
        }
        startPage();
        List list = ysService.getMark(uid,beginTime,endTime);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 用户黑名单
     */
    @ApiOperation(value = "用户黑名单")
    @GetMapping("/black")
    public AjaxResult black(Integer uid, String beginTime, String endTime, HttpServletRequest httpRequest) {
        if(checkLogin(httpRequest)){
            throw new CustomException("登录信息失效，请登录后访问",401);
        }
        startPage();
        List list = ysService.getBlack(uid,beginTime,endTime);
        return AjaxResult.success(getDataTable(list));
    }
}
