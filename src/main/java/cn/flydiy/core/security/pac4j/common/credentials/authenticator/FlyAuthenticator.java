package cn.flydiy.core.security.pac4j.common.credentials.authenticator;

import cn.flydiy.base.config.security.exception.account.DisabledAccountException;
import cn.flydiy.base.config.security.exception.account.LockedAccountException;
import cn.flydiy.base.config.security.exception.account.UnknownAccountException;
import cn.flydiy.base.config.security.exception.credentials.BlankCredentialsException;
import cn.flydiy.base.config.security.exception.credentials.IncorrectPasswordException;
import cn.flydiy.base.util.SystemUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.constants.LoginStatus;
import cn.flydiy.core.entity.User;
import cn.flydiy.core.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.CommonProfile;

/**
 *  包名和类名修改, 需要同步 cn.flydiy.base.config.security.pac4j.common.credentials.RealmHelper#REALM_DEFAULT
 * Created by flying on 17-8-18.
 */
public class FlyAuthenticator implements Authenticator<UsernamePasswordCredentials> {


    private Logger logger = LogManager.getLogger();

    @Override
    public void validate(UsernamePasswordCredentials credentials, WebContext context) throws HttpAction, CredentialsException {

        String account = credentials.getUsername();
        String password = credentials.getPassword();
        if (StringUtil.isBlank(account) || StringUtil.isBlank(password)) {
            // 用户名或者密码不能为空
            throw new BlankCredentialsException();
        }

        User user = SpringContext.getBean(AuthService.class).findByAccount(account);
        if (user == null) {
            logger.warn("Authentication failed for non-existent user {}.", account);
            throw new UnknownAccountException();
        }
        if (password.length() < 6 || StringUtil.notEquals(SystemUtil.encryptPassword(password), user.getPassword())) {
            logger.warn("Authentication failed for user {}.", account);
            throw new IncorrectPasswordException();
        }
        if (LoginStatus.LOCK.equalsIgnoreCase(user.getStatus())) {
            logger.warn("user locked!! {}", account);
            throw new LockedAccountException();
        }
        if (LoginStatus.DISABLE.equalsIgnoreCase(user.getStatus())) {
            logger.warn("user Disabled!! {}", account);
            throw new DisabledAccountException();
        }


        final CommonProfile profile = new CommonProfile();

        profile.setId(account);
        profile.addAttribute(Pac4jConstants.USERNAME, account);
        credentials.setUserProfile(profile);
    }
}