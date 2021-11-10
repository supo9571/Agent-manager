package com.manager.controller.system;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.constant.UserConstants;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysMenu;
import com.manager.common.core.domain.entity.SysRole;
import com.manager.common.core.domain.model.LoginUser;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.ServletUtils;
import com.manager.common.utils.StringUtils;
import com.manager.framework.web.service.SysPermissionService;
import com.manager.framework.web.service.TokenService;
import com.manager.system.domain.SysUserRole;
import com.manager.system.service.ISysMenuService;
import com.manager.system.service.ISysRoleService;
import com.manager.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色信息
 *
 * @author marvin
 */
@RestController
@Api(tags = "角色管理")
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysUserService userService;

    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @ApiOperation(value = "角色列表")
    @GetMapping("/list")
    public AjaxResult list(SysRole role) {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @ApiOperation(value = "获取角色详细")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable @ApiParam(name = "角色id", value = "1") Long roleId) {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @ApiOperation(value = "新增角色")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("add")
    public AjaxResult add(String roleName) {
        SysRole role = new SysRole(roleName);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @ApiOperation(value = "修改角色权限")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("edit")
    public AjaxResult edit(@Validated @RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
//        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
//            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
//        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
//            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
//        }
        role.setUpdateBy(SecurityUtils.getUsername());
        if (roleService.updateRole(role) > 0) {
            // 更新缓存用户权限
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName(), ManagerConfig.getTid()));
                tokenService.setLoginUser(loginUser);
            }
            return AjaxResult.success();
        }
        return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改保存角色名称
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @ApiOperation(value = "修改保存角色名称")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("editName")
    public AjaxResult editName(String roleName, Long roleId) {
        SysRole role = new SysRole(roleId, roleName);
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        roleService.updateRoleName(role);
        return AjaxResult.success();
    }

    /**
     * 修改保存数据权限
     */
//    @PreAuthorize("@ss.hasPermi('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @ApiOperation(value = "修改保存数据权限")
//    @PostMapping("/dataScope")
//    public AjaxResult dataScope(@RequestBody SysRole role)
//    {
//        roleService.checkRoleAllowed(role);
//        return toAjax(roleService.authDataScope(role));
//    }

    /**
     * 状态修改
     */
//    @PreAuthorize("@ss.hasPermi('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @ApiOperation(value = "角色状态修改")
//    @PutMapping("/changeStatus")
//    public AjaxResult changeStatus(@RequestBody SysRole role)
//    {
//        roleService.checkRoleAllowed(role);
//        role.setUpdateBy(SecurityUtils.getUsername());
//        return toAjax(roleService.updateRoleStatus(role));
//    }

    /**
     * 删除角色
     */
//    @PreAuthorize("@ss.hasPermi('system:role:remove')")
//    @Log(title = "角色管理", businessType = BusinessType.DELETE)
//    @ApiOperation(value = "角色删除")
//    @DeleteMapping("/{roleIds}")
//    public AjaxResult remove(@PathVariable Long[] roleIds)
//    {
//        return toAjax(roleService.deleteRoleByIds(roleIds));
//    }

    /**
     * 获取角色选择框列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @ApiOperation(value = "获取角色选择框列表")
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        return AjaxResult.success(roleService.selectRoleAll());
    }

    /**
     * 查询已分配用户角色列表
     */
//    @PreAuthorize("@ss.hasPermi('system:role:list')")
//    @ApiOperation(value = "查询已分配用户角色列表")
//    @GetMapping("/authUser/allocatedList")
//    public AjaxResult allocatedList(SysUser user)
//    {
//        startPage();
//        List<SysUser> list = userService.selectAllocatedList(user);
//        return AjaxResult.success(getDataTable(list));
//    }

    /**
     * 查询未分配用户角色列表
     */
//    @PreAuthorize("@ss.hasPermi('system:role:list')")
//    @ApiOperation(value = "查询未分配用户角色列表")
//    @GetMapping("/authUser/unallocatedList")
//    public TableDataInfo unallocatedList(SysUser user)
//    {
//        startPage();
//        List<SysUser> list = userService.selectUnallocatedList(user);
//        return getDataTable(list);
//    }

    /**
     * 取消授权用户
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @ApiOperation(value = "取消授权用户")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    public AjaxResult cancelAuthUser(@RequestBody SysUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
//    @PreAuthorize("@ss.hasPermi('system:role:edit')")
//    @ApiOperation(value = "批量取消授权用户")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
//    @PostMapping("/authUser/cancelAll")
//    public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds)
//    {
//        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
//    }

    /**
     * 批量选择用户授权
     */
//    @PreAuthorize("@ss.hasPermi('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
//    @ApiOperation(value = "批量选择用户授权")
//    @PostMapping("/authUser/selectAll")
//    public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds)
//    {
//        return toAjax(roleService.insertAuthUsers(roleId, userIds));
//    }

    @Autowired
    private ISysMenuService menuService;

    /**
     * 加载对应角色菜单列表树
     */
    @ApiOperation(value = "加载对应角色菜单列表树")
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMenu> menus = menuService.selectMenuList(loginUser.getUser());
        Map map = new HashMap();
        map.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        map.put("menus", menuService.buildMenuTreeSelect(menus));
        return AjaxResult.success(map);
    }

    /**
     * 获取菜单下拉树列表
     */
    @ApiOperation(value = "获取登录账号菜单下拉树列表")
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenu menu) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMenu> menus = menuService.selectMenuList(menu, loginUser.getUser());
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }
}
