package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.InfoService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.ExpertCreateService;
import cn.flydiy.hangj.dto.ExpertCreateDto;

/**
* 新建行家控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-14 11:58:35.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertCreateController extends BaseController {

    @Autowired
    private ExpertCreateService expertCreateService;
    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
    ExpertCreateDto expertCreateDto = getParamObj(ExpertCreateDto.class);
    expertCreateService.saveDto(expertCreateDto);
    super.render(new ResponseData(expertCreateDto));
    }

    //提交
    public void commitEntity() {
    ExpertCreateDto expertCreateDto = getParamObj(ExpertCreateDto.class);
    expertCreateService.commit(expertCreateDto);
    super.render(new ResponseData(expertCreateDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ExpertCreateDto> expertCreateDtos = BeanUtil.convertMapsToBeans(datas, ExpertCreateDto.class);
    expertCreateService.batchCommit(expertCreateDtos);
        super.render(new ResponseData(expertCreateDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  expertCreateService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ExpertCreateDto expertCreateDto = getParamObj(ExpertCreateDto.class);
    expertCreateService.updateDto(expertCreateDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ExpertCreateDto expertCreateDto = getParamObj(ExpertCreateDto.class);
            expertCreateService.updateDtoNoSaveHistory(expertCreateDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    expertCreateService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


            //ExpertCreate的手工代码
                // 自定义行家保存
    public void saveExpert(){
        ExpertCreateDto expertCreateDto = getParamObj(ExpertCreateDto.class);
        expertCreateService.saveExpert(expertCreateDto);
        super.render(new ResponseData(expertCreateDto));
    }

}
