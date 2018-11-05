package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.NoticeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 通知中心代码(勿删)数据转换参数
* Modify by v_wyaohu on 2018-8-31 17:11:03.
*/
public class NoticeListDto extends BaseDto {


    private cn.flydiy.hangj.entity.Notice notice = new cn.flydiy.hangj.entity.Notice();



    public cn.flydiy.hangj.entity.Notice getNotice() {
        return this.notice;
    }
    public void setNotice(cn.flydiy.hangj.entity.Notice notice) {
        this.notice = notice;
    }


}
