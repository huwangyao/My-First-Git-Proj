package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.InfoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 首页数据转换参数
* Modify by v_sunlli(李小阳) on 2018-8-14 15:17:25.
*/
public class HomePageDto extends BaseDto {


    private cn.flydiy.hangj.entity.Info info = new cn.flydiy.hangj.entity.Info();



    public cn.flydiy.hangj.entity.Info getInfo() {
        return this.info;
    }
    public void setInfo(cn.flydiy.hangj.entity.Info info) {
        this.info = info;
    }


}
