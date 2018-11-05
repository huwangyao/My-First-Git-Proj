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

import cn.flydiy.hangj.service.HomePageService;
import cn.flydiy.hangj.dto.HomePageDto;

/**
* 首页控制器
* Modify by v_sunlli(李小阳) on 2018-8-14 15:17:23.
*/
@cn.flydiy.core.annotation.WebController
public class HomePageController extends BaseController {

    @Autowired
    private HomePageService homePageService;
    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
    HomePageDto homePageDto = getParamObj(HomePageDto.class);
    homePageService.saveDto(homePageDto);
    super.render(new ResponseData(homePageDto));
    }

    //提交
    public void commitEntity() {
    HomePageDto homePageDto = getParamObj(HomePageDto.class);
    homePageService.commit(homePageDto);
    super.render(new ResponseData(homePageDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<HomePageDto> homePageDtos = BeanUtil.convertMapsToBeans(datas, HomePageDto.class);
    homePageService.batchCommit(homePageDtos);
        super.render(new ResponseData(homePageDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  homePageService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    HomePageDto homePageDto = getParamObj(HomePageDto.class);
    homePageService.updateDto(homePageDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            HomePageDto homePageDto = getParamObj(HomePageDto.class);
            homePageService.updateDtoNoSaveHistory(homePageDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    homePageService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void dataTableForHangj_homePage() {
        List<Map> result = homePageService.dataTableForHangj_homePage(getParamObj(cn.flydiy.hangj.entity.Info.class));
        super.render(new ResponseData(result));
    }

}
