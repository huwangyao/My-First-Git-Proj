package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.TypeService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.TypeManagerService;
import cn.flydiy.hangj.dto.TypeManagerDto;

/**
* 分类管理控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-20 12:00:41.
*/
@cn.flydiy.core.annotation.WebController
public class TypeManagerController extends BaseController {

    @Autowired
    private TypeManagerService typeManagerService;
    @Autowired
    private TypeService typeService;

    //保存
    public void saveEntity() {
    TypeManagerDto typeManagerDto = getParamObj(TypeManagerDto.class);
    typeManagerService.saveDto(typeManagerDto);
    super.render(new ResponseData(typeManagerDto));
    }

    //提交
    public void commitEntity() {
    TypeManagerDto typeManagerDto = getParamObj(TypeManagerDto.class);
    typeManagerService.commit(typeManagerDto);
    super.render(new ResponseData(typeManagerDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<TypeManagerDto> typeManagerDtos = BeanUtil.convertMapsToBeans(datas, TypeManagerDto.class);
    typeManagerService.batchCommit(typeManagerDtos);
        super.render(new ResponseData(typeManagerDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  typeManagerService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    TypeManagerDto typeManagerDto = getParamObj(TypeManagerDto.class);
    typeManagerService.updateDto(typeManagerDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            TypeManagerDto typeManagerDto = getParamObj(TypeManagerDto.class);
            typeManagerService.updateDtoNoSaveHistory(typeManagerDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    typeManagerService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void dataTableForHangj_typeManager() {
        Map param = getParamMap();
        List<Map> result = typeManagerService.dataTableForHangj_typeManager(param);
        super.render(new ResponseData(result));
    }

}
