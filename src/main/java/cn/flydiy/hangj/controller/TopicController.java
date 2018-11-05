package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.TopicService;
import cn.flydiy.hangj.entity.Topic;

/**
* 行家话题控制器
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
@cn.flydiy.core.annotation.WebController
public class TopicController extends BaseController {

    @Autowired
    private TopicService topicService;

    //保存
    public void saveEntity() {
        Topic topic = getParamObj(Topic.class);
        topicService.saveEntity(topic);
        super.render(new ResponseData(topic));
    }

    //批量保存或者更新
    public void batchSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("topic");
        List<Topic> topics = BeanUtil.convertMapsToBeans(listMap, Topic.class);
        List<Topic> saveArr = new java.util.ArrayList<>();
        List<Topic> updateArr = new java.util.ArrayList<>();
        for (Topic topic : topics) {
            if (topic.getId() == null) {
            
                saveArr.add(topic);
            }else {
                updateArr.add(topic);
            }
        }
        topicService.saveEntity(saveArr.toArray(new Topic[]{}));
        topicService.updateEntity(updateArr.toArray(new Topic[]{}));
        super.render(new ResponseData(topics));
    }

    //批量整个保存
    public void batchWholeSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("topic");
        List<Topic> topics = BeanUtil.convertMapsToBeans(listMap, Topic.class);
        topicService.saveWhole(topics.toArray(new Topic[]{}));
        super.render(new ResponseData(topics));
    }

    //根据id查询
    public void queryById() {
        Map map = getParamMap();
        Topic result =  topicService.findOne(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
    }

    //更新
    public void updateEntity() {
        Topic topic = getParamObj(Topic.class);
        topicService.updateEntity(topic);
        super.render(new ResponseData(topic));
    }

    public void saveOrUpdate(){
        Topic topic = getParamObj(Topic.class);
        if (topic.getId() == null) {
        
            topicService.save(topic);
        }else{
            topicService._updateEntityById(topic);
        }
        super.render(new ResponseData(topic));
    }

    //根据id删除
    public void deleteById() {
        Map map = getParamMap();
        topicService.delete(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
    }

    //批量删除
    public void batchDelete(){
        Map map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        topicService.delete(ids.toArray(new String[]{}));
        super.render(new ResponseData());
    }

    public void queryByParam(){
        Topic condition = getParamObj(Topic.class);
        List<Topic> result = topicService.queryByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryPageByParam(){
        Topic condition = getParamObj(Topic.class);
        List<Topic> result = topicService.queryPageByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryByIds(){
        Map<String, Object> map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        List<Topic> result = topicService.findByIds(ids.toArray(new String[]{}));
        super.render(new ResponseData(result));
    }

    public void updateByParam(){
        Topic updateParam = getParamObj(Topic.class);
        topicService.updateByParam(updateParam);
        super.render(new ResponseData());
    }

    public void queryByInfoId(){
        Map map = getParamMap();
        String infoId = MapUtils.getString(map,"infoId");
        List<Map> result = topicService.queryByInfoId(infoId);
        super.render(new ResponseData(result));
    }

    public void deleteByInfoId(){
        Map map = getParamMap();
        String infoId = MapUtils.getString(map,"infoId");
        topicService.deleteByInfoId(infoId);
        super.render(new ResponseData());
    }

}
