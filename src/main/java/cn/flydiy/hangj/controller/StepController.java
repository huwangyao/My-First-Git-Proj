package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.StepService;
import cn.flydiy.hangj.dto.StepDto;

/**
* 步骤状态控制器
* Modify by v_wyaohu(胡王耀) on 2018-5-25 17:37:01.
*/
@cn.flydiy.core.annotation.WebController
public class StepController extends BaseController {

    @Autowired
    private StepService stepService;



}
