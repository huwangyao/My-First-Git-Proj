package cn.flydiy.core.security.shiro.realm;

import cn.flydiy.base.util.SystemUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.entity.User;
import cn.flydiy.core.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @see cn.flydiy.base.config.security.shiro.realm.RealmHelper

 */
public class UserRealm extends BaseRealm {


    private Logger logger = LogManager.getLogger();


    /**
     * 登陆验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String account = upt.getUsername();

        String password = String.valueOf(upt.getPassword());

        User user = SpringContext.getBean(UserRepository.class).findByAccount(account);
        if (user == null) {
            logger.warn("Authentication failed for non-existent user {}.", account);
            throw new UnknownAccountException("sys.auth.loginError");
        }
        if(StringUtil.notEquals(SystemUtil.encryptPassword(password), user.getPassword())) {
            logger.warn("Authentication failed for user {}.", account);
            throw new IncorrectCredentialsException("sys.auth.loginError");
        }
        if ("lock".equalsIgnoreCase(user.getStatus())) {
            logger.warn("user locked!! {}", account);
            throw new LockedAccountException("sys.auth.loginError");
        }

        String pa = "a-=+LoaIf*80";
        upt.setPassword(pa.toCharArray());

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(account, pa, getName());

        return simpleAuthenticationInfo;


    }


}





