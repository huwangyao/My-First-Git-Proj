package cn.flydiy.web.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.web.entity.Holiday;
import cn.flydiy.web.service.HolidayService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by player on 2018/2/1.
 */
@WebController
public class HolidayController extends BaseController{

    @Autowired
    private HolidayService holidayService;

    public void getWorkDays(){
        Map<String, Object> paramMap = getParamMap();
        String start = MapUtils.getString(paramMap,"start");
        String end = MapUtils.getString(paramMap,"end");

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        int days = holidayService.getWorkDays(startDate,endDate);
        super.render(new ResponseData(days));
    }

    public void setWorkDay(){
        Map<String, Object> paramMap = getParamMap();
        List<String> dateStrs = (List<String>) paramMap.get("dateStrs");
        holidayService.setWorkDay(dateStrs);
        super.render(new ResponseData());
    }

    public void setNoWorkDay(){
        Map<String, Object> paramMap = getParamMap();
        List<String> dateStrs = (List<String>) paramMap.get("dateStrs");
        String name = MapUtils.getString(paramMap,"name","节假日");
        List<Holiday> holidays = holidayService.setHoliDay(dateStrs, name);
        super.render(new ResponseData(holidays));
    }

    public void queryHolidays(){
        Holiday holiday = getParamObj(Holiday.class);
        List<Holiday> holidays = holidayService.queryHolidays(holiday);
        super.render(new ResponseData(holidays));
    }

    public void updateHolidayName(){
        Map<String, Object> paramMap = getParamMap();
        String id = MapUtils.getString(paramMap, "id");
        String name = MapUtils.getString(paramMap,"name");
        holidayService.updateHolidayName(id,name);
        super.render(new ResponseData());
    }

    public void initWeekend(){
        holidayService.initWeekend();
        super.render(new ResponseData());
    }
}
