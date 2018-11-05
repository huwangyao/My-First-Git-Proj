package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.AllConfigService;
import cn.flydiy.hangj.dto.AllConfigDto;

/**
* 后台管理控制器
* Modify by jerrywzhang(张伟) on 2018-6-14 18:08:41.
*/
@cn.flydiy.core.annotation.WebController
public class AllConfigController extends BaseController {

    @Autowired
    private AllConfigService allConfigService;



}
