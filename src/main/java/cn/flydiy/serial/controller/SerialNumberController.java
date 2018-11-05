package cn.flydiy.serial.controller;


import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.serial.util.SerialNumberUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by flying on 16-12-2.
 */
@WebController
public class SerialNumberController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    /**
     * 查询到下一个序列号，此方法无论调用多少次都是同样的结果，直到调用getSerial()
     */
    public void findSerial(){
        Map map = getParamMap();
        String table = MapUtils.getString(map, "table");
        String col=MapUtils.getString(map,"col");
        String result;

        String rule = MapUtils.getString(map, "rule");
        if (rule != null) {
            result = SerialNumberUtils.findSerialNum(table, col, rule, map);
        }else {
            result = SerialNumberUtils.findSerialNum(table, col, map);
        }
        super.render(new ResponseData(result));
    }

    /**
     * 查询到下一个序列号，此方法每调一次会使下一个序列号加1
     */
    public void getSerial(){
        Map map = getParamMap();
        String table = MapUtils.getString(map, "table");
        String col=MapUtils.getString(map,"col");
        String result;

        String rule = MapUtils.getString(map, "rule");
        if (rule != null) {
            result = SerialNumberUtils.getSerialNum(table, col, rule, map);
        }else {
            result = SerialNumberUtils.getSerialNum(table, col, map);
        }
        super.render(new ResponseData(result));
    }
}