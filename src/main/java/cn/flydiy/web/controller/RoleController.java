package cn.flydiy.web.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.entity.Role;
import cn.flydiy.web.service.RoleService;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@WebController
public class RoleController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    RoleService roleService;

    public void queryRoleTree() {

        Map<String, Object> paramMap = getParamMap();
        String corpId = MapUtils.getString(paramMap, "corpId");

        List<Map> roles = roleService.queryRoleTree(corpId);

        ResponseData responseData = new ResponseData(roles);
        render(responseData);
    }

    public void queryRoles() {

        Map<String, Object> paramMap = getParamMap();
        String corpId = MapUtils.getString(paramMap, "corpId");
        if (StringUtil.isEmpty(corpId)) {
            corpId = WebUtils.getLoginUser().getCorpId();
        }

        List<Map> roles = roleService.queryAllRole(corpId);

        ResponseData responseData = new ResponseData(roles);
        render(responseData);
    }

    public void updateRole() {

        Role role = getParamObj(Role.class);

        role.setCorpId(WebUtils.getLoginUser().getCorpId());

        roleService._updateEntityById(role);

        ResponseData responseData = new ResponseData();
        render(responseData);
    }

    public void addRole() {

        Role role = getParamObj(Role.class);


        role.setCorpId(WebUtils.getLoginUser().getCorpId());
        roleService.save(role);

        ResponseData responseData = new ResponseData();
        render(responseData);
    }

    public void removeRole() {

        Map<String, Object> paramMap = getParamMap();
        String id = MapUtils.getString(paramMap, "id");

        roleService._deleteByIds(id);

        ResponseData responseData = new ResponseData();
        render(responseData);
    }

}