package com.manager.controller.system;

import com.manager.common.constant.Constants;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysMenu;
import com.manager.common.core.domain.entity.SysUser;
import com.manager.common.core.domain.model.LoginBody;
import com.manager.common.core.domain.model.LoginUser;
import com.manager.common.utils.ServletUtils;
import com.manager.framework.web.service.SysLoginService;
import com.manager.framework.web.service.SysPermissionService;
import com.manager.framework.web.service.TokenService;
import com.manager.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 登录验证
 *
 * @author marvin
 */
@Api(tags = "登录管理")
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation(value = "登录方法")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        Map map = new HashMap();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getGoogleCode());
        map.put(Constants.TOKEN, token);
        return AjaxResult.success(map);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Map map = new HashMap();
        map.put("user", user);
        map.put("roles", roles);
        map.put("permissions", permissions);
        return AjaxResult.success(map);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @ApiOperation(value = "获取用户路由")
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
