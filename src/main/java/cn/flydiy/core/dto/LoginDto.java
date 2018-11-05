package cn.flydiy.core.dto;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.validation.NotBlank;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Created by flying on 16-11-26.
 */
public class LoginDto extends BaseDto {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -5840389748638443768L;

    @NotBlank(message = "{sys.nameORpwd.empty}")
    private String account;
    @NotNull(message = "{sys.nameORpwd.empty}")
    private String password;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
