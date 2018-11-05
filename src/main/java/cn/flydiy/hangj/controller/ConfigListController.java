package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.BannerConfigService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.ConfigListService;
import cn.flydiy.hangj.dto.ConfigListDto;

/**
* banner配置列表控制器
* Modify by v_wyaohu(胡王耀) on 2018-5-31 15:00:27.
*/
@cn.flydiy.core.annotation.WebController
public class ConfigListController extends BaseController {

    @Autowired
    private ConfigListService configListService;
    @Autowired
    private BannerConfigService bannerConfigService;

    //保存
    public void saveEntity() {
    ConfigListDto configListDto = getParamObj(ConfigListDto.class);
    configListService.saveDto(configListDto);
    super.render(new ResponseData(configListDto));
    }

    //提交
    public void commitEntity() {
    ConfigListDto configListDto = getParamObj(ConfigListDto.class);
    configListService.commit(configListDto);
    super.render(new ResponseData(configListDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ConfigListDto> configListDtos = BeanUtil.convertMapsToBeans(datas, ConfigListDto.class);
    configListService.batchCommit(configListDtos);
        super.render(new ResponseData(configListDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  configListService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ConfigListDto configListDto = getParamObj(ConfigListDto.class);
    configListService.updateDto(configListDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ConfigListDto configListDto = getParamObj(ConfigListDto.class);
            configListService.updateDtoNoSaveHistory(configListDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    configListService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void dataTableForHangj_configList() {
        List<Map> result = configListService.dataTableForHangj_configList(getParamObj(cn.flydiy.hangj.entity.BannerConfig.class));
        super.render(new ResponseData(result));
    }

}
