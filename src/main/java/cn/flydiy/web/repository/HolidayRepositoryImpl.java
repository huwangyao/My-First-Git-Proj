package cn.flydiy.web.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.web.entity.Holiday;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by player on 2018/2/1.
 */
@Repository
public class HolidayRepositoryImpl extends BaseRepositoryImpl<Holiday> implements HolidayRepository{
    @Override
    public List<Holiday> queryHolidayOfBetween(Date start, Date end) {
        DBHelper<Holiday> dbHelper = getDbHelper();
        dbHelper.add(Expression.le("localDate",end));
//        dbHelper.add(Expression.or(Expression.le("localDate",end),Expression.eq("localDate",end)));
        dbHelper.add(Expression.ge("localDate",start));
        dbHelper.add(Expression.eq("work",0));
        return dbHelper.list();
    }

    @Override
    public List<Holiday> queryHolidays(Holiday holiday) {
        DBHelper<Holiday> dbHelper = getDbHelper();
        dbHelper.add(holiday.getYear()!=null,Expression.eq("year",holiday.getYear()));
        dbHelper.add(holiday.getMonth()!=null,Expression.eq("localDate",holiday.getMonth()));
        dbHelper.add(Expression.eq("work",0));
        return dbHelper.list();
    }

    @Override
    public void updateWorkByDates(List<Date> dateList,Integer work) {
        DBHelper<Holiday> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("localDate",dateList.toArray(new Date[dateList.size()])));
        dbHelper.setUpdateColumn("work",work);
        dbHelper.update();
    }

    @Override
    public List<Holiday> queryByDates(List<Date> dateList) {
        DBHelper<Holiday> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("localDate",dateList.toArray(new Date[dateList.size()])));
        return dbHelper.list();
    }
}
