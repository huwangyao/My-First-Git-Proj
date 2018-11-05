package cn.flydiy.core.security.shiro.realm;

import cn.flydiy.core.security.shiro.permission.UrlPermissionResolver;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @see cn.flydiy.base.config.security.shiro.realm.RealmHelper
 * Created by flying on 17-7-12.
 */
public abstract class BaseRealm  extends AuthorizingRealm {


    private PermissionResolver permissionResolver = new UrlPermissionResolver();

    public PermissionResolver getPermissionResolver() {
        return permissionResolver;
    }

    public void setPermissionResolver(PermissionResolver permissionResolver) {

        this.permissionResolver = permissionResolver;
    }


    /**
     * 权限验证
     * @param principals
     * @return
     */
    @Override
    protected final AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        //add Permission Menu
//        info.setStringPermissions(Sets.newHashSet(userService.getPermission(username)));
//        //add Roles String[Set<String> roles]
//        //info.setRoles(roles);
        return info;
    }




}
