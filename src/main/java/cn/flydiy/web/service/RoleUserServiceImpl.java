package cn.flydiy.web.service;

import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.service.UserService;
import cn.flydiy.web.dto.RoleUserDto;
import cn.flydiy.web.entity.Role;
import cn.flydiy.web.entity.RoleUser;
import cn.flydiy.web.repository.RoleUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUser> implements RoleUserService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    RoleUserRepository roleUserRepository;
    @Autowired
    UserService userService;


    @Override
    public List<String> findUserIdByRoleId(String... roleIds) {
        List<RoleUser> roleUsers = roleUserRepository.findUserIdByRoleId(roleIds);

        List<String> userIds = roleUsers.stream().map(RoleUser::getUserId).distinct().collect(Collectors.toList());
        return userIds;
    }

    @Override
    public List<String> findPageUserIdByRoleId(String... roleIds) {
        List<RoleUser> roleUsers = roleUserRepository.findPageUserIdByRoleId(roleIds);

        List<String> userIds = roleUsers.stream().map(RoleUser::getUserId).distinct().collect(Collectors.toList());
        return userIds;
    }

    @Override
    public void saveRoleUser(RoleUserDto roleUserDto) {
        // 获取角色人员对象数组
        List<RoleUser> roleUser = BeanUtil.convertMapsToBeans(roleUserDto.getRoleUser(), RoleUser.class);
        // 删除该角色中的所有人员
        Role role = roleUserDto.getRole();
        this.deleteRoleUserByRoleId(role.getId());
        // 保存角色人员
        if (roleUser.size() > 0) {
            this.save(roleUser.toArray(new RoleUser[]{}));
        }
    }

    @Override
    public void deleteRoleUserByRoleId(String roleId) {
        roleUserRepository.deleteRoleUserByRoleId(roleId);
    }

    @Override
    public List<Map> queryPageRoleUserByRoleId(String... roleIds) {
        List<Map> result = new ArrayList();
        // 获取角色人员
        List<Map> resultList  = BeanUtil.convertBeansToMaps(roleUserRepository.findPageUserIdByRoleId(roleIds));
        // 获取人员ID
        List<String> userIds = this.findPageUserIdByRoleId(roleIds);
        // 获取人员list
        List<Map> userList = userService.queryUserListByIds(userIds.toArray(new String[]{}));
        for(Map roleUser:resultList){
            String userId = (String) roleUser.get("userId");
            for(Map map:userList){
                String id = (String) map.get("id");
                if(StringUtil.equalsIgnoreCase(id,userId)){
                    roleUser.put("realName",map.get("realName"));
                    break;
                }
            }
        }
        return userList;
    }

    @Override
    public List<Map> findRoleUserByRoleId(String... roleIds) {
        // 获取角色人员
        List<Map> resultList  = BeanUtil.convertBeansToMaps(roleUserRepository.findUserIdByRoleId(roleIds));
        // 获取人员ID
        List<String> userIds = this.findUserIdByRoleId(roleIds);
        // 获取人员list
        List<Map> userList = userService.queryUserListByIds(userIds.toArray(new String[]{}));
        for(Map roleUser:resultList){
            String userId = (String) roleUser.get("userId");
            for(Map map:userList){
                String id = (String) map.get("id");
                if(StringUtil.equalsIgnoreCase(id,userId)){
                    roleUser.put("userName",map.get("realName"));
                    break;
                }
            }
        }
        return resultList;
    }
}
