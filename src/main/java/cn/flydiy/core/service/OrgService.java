package cn.flydiy.core.service;


import cn.flydiy.core.entity.Org;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-2.
 */
@Validated
public interface OrgService extends BaseService<Org> {


    List<Map> queryOrgList(String id);

    Map queryOrgInfo(String id);

    /**
     * 根据orgId查询其下属组织
     */
    List<Map> querySonOrgByOrgId(String parentId,String id, String title);

    /**
     * 当传入id数组,初始化组织数据
     *
     * @param ids
     * @return
     */
    Map queryOrgDataByOrgIds(String[] ids);


    /**
     * 查询根据parentId下级组织
     */
    List<Map> countSonOrgByOrgId(String parentId);

    /**
     * 根据组织查询所有下级组织列表
     * @param orgId
     * @return
     */
    List<Map> querySonOrgListByOrgId(String orgId);

    /**
     * 根据组织ID查询上级组织直到根节点(面包屑数据)
     * @param orgId 组织ID
     * @return
     */
    List<Map> queryFatherOrgListByOrgId(String orgId);

    //--------------------------上面的全要删掉

    /**
     * 当传入id数组,初始化组织数据
     * @param ids
     * @return
     */
    List<Org> queryOrgListByIds(String[] ids);

    /**
     * 查询用户法人单位下的所有组织(过滤掉无效的组织)
     * @return
     */
    List<Org> queryAllOrg();

    /**
     * 查询用户法人单位下的所有组织(过滤掉无效的组织)
     * @return
     */
    List<Org> queryAllOrg(String filterType);

    /**
     * 根据名称搜索组织
     * @param param
     * @return
     */
    List<Org> queryOrgByParams(String param);

    /**
     * 查询用户法人单位下的所有组织(包含无效的组织)
     * @return
     */
    List queryAllOrgIgnoreStatus();
}
