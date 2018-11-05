package cn.flydiy.hangj.entity;


/**
* banner配置信息实体
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:39.
*/

@javax.persistence.Entity(name = "hangj_banner_config")
@javax.persistence.Table(name = "hangj_banner_config", indexes = {@javax.persistence.Index(columnList = "id"), @javax.persistence.Index(columnList = "no")})
@cn.flydiy.core.annotation.Comment("banner配置信息")
public class BannerConfig extends cn.flydiy.core.entity.BaseEntity {

    @javax.persistence.Transient
    @cn.flydiy.common.json.annotation.JsonIgnore
    private static final long serialVersionUID = -7931363903081679169L;



    
    
    
    @javax.persistence.Column(name = "id",length=36)
    @cn.flydiy.core.annotation.Comment("id")
    private String id;

    
    
    
    @javax.persistence.Column(name = "name",length=255)
    @cn.flydiy.core.annotation.Comment("banner名称")
    private String name;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "pic")
    @cn.flydiy.core.annotation.Comment("图片")
    private String pic;

    
    
    
    @javax.persistence.Column(name = "no",length=11)
    @cn.flydiy.core.annotation.Comment("排序")
    private Integer no;

    
    
    
    @javax.persistence.Column(name = "url",length=255)
    @cn.flydiy.core.annotation.Comment("跳转链接")
    private String url;

    
    
    
    @javax.persistence.Column(name = "version")
    @cn.flydiy.core.annotation.Comment("版本号")
    private Integer version;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getNo() {
        return no;
    }
    public void setNo(Integer no) {
        this.no = no;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
