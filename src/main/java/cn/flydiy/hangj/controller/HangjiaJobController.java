package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.OrderService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.HangjiaJobService;
import cn.flydiy.hangj.dto.HangjiaJobDto;

/**
* 定时任务代码(勿删)控制器
* Modify by v_wyaohu on 2018-8-31 17:45:35.
*/
@cn.flydiy.core.annotation.WebController
public class HangjiaJobController extends BaseController {

    @Autowired
    private HangjiaJobService hangjiaJobService;
    @Autowired
    private OrderService orderService;

    //保存
    public void saveEntity() {
    HangjiaJobDto hangjiaJobDto = getParamObj(HangjiaJobDto.class);
    hangjiaJobService.saveDto(hangjiaJobDto);
    super.render(new ResponseData(hangjiaJobDto));
    }

    //提交
    public void commitEntity() {
    HangjiaJobDto hangjiaJobDto = getParamObj(HangjiaJobDto.class);
    hangjiaJobService.commit(hangjiaJobDto);
    super.render(new ResponseData(hangjiaJobDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<HangjiaJobDto> hangjiaJobDtos = BeanUtil.convertMapsToBeans(datas, HangjiaJobDto.class);
    hangjiaJobService.batchCommit(hangjiaJobDtos);
        super.render(new ResponseData(hangjiaJobDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  hangjiaJobService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    HangjiaJobDto hangjiaJobDto = getParamObj(HangjiaJobDto.class);
    hangjiaJobService.updateDto(hangjiaJobDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            HangjiaJobDto hangjiaJobDto = getParamObj(HangjiaJobDto.class);
            hangjiaJobService.updateDtoNoSaveHistory(hangjiaJobDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    hangjiaJobService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
