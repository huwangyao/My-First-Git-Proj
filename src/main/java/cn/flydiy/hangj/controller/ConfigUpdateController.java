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

import cn.flydiy.hangj.service.ConfigUpdateService;
import cn.flydiy.hangj.dto.ConfigUpdateDto;

/**
* banner编辑控制器
* Modify by v_wyaohu(胡王耀) on 2018-5-31 16:28:34.
*/
@cn.flydiy.core.annotation.WebController
public class ConfigUpdateController extends BaseController {

    @Autowired
    private ConfigUpdateService configUpdateService;
    @Autowired
    private BannerConfigService bannerConfigService;

    //保存
    public void saveEntity() {
    ConfigUpdateDto configUpdateDto = getParamObj(ConfigUpdateDto.class);
    configUpdateService.saveDto(configUpdateDto);
    super.render(new ResponseData(configUpdateDto));
    }

    //提交
    public void commitEntity() {
    ConfigUpdateDto configUpdateDto = getParamObj(ConfigUpdateDto.class);
    configUpdateService.commit(configUpdateDto);
    super.render(new ResponseData(configUpdateDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ConfigUpdateDto> configUpdateDtos = BeanUtil.convertMapsToBeans(datas, ConfigUpdateDto.class);
    configUpdateService.batchCommit(configUpdateDtos);
        super.render(new ResponseData(configUpdateDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  configUpdateService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ConfigUpdateDto configUpdateDto = getParamObj(ConfigUpdateDto.class);
    configUpdateService.updateDto(configUpdateDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ConfigUpdateDto configUpdateDto = getParamObj(ConfigUpdateDto.class);
            configUpdateService.updateDtoNoSaveHistory(configUpdateDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    configUpdateService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
