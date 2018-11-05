package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.ExpertEditService;
import cn.flydiy.hangj.dto.ExpertEditDto;

/**
* 行家编辑控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-14 14:52:38.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertEditController extends BaseController {

    @Autowired
    private ExpertEditService expertEditService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private TopicService topicService;

    //保存
    public void saveEntity() {
    ExpertEditDto expertEditDto = getParamObj(ExpertEditDto.class);
    expertEditService.saveDto(expertEditDto);
    super.render(new ResponseData(expertEditDto));
    }

    //提交
    public void commitEntity() {
    ExpertEditDto expertEditDto = getParamObj(ExpertEditDto.class);
    expertEditService.commit(expertEditDto);
    super.render(new ResponseData(expertEditDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ExpertEditDto> expertEditDtos = BeanUtil.convertMapsToBeans(datas, ExpertEditDto.class);
    expertEditService.batchCommit(expertEditDtos);
        super.render(new ResponseData(expertEditDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  expertEditService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ExpertEditDto expertEditDto = getParamObj(ExpertEditDto.class);
    expertEditService.updateDto(expertEditDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ExpertEditDto expertEditDto = getParamObj(ExpertEditDto.class);
            expertEditService.updateDtoNoSaveHistory(expertEditDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    expertEditService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void dataTableForHangj_expertEdit() {
        List<Map> result = expertEditService.dataTableForHangj_expertEdit(getParamObj(cn.flydiy.hangj.entity.Topic.class));
        super.render(new ResponseData(result));
    }
    //   
    public void queryHangjTypeForHangj_expertEdit() {
        List<Map> result = expertEditService.queryHangjTypeForHangj_expertEdit(getParamObj(cn.flydiy.hangj.entity.Type.class));
        super.render(new ResponseData(result));
    }

}
