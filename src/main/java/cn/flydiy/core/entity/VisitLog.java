package cn.flydiy.core.entity;

import cn.flydiy.common.json.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 前端上报的访问日志, 用来做统计
 * Created by flying on 16-12-26.
 */
@Entity
@Table(name = "sys_visit_log"
        , indexes = {@Index(columnList = "id")}
        )
public class VisitLog extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -7089982368203316595L;

    @Column(length = 50)
    private String projectToken;

    @Column(length = 10)
    private String type;

    @Column(length = 32)
    private String uid;

    @Column(length = 32)
    private String batchId;


    private Integer visitIndex;

    @Column(length = ID_LEN)
    private String userId;

    @Column(length = 200)
    private String userName;

    @Column(length = 500)
    private String referer;

    @Column(length = 20)
    private String source;

    @Column(length = 500)
    private String url;

    @Column(length = 200)
    private String uri;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    private Integer startYear;

    private Integer startMonth;

    private Integer startDay;

    private Integer startHour;

    private Integer startMin;

    private Integer stayTime;

    @Column(length = 200)
    private String host; // 域名

    @Column(length = 50)
    private String eventCode; // 同一件事件的唯一编码,用来做统计

    @Column(length = 50)
    private String eventFrom; // 同一件事件的不同来源,用来做统计

    @Column(length = 50)
    private String extCode; // 同一件事件的拓展code,用来做统计

    @Column(length = 200)
    private String remark; // 备注说明

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Integer getVisitIndex() {
        return visitIndex;
    }

    public void setVisitIndex(Integer visitIndex) {
        this.visitIndex = visitIndex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getStartMin() {
        return startMin;
    }

    public void setStartMin(Integer startMin) {
        this.startMin = startMin;
    }

    public Integer getStayTime() {
        return stayTime;
    }

    public void setStayTime(Integer stayTime) {
        this.stayTime = stayTime;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    public String getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }
}


