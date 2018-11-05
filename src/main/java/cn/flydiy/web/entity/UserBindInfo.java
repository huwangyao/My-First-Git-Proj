package cn.flydiy.web.entity;

import cn.flydiy.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by player on 2017/10/10.
 */
@Entity
@Table(name = "sys_user_bind_info"
        , indexes = {@Index(columnList = "id")}
)
public class UserBindInfo extends BaseEntity{
    //我们系统中的用户id
    private String userId;

    //接入网站
    private String type;

    //网站所给出的绑定信息(一般是那个网站的用户ID)
    private String bindingId;

    //昵称
    private String nick;

    //账号
    private String account;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBindingId() {
        return bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
