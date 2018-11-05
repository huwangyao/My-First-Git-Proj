package cn.flydiy.core.constants;

import cn.flydiy.core.constant.BaseLoginStatus;

/**
 * Created by hoo on 2017/8/9.
 */
public class LoginStatus {
    /**
     * 登陆状态
     */
    public static final String RESET = BaseLoginStatus.RESET; // 强行重置密码状态
    public static final String VALID = BaseLoginStatus.VALID; // 正常使用状态
    public static final String LOCK = BaseLoginStatus.LOCK; // 锁定状态
    public static final String DISABLE = BaseLoginStatus.DISABLE; // 禁用状态
    public static final String INITIAL = BaseLoginStatus.INITIAL; // 初始化密码

}
