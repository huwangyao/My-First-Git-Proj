package cn.flydiy.core.service;

import cn.flydiy.core.entity.Org;
import cn.flydiy.core.repository.OrgRepository;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-2.
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl<Org> implements OrgService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    OrgRepository orgRepository;

    @Override
    public List<Map> queryOrgList(String id) {

        List<Org> orgList = orgRepository.queryOrgList(id);


        List<Map> mapList = BeanUtil.convertBeansToMaps(orgList);

        return mapList;
    }

    @Override
    public Map queryOrgInfo(String id) {
        Org org = orgRepository.findOne(id);

        Map map = new HashMap();
        BeanUtil.copyPropertiesToMap(map, org);
        return map;
    }

    @Override
    public List<Map> querySonOrgByOrgId(String parentId,String id, String title) {
        List<Map> mapList = new ArrayList();
        // 如果有ID,就直接查找
//        if(StringUtil.isNotEmpty(id)){
//            Org org = findOne(id);
//            Map orgMap = new HashMap();
//            if(org!=null){
//                BeanUtil.copyPropertiesToMap(orgMap,org);
//            }
//            mapList.add(orgMap);
//        }else{
            // 根据orgId查询下级组织
            List<Org> orgList = orgRepository.countSonOrgByOrgId(parentId, title);
            // object数组转化成map数组
            mapList = BeanUtil.convertBeansToMaps(orgList);
            // 遍历每个下级组织是否还有下级组织
            String sonOrgId = "";
            int sonOrgCount = 0;
            for (Map map : mapList) {
                sonOrgId = (String) map.get("id");
                if (!StringUtil.equals(id, sonOrgId)) {
                    sonOrgCount = orgRepository.countSonOrgByOrgId(sonOrgId, title).size();
                }
                map.put("sonOrgCount", sonOrgCount);
            }
//        }
        return mapList;
    }

    @Override
    public Map queryOrgDataByOrgIds(String[] ids) {
        List<Map> fatherOrgList = new ArrayList();

        if (ids.length < 2) {
            Map parentMap = new HashMap();
            parentMap = this.queryParentOrgAndSameLevelOrgList(ids[0]);
            parentMap.put("rootOrgList", new ArrayList());
            return parentMap;
        } else {
            Map result = new HashMap();
            // 查询第一层组织的信息
            Org temp = this.findOne(ids[0]);
            List<Org> rootOrgList = orgRepository.countSonOrgByOrgId(temp.getCorpId(), null);
            result.put("rootOrgList", new ArrayList());
            // 查询每个id的父节点及其子节点信息
            for (int i = 0; i < ids.length; i++) {
                // 根据ID查询该组织的信息
                Map map = this.queryParentOrgAndSameLevelOrgList(ids[0]);
                result.put("obj" + i, map);
            }

            return result;
        }
    }

    public Map queryParentOrgAndSameLevelOrgList(String id) {
        // 根据ID查询该组织的信息
        Org org = this.findOne(id);
        // 查询该组织的父节点
        String parentId = org.getParentId();
        Org parentOrg = this.findOne(parentId);
        Map parentMap = new HashMap();
        BeanUtil.copyPropertiesToMap(parentMap, parentOrg);
        // 查询该父节点的所有子节点
        List<Org> parentToSonOrgList = orgRepository.countSonOrgByOrgId(parentId, null);
        parentMap.put("sonOrgList", parentToSonOrgList);
        return parentMap;
    }


    @Override
    public List<Map> countSonOrgByOrgId(String parentId) {
        List<Org> orgList = orgRepository.countSonOrgByOrgId(parentId, null);
        return BeanUtil.convertBeansToMaps(orgList);
    }

    @Override
    public List<Map> querySonOrgListByOrgId(String orgId) {
        List<Org> result = new ArrayList();
        List<Org> sonOrgList = orgRepository.countSonOrgByOrgId(orgId,null);
        if(sonOrgList.size()>0){
            result.addAll(sonOrgList);
            for(Org org:sonOrgList){
                String sonOrgId = org.getId();
                List<Org> graSonOrgList = orgRepository.countSonOrgByOrgId(sonOrgId,null);
                if(graSonOrgList.size()>0){
                    result.addAll(graSonOrgList);
                }
            }
        }

        return BeanUtil.convertBeansToMaps(result);
    }

    @Override
    public List<Map> queryFatherOrgListByOrgId(String orgId) {
        // 获取面包屑数组
        List<Org> fatherList = this.queryFatherOrgByOrgId(orgId);

        // 整理面包屑顺序
        List<Org> result = new ArrayList<Org>();
        String parentId = "-1";
        for(Org org : fatherList){
            if(StringUtil.equalsIgnoreCase(org.getParentId(),parentId)){
                parentId = org.getId();
                result.add(org);
            }
        }
        return BeanUtil.convertBeansToMaps(result);
    }

    /**
     * 根据组织ID获取面包屑数组
     * @param id
     * @return
     */
    public List<Org> queryFatherOrgByOrgId(String id){
        Org org = this.findOne(id);
        List<Org> resultList = new ArrayList();
        resultList.add(org);
        String parentId = org.getParentId();
        if(!StringUtil.equalsIgnoreCase(parentId,"-1")){
            List<Org> fatherList = this.queryFatherOrgByOrgId(parentId);
            resultList.addAll(fatherList);
        }
        return resultList;
    }



    //-------------上面的全要删掉

    @Override
    public List<Org> queryOrgListByIds(String[] ids) {
        List<Org> orgList = new ArrayList();
        List<Org> allOrgList = orgRepository.queryAllOrg(false);
        for(String id:ids){
            boolean flag = false;
            for(Org org:allOrgList){
                if(StringUtil.equalsIgnoreCase(id,org.getId())){
                    orgList.add(org);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                Org emptyOrg = new Org();
                emptyOrg.setId(id);
                emptyOrg.setName("该组织不存在");
                orgList.add(emptyOrg);
            }
        }

        return orgList;
    }

    @Override
    public List<Org> queryAllOrg() {
        List<Org> orgList = orgRepository.queryAllOrg(true);
        return orgList;
    }

    @Override
    public List<Org> queryAllOrg(String filterType) {
        List<Org> orgList = orgRepository.queryAllOrg(true,filterType);
        return orgList;
    }

    @Override
    public List<Org> queryOrgByParams(String param) {
        return orgRepository.queryOrgByParams(param);
    }

    @Override
    public List queryAllOrgIgnoreStatus() {
        List<Org> orgList = orgRepository.queryAllOrg(false);
        return BeanUtil.convertBeansToMaps(orgList);
    }
}
