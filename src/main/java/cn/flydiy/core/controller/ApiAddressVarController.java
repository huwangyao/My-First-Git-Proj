package cn.flydiy.core.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.entity.ApiAddressVar;
import cn.flydiy.core.service.ApiAddressVarService;
import cn.flydiy.core.web.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by player on 2018/5/11.
 */
@WebController
public class ApiAddressVarController extends BaseController{

    @Autowired
    private ApiAddressVarService apiAddressVarService;

    public void batchSave(){
        Map<String, Object> paramMap = getParamMap();
        List<Map> addressVars = (List<Map>) paramMap.get("addressVars");
        List<ApiAddressVar> apiAddressVars = BeanUtil.convertMapsToBeans(addressVars, ApiAddressVar.class);
        apiAddressVarService.batchSave(apiAddressVars);
        render(new ResponseData(apiAddressVars));
    }

    public void queryAll(){
        List<ApiAddressVar> apiAddressVars = apiAddressVarService.queryAll();
        render(new ResponseData(apiAddressVars));
    }
}
