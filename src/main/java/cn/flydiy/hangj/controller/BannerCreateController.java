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

import cn.flydiy.hangj.service.BannerCreateService;
import cn.flydiy.hangj.dto.BannerCreateDto;

/**
* banner新增控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:39.
*/
@cn.flydiy.core.annotation.WebController
public class BannerCreateController extends BaseController {

    @Autowired
    private BannerCreateService bannerCreateService;
    @Autowired
    private BannerConfigService bannerConfigService;

    //保存
    public void saveEntity() {
    BannerCreateDto bannerCreateDto = getParamObj(BannerCreateDto.class);
    bannerCreateService.saveDto(bannerCreateDto);
    super.render(new ResponseData(bannerCreateDto));
    }

    //提交
    public void commitEntity() {
    BannerCreateDto bannerCreateDto = getParamObj(BannerCreateDto.class);
    bannerCreateService.commit(bannerCreateDto);
    super.render(new ResponseData(bannerCreateDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<BannerCreateDto> bannerCreateDtos = BeanUtil.convertMapsToBeans(datas, BannerCreateDto.class);
    bannerCreateService.batchCommit(bannerCreateDtos);
        super.render(new ResponseData(bannerCreateDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  bannerCreateService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    BannerCreateDto bannerCreateDto = getParamObj(BannerCreateDto.class);
    bannerCreateService.updateDto(bannerCreateDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            BannerCreateDto bannerCreateDto = getParamObj(BannerCreateDto.class);
            bannerCreateService.updateDtoNoSaveHistory(bannerCreateDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    bannerCreateService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
