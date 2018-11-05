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

import cn.flydiy.hangj.service.HangjTypeEditService;
import cn.flydiy.hangj.dto.HangjTypeEditDto;

/**
* 编辑类型弹窗控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-14 14:39:11.
*/
@cn.flydiy.core.annotation.WebController
public class HangjTypeEditController extends BaseController {

    @Autowired
    private HangjTypeEditService hangjTypeEditService;
    @Autowired
    private TypeService typeService;

    //保存
    public void saveEntity() {
    HangjTypeEditDto hangjTypeEditDto = getParamObj(HangjTypeEditDto.class);
    hangjTypeEditService.saveDto(hangjTypeEditDto);
    super.render(new ResponseData(hangjTypeEditDto));
    }

    //提交
    public void commitEntity() {
    HangjTypeEditDto hangjTypeEditDto = getParamObj(HangjTypeEditDto.class);
    hangjTypeEditService.commit(hangjTypeEditDto);
    super.render(new ResponseData(hangjTypeEditDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<HangjTypeEditDto> hangjTypeEditDtos = BeanUtil.convertMapsToBeans(datas, HangjTypeEditDto.class);
    hangjTypeEditService.batchCommit(hangjTypeEditDtos);
        super.render(new ResponseData(hangjTypeEditDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  hangjTypeEditService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    HangjTypeEditDto hangjTypeEditDto = getParamObj(HangjTypeEditDto.class);
    hangjTypeEditService.updateDto(hangjTypeEditDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            HangjTypeEditDto hangjTypeEditDto = getParamObj(HangjTypeEditDto.class);
            hangjTypeEditService.updateDtoNoSaveHistory(hangjTypeEditDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    hangjTypeEditService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
