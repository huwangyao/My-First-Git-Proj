package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.TypeService;
import cn.flydiy.hangj.entity.Type;

/**
* 分类管理控制器
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
@cn.flydiy.core.annotation.WebController
public class TypeController extends BaseController {

    @Autowired
    private TypeService typeService;

    //保存
    public void saveEntity() {
        Type type = getParamObj(Type.class);
        typeService.saveEntity(type);
        super.render(new ResponseData(type));
    }

    //批量保存或者更新
    public void batchSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("type");
        List<Type> types = BeanUtil.convertMapsToBeans(listMap, Type.class);
        List<Type> saveArr = new java.util.ArrayList<>();
        List<Type> updateArr = new java.util.ArrayList<>();
        for (Type type : types) {
            if (type.getId() == null) {
            
                saveArr.add(type);
            }else {
                updateArr.add(type);
            }
        }
        typeService.saveEntity(saveArr.toArray(new Type[]{}));
        typeService.updateEntity(updateArr.toArray(new Type[]{}));
        super.render(new ResponseData(types));
    }

    //批量整个保存
    public void batchWholeSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("type");
        List<Type> types = BeanUtil.convertMapsToBeans(listMap, Type.class);
        typeService.saveWhole(types.toArray(new Type[]{}));
        super.render(new ResponseData(types));
    }

    //根据id查询
    public void queryById() {
        Map map = getParamMap();
        Type result =  typeService.findOne(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
    }

    //更新
    public void updateEntity() {
        Type type = getParamObj(Type.class);
        typeService.updateEntity(type);
        super.render(new ResponseData(type));
    }

    public void saveOrUpdate(){
        Type type = getParamObj(Type.class);
        if (type.getId() == null) {
        
            typeService.save(type);
        }else{
            typeService._updateEntityById(type);
        }
        super.render(new ResponseData(type));
    }

    //根据id删除
    public void deleteById() {
        Map map = getParamMap();
        typeService.delete(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
    }

    //批量删除
    public void batchDelete(){
        Map map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        typeService.delete(ids.toArray(new String[]{}));
        super.render(new ResponseData());
    }

    public void queryByParam(){
        Type condition = getParamObj(Type.class);
        List<Type> result = typeService.queryByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryPageByParam(){
        Type condition = getParamObj(Type.class);
        List<Type> result = typeService.queryPageByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryByIds(){
        Map<String, Object> map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        List<Type> result = typeService.findByIds(ids.toArray(new String[]{}));
        super.render(new ResponseData(result));
    }

    public void updateByParam(){
        Type updateParam = getParamObj(Type.class);
        typeService.updateByParam(updateParam);
        super.render(new ResponseData());
    }


}
