package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.TopicService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 话题管理数据转换参数
* Modify by v_sunlli on 2018-8-23 11:22:43.
*/
public class TopicManagerDto extends BaseDto {


    private cn.flydiy.hangj.entity.Topic topic = new cn.flydiy.hangj.entity.Topic();



    public cn.flydiy.hangj.entity.Topic getTopic() {
        return this.topic;
    }
    public void setTopic(cn.flydiy.hangj.entity.Topic topic) {
        this.topic = topic;
    }


}
