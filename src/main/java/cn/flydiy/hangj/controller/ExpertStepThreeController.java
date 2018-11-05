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

import cn.flydiy.hangj.service.ExpertStepThreeService;
import cn.flydiy.hangj.dto.ExpertStepThreeDto;

/**
* 步骤三控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-12 21:21:40.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertStepThreeController extends BaseController {

    @Autowired
    private ExpertStepThreeService expertStepThreeService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
    ExpertStepThreeDto expertStepThreeDto = getParamObj(ExpertStepThreeDto.class);
    expertStepThreeService.saveDto(expertStepThreeDto);
    super.render(new ResponseData(expertStepThreeDto));
    }

    //提交
    public void commitEntity() {
    ExpertStepThreeDto expertStepThreeDto = getParamObj(ExpertStepThreeDto.class);
    expertStepThreeService.commit(expertStepThreeDto);
    super.render(new ResponseData(expertStepThreeDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ExpertStepThreeDto> expertStepThreeDtos = BeanUtil.convertMapsToBeans(datas, ExpertStepThreeDto.class);
    expertStepThreeService.batchCommit(expertStepThreeDtos);
        super.render(new ResponseData(expertStepThreeDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  expertStepThreeService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ExpertStepThreeDto expertStepThreeDto = getParamObj(ExpertStepThreeDto.class);
    expertStepThreeService.updateDto(expertStepThreeDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ExpertStepThreeDto expertStepThreeDto = getParamObj(ExpertStepThreeDto.class);
            expertStepThreeService.updateDtoNoSaveHistory(expertStepThreeDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    expertStepThreeService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
