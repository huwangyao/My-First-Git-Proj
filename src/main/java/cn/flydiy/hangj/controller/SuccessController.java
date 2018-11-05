package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.SuccessService;
import cn.flydiy.hangj.dto.SuccessDto;

/**
* 成功控制器
* Modify by v_sunlli(李小阳) on 2018-8-14 14:30:55.
*/
@cn.flydiy.core.annotation.WebController
public class SuccessController extends BaseController {

    @Autowired
    private SuccessService successService;



}
