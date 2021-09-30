package com.manager.controller.system;

import com.manager.common.annotation.Log;
import com.manager.common.constant.UserConstants;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysMenu;
import com.manager.common.core.domain.entity.SysRole;
import com.manager.common.core.domain.entity.SysUser;
import com.manager.common.core.domain.model.LoginUser;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.StringUtils;
import com.manager.common.utils.google.GoogleAuth;
import com.manager.system.service.ISysMenuService;
import com.manager.system.service.ISysPostService;
import com.manager.system.service.ISysRoleService;
import com.manager.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author marvin
 */
@RestController
@Api(tags = "账号管理")
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;
    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @ApiOperation(value = "查询用户列表")
    @GetMapping("/list")
    public AjaxResult list(SysUser user) {
        startPage();
        if(SecurityUtils.getLoginUser().getUser().isAdmin()){
            user.setIsLoginAdmin("admin");
        }
        List list = userService.selectUserList(user);
        return AjaxResult.success("查询成功", getDataTable(list));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增用户")
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return toAjax(userService.insertUser(user, loginUser.getUser().getUserId()));
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @ApiIgnore
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        Map map = new HashMap<>();
        List<SysRole> roles = roleService.selectRoleAll();
        map.put("roles", roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        if (StringUtils.isNotNull(userId)) {
            map.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            map.put("postIds", postService.selectPostListByUserId(userId));
            map.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return AjaxResult.success(map);
    }


    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改用户")
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        if(StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        }
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
//    @PreAuthorize("@ss.hasPermi('system:user:remove')")
//    @Log(title = "用户管理", businessType = BusinessType.DELETE)
//    @ApiOperation(value = "删除用户")
//    @PostMapping("/{userIds}")
//    public AjaxResult remove(@PathVariable Long[] userIds)
//    {
//        return toAjax(userService.deleteUserByIds(userIds));
//    }

    /**
     * 重置密码
     */
//    @PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
//    @ApiOperation(value = "重置密码")
//    @PostMapping("/resetPwd")
//    public AjaxResult resetPwd(@RequestBody SysUser user)
//    {
//        userService.checkUserAllowed(user);
//        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
//        user.setUpdateBy(SecurityUtils.getUsername());
//        return toAjax(userService.resetPwd(user));
//    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "状态启停")
    @PostMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @ApiOperation(value = "用户权限详情")
    @GetMapping("/authRole/{userId}")
    public AjaxResult authRole(@ApiParam(value = "用户id") Long userId) {
        Map map = new HashMap();
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        map.put("user", user);
        map.put("roles", roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return AjaxResult.success(map);
    }

    /**
     * 用户授权角色
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @ApiOperation(value = "用户权限授权")
    @PostMapping("/authRole")
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    /**
     * 获取google随机密钥
     */
    @PreAuthorize("@ss.hasPermi('system:google:edit')")
    @ApiOperation(value = "获取google密钥")
    @GetMapping("/getGoogleKey")
    public AjaxResult getGoogleKey() {
        return AjaxResult.success("获取google密钥成功", GoogleAuth.getKeyStr());
    }

    /**
     * 获取 用户google密钥
     */
    @PreAuthorize("@ss.hasPermi('system:google:query')")
    @ApiOperation(value = "查询用户google密钥")
    @GetMapping("/queryGoogleKey")
    public AjaxResult queryGoogleKey(Long userId) {
        return AjaxResult.success("查询google密钥成功", userService.queryGoogleKey(userId));
    }

    /**
     * 保存 用户google密钥
     */
    @PreAuthorize("@ss.hasPermi('system:google:save')")
    @ApiOperation(value = "保存用户google密钥")
    @GetMapping("/saveGoogleKey")
    public AjaxResult saveGoogleKey(Long userId,String googleKey) {
        return AjaxResult.success("保存google密钥成功", userService.saveGoogleKey(userId,googleKey));
    }

    @Autowired
    private ISysMenuService menuService;
    /**
     * 获取用户菜单下拉树列表
     */
    @ApiOperation(value = "获取用户菜单下拉树列表")
    @GetMapping("/userTree")
    public AjaxResult treeselect(Long userId) {
        List<SysMenu> menus = menuService.selectMenuList(new SysMenu(), userId);
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }
}
