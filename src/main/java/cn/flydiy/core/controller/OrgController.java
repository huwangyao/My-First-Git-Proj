package cn.flydiy.core.controller;


import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.entity.Org;
import cn.flydiy.core.service.OrgService;
import cn.flydiy.core.web.ResponseData;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-2.
 */
@WebController
public class OrgController extends BaseController {
    private static final Logger logger = LogManager.getLogger();


    @Autowired
    OrgService orgService;


    /**
     * 组织列表查询
     */
    public void queryOrgList() {

        Map<String, Object> paramMap = getParamMap();
        String id = MapUtils.getString(paramMap, "id");

        List<Map> list = orgService.queryOrgList(id);

        ResponseData responseData = new ResponseData(list);

        render(responseData);

    }

    /**
     * 根据orgId查询下级的组织
     */
    public void querySonOrgByOrgId() {

        Map<String, Object> paramMap = getParamMap();
        String parentId = MapUtils.getString(paramMap, "orgId");
        String id = MapUtils.getString(paramMap, "id");
        String title = MapUtils.getString(paramMap, "title");

        List<Map> list = orgService.querySonOrgByOrgId(parentId,id, title);

        ResponseData responseData = new ResponseData(list);

        render(responseData);

    }

    /**
     * 组织查询
     */
    public void queryOrgInfo() {
        Map<String, Object> paramMap = getParamMap();
        String id = MapUtils.getString(paramMap, "id");

        Map map = orgService.queryOrgInfo(id);

        ResponseData responseData = new ResponseData(map);

        render(responseData);
    }

    /**
     * 当传入id数组,初始化组织数据
     */
    public void queryOrgDataByOrgIds() {
        Map<String, Object> paramMap = getParamMap();

        List<String> ids = (List<String>) paramMap.get("id");
        Map map = orgService.queryOrgDataByOrgIds(ids.toArray(new String[]{}));

        ResponseData responseData = new ResponseData(map);

        render(responseData);
    }

    //------------------ 上面的要删掉
    /**
     * 当传入id数组,初始化组织数据
     */
    public void queryOrgListByIds() {
        Map<String, Object> paramMap = getParamMap();

        List<String> ids = (List<String>) paramMap.get("ids");
        List<Org> list = orgService.queryOrgListByIds(ids.toArray(new String[]{}));

        ResponseData responseData = new ResponseData(list);

        render(responseData);
    }

    public void queryOrgByParams(){
        Map<String, Object> paramMap = getParamMap();
        String param = MapUtils.getString(paramMap, "param");

        List<Org> list = orgService.queryOrgByParams(param);
        ResponseData responseData = new ResponseData(list);

        render(responseData);
    }
}