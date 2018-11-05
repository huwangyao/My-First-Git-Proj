package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.TopicService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 新建话题弹窗数据转换参数
* Modify by v_wyaohu(胡王耀) on 2018-6-19 12:00:23.
*/
public class TopicCreateDialogDto extends BaseDto {


    private cn.flydiy.hangj.entity.Topic topic = new cn.flydiy.hangj.entity.Topic();



    public cn.flydiy.hangj.entity.Topic getTopic() {
        return this.topic;
    }
    public void setTopic(cn.flydiy.hangj.entity.Topic topic) {
        this.topic = topic;
    }


}
