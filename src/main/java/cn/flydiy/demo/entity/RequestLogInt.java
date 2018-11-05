package cn.flydiy.demo.entity;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.entity.BaseIntEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by flying on 16-11-26.
 */
@Entity
@Table(name = "test_request_log_int"
        , indexes = {@Index(columnList = "id")}
        )
public class RequestLogInt extends BaseIntEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 9087968834906825627L;

    private String url;
    private Date startDate;
    private Integer processTime;

    private String username;
    private String userAgent;

    private Double doubleNum;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Integer processTime) {
        this.processTime = processTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Double getDoubleNum() {
        return doubleNum;
    }

    public void setDoubleNum(Double doubleNum) {
        this.doubleNum = doubleNum;
    }
}
