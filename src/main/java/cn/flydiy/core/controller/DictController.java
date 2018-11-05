package cn.flydiy.core.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.entity.Dict;
import cn.flydiy.core.entity.DictItem;
import cn.flydiy.core.service.DictService;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
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
public class DictController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    DictService dictService;

    /**
     * 查询枚举项,当有搜索条件时加上搜索条件title
     */
    public void queryEnumTerm() {

        Map<String, Object> paramMap = getParamMap();

        String title = MapUtils.getString(paramMap, "title");
        List<Map> list = dictService.queryEnumTerm(title);

        ResponseData responseData = new ResponseData(list);
        render(responseData);

    }

    /**
     * 插入枚举项
     */
    public void saveEnum(){
        Dict dict = getParamObj(Dict.class);

        dict.setCorpId(WebUtils.getLoginUser().getCorpId());
        dictService.save(dict);

        ResponseData responseData = new ResponseData();
        render(responseData);
    }

    /**
     * 根据code查询dict和dictItem
     */
    public void queryDictAndDictItemByCode(){
        Map<String, Object> paramMap = getParamMap();

        String code = MapUtils.getString(paramMap, "code");
        Map map = dictService.queryDictAndDictItemByCode(code);

        ResponseData responseData = new ResponseData(map);
        render(responseData);
    }

    /**
     * 保存dict和dictItem信息
     */
    public void updateDictAndDictItems(){
        Map<String, Object> paramMap = getParamMap();
        List<Map> dictItemsMap = (List<Map>) paramMap.get("dictItems");
        List<DictItem> dictItems = BeanUtil.convertMapsToBeans(dictItemsMap,DictItem.class);
        Map map = (Map)paramMap.get("dict");
        Dict dict = new Dict();
        BeanUtil.copyPropertiesToBean(dict,map);
        dictService.updateDictAndDictItems(dict,dictItems);

        ResponseData responseData = new ResponseData();
        render(responseData);
    }

}