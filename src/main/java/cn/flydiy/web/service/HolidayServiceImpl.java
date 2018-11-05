package cn.flydiy.web.service;

import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.web.entity.Holiday;
import cn.flydiy.web.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by player on 2018/2/1.
 */
@Service
public class HolidayServiceImpl extends BaseServiceImpl<Holiday> implements HolidayService{
    @Autowired
    private HolidayRepository holidayRepository;

    private void createWeekend(Integer createYear){
        LocalDate start = LocalDate.of(createYear, 1, 1);
        LocalDate end = LocalDate.of(createYear, 12, 31);

        List<Holiday> holidays = new ArrayList<>();
        List<LocalDate> betweenDate = getBetweenWeekend(start, end);
        for (LocalDate localDate : betweenDate) {
            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();
            System.out.println(year+"-"+month+"-"+day);
            Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            Holiday holiday = new Holiday(year, month, day, "周假", 0, Date.from(instant));
            holidays.add(holiday);
        }
        save(holidays.toArray(new Holiday[holidays.size()]));
    }

    private  List<LocalDate> getBetweenWeekend(LocalDate startDate, LocalDate endDate){
        List<LocalDate> list = new ArrayList<>();

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }

        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            DayOfWeek dayOfWeek = f.getDayOfWeek();
            if(DayOfWeek.SATURDAY == dayOfWeek || DayOfWeek.SUNDAY == dayOfWeek){
                list.add(f);
            }
        });

        return list;
    }

    private  List<LocalDate> getBetween(LocalDate startDate, LocalDate endDate){
        List<LocalDate> list = new ArrayList<>();

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }

        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {list.add(f);});

        return list;
    }

    @Override
    public int getWorkDays(LocalDate startDate, LocalDate endDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        Date start = Date.from(startDate.atStartOfDay(zoneId).toInstant());
        Date end = Date.from(endDate.atStartOfDay(zoneId).toInstant());

        List<LocalDate> between = getBetween(startDate, endDate);

        List<Holiday> holidays = holidayRepository.queryHolidayOfBetween(start,end);

        return between.size() - holidays.size();
    }

    @Override
    public void setWorkDay(List<String> dateStrs) {
        if(CollectionUtils.isNotEmpty(dateStrs)){
            ZoneId zoneId = ZoneId.systemDefault();
            List<Date> dateList = new ArrayList<>();
            for (String dateStr : dateStrs) {
                Date temp = Date.from(LocalDate.parse(dateStr).atStartOfDay(zoneId).toInstant());
                dateList.add(temp);
            }
            holidayRepository.updateWorkByDates(dateList,1);
        }

    }

    @Override
    public List setHoliDay(List<String> dateStrs, String name) {
        if(CollectionUtils.isNotEmpty(dateStrs)){
            ZoneId zoneId = ZoneId.systemDefault();
            List<Date> dateList = new ArrayList<>();
            for (String dateStr : dateStrs) {
                Date temp = Date.from(LocalDate.parse(dateStr).atStartOfDay(zoneId).toInstant());
                dateList.add(temp);
            }

            List<Holiday> holidays = holidayRepository.queryByDates(dateList);
            List<Holiday> addHolidays = new ArrayList<>();
            for (Date date : dateList) {
                boolean found = false;
                for (Holiday holiday : holidays) {
                    if(date.equals(holiday.getLocalDate())){
                        holiday.setWork(0);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(date);
                    int year = instance.get(Calendar.YEAR);
                    int month=instance.get(Calendar.MONTH)+1;
                    int day=instance.get(Calendar.DAY_OF_MONTH);
                    addHolidays.add(new Holiday(year,month,day,name,0,date));
                }
            }
            save(addHolidays.toArray(new Holiday[addHolidays.size()]));
            _updateEntityById(holidays.toArray(new Holiday[holidays.size()]));
            addHolidays.addAll(holidays);
            return addHolidays;
        }else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<Holiday> queryHolidays(Holiday holiday) {
        return holidayRepository.queryHolidays(holiday);
    }

    @Override
    public void initWeekend() {
        Holiday holiday = new Holiday();
        List<Holiday> holidays = queryHolidays(holiday);
        if(CollectionUtils.isEmpty(holidays)){
            for (int i = 2000; i < 2050; i++) {
                createWeekend(i);
            }
        }
    }

    @Override
    public void updateHolidayName(String id, String name) {
        Holiday holiday = findOne(id);
        holiday.setName(name);
        _updateEntityById(holiday);
    }
}
