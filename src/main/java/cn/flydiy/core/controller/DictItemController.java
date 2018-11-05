package cn.flydiy.core.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.service.DictItemService;
import cn.flydiy.core.web.ResponseData;
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
public class DictItemController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    DictItemService dictItemService;

    /**
     * 根据枚举项的code查询对应的枚举值,当有搜索条件时加上搜索条件title
     */
    public void queryEnumItemByEnumCode() {

        Map<String, Object> paramMap = getParamMap();
        String value = MapUtils.getString(paramMap, "value");
        String code = MapUtils.getString(paramMap, "code");
        String title = MapUtils.getString(paramMap, "title");

        List<Map> list = dictItemService.queryEnumItemByEnumCode(value,code, title);

        ResponseData responseData = new ResponseData(list);

        render(responseData);

    }

    /**
     * 根据字典项的code数组查询对应的枚举值,封装成map返回
     */
    public void queryDictItemListByDictCodes(){
        Map<String, Object> paramMap = getParamMap();
        List<String> codeList = (List<String>)paramMap.get("codes");
        String[] codes = codeList.toArray(new String[]{});

        Map map = dictItemService.queryDictItemListByDictCodes(codes);

        ResponseData responseData = new ResponseData(map);

        render(responseData);
    }
}