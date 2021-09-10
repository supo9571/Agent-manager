package com.manager.framework.web.service;

import javax.annotation.Resource;

import com.manager.common.core.domain.entity.SysUser;
import com.manager.common.utils.google.GoogleAuth;
import com.manager.framework.manager.AsyncManager;
import com.manager.system.service.SysIpWhiteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.manager.common.constant.Constants;
import com.manager.common.core.domain.model.LoginUser;
import com.manager.common.core.redis.RedisCache;
import com.manager.common.exception.CustomException;
import com.manager.common.exception.user.CaptchaException;
import com.manager.common.exception.user.CaptchaExpireException;
import com.manager.common.exception.user.UserPasswordNotMatchException;
import com.manager.common.utils.DateUtils;
import com.manager.common.utils.MessageUtils;
import com.manager.common.utils.ServletUtils;
import com.manager.common.utils.ip.IpUtils;
import com.manager.framework.manager.factory.AsyncFactory;
import com.manager.system.service.ISysConfigService;
import com.manager.system.service.ISysUserService;

import java.util.List;

/**
 * 登录校验方法
 *
 * @author marvin
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysIpWhiteService sysIpWhiteService;
    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param googleCode 验证码
     * @return 结果
     */
    public String login(String username, String password, String googleCode)
    {
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //验证google验证码
        if(loginUser.getUser().isSwitchOpen() && !GoogleAuth.isPattern(loginUser.getUser().getGoogleKey(),googleCode)){
            throw new CustomException("google验证码错误");
        }


        //验证ip
        String ips = sysIpWhiteService.selectIpByUserId(loginUser.getUser().getUserId()+"");
        if(ips.isEmpty() || ips.contains(IpUtils.getHostIp())){
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
            recordLoginInfo(loginUser.getUser());
            // 生成token
            return tokenService.createToken(loginUser);
        }else {
            throw new CustomException("ip被限制");
        }
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user)
    {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(user);
    }
}
