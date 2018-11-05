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

import cn.flydiy.hangj.service.ExpertStepOneService;
import cn.flydiy.hangj.dto.ExpertStepOneDto;

/**
* 步骤一控制器
* Modify by v_sunlli on 2018-8-22 14:57:07.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertStepOneController extends BaseController {

    @Autowired
    private ExpertStepOneService expertStepOneService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private TopicService topicService;

    //保存
    public void saveEntity() {
    ExpertStepOneDto expertStepOneDto = getParamObj(ExpertStepOneDto.class);
    expertStepOneService.saveDto(expertStepOneDto);
    super.render(new ResponseData(expertStepOneDto));
    }

    //提交
    public void commitEntity() {
    ExpertStepOneDto expertStepOneDto = getParamObj(ExpertStepOneDto.class);
    expertStepOneService.commit(expertStepOneDto);
    super.render(new ResponseData(expertStepOneDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ExpertStepOneDto> expertStepOneDtos = BeanUtil.convertMapsToBeans(datas, ExpertStepOneDto.class);
    expertStepOneService.batchCommit(expertStepOneDtos);
        super.render(new ResponseData(expertStepOneDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  expertStepOneService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ExpertStepOneDto expertStepOneDto = getParamObj(ExpertStepOneDto.class);
    expertStepOneService.updateDto(expertStepOneDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ExpertStepOneDto expertStepOneDto = getParamObj(ExpertStepOneDto.class);
            expertStepOneService.updateDtoNoSaveHistory(expertStepOneDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    expertStepOneService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
