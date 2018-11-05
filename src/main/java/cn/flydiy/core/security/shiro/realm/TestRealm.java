package cn.flydiy.core.security.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

public class TestRealm extends BaseRealm {


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

//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, password, getName());

        return null;

    }


}





