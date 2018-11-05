package cn.flydiy.core.entity;

import cn.flydiy.core.annotation.Comment;

import javax.persistence.*;

/**
 * Created by player on 2018/5/28.
 */
@Entity
@Table(name = "sys_front_error_log"
        , indexes = {@Index(columnList = "id")}
)
public class FrontErrorLog extends BaseEntity{

    @Comment("异常名称")
    private String name;

    @Comment("异常路由")
    private String router;

    @Comment("异常信息")
    @Column(length = 4000)
    private String message;

    @Comment("栈信息")
    @Lob
    private String stack;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
