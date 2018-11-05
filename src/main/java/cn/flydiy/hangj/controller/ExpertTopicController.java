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

import cn.flydiy.hangj.service.ExpertTopicService;
import cn.flydiy.hangj.dto.ExpertTopicDto;

/**
* 添加话题控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-12 21:22:22.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertTopicController extends BaseController {

    @Autowired
    private ExpertTopicService expertTopicService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
    ExpertTopicDto expertTopicDto = getParamObj(ExpertTopicDto.class);
    expertTopicService.saveDto(expertTopicDto);
    super.render(new ResponseData(expertTopicDto));
    }

    //提交
    public void commitEntity() {
    ExpertTopicDto expertTopicDto = getParamObj(ExpertTopicDto.class);
    expertTopicService.commit(expertTopicDto);
    super.render(new ResponseData(expertTopicDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ExpertTopicDto> expertTopicDtos = BeanUtil.convertMapsToBeans(datas, ExpertTopicDto.class);
    expertTopicService.batchCommit(expertTopicDtos);
        super.render(new ResponseData(expertTopicDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  expertTopicService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ExpertTopicDto expertTopicDto = getParamObj(ExpertTopicDto.class);
    expertTopicService.updateDto(expertTopicDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ExpertTopicDto expertTopicDto = getParamObj(ExpertTopicDto.class);
            expertTopicService.updateDtoNoSaveHistory(expertTopicDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    expertTopicService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
