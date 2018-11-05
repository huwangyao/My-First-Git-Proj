package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.InfoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 新建行家数据转换参数
* Modify by v_wyaohu(胡王耀) on 2018-6-14 11:58:36.
*/
public class ExpertCreateDto extends BaseDto {


    private cn.flydiy.hangj.entity.Info info = new cn.flydiy.hangj.entity.Info();



    public cn.flydiy.hangj.entity.Info getInfo() {
        return this.info;
    }
    public void setInfo(cn.flydiy.hangj.entity.Info info) {
        this.info = info;
    }


}
