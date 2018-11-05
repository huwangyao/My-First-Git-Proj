
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.ManagerListDto;

/**
* 行家列表
* Modify by v_wyaohu(胡王耀) on 2018-6-14 19:34:35.
*/
@org.springframework.stereotype.Service
public class ManagerListServiceImpl implements ManagerListService {

    @Autowired
    private InfoService infoService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private OrderService orderService;//行家列表的手工注入的代码;



    @Override
    public void saveDto(ManagerListDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();
        
        infoService.saveEntity(info);
        List<cn.flydiy.hangj.entity.Topic> topics = BeanUtil.convertMapsToBeans(dto.getTopic(),cn.flydiy.hangj.entity.Topic.class);
        for(cn.flydiy.hangj.entity.Topic topic: topics){
            topic.setInfoId(info.getId());
            topic.setInfoIdVer(info.getVersion());
        }
        topicService.saveEntity(topics.toArray(new cn.flydiy.hangj.entity.Topic[]{}));
        dto.setTopic(topics);
    }

    @Override
    public void commit(ManagerListDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();
        if(cn.flydiy.common.util.StringUtil.isEmpty(info.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.Info info = infoService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        BeanUtil.copyPropertiesToMap(map,info);
        result.put("info",map);
        List<Map> topics = null;
        if(_isMasterData){
            topics = topicService.queryByInfoId(info.getVersion(),info.getId());
        }else {
            topics = topicService.queryByInfoId(info.getId());
        }
        result.put("topic",topics);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Info> infos = infoService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);

        for (cn.flydiy.hangj.entity.Info info : infos) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,info);
            List<Map> topics = null;
            if(_isMasterData){
                topics = topicService.queryByInfoId(info.getVersion(),info.getId());
            }else {
                topics = topicService.queryByInfoId(info.getId());
            }
            temp.put("topic",topics);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(ManagerListDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();

        
        infoService.updateEntity(info);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        if(_isMasterData){
            dto.setInfo(info);
        List<cn.flydiy.hangj.entity.Topic> topics = BeanUtil.convertMapsToBeans(dto.getTopic(),cn.flydiy.hangj.entity.Topic.class);
        for(cn.flydiy.hangj.entity.Topic topic: topics){
        topic.setInfoId(info.getId());
        topic.setInfoIdVer(info.getVersion());
        }
        topicService.saveEntity(topics.toArray(new cn.flydiy.hangj.entity.Topic[]{}));
            return ;
        }

        List<cn.flydiy.hangj.entity.Topic> topics =dto.getTopic();
        List<String> topicDelIds = null;
        if (_isMasterData){
            topicDelIds = topicService.updateByInfoId(info.getVersion(),info.getId(), topics);
        }else {
            topicDelIds = topicService.updateByInfoId(info.getId(), topics);
        }

    }


    @Override
    public void updateDtoNoSaveHistory(ManagerListDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();

        
        infoService.updateEntityNoSaveHistory(info);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);

        List<cn.flydiy.hangj.entity.Topic> topics =dto.getTopic();
        List<String> topicDelIds = null;
        if (_isMasterData){
            topicDelIds = topicService.updateByInfoId(info.getVersion(),info.getId(), topics);
        }else {
            topicDelIds = topicService.updateByInfoId(info.getId(), topics);
        }

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        cn.flydiy.hangj.entity.Info info = infoService.findOne(id);
        infoService.delete(id);
        List<Map> topics = null;
        if(_isMasterData){
            topics = topicService.queryByInfoId(info.getVersion(),id);
        }else {
            topics = topicService.queryByInfoId(id);
        }
        String[] topicIds = CollectionUtils.getValuesByKey(topics, "id");
        if(!_isMasterData){
            topicService.delete(topicIds);
        }else {
            topicService.setIsNewToZero(topicIds);
        }
    }

