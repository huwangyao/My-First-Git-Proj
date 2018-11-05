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

import cn.flydiy.hangj.service.HangjTypeCreateService;
import cn.flydiy.hangj.dto.HangjTypeCreateDto;

/**
* 新建类型弹窗控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-14 14:39:23.
*/
@cn.flydiy.core.annotation.WebController
public class HangjTypeCreateController extends BaseController {

    @Autowired
    private HangjTypeCreateService hangjTypeCreateService;
    @Autowired
    private TypeService typeService;

    //保存
    public void saveEntity() {
    HangjTypeCreateDto hangjTypeCreateDto = getParamObj(HangjTypeCreateDto.class);
    hangjTypeCreateService.saveDto(hangjTypeCreateDto);
    super.render(new ResponseData(hangjTypeCreateDto));
    }

    //提交
    public void commitEntity() {
    HangjTypeCreateDto hangjTypeCreateDto = getParamObj(HangjTypeCreateDto.class);
    hangjTypeCreateService.commit(hangjTypeCreateDto);
    super.render(new ResponseData(hangjTypeCreateDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<HangjTypeCreateDto> hangjTypeCreateDtos = BeanUtil.convertMapsToBeans(datas, HangjTypeCreateDto.class);
    hangjTypeCreateService.batchCommit(hangjTypeCreateDtos);
        super.render(new ResponseData(hangjTypeCreateDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  hangjTypeCreateService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    HangjTypeCreateDto hangjTypeCreateDto = getParamObj(HangjTypeCreateDto.class);
    hangjTypeCreateService.updateDto(hangjTypeCreateDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            HangjTypeCreateDto hangjTypeCreateDto = getParamObj(HangjTypeCreateDto.class);
            hangjTypeCreateService.updateDtoNoSaveHistory(hangjTypeCreateDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    hangjTypeCreateService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
