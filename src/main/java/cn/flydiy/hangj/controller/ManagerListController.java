package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import cn.flydiy.metadata.util.MetadataExcelUtil;//行家列表的手工导入的代码;
import java.io.FileInputStream;//行家列表的手工导入的代码;
import java.io.FileNotFoundException;//行家列表的手工导入的代码;
import java.io.IOException;//行家列表的手工导入的代码;
import java.io.InputStream;//行家列表的手工导入的代码;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.ManagerListService;
import cn.flydiy.hangj.dto.ManagerListDto;

/**
* 行家列表控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-14 19:34:32.
*/
@cn.flydiy.core.annotation.WebController
public class ManagerListController extends BaseController {

    @Autowired
    private ManagerListService managerListService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private InfoService infoService;

    //保存
    public void saveEntity() {
    ManagerListDto managerListDto = getParamObj(ManagerListDto.class);
    managerListService.saveDto(managerListDto);
    super.render(new ResponseData(managerListDto));
    }

    //提交
    public void commitEntity() {
    ManagerListDto managerListDto = getParamObj(ManagerListDto.class);
    managerListService.commit(managerListDto);
    super.render(new ResponseData(managerListDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<ManagerListDto> managerListDtos = BeanUtil.convertMapsToBeans(datas, ManagerListDto.class);
    managerListService.batchCommit(managerListDtos);
        super.render(new ResponseData(managerListDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  managerListService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    ManagerListDto managerListDto = getParamObj(ManagerListDto.class);
    managerListService.updateDto(managerListDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            ManagerListDto managerListDto = getParamObj(ManagerListDto.class);
            managerListService.updateDtoNoSaveHistory(managerListDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    managerListService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void dataTableForHangj_managerList() {
        Map param = getParamMap();
        List<Map> result = managerListService.dataTableForHangj_managerList(param);
        super.render(new ResponseData(result));
    }
            //ManagerList的手工代码
                // 查询用户信息
    public void queryStaffInfo(){
        Map param = getParamMap();
        Map result = managerListService.queryStaffInfo(param);
        super.render(new ResponseData(result));
    }

    // 行家首页查询
    public void queryTopicForHomePage(){
        Map param = getParamMap();
        List<Map> result = managerListService.queryTopicForHomePage(param);
        super.render(new ResponseData(result));
    }

    // 查询行家的所有话题
    public void queryExpertWithTopic(){
        Map param = getParamMap();
        Map result = managerListService.queryExpertWithTopic(param);
        super.render(new ResponseData(result));
    }

    public void getResourcesByExcelTopic() {
         try {
             InputStream fileInputStreamInfo = new FileInputStream("C:\\Users\\Administrator\\Desktop\\行家\\行家信息.xlsx");
             InputStream fileInputStreamItem = new FileInputStream("C:\\Users\\Administrator\\Desktop\\行家\\话题信息.xlsx");
             List<Map> maps = MetadataExcelUtil.readExcel(fileInputStreamInfo);
             List<Map> mapsT = MetadataExcelUtil.readExcel(fileInputStreamItem);
             managerListService.getResourcesByExcelTopic(maps,mapsT);
         } catch (FileNotFoundException notFount) {
             System.out.println("------------------------------------------------------------------------\n" + notFount);
         } catch (IOException ioErr) {
             System.out.println("-------------------------------------------------------------------------" + ioErr);
         }catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
             System.out.println("-----------------------------------------------------------------------\n" + e);
         }
     }

          // 查询行家话题数量
    public void getExpertTopicCount(){
        Map param = getParamMap();
        Map result = managerListService.getExpertTopicCount(param);
        super.render(new ResponseData(result));
    }

}
