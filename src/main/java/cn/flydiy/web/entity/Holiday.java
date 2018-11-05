package cn.flydiy.web.entity;

import cn.flydiy.core.annotation.Comment;
import cn.flydiy.core.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by player on 2018/2/1.
 */
@Entity
@Table(name = "web_holiday")
public class Holiday extends BaseEntity{

    @Comment("年")
    private Integer year;

    @Comment("月")
    private Integer month;

    @Comment("日")
    private Integer day;

    @Column(length = 36)
    @Comment("假期名称")
    private String name;

    @Comment("是否补班")
    private Integer work;

    @Temporal(TemporalType.DATE)
    private Date localDate;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public Holiday() {
    }

    public Holiday(Integer year, Integer month, Integer day, String name, Integer work, Date localDate) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.name = name;
        this.work = work;
        this.localDate = localDate;
    }
}
