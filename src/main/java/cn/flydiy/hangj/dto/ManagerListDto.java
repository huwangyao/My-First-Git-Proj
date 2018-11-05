package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 行家列表数据转换参数
* Modify by v_wyaohu(胡王耀) on 2018-6-14 19:34:35.
*/
public class ManagerListDto extends BaseDto {


    private cn.flydiy.hangj.entity.Info info = new cn.flydiy.hangj.entity.Info();

        private List<cn.flydiy.hangj.entity.Topic> topic = new ArrayList();



    public cn.flydiy.hangj.entity.Info getInfo() {
        return this.info;
    }
    public void setInfo(cn.flydiy.hangj.entity.Info info) {
        this.info = info;
    }
        public List<cn.flydiy.hangj.entity.Topic> getTopic() {
            return this.topic;
        }
        public void setTopic(List<cn.flydiy.hangj.entity.Topic> topic) {
            this.topic = topic;
        }



}
