package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.TopicCreateDialogService;
import cn.flydiy.hangj.dto.TopicCreateDialogDto;

/**
* 新建话题弹窗控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-19 12:00:21.
*/
@cn.flydiy.core.annotation.WebController
public class TopicCreateDialogController extends BaseController {

    @Autowired
    private TopicCreateDialogService topicCreateDialogService;
    @Autowired
    private TopicService topicService;

    //保存
    public void saveEntity() {
    TopicCreateDialogDto topicCreateDialogDto = getParamObj(TopicCreateDialogDto.class);
    topicCreateDialogService.saveDto(topicCreateDialogDto);
    super.render(new ResponseData(topicCreateDialogDto));
    }

    //提交
    public void commitEntity() {
    TopicCreateDialogDto topicCreateDialogDto = getParamObj(TopicCreateDialogDto.class);
    topicCreateDialogService.commit(topicCreateDialogDto);
    super.render(new ResponseData(topicCreateDialogDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<TopicCreateDialogDto> topicCreateDialogDtos = BeanUtil.convertMapsToBeans(datas, TopicCreateDialogDto.class);
    topicCreateDialogService.batchCommit(topicCreateDialogDtos);
        super.render(new ResponseData(topicCreateDialogDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  topicCreateDialogService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    TopicCreateDialogDto topicCreateDialogDto = getParamObj(TopicCreateDialogDto.class);
    topicCreateDialogService.updateDto(topicCreateDialogDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            TopicCreateDialogDto topicCreateDialogDto = getParamObj(TopicCreateDialogDto.class);
            topicCreateDialogService.updateDtoNoSaveHistory(topicCreateDialogDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    topicCreateDialogService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void queryHangjTypeForHangj_topicCreateDialog() {
        List<Map> result = topicCreateDialogService.queryHangjTypeForHangj_topicCreateDialog(getParamObj(cn.flydiy.hangj.entity.Type.class));
        super.render(new ResponseData(result));
    }

}
