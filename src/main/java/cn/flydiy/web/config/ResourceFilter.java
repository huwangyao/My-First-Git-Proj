package cn.flydiy.web.config;


import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.listener.WebConfig;
import cn.flydiy.core.service.BaseUserService;
import cn.flydiy.core.web.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源filter
 * Created by flying on 16-12-2.
 */
public class ResourceFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private static final Map<String, String> urlRole = new HashMap<>(1024);

    private static final String anon = "anon";


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            uri = uri + pathInfo;
        }


        // 判断当前用户是否拥有该api对应的角色
        if (!isPermit(uri)) {

            logger.warn("NotAuthorized:" + uri);
            // 没有权限访问
            WebUtils.renderNotAuthorized();

            return;
        }

        chain.doFilter(request, response);

    }

    private boolean isPermit(String uri) {


        //   从配置文件中配置的权限
        initAuthRequired(uri);


        // 1.获取能访问该uri对应的角色key值,,从配置文件中配置的权限
        String roles = urlRole.get(uri);

        Map<String, List> allMenuUrlWithRoleInCache = SpringContext.getBean(BaseUserService.class).findAllMenuUrlWithRoleInCache();

        if (allMenuUrlWithRoleInCache.containsKey(uri)) {

            List roleList = allMenuUrlWithRoleInCache.get(uri);
            if (anon.equals(roles)) {
                roles = StringUtil.join(roleList, ",");
            } else {
                roles = roles + "," + StringUtil.join(roleList, ",");
            }
        }

        logger.info("uri:" + uri + ":role->" + roles);

        if (anon.equals(roles)) {
            return true;
        }


        String currUserRoles = WebUtils.getLoginUserRole();
        logger.info("api-roles:" + roles + "::currUserRoles:" + currUserRoles);

        if (StringUtil.isBlank(roles) || StringUtil.isBlank(currUserRoles)) {
            return false;
        }

        for (String role : roles.split(",")) {
            for (String currUserRole : currUserRoles.split(",")) {
                if (role.equals(currUserRole)) {
                    return true;
                }
            }
        }
        return false;

    }


    /**
     * 从配置文件中配置的权限
     * authFilter中验证uri是否需要登录访问
     *
     * @param uri
     * @return
     */
    private static void initAuthRequired(String uri) {

        if (urlRole.containsKey(uri)) {
            return;
        }

        if (WebConfig.resourceFilterPath.keySet().contains(uri)) {
            urlRole.put(uri, WebConfig.resourceFilterPath.get(uri));
            return;
        }

        if (initAuthRequiredPrefix(uri)) {
            return;
        }

        // 不用校验
        urlRole.put(uri, anon);

    }

    private static boolean initAuthRequiredPrefix(String uri) {
        for (String authFilterPathPrefix : WebConfig.resourceFilterPathPrefix.keySet()) {
            if (uri.startsWith(authFilterPathPrefix)) {
                urlRole.put(uri, WebConfig.resourceFilterPathPrefix.get(authFilterPathPrefix));
                return true;
            }
        }
        return false;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug(filterConfig);
    }

    @Override
    public void destroy() {
    }
}
