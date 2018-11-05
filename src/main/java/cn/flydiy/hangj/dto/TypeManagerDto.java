package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.TypeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 分类管理数据转换参数
* Modify by v_wyaohu(胡王耀) on 2018-6-20 12:00:42.
*/
public class TypeManagerDto extends BaseDto {


    private cn.flydiy.hangj.entity.Type type = new cn.flydiy.hangj.entity.Type();



    public cn.flydiy.hangj.entity.Type getType() {
        return this.type;
    }
    public void setType(cn.flydiy.hangj.entity.Type type) {
        this.type = type;
    }


}
