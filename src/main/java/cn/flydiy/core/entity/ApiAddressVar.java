package cn.flydiy.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by player on 2018/5/11.
 */
@Table(name = "sys_api_address_var")
@Entity
public class ApiAddressVar extends  BaseEntity{

    private String code;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
