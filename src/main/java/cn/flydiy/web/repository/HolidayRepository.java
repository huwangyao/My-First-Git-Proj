package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.Holiday;

import java.util.Date;
import java.util.List;

/**
 * Created by player on 2018/2/1.
 */
public interface HolidayRepository extends BaseRepository<Holiday>{
    List<Holiday> queryHolidayOfBetween(Date start, Date end);

    List<Holiday> queryHolidays(Holiday holiday);

    void updateWorkByDates(List<Date> dateList,Integer work);

    List<Holiday> queryByDates(List<Date> dateList);
}
