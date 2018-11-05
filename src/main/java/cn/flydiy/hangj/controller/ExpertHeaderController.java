package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.ExpertHeaderService;
import cn.flydiy.hangj.dto.ExpertHeaderDto;

/**
* 头部控制器
* Modify by v_wyaohu on 2018-8-29 14:41:15.
*/
@cn.flydiy.core.annotation.WebController
public class ExpertHeaderController extends BaseController {

    @Autowired
    private ExpertHeaderService expertHeaderService;



}
