package cn.flydiy.core.repository;

import cn.flydiy.core.entity.User;

import java.util.List;

/**
 * Created by flying on 16-12-2.
 */
public interface UserRepository extends BaseRepository<User> {
    String getPasswordForUser(String account);

    User findByAccount(String account);

    //---------------------------------

    /**
     * 根据法人单位id查询该组织下的所有人员
     *
     * @param id 组织ID
     * @return
     */
    List<User> queryUserListByOrgId(String id);

    /**
     *
     * 根据法人单位ID查询所有人
     *
     * @param orgId 组织ID
     * @return
     */
    List<User> queryAllUserByOrgId(String orgId);

    /**
     * 查询用户所在法人单位下的所有人员
     * @return
     */
    List<User> queryAllUser();
    /**
     * 所有人员
     * @return
     */
    List<User> queryAllUser2();

    /**
     * 根据姓名搜索人员
     * @param param
     * @return
     */
    List queryUserByParams(String param);
}