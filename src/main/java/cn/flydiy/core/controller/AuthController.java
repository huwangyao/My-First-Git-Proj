package cn.flydiy.core.controller;


import cn.flydiy.base.config.security.shiro.helper.ShiroHelper;
import cn.flydiy.base.util.SystemUtil;
import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.HttpRequestUtil;
import cn.flydiy.common.util.JsonUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.cache.CacheAccessToken;
import cn.flydiy.core.cache.CacheManager;
import cn.flydiy.core.constants.LoginLogStatus;
import cn.flydiy.core.dto.LoginDto;
import cn.flydiy.core.entity.User;
import cn.flydiy.core.service.AuthService;
import cn.flydiy.core.service.BaseUserService;
import cn.flydiy.core.service.LoginLogService;
import cn.flydiy.core.service.UserService;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.entity.UserBindInfo;
import cn.flydiy.web.service.UserBindInfoService;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkJsApiSingnature;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flying on 16-12-2.
 */
@WebController
public class AuthController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    public final String DINGDING_TYPE = "DING";
    public final String WECHAT_TYPE = "WECHAT";

    @Autowired
    AuthService authService;
    @Autowired
    LoginLogService loginLogService;
    @Autowired
    UserService userService;
    @Autowired
    BaseUserService baseUserService;

    @Autowired
    UserBindInfoService userBindInfoService;


    public void login(HttpServletRequest request) {


        LoginDto loginDto = getParamObj(LoginDto.class);

        ResponseData responseData = new ResponseData(false);

        User user;
        user = this.baseUserService.authenticate2(
                loginDto.getAccount(), loginDto.getPassword()
        );

        if (user != null) {
            user.setPassword(null);
            responseData.setSuccess(true);
            responseData.setObj(addUserExtraInfo(user));
        }


//        request.changeSessionId();

        super.render(responseData);
    }

//    /**
//     * pac4j 登录
//     */
//    public void login() {
//        String account = ShiroHelper.getAccount();
//        ResponseData responseData = handleLogin(account, LoginLogStatus.LOGIN_TYPE_LOGIN);
//
//        super.render(responseData);
//    }

    /**
     * 统一认证登录
     */
    public void casLogin() {


        String account = ShiroHelper.getAccount();
        ResponseData responseData = handleLogin(account, LoginLogStatus.LOGIN_TYPE_CAS_LOGIN);

        super.render(responseData);
    }


    private ResponseData handleLogin(String account,String loginType){
        User user = authService.checkAndSetLoginUser(account,loginType);
        ResponseData responseData = new ResponseData();
        responseData.setObj(user);


        // fixme flying 暂时不改变id, 1.登录日志需要同步修改后的id, 2.cas登录方式需要测试
        // 登录完成后改变id会增加安全性
//        WebUtils.getRequest().changeSessionId();

        return responseData;
    }


    /**
     * 查询当前登录用户
     */
    public void queryCurrentUser() {

        ResponseData responseData = new ResponseData();

        User user = WebUtils.getLoginUser();

        if (user != null) {
            user.setPassword(null);
            responseData.setSuccess(true);
            responseData.setObj(addUserExtraInfo(user));
        } else {
            responseData.setSuccess(false);
            responseData.setCode("sys.NoLoginUser");
        }

        super.render(responseData);
    }

    private Map addUserExtraInfo(User user){
        Map map = new HashMap();
        BeanUtil.copyPropertiesToMap(map,user);
        map.put("loginType", WebUtils.getLoginType());
        return map;
    }

    /**
     * 注销
     *
     * @param request
     * @param session
     */
    public void logout(HttpServletRequest request, HttpSession session) {

        User paramObj = getParamObj(User.class);

        logger.debug("User {} logged out.", paramObj);


        try {
            ShiroHelper.logout();
        }catch (Exception e){
        }

        try {
            session.invalidate(); // shiro注销的时候已经将session清除了
        }catch (Exception e){
        }


        super.render(new ResponseData());
    }

    /**
     * 该方法测试后端是否启动成功
     */
    public void testOnline() {
        super.render(new ResponseData());
    }

    /**
     * 第三方登录(微信\钉钉)
     */
    public void thirdPartyLogin(){
        Map<String, Object> paramMap = getParamMap();
        String code = MapUtils.getString(paramMap, "code");
        String type = MapUtils.getString(paramMap, "type");

        String bindId = "";
        ResponseData responseData  = null;

        //钉钉登录
        if (DINGDING_TYPE.equals(type)) {
//            //先从缓存取出accessToken,两个小时会销毁一次,也就是说两个小时请求一次
            String accessToken = CacheAccessToken.getAccessToken(CacheAccessToken.ACCESS_CACHE_PREFIX,CacheAccessToken.DINGDING_TYPE);
            String userInfo = HttpRequestUtil.sendGet("https://oapi.dingtalk.com/user/getuserinfo", "access_token="+accessToken+"&code="+code);
            logger.debug(accessToken + "," + code + "," + userInfo);
            Map dingResult = JsonUtil.getMapByJson(userInfo);
            bindId = MapUtils.getString(dingResult,"userid");

            if(StringUtil.isEmpty(bindId)){
                responseData = new ResponseData(userInfo);
                responseData.setMsg(userInfo);
            }else {
                responseData = queryByBindIdAndTypeAndHandleLogin(bindId,type);
            }

//            String accessToken = (String)CacheManager.get("thirdParty", DINGDING_TYPE);
//            if(StringUtil.isEmpty(accessToken)){
//                String result = HttpRequestUtil.sendGet("https://oapi.dingtalk.com/sns/gettoken", "appid=dingoa8zukxjwpinbuwnzg&appsecret=faHoJ9Mt03BlbzZfbfzsgPU56X9V0L8BWyZfvzAOxr72iFKFKC45T8EZ-uBgdJQ-");
//                Map accessMap = JsonUtil.getMapByJson(result);
//                accessToken = MapUtils.getString(accessMap, "access_token");
//                CacheManager.put("thirdParty", DINGDING_TYPE,accessToken,2*60*60);
//            }
//
//            //获取get_persistent_code
//            String jsonParam = "{\"access_token\":\""+accessToken+"\",\"tmp_auth_code\":\""+code+"\"}";
//            String persistentCode = HttpRequestUtil.sendPost("https://oapi.dingtalk.com/sns/get_persistent_code?access_token="+accessToken, jsonParam,true);
//
//            //获取sns_token
//            String post1 = HttpRequestUtil.sendPost("https://oapi.dingtalk.com/sns/get_sns_token?access_token=" + accessToken, persistentCode, true);
//            Map accessMap = JsonUtil.getMapByJson(post1);
//            String snsToken = MapUtils.getString(accessMap, "sns_token");
//
//            //获取钉钉用户信息
//            String post2 = HttpRequestUtil.sendGet("https://oapi.dingtalk.com/sns/getuserinfo", "sns_token="+snsToken);
//            Map dingResult = JsonUtil.getMapByJson(post2);
//            Map user_info = MapUtils.getMap(dingResult, "user_info");
//            bindId = MapUtils.getString(user_info, "dingId");
////            String nick = MapUtils.getString(user_info, "nick");昵称
//            if(StringUtil.isEmpty(bindId)){//如果取不到展示报错信息
//                responseData = new ResponseData(post2);
//                responseData.setMsg(post2);
//            }else {
//                responseData = queryByBindIdAndTypeAndHandleLogin(bindId,type);
//            }
        } else if (WECHAT_TYPE.equals(type)) {//微信登录
            //appid应该是从项目中获取的 目前写死
            String post = HttpRequestUtil.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", "appid=wxa5e78c5575616c6b&secret=50f4de16f61f7a79102a71a4e09401d4&code="+code+"&grant_type=authorization_code");
            Map wechaatResult = JsonUtil.getMapByJson(post);
            bindId = MapUtils.getString(wechaatResult,"openid");
            if(StringUtil.isEmpty(bindId)){
                responseData = new ResponseData(post);
                responseData.setMsg(post);
            }else {
                responseData = queryByBindIdAndTypeAndHandleLogin(bindId,type);
            }
        }else {
            ExceptionUtil.throwBaseRunTimeException("暂不支持的类型","暂不支持的类型");
        }

        if(responseData == null){
            responseData = new ResponseData(bindId);//其实这里不应该返回openId,应该放到session中 登录的时候再去取

            responseData.setMsg("此第三方用户还没与我们系统绑定,请先登录一次系统进行绑定");
        }
        super.render(responseData);
    }

    /**
     * 第三方登录如果没有绑定我们系统,则进行绑定操作
     */
    public void loginAndBind(){
        Map<String, Object> paramMap = getParamMap();
        String nick = MapUtils.getString(paramMap, "nick");
        String type = MapUtils.getString(paramMap, "type");
        String code = MapUtils.getString(paramMap, "code");

        if (StringUtil.isEmpty(code) || StringUtil.isEmpty(type)) {
            throw new BaseRunTimeException("sys.auth.thirdPart.codeOrType.empty");
        }

        String account = ShiroHelper.getAccount();
        ResponseData responseData = handleLogin(account, LoginLogStatus.LOGIN_TYPE_LOGIN);

        User user = (User) responseData.getObj();
        UserBindInfo userBindInfo = new UserBindInfo();
        userBindInfo.setBindingId(code);
        userBindInfo.setUserId(user.getId());
        userBindInfo.setType(type);
        userBindInfo.setAccount(account);
        userBindInfo.setNick(nick);

        userBindInfoService.handleBind(userBindInfo);
        super.render(responseData);
    }

    public void getFiveParams(){
        String corpId = "ding99f9d852bdc6de9d35c2f4657eb6378f";
        String agentid = "161275858";
        String accessToken = CacheAccessToken.getAccessToken(CacheAccessToken.ACCESS_CACHE_PREFIX,CacheAccessToken.DINGDING_TYPE);
        String ticket = (String) CacheManager.get(CacheAccessToken.ACCESS_CACHE_PREFIX,accessToken);
        if(StringUtil.isEmpty(ticket)){
            String ticketResult = HttpRequestUtil.sendGet("https://oapi.dingtalk.com/get_jsapi_ticket", "access_token=" + accessToken);
            Map ticketMap = JsonUtil.getMapByJson(ticketResult);
            ticket = MapUtils.getString(ticketMap, "ticket");
            CacheManager.put(CacheAccessToken.ACCESS_CACHE_PREFIX,accessToken,ticket);
        }
        ResponseData responseData = new ResponseData();
        try {
            long timeStamp = System.currentTimeMillis() / 1000;
            String nonceStr = "flydiy";
            String url = SystemUtil.getWebAddress()+"/?Ding";
            String signature = DingTalkJsApiSingnature.getJsApiSingnature(url, nonceStr, timeStamp,ticket);

            String configValue = "{jsticket:'" + ticket + "',signature:'" + signature + "',nonceStr:'" + nonceStr + "',timeStamp:'"
                    + timeStamp + "',corpId:'" + corpId + "',agentid:'" + agentid + "'}";
            Map result = new HashMap();
            result.put("jsticket",ticket);
            result.put("signature",signature);
            result.put("nonceStr",nonceStr);
            result.put("timeStamp",timeStamp);
            result.put("corpId",corpId);
            result.put("agentid",agentid);
            result.put("url", url);
            System.out.println(configValue);
            responseData.setObj(result);
        } catch (DingTalkEncryptException e) {
            e.printStackTrace();
            responseData.setSuccess(false);
            responseData.setMsg("获取参数失败");
        }

        super.render(responseData);
    }


    /**
     * 查询第三方账号是否已经绑定当前系统
     * @param bindId
     * @param type
     * @return
     */
    private ResponseData queryByBindIdAndTypeAndHandleLogin(String bindId, String type){
        UserBindInfo userBindInfo = userBindInfoService.queryByBindIdAndType(bindId,type);
        if(userBindInfo!=null) {
            User user = userService.findOne(userBindInfo.getUserId());
            return handleLogin(user.getAccount(), type);
        }
        return null;
    }
}