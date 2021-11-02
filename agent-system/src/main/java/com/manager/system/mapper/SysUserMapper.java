package com.manager.system.mapper;

import java.util.List;

import com.manager.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 用户表 数据层
 *
 * @author marvin
 */
public interface SysUserMapper {

    /**
     * 根据条件分页查询未已配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(@Param("userName") String userName,@Param("tenant")String tenant);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);


    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public int checkUserNameUnique(@Param("userName") String userName,@Param("tenant") String tenant);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);


    /**
     * 根据条件分页查询用户列表
     */
    List selectUserList(SysUser sysUser);

    /**
     * 新增用户信息
     */
    int insertUser(SysUser user);

    /**
     * 新增用户与角色关联
     */
    void insertUserRole(@Param("userId") long userId, @Param("roleId") String roleId);

    /**
     * 查询google密钥
     */
    String queryGoogleKey(@Param("userId") Long userId);

    Long selectUserIdByUserName(@Param("userName") String userName);

    @Update("update sys_user set google_key = #{googleKey} where user_id = #{userId}")
    int saveGoogleKey(@Param("userId") Long userId, @Param("googleKey") String googleKey);

    @Select("select count(1) from sys_ip_white where user_id = #{userId}")
    Integer selectUserIps(@Param("userId")Long userId);
}
