package cn.flydiy.web.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.web.dto.RoleUserDto;
import cn.flydiy.web.service.RoleUserService;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by hoo on 17-2-19.
 */
@WebController
public class RoleUserController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    RoleUserService roleUserService;

    public void queryRoleUserByRoleId() {
        Map<String, Object> paramMap = getParamMap();
        String roleId = MapUtils.getString(paramMap, "roleId");

        List<Map> roleUser = roleUserService.findRoleUserByRoleId(roleId);
        ResponseData responseData = new ResponseData(roleUser);
        render(responseData);
    }

    public void addRoleUser() {
        RoleUserDto roleUserDto = getParamObj(RoleUserDto.class);
        ResponseData data = new ResponseData();

        roleUserService.saveRoleUser(roleUserDto);

        ResponseData responseData = new ResponseData();
        render(responseData);
    }

    public void queryPageRoleUserByRoleId(){
        Map<String, Object> paramMap = getParamMap();
        List<String> idsLsit = (List<String>)paramMap.get("roleId");
        String[] roleId = idsLsit.toArray(new String[]{});

        List<Map> roleUser = roleUserService.queryPageRoleUserByRoleId(roleId);
        ResponseData responseData = new ResponseData(roleUser);
        render(responseData);
    }

}