package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.MyNoticeService;
import cn.flydiy.hangj.dto.MyNoticeDto;

/**
* 通知中心控制器
* Modify by v_wyaohu on 2018-8-29 20:54:46.
*/
@cn.flydiy.core.annotation.WebController
public class MyNoticeController extends BaseController {

    @Autowired
    private MyNoticeService myNoticeService;



}
