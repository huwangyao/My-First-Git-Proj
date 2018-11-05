package cn.flydiy.core.controller;


import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.entity.User;
import cn.flydiy.core.constants.LoginStatus;
import cn.flydiy.core.service.UserService;
import cn.flydiy.core.web.ResponseData;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by hoo on 17-1-25.
 */
@WebController
public class UserController extends BaseController {
    private static final Logger logger = LogManager.getLogger();


    @Autowired
    UserService userService;

    public void querySonOrgAndUserByOrgId(){
        Map<String, Object> paramMap = getParamMap();
        String roleId = MapUtils.getString(paramMap,"roleId");
        String parentId = MapUtils.getString(paramMap, "parentId");
        String type = MapUtils.getString(paramMap, "type");// 组织选择,人员选择
        String filterType = MapUtils.getString(paramMap, "filterType"); // 过滤类型

        Map map = userService.querySonOrgAndUserByOrgId(parentId,type,roleId,filterType);
        ResponseData responseData = new ResponseData(map);

        render(responseData);
    }

    public void queryUserListByIds(){
        Map<String, Object> paramMap = getParamMap();
        List<String> idsLsit = (List<String>)paramMap.get("ids");
        String[] ids = idsLsit.toArray(new String[]{});

        List<Map> list = userService.queryUserListByIds(ids);
        ResponseData responseData = new ResponseData(list);

        render(responseData);
    }

    /**
     * 用户组件搜索用户
     */
    public void queryUserByParams(){
        Map<String, Object> paramMap = getParamMap();
        String param = MapUtils.getString(paramMap, "param");
        String roleId = MapUtils.getString(paramMap,"roleId");
        String parentId = MapUtils.getString(paramMap, "parentId");

        List<User> list = userService.queryUserByParams(param,parentId,roleId);
        ResponseData responseData = new ResponseData(list);

        render(responseData);
    }

    //ChangePassword的手工代码
    public void changePassword() {
        Map map = getParamMap();
        String userPassword=userService.findOne(cn.flydiy.core.web.WebUtils.getLoginUser().getId()).getPassword();
        String oldPassword = cn.flydiy.base.util.SystemUtil.encryptPassword(MapUtils.getString(map, "oldPassword"));
        String state;
        if(cn.flydiy.common.util.StringUtil.equals(userPassword, oldPassword)){
            userService.changePassword(MapUtils.getString(map,"newPassword"));
            state="true";
        }else{
            state="false";
        }
        super.render(new ResponseData(state));

    }

    public void resetPsw() {
        Map<String, Object> paramMap = getParamMap();
        List<String> idsLsit = (List<String>)paramMap.get("ids");
        String[] ids = idsLsit.toArray(new String[]{});

        userService.resetPsw(ids);
        ResponseData responseData = new ResponseData();
        render(responseData);

    }

    public void isResetPsw(){
        User userInfo = userService.findOne(cn.flydiy.core.web.WebUtils.getLoginUser().getId());
        String state;
        if(userInfo == null){
            state="false";
        }else{
            String status=userService.findOne(cn.flydiy.core.web.WebUtils.getLoginUser().getId()).getStatus();

            if(cn.flydiy.common.util.StringUtil.equals(status, LoginStatus.RESET)){
                state="true";
            }else{
                state="false";
            }
        }

        super.render(new ResponseData(state));
    }

    public void queryAllUserByOrgId(){
        Map<String, Object> paramMap = getParamMap();
        String orgId = (String)paramMap.get("orgId");

        List<Map> userList = userService.queryAllUserByOrgId(orgId);
        ResponseData responseData = new ResponseData(userList);
        render(responseData);
    }
}