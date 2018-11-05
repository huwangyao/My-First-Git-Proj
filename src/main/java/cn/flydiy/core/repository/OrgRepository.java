package cn.flydiy.core.repository;

import cn.flydiy.core.entity.Org;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
public interface OrgRepository extends BaseRepository<Org> {
    List<Org> queryOrgList(String id);

    /**
     * 判断该节点下是否还有子节点
     */
    List<Org> countSonOrgByOrgId(String id, String title);

    /**
     * 查询所有组织
     * @return
     */
    List<Org> queryAllList();


    //-----------上面的全要删掉的
    /**
     * 查询所有组织
     * @return
     */
    List<Org> queryAllOrg(boolean isStatus);
    /**
     * 查询所有组织
     * @return
     */
    List<Org> queryAllOrg(boolean isStatus,String filterType);

    /**
     * 根据名称搜索组织
     * @param param
     * @return
     */
    List<Org> queryOrgByParams(String param);
}
