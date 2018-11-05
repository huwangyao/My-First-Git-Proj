package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.entity.Info;

/**
* 行家信息控制器
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
@cn.flydiy.core.annotation.WebController
public class InfoController extends BaseController {

    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
        Info info = getParamObj(Info.class);
        infoService.saveEntity(info);
        super.render(new ResponseData(info));
    }

    //批量保存或者更新
    public void batchSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("info");
        List<Info> infos = BeanUtil.convertMapsToBeans(listMap, Info.class);
        List<Info> saveArr = new java.util.ArrayList<>();
        List<Info> updateArr = new java.util.ArrayList<>();
        for (Info info : infos) {
            if (info.getId() == null) {
            
                saveArr.add(info);
            }else {
                updateArr.add(info);
            }
        }
        infoService.saveEntity(saveArr.toArray(new Info[]{}));
        infoService.updateEntity(updateArr.toArray(new Info[]{}));
        super.render(new ResponseData(infos));
    }

    //批量整个保存
    public void batchWholeSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("info");
        List<Info> infos = BeanUtil.convertMapsToBeans(listMap, Info.class);
        infoService.saveWhole(infos.toArray(new Info[]{}));
        super.render(new ResponseData(infos));
    }

    //根据id查询
    public void queryById() {
        Map map = getParamMap();
        Info result =  infoService.findOne(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
    }

    //更新
    public void updateEntity() {
        Info info = getParamObj(Info.class);
        infoService.updateEntity(info);
        super.render(new ResponseData(info));
    }

    public void saveOrUpdate(){
        Info info = getParamObj(Info.class);
        if (info.getId() == null) {
        
            infoService.save(info);
        }else{
            infoService._updateEntityById(info);
        }
        super.render(new ResponseData(info));
    }

    //根据id删除
    public void deleteById() {
        Map map = getParamMap();
        infoService.delete(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
    }

    //批量删除
    public void batchDelete(){
        Map map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        infoService.delete(ids.toArray(new String[]{}));
        super.render(new ResponseData());
    }

    public void queryByParam(){
        Info condition = getParamObj(Info.class);
        List<Info> result = infoService.queryByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryPageByParam(){
        Info condition = getParamObj(Info.class);
        List<Info> result = infoService.queryPageByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryByIds(){
        Map<String, Object> map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        List<Info> result = infoService.findByIds(ids.toArray(new String[]{}));
        super.render(new ResponseData(result));
    }

    public void updateByParam(){
        Info updateParam = getParamObj(Info.class);
        infoService.updateByParam(updateParam);
        super.render(new ResponseData());
    }


}