    @Override
    public void batchCommit(List<ManagerListDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (ManagerListDto dto : dtos) {
                commit(dto);
            }
        }
    }



        //ManagerList的手工代码
            @Override
    public Map queryStaffInfo(Map param) {
        String account = cn.flydiy.core.web.WebUtils.getLoginUser().getAccount();
        cn.flydiy.hangj.dto.StaffDto loginStaffDto = cn.flydiy.hangj.cache.StaffInfoCache.staffCache.queryByEngName(account);
        if(loginStaffDto == null){
            throw new cn.flydiy.common.exception.BaseRunTimeException("esf.login.user.is.nothing",new String[]{account});
        }
        Map result = new HashMap();
        result.put("staff",loginStaffDto);
        return result;
    }

    @Override
    public List<Map> queryTopicForHomePage(Map param) {
        // 首先查询话题
        List<Map> topicList = topicService.queryPageTopicMapByParams(param);
        // 获取行家id
        List<String> infoIds = new ArrayList();
        for(Map map : topicList){
            String infoId = MapUtils.getString(map,"infoId");
            infoIds.add(infoId);
        }
        // 查询行家信息
        List<Map> result = new ArrayList();
        if(infoIds.size()>0){
            List<cn.flydiy.hangj.entity.Info> expertList = infoService.findByIds(infoIds.toArray(new String[]{}));
            for(Map topic:topicList){
                String infoId = MapUtils.getString(topic,"infoId");
                // 处理简介
                String topicOutline = MapUtils.getString(topic,"topicOutline");
                String shortLine = "";
                if(StringUtil.isNotEmpty(topicOutline)){
                    if(topicOutline.lastIndexOf("</p>")>=0){
                        shortLine = topicOutline.replaceAll("</p>"," ");   // 用空格替换
                        shortLine = shortLine.replaceAll("<.*?>","");   // 用空格替换
                       //  shortLine = topicOutline.substring(topicOutline.indexOf("<p>")+"<p>".length(),topicOutline.lastIndexOf("</p>"));
                    }else{
                        shortLine = topicOutline.replaceAll("</p>"," ");   // 用空格替换
                        shortLine = shortLine.replaceAll("<.*?>","");   // 用空格替换
                        // shortLine = topicOutline.substring(topicOutline.indexOf("<p>")+"<p>".length());
                    }
                }
                topic.put("shortLine",shortLine);
                for(cn.flydiy.hangj.entity.Info expert:expertList){
                   if(StringUtil.equalsIgnoreCase(infoId,expert.getId())){
                        String name = expert.getName();  // 名称
                        String pic = expert.getPic(); // 头像
                        String positionName = expert.getTag();  // 职位
                        topic.put("expert_name",name);
                        if(StringUtil.isNotEmpty(expert.getNewPic())){
                            topic.put("newPic",expert.getNewPic());
                        }
                        topic.put("expert_pic",pic);
                        topic.put("expert_position",positionName);

                        if(StringUtil.equalsIgnoreCase("已审核",expert.getStatus())){
                            result.add(topic);
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Map queryExpertWithTopic(Map param) {
        String id = MapUtils.getString(param,"id");

        cn.flydiy.hangj.entity.Topic topicParam = topicService.findOne(id);
        // 行家信息
        cn.flydiy.hangj.entity.Info info = infoService.findOne(topicParam.getInfoId());
        List<cn.flydiy.hangj.entity.Topic> topicList = new ArrayList();
        topicList.add(topicParam);
        // 查询其他话题
        cn.flydiy.hangj.entity.Topic otherParam = new cn.flydiy.hangj.entity.Topic();
        otherParam.setInfoId(topicParam.getInfoId());
        otherParam.setStatus("已审核");
        List<cn.flydiy.hangj.entity.Topic> other = topicService.queryByParams(otherParam);
        for(cn.flydiy.hangj.entity.Topic topic : other){
            String topicId = topic.getId();
            if(!StringUtil.equalsIgnoreCase(id,topicId)){
                topicList.add(topic);
            }
        }

        // 查询行家见过的人数
        String expertName = info.getName();
        Map orderParam = new HashMap();
        orderParam.put("expertName",expertName);
        int count = 0 ;
        List<Map> orderList = orderService.getOrderCountByExpertName(orderParam);
        count = orderList.size();

        Map result = new HashMap();
        result.put("info",info);
        result.put("topic",topicList);
        result.put("count",count);
        return result;
    }

    @Override
    public void getResourcesByExcelTopic(List<Map> maps,List<Map> mapsT) {
        List<cn.flydiy.hangj.entity.Info> resources = new ArrayList<>();
        for (Map loop : maps) {
            if (MapUtils.isEmpty(loop)) continue;
            System.out.println("//////////////////////////////////////////////////////////");
            cn.flydiy.hangj.entity.Info info = new cn.flydiy.hangj.entity.Info();

            // 序号
            String name = MapUtils.getString(loop,"name");
            // 获取图片
            cn.flydiy.hangj.dto.StaffDto loginStaffDto = cn.flydiy.hangj.cache.StaffInfoCache.staffCache.queryByEngName(name);
            if(loginStaffDto != null){
                info.setPic(loginStaffDto.getPhotoUrl());
                info.setOrgStr(loginStaffDto.getOrganizationFullName());
                info.setPosition(loginStaffDto.getPositionName());
                if(StringUtil.isNotEmpty(loginStaffDto.getLastHireDate())){
                    String dateStr = loginStaffDto.getLastHireDate()+" 00:00:00";
                    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        java.util.Date date = format.parse(dateStr);
                        info.setEntryDate(date);
                    }catch(Exception e){

                    }
                }
            }
            info.setName(name);
            info.setAddress(MapUtils.getString(loop,"address"));
            if(StringUtil.isNotEmpty(MapUtils.getString(loop,"work"))){
                info.setCareer("<p>"+MapUtils.getString(loop,"work")+"</p>");
            }
            if(StringUtil.isNotEmpty(MapUtils.getString(loop,"exp"))){
                info.setProjectExp("<p>"+MapUtils.getString(loop,"exp")+"</p>");
            }
            if(StringUtil.isNotEmpty(MapUtils.getString(loop,"prize"))){
                info.setAwards("<p>"+MapUtils.getString(loop,"prize")+"</p>");
            }
            info.setTag(MapUtils.getString(loop,"title"));
            info.setStatus("已审核");

            resources.add(info);
            // 保存行家信息
            infoService.saveEntity(info);

            // 保存行家话题信息
            List<cn.flydiy.hangj.entity.Topic> itemList = new ArrayList<>();
            for(Map map:mapsT){
               String nameStr = MapUtils.getString(map,"name");
                if(StringUtil.equalsIgnoreCase(name,nameStr)){
                    cn.flydiy.hangj.entity.Topic one = new cn.flydiy.hangj.entity.Topic();
                    one.setInfoId(info.getId());
                    one.setInfoIdVer(1);
                    one.setStatusCode("hangj-topic-status");
                    one.setStatus("已审核");
                    one.setName(MapUtils.getString(map,"title"));
                    one.setType(MapUtils.getString(map,"type"));
                    if(StringUtil.isNotEmpty(MapUtils.getString(map,"point"))){
                        one.setTopicOutline("<p>"+MapUtils.getString(map,"point")+"</p>");
                    }
                    if(StringUtil.isNotEmpty(MapUtils.getString(map,"relevantExp"))){
                        one.setRelevantExp("<p>"+MapUtils.getString(map,"relevantExp")+"</p>");
                    }
                    one.setConsultTime("1");
                    itemList.add(one);
                }
            }
            topicService.saveEntity(itemList.toArray(new cn.flydiy.hangj.entity.Topic[]{}));
        }
    }

        @Override
    public Map getExpertTopicCount(Map param) {
        String infoId = MapUtils.getString(param,"id");
        cn.flydiy.hangj.entity.Topic topic = new cn.flydiy.hangj.entity.Topic();
        topic.setInfoId(infoId);
        topic.setStatus("已审核");
        List<Map> topicList = topicService.queryMapByParams(topic);
        int count = topicList.size();

        Map result = new HashMap();
        result.put("count",count);
        return result;
    }

    public List<Map> dataTableForHangj_managerList(Map param){
        List<Map> result = infoService.dataTableForHangj_managerList(param);
        return result;
    }

}


