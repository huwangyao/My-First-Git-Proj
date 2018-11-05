package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.entity.Holiday;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by player on 2018/2/1.
 */
public interface HolidayService extends BaseService<Holiday>{


    int getWorkDays(LocalDate startDate, LocalDate endDate);

    void setWorkDay(List<String> dateStrs);

    List<Holiday> setHoliDay(List<String> dateStrs, String name);

    List<Holiday> queryHolidays(Holiday holiday);

    void initWeekend();

    void updateHolidayName(String id, String name);
}
