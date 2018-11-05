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

import cn.flydiy.hangj.service.ExpertDetailService;
import cn.flydiy.hangj.dto.ExpertDetailDto;

/**
* 详情控制器
* Modify by v_wyaohu on 2018-8-21 16:14:42.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertDetailController extends BaseController {

    @Autowired
    private ExpertDetailService expertDetailService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
    ExpertDetailDto expertDetailDto = getParamObj(ExpertDetailDto.class);
    expertDetailService.saveDto(expertDetailDto);
    super.render(new ResponseData(expertDetailDto));
    }

    //提交
    public void commitEntity() {
    ExpertDetailDto expertDetailDto = getParamObj(ExpertDetailDto.class);
    expertDetailService.commit(expertDetailDto);
    super.render(new ResponseData(expertDetailDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ExpertDetailDto> expertDetailDtos = BeanUtil.convertMapsToBeans(datas, ExpertDetailDto.class);
    expertDetailService.batchCommit(expertDetailDtos);
        super.render(new ResponseData(expertDetailDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  expertDetailService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ExpertDetailDto expertDetailDto = getParamObj(ExpertDetailDto.class);
    expertDetailService.updateDto(expertDetailDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ExpertDetailDto expertDetailDto = getParamObj(ExpertDetailDto.class);
            expertDetailService.updateDtoNoSaveHistory(expertDetailDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    expertDetailService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
