package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.MyHangjBookService;
import cn.flydiy.hangj.dto.MyHangjBookDto;

/**
* 我约的行家控制器
* Modify by v_sunlli on 2018-8-31 10:16:06.
*/
@cn.flydiy.core.annotation.WebController
public class MyHangjBookController extends BaseController {

    @Autowired
    private MyHangjBookService myHangjBookService;



}
