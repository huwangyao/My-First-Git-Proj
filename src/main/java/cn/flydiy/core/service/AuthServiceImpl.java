package cn.flydiy.core.service;

import cn.flydiy.base.config.security.shiro.helper.ShiroHelper;
import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.core.constants.LoginLogStatus;
import cn.flydiy.core.entity.LoginLog;
import cn.flydiy.core.entity.User;
import cn.flydiy.core.repository.UserRepository;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.service.UserBindInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;

/**
 * Created by flying on 16-12-2.
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl<User> implements AuthService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    UserRepository userRepository;
    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private UserBindInfoService userBindInfoService;

    @Override
    public User authenticate(String account, String password) {


        UsernamePasswordToken token = new UsernamePasswordToken(account, password);

        try {
            ShiroHelper.login(token);
        } catch (LockedAccountException e) {
            ExceptionUtil.throwBaseRunTimeException("sys.auth.login.locked");
        } catch (AuthenticationException e) {
            ExceptionUtil.throwBaseRunTimeException("sys.auth.loginError");
        }



        // 查询数据库是否有该用户信息
        User user = this.userRepository.findByAccount(account);
        if (user == null) {
            log.warn("sys.auth.loginUserNotInDb {}.", account);
            ShiroHelper.logout();
            ExceptionUtil.throwBaseRunTimeException("sys.auth.loginUserNotInDb",new String[]{account});
        }

        log.debug("User {} successfully authenticated.", account);

        user.setPassword(null);

        WebUtils.setLoginUser(user);

        return user;
    }



    @Override
    public User checkAndSetLoginUser(String account, String loginType) {

        LoginLog loginLog = BaseUserServiceImpl.buildLoginLog(account, loginType);

        // 必须校验通过
        if (!ShiroHelper.isAuthenticated()) {
            // 按理说 不可能发生未校验通过的情况
            loginLog.setLoginStatus(LoginLogStatus.LOGIN_STATUS_FAIL);
            loginLog.setRemark("!ShiroHelper.isAuthenticated():"+account);

            saveLoginLog(loginLog);

            log.warn("sys.auth.loginUserNotInDb noAuthenticated {}.", account);
            ExceptionUtil.throwBaseRunTimeException("sys.auth.loginUserNotInDb",new String[]{"noAuthenticated:"+account});
        }


        // 查询数据库是否有该用户信息
        User user = this.userRepository.findByAccount(account);
        if (user == null) {
            loginLog.setLoginStatus(LoginLogStatus.LOGIN_STATUS_FAIL);
            loginLog.setRemark("sys.auth.loginUserNotInDb: "+account);

            saveLoginLog(loginLog);

            log.warn("sys.auth.loginUserNotInDb {}.", account);
            ShiroHelper.logout();
            ExceptionUtil.throwBaseRunTimeException("sys.auth.loginUserNotInDb",new String[]{account});
        }

        log.debug("User {} successfully authenticated.", account);

        user.setPassword(null);

        WebUtils.setLoginUser(user);
        WebUtils.setLoginType(loginType);

        loginLog.setLoginStatus(LoginLogStatus.LOGIN_STATUS_SUCCESS);

        // 设置登录日志的用户信息
        BaseUserServiceImpl.setLoginLogUserInfo(loginLog,user);
        // 保存登录日志
        saveLoginLog(loginLog);
        // 保存登录信息
        BaseUserServiceImpl.saveLoginInfoCache(loginLog);


        // 清除之前的用户聊天token
//        RequestContext context1 = RequestContext.getContext();
//        HttpServletResponse response = context1.getResponse();
//        HttpServletRequest request = context1.getRequest();
//        Cookie[] cookies = request.getCookies();
//
//        for (Cookie cookie : cookies) {
//            if ("rc_token".equals(cookie.getName()) || "rc_uid".equals(cookie.getName())) {
//                cookie.setValue(null);
//                cookie.setMaxAge(0);
//                cookie.setPath(WebUtils.calculatePath(request));
//                response.addCookie(cookie);
//            }
//        }

        // 同步聊天用户信息
//        chatUserService.syncChatUser(user);

        return user;
    }

//    @Autowired
//    ChatUserService chatUserService;


    /**
     * 保存登录日志
     * @param loginLog
     */
    private void saveLoginLog(LoginLog loginLog){
        loginLogService.save(loginLog);
    }



    @Override
    public void saveUser(User user, String newPassword) {

    }


    @Override
    public User findByAccount(String account) {
        User user = this.userRepository.findByAccount(account);

        return user;
    }


//    @Override
//    public User thirdPartyLogin(String bindId, String type) {
//        UserBindInfo userBindInfo = userBindInfoService.queryByBindIdAndType(bindId,type);
//        if(userBindInfo!=null){
//            User user = userRepository.findOne(userBindInfo.getUserId());
//            String account = user.getAccount();
//            LoginLog loginLog = buildLoginLog(account, LoginLogStatus.LOGIN_TYPE_LOGIN);
//            log.debug("User {} successfully authenticated.", account);
//            user.setPassword(null);
//            WebUtils.setLoginUser(user);
//            loginLog.setLoginStatus(LoginLogStatus.LOGIN_STATUS_SUCCESS);
//            // 设置登录日志的用户信息
//            setLoginLogUserInfo(loginLog,user);
//            // 保存登录日志
//            saveLoginLog(loginLog);
//            // 保存登录信息
//            saveLoginInfoCache(loginLog);
//            return user;
//        }
//        return null;
//    }
}
