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

import cn.flydiy.hangj.service.ExpertStepTwoService;
import cn.flydiy.hangj.dto.ExpertStepTwoDto;

/**
* 步骤二控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-12 21:22:02.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertStepTwoController extends BaseController {

    @Autowired
    private ExpertStepTwoService expertStepTwoService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
    ExpertStepTwoDto expertStepTwoDto = getParamObj(ExpertStepTwoDto.class);
    expertStepTwoService.saveDto(expertStepTwoDto);
    super.render(new ResponseData(expertStepTwoDto));
    }

    //提交
    public void commitEntity() {
    ExpertStepTwoDto expertStepTwoDto = getParamObj(ExpertStepTwoDto.class);
    expertStepTwoService.commit(expertStepTwoDto);
    super.render(new ResponseData(expertStepTwoDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ExpertStepTwoDto> expertStepTwoDtos = BeanUtil.convertMapsToBeans(datas, ExpertStepTwoDto.class);
    expertStepTwoService.batchCommit(expertStepTwoDtos);
        super.render(new ResponseData(expertStepTwoDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  expertStepTwoService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ExpertStepTwoDto expertStepTwoDto = getParamObj(ExpertStepTwoDto.class);
    expertStepTwoService.updateDto(expertStepTwoDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ExpertStepTwoDto expertStepTwoDto = getParamObj(ExpertStepTwoDto.class);
            expertStepTwoService.updateDtoNoSaveHistory(expertStepTwoDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    expertStepTwoService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
