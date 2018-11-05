package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.MyMenuService;
import cn.flydiy.hangj.dto.MyMenuDto;

/**
* 左侧菜单控制器
* Modify by v_sunlli on 2018-8-29 21:31:49.
*/
@cn.flydiy.core.annotation.WebController
public class MyMenuController extends BaseController {

    @Autowired
    private MyMenuService myMenuService;



}
