package cn.flydiy.core.service;


import cn.flydiy.core.entity.User;
import cn.flydiy.core.validation.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by flying on 16-12-2.
 */
@Validated
public interface AuthService extends BaseService<User> {

    /**
     * 登录校验
     * @param account
     * @param password
     * @return
     */
    User authenticate(
            @NotBlank(message = "{sys.nameORpwd.empty}")
                    String account,
            @NotBlank(message = "{sys.nameORpwd.empty}")
                    String password
    );

    /**
     * 根据账号查询账号, 如果有账号信息, 则设置都session中
     * @param account
     * @param loginType
     * @return
     */
    User checkAndSetLoginUser(@NotBlank(message = "{sys.nameORpwd.empty}") String account, String loginType);

    void saveUser(
            @NotNull(message = "{validate.authenticate.saveUser}") @Valid
                    User user,
            String newPassword
    );

    User findByAccount(String account);

//    User thirdPartyLogin(String bindId, String type);
}
