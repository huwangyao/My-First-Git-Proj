package cn.flydiy.hangj.entity;


/**
* 分类管理实体
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/

@javax.persistence.Entity(name = "hangj_type")
@javax.persistence.Table(name = "hangj_type", indexes = {@javax.persistence.Index(columnList = "id"), @javax.persistence.Index(columnList = "no")})
@cn.flydiy.core.annotation.Comment("分类管理")
public class Type extends cn.flydiy.core.entity.BaseEntity {

    @javax.persistence.Transient
    @cn.flydiy.common.json.annotation.JsonIgnore
    private static final long serialVersionUID = -7931363903081679169L;



    
    
    
    @javax.persistence.Column(name = "id",length=36)
    @cn.flydiy.core.annotation.Comment("id")
    private String id;

    
    
    
    @javax.persistence.Column(name = "name",length=255)
    @cn.flydiy.core.annotation.Comment("名称")
    private String name;

    
    
    
    @javax.persistence.Column(name = "no",length=11)
    @cn.flydiy.core.annotation.Comment("序号")
    private Integer no;

    
    
    
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

    public Integer getNo() {
        return no;
    }
    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
