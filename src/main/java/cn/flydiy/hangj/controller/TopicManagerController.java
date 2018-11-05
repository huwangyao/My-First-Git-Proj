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

import cn.flydiy.hangj.service.TopicManagerService;
import cn.flydiy.hangj.dto.TopicManagerDto;

/**
* 话题管理控制器
* Modify by v_sunlli on 2018-8-23 11:22:41.
*/
@cn.flydiy.core.annotation.WebController
public class TopicManagerController extends BaseController {

    @Autowired
    private TopicManagerService topicManagerService;
    @Autowired
    private cn.flydiy.hangj.service.TypeService typeService;//话题管理的手工注入的代码;
    @Autowired
    private TopicService topicService;
    @Autowired
    private cn.flydiy.hangj.service.InfoService infoService;//话题管理的手工注入的代码;

    //保存
    public void saveEntity() {
    TopicManagerDto topicManagerDto = getParamObj(TopicManagerDto.class);
    topicManagerService.saveDto(topicManagerDto);
    super.render(new ResponseData(topicManagerDto));
    }

    //提交
    public void commitEntity() {
    TopicManagerDto topicManagerDto = getParamObj(TopicManagerDto.class);
    topicManagerService.commit(topicManagerDto);
    super.render(new ResponseData(topicManagerDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<TopicManagerDto> topicManagerDtos = BeanUtil.convertMapsToBeans(datas, TopicManagerDto.class);
    topicManagerService.batchCommit(topicManagerDtos);
        super.render(new ResponseData(topicManagerDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  topicManagerService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    TopicManagerDto topicManagerDto = getParamObj(TopicManagerDto.class);
    topicManagerService.updateDto(topicManagerDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            TopicManagerDto topicManagerDto = getParamObj(TopicManagerDto.class);
            topicManagerService.updateDtoNoSaveHistory(topicManagerDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    topicManagerService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void queryHangjTypeForHangj_topicManager() {
        List<Map> result = topicManagerService.queryHangjTypeForHangj_topicManager(getParamObj(cn.flydiy.hangj.entity.Type.class));
        super.render(new ResponseData(result));
    }
    //   
    public void queryHangjType3ForHangj_topicManager() {
        List<Map> result = topicManagerService.queryHangjType3ForHangj_topicManager(getParamObj(cn.flydiy.hangj.entity.Type.class));
        super.render(new ResponseData(result));
    }
    //   
    public void dataTableForHangj_topicManager() {
        Map param = getParamMap();
        List<Map> result = topicManagerService.dataTableForHangj_topicManager(param);
        super.render(new ResponseData(result));
    }
public void queryHangjInfo2ForHangj_topicManager() throws java.io.IOException {
    Map param = getParamMap();
    List<Map> result = topicManagerService.queryHangjInfo2ForHangj_topicManager(param);
    super.render(new ResponseData(result));
}
            //TopicManager的手工代码
                //OrderList的手工代码
    public void topicNameAndNo() throws java.io.IOException {
        Map param = getParamMap();
        // 查询所有的话题
        cn.flydiy.hangj.entity.Topic topicParam = new cn.flydiy.hangj.entity.Topic();
        topicParam.setStatus("已审核");
        List<cn.flydiy.hangj.entity.Topic> result = topicService.queryByParams(topicParam);
        // 查询所有的行家
        cn.flydiy.hangj.entity.Info infoParam = new cn.flydiy.hangj.entity.Info();
        List<cn.flydiy.hangj.entity.Info> infoResult = infoService.queryByParams(infoParam);
        // 导出的数据
        List<Map> data = new java.util.ArrayList();
        for(cn.flydiy.hangj.entity.Topic topicObj : result){
            Map obj = new java.util.LinkedHashMap();
            String infoId = topicObj.getInfoId();
            obj.put("id",topicObj.getId());
            obj.put("行家名称","-");
            for(cn.flydiy.hangj.entity.Info infoObj:infoResult){
                String id = infoObj.getId();
                if(cn.flydiy.common.util.StringUtil.equalsIgnoreCase(id,infoId)){
                    obj.put("行家名称",infoObj.getName());
                    break;
                }
            }
            obj.put("话题名称",topicObj.getName());
            obj.put("序号",topicObj.getNo());
            data.add(obj);
        }

        java.io.ByteArrayOutputStream output =  cn.flydiy.metadata.util.MetadataExcelUtil.exportTableDataExcel(data);
        cn.flydiy.core.web.WebUtils.renderDownload(output.toByteArray(),"hangj_topic_sort.xlsx");
    }

    // 更新行家话题排序
public void updateTopicSort(){
    Map param = getParamMap();
    topicManagerService.updateTopicSort(param);
    super.render(new ResponseData());
}

    // 导出已审核的话题数据
    public void getTopicInfo() throws java.io.IOException {
        Map param = getParamMap();
        // 查询所有的话题
        cn.flydiy.hangj.entity.Topic topicParam = new cn.flydiy.hangj.entity.Topic();
        topicParam.setStatus("已审核");
        List<cn.flydiy.hangj.entity.Topic> result = topicService.queryByParams(topicParam);
        // 查询所有的行家
        cn.flydiy.hangj.entity.Info infoParam = new cn.flydiy.hangj.entity.Info();
        List<cn.flydiy.hangj.entity.Info> infoResult = infoService.queryByParams(infoParam);
        // 查询所有的话题分类
        cn.flydiy.hangj.entity.Type typeParam = new cn.flydiy.hangj.entity.Type();
        List<cn.flydiy.hangj.entity.Type> typeResult = typeService.queryByParams(typeParam);
        // 导出的数据
        List<Map> data = new java.util.ArrayList();
        for(cn.flydiy.hangj.entity.Topic topicObj : result){
            Map obj = new java.util.LinkedHashMap();
            String infoId = topicObj.getInfoId();
            // 遍历行家信息
            for(cn.flydiy.hangj.entity.Info infoObj:infoResult){
                String id = infoObj.getId();
                // 找到对应的行家信息
                if(cn.flydiy.common.util.StringUtil.equalsIgnoreCase(id,infoId)){
                    obj.put("序列",topicObj.getNo());    // 1
                    // 记录的行家名称
                    String name = infoObj.getName();
                    int index = -2;
                    index = name.indexOf("(");
                    if(index>-1){
                        obj.put("英文名",name.substring(0,index));   // 2
                    }else{
                        obj.put("英文名",name);   // 2
                    }
                    obj.put("城市",infoObj.getAddress());  // 3
                    obj.put("职业titile",infoObj.getTag()); // 4
                    String typeId = topicObj.getType();
                    obj.put("分类",typeId);     //5
                    // 遍历所有的话题类型
                    for(cn.flydiy.hangj.entity.Type typeObj : typeResult){
                        // 找到话题对应的类型
                        if(cn.flydiy.common.util.StringUtil.equalsIgnoreCase(typeId,typeObj.getId())){
                            obj.put("分类",typeObj.getName());     // 5
                            break;
                        }
                    }
                    // 职业经历
                    String career = infoObj.getCareer();
                    obj.put("职业经历",this.dealEditorContent(career));    // 6
                    // 项目经历
                    String projectExp = infoObj.getProjectExp();
                    obj.put("项目经历",this.dealEditorContent(projectExp));    // 7
                    // 所获奖项
                    String awards = infoObj.getAwards();
                    obj.put("所获奖项",this.dealEditorContent(awards));    // 8
                    // 话题
                    obj.put("话题",topicObj.getName());    // 9
                    // 要点
                    String topicOutline = topicObj.getTopicOutline();
                    obj.put("要点",this.dealEditorContent(topicOutline));    // 10
                    // 该领域资历或成就(选填)
                    String relevantExp = infoObj.getAwards();
                    obj.put("该领域资历或成就(选填)",this.dealEditorContent(relevantExp));    // 11
                    break;
                }
            }
            data.add(obj);
        }

        java.io.ByteArrayOutputStream output =  cn.flydiy.metadata.util.MetadataExcelUtil.exportTableDataExcel(data);
        cn.flydiy.core.web.WebUtils.renderDownload(output.toByteArray(),"hangj_topic_info.xlsx");
    }

    // 处理html标签
    public String dealEditorContent(String str){
        String shortLine = "";
        if(cn.flydiy.common.util.StringUtil.isNotEmpty(str)){
            if(str.lastIndexOf("</p>")>=0){
                shortLine = str.replaceAll("</p>"," ");   // 用空格替换
                shortLine = shortLine.replaceAll("<.*?>","");   // 用空格替换
            }else{
                shortLine = str.replaceAll("</p>"," ");   // 用空格替换
                shortLine = shortLine.replaceAll("<.*?>","");   // 用空格替换
            }
        }
        return shortLine;
    }

public void queryHangjInfo1ForHangj_topicManager() throws java.io.IOException {
    Map param = getParamMap();
    List<Map> result = topicManagerService.queryHangjInfo1ForHangj_topicManager(param);
    super.render(new ResponseData(result));
}

}
