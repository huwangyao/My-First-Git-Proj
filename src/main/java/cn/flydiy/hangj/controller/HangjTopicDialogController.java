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

import cn.flydiy.hangj.service.HangjTopicDialogService;
import cn.flydiy.hangj.dto.HangjTopicDialogDto;

/**
* 行家话题信息弹窗控制器
* Modify by v_sunlli on 2018-8-23 11:19:55.
*/
@cn.flydiy.core.annotation.WebController
public class HangjTopicDialogController extends BaseController {

    @Autowired
    private HangjTopicDialogService hangjTopicDialogService;
    @Autowired
    private TopicService topicService;

    //保存
    public void saveEntity() {
    HangjTopicDialogDto hangjTopicDialogDto = getParamObj(HangjTopicDialogDto.class);
    hangjTopicDialogService.saveDto(hangjTopicDialogDto);
    super.render(new ResponseData(hangjTopicDialogDto));
    }

    //提交
    public void commitEntity() {
    HangjTopicDialogDto hangjTopicDialogDto = getParamObj(HangjTopicDialogDto.class);
    hangjTopicDialogService.commit(hangjTopicDialogDto);
    super.render(new ResponseData(hangjTopicDialogDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<HangjTopicDialogDto> hangjTopicDialogDtos = BeanUtil.convertMapsToBeans(datas, HangjTopicDialogDto.class);
    hangjTopicDialogService.batchCommit(hangjTopicDialogDtos);
        super.render(new ResponseData(hangjTopicDialogDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  hangjTopicDialogService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    HangjTopicDialogDto hangjTopicDialogDto = getParamObj(HangjTopicDialogDto.class);
    hangjTopicDialogService.updateDto(hangjTopicDialogDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            HangjTopicDialogDto hangjTopicDialogDto = getParamObj(HangjTopicDialogDto.class);
            hangjTopicDialogService.updateDtoNoSaveHistory(hangjTopicDialogDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    hangjTopicDialogService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void queryHangjTypeForHangj_hangjTopicDialog() {
        List<Map> result = hangjTopicDialogService.queryHangjTypeForHangj_hangjTopicDialog(getParamObj(cn.flydiy.hangj.entity.Type.class));
        super.render(new ResponseData(result));
    }

}
