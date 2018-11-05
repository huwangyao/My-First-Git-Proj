package cn.flydiy.core.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.entity.FrontErrorLog;
import cn.flydiy.core.service.FrontErrorLogService;
import cn.flydiy.core.web.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by player on 2018/5/28.
 */
@WebController
public class FrontErrorLogController extends BaseController {

    @Autowired
    private FrontErrorLogService frontErrorLogService;

    public void saveEntity(){
        FrontErrorLog frontErrorLog = getParamObj(FrontErrorLog.class);
        frontErrorLogService.save(frontErrorLog);
        render(new ResponseData(frontErrorLog));
    }
}
