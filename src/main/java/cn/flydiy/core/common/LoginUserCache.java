package cn.flydiy.core.common;

import cn.flydiy.core.web.WebUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 17-11-15.
 */
public class LoginUserCache {


    /**
     * 登陆用兼职信息
     */
    private static final String SESSION_LoginUser_partTimeInfo = "LoginUser_partTimeInfo";


    /**
     * 获取登陆用户兼职信息
     */
    public static List<Map> getLoginUserPartTimeInfo(){
        List<Map> partTimes = WebUtils.getSessionAttribute(SESSION_LoginUser_partTimeInfo, List.class);
        return partTimes;
    }

    /**
     * 获取登陆用户兼职信息
     */
    public static void setLoginUserPartTimeInfo(List<Map> partTimes){
        WebUtils.setSessionAttribute(SESSION_LoginUser_partTimeInfo, partTimes);
    }

}
