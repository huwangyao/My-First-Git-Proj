package cn.flydiy.core.service;

import cn.flydiy.core.entity.User;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface UserService extends BaseService<User> {

    User findByAccount(String account);


    /**
     * 查询用户所在法人单位下的所有人员
     * @return
     */
    List<Map> queryAllUser();

    /**
     * 根据组织ID查询所有人
     * @param orgId 组织ID
     * @return
     */
    List<Map> queryUserListByOrgId(String orgId);

    /**
     * 根据组织ID查询下级组织和当前组织的人员
     * @param parentId
     * @return
     */
    Map querySonOrgAndUserByOrgId(String parentId,String type,String roleId,String filterType);

    /**
     * 根据id数组查询人员数组
     * @param ids
     * @return
     */
    List<Map> queryUserListByIds(String[] ids);

    /**
     * 根据人员名称查找人员
     * @param param
     * @return
     */
    List<User> queryUserByParams(String param,String parentId,String roleId);

    /**
     * ChangePassword的手工代码
     * @param password
     */
    void changePassword(String password);

    /**
     * 初始化密码
     * @param ids 人员id数组
     */
    void resetPsw(String[] ids);

    /**
     * 查询该组织和所有子组织的人员
     * @param orgId 组织ID
     * @return
     */
    List<Map> queryAllUserByOrgId(String orgId);
}