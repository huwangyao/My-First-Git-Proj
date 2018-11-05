package cn.flydiy.web.entity;


import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.annotation.Comment;
import cn.flydiy.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by flying on 16-12-2.
 */
@Entity
@Table(name = "sys_attachment"
        , indexes = {@Index(columnList = "id")}
)
@Comment("附件表")
public class Attachment extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 8064731619189902042L;

    @Column(length = ID_LEN)
    @Comment("表记录ID")
    private String bizId;

    @Comment("表记录ID")
    private Integer bizIdVer;

    @Column(length = 32)
    @Comment("表名")
    private String bizTable;

    @Column(length = 200)
    @Comment("附件名")
    private String filename;// 附件名

    @Comment("附件大小")
    private Long filesize;

    @Column(length = 150)
    @Comment("附件地址")
    private String url;

    @Column(length = 32)
    @Comment("附件类型")
    private String type;

    @Comment("附件顺序")
    private Integer sort;

    @Comment("下载次数")
    private Long downCount;

    @Column(length = 100)
    @Comment("附件类型")
    private String mimeContentType;


    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Integer getBizIdVer() {
        return bizIdVer;
    }

    public void setBizIdVer(Integer bizIdVer) {
        this.bizIdVer = bizIdVer;
    }

    public String getBizTable() {
        return bizTable;
    }

    public void setBizTable(String bizTable) {
        this.bizTable = bizTable;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getDownCount() {
        return downCount;
    }

    public void setDownCount(Long downCount) {
        this.downCount = downCount;
    }

    public String getMimeContentType() {
        return mimeContentType;
    }

    public void setMimeContentType(String mimeContentType) {
        this.mimeContentType = mimeContentType;
    }

    public Attachment(String bizTable, String filename, Long filesize, String url, String mimeContentType) {
        this.bizTable = bizTable;
        this.filename = filename;
        this.filesize = filesize;
        this.url = url;
        this.mimeContentType = mimeContentType;
    }

    public Attachment() {
    }
}