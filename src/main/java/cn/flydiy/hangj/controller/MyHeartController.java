package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.MyHeartService;
import cn.flydiy.hangj.dto.MyHeartDto;

/**
* 我的心得控制器
* Modify by v_sunlli on 2018-8-23 11:26:13.
*/
@cn.flydiy.core.annotation.WebController
public class MyHeartController extends BaseController {

    @Autowired
    private MyHeartService myHeartService;



}
