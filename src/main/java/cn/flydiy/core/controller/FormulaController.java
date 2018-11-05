package cn.flydiy.core.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.entity.Formula;
import cn.flydiy.core.service.FormulaService;
import cn.flydiy.core.web.ResponseData;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by player on 2017/10/26.
 */
@WebController
public class FormulaController extends BaseController{

    @Autowired
    private FormulaService formulaService;

    public void saveEntity(){
        Formula paramObj = getParamObj(Formula.class);
        formulaService.save(paramObj);
        render(new ResponseData());
    }

    public void updateEntity(){
        Formula paramObj = getParamObj(Formula.class);
        formulaService._updateEntityById(paramObj);
        render(new ResponseData());
    }

    public void deleteById(){
        Map<String, Object> paramMap = getParamMap();
        String id = MapUtils.getString(paramMap, "id");
        formulaService._deleteByIds(id);
        render(new ResponseData());
    }

    public void queryByTableCode(){
        Map<String, Object> paramMap = getParamMap();
        String tableCode = MapUtils.getString(paramMap, "tableCode");
        List<Formula> formulas = formulaService.queryByTableCode(tableCode);
        render(new ResponseData(formulas));
    }
}
