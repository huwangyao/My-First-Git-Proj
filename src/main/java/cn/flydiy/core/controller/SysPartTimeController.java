package cn.flydiy.core.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.LoginUserCache;
import cn.flydiy.core.entity.Jobs;
import cn.flydiy.core.entity.Org;
import cn.flydiy.core.entity.SysPartTime;
import cn.flydiy.core.entity.User;
import cn.flydiy.core.service.JobsService;
import cn.flydiy.core.service.OrgService;
import cn.flydiy.core.service.SysPartTimeService;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 工作兼任控制器
* Created by 梁宇湘 on 2017-11-15 10:07:37.
*/
@cn.flydiy.core.annotation.WebController
public class SysPartTimeController extends BaseController {

    @Autowired
    private SysPartTimeService sysPartTimeService;
    @Autowired
    private JobsService jobsService;
    @Autowired
    private OrgService orgService;

//    //保存
//    public void saveEntity() {
//        SysPartTime sysPartTime = getParamObj(SysPartTime.class);
//        sysPartTimeService.save(sysPartTime);
//        super.render(new ResponseData(sysPartTime));
//    }
//
//    //批量保存
//    public void batchSave(){
//        Map<String, Object> paramMap = getParamMap();
//        List listMap = (List) paramMap.get("sysPartTime");
//        List<SysPartTime> sysPartTimes = BeanUtil.convertMapsToBeans(listMap, SysPartTime.class);
//        sysPartTimeService.save(sysPartTimes.toArray(new SysPartTime[]{}));
//        super.render(new ResponseData(sysPartTimes));
//    }
//
//    //批量整个保存
//    public void batchWholeSave(){
//        Map<String, Object> paramMap = getParamMap();
//        List listMap = (List) paramMap.get("sysPartTime");
//        List<SysPartTime> sysPartTimes = BeanUtil.convertMapsToBeans(listMap, SysPartTime.class);
//        sysPartTimeService.saveWhole(sysPartTimes.toArray(new SysPartTime[]{}));
//        super.render(new ResponseData(sysPartTimes));
//    }
//
//    //根据id查询
//    public void queryById() {
//        Map map = getParamMap();
//        SysPartTime result =  sysPartTimeService.findOne(MapUtils.getString(map,"id"));
//        super.render(new ResponseData(result));
//    }
//
//    //更新
//    public void updateEntity() {
//        SysPartTime sysPartTime = getParamObj(SysPartTime.class);
//        sysPartTimeService.update(sysPartTime);
//        super.render(new ResponseData());
//    }
//
//    //根据id删除
//    public void deleteById() {
//        Map map = getParamMap();
//        sysPartTimeService.delete(MapUtils.getString(map,"id"));
//        super.render(new ResponseData());
//    }
//
//    //批量删除
//    public void batchDelete(){
//        Map map = getParamMap();
//        List<String> ids = (List<String>) map.get("ids");
//        sysPartTimeService.delete(ids.toArray(new String[]{}));
//        super.render(new ResponseData());
//    }
//
//    public void queryByParam(){
//        SysPartTime condition = getParamObj(SysPartTime.class);
//        List<SysPartTime> result = sysPartTimeService.queryByParams(condition);
//        super.render(new ResponseData(result));
//    }
//
//    public void queryByIds(){
//        Map<String, Object> map = getParamMap();
//        List<String> ids = (List<String>) map.get("ids");
//        List<SysPartTime> result = sysPartTimeService.findByIds(ids.toArray(new String[]{}));
//        super.render(new ResponseData(result));
//    }

    /**
     * 查询我的兼任信息
     */
    public void queryMyPartTime(){

        List<Map> loginUserPartTimeInfo = LoginUserCache.getLoginUserPartTimeInfo();
        if (loginUserPartTimeInfo != null) {
            super.render(new ResponseData(loginUserPartTimeInfo));
            return;
        }
        loginUserPartTimeInfo = new ArrayList<>();

        User loginUser = WebUtils.getLoginUser();


        SysPartTime currPartTime = new SysPartTime();

        currPartTime.setUserId(loginUser.getId());
        currPartTime.setJobsId(loginUser.getJobsId());
        currPartTime.setJobsIdVer(loginUser.getJobsIdVer());
        currPartTime.setCorpId(loginUser.getCorpId());
        currPartTime.setCorpIdVer(loginUser.getCorpIdVer());
        currPartTime.setOrgId(loginUser.getOrgId());
        currPartTime.setOrgIdVer(loginUser.getOrgIdVer());


        SysPartTime partTimeParam = new SysPartTime();
        partTimeParam.setUserId(loginUser.getId());
        List<SysPartTime> sysPartTimes = sysPartTimeService.queryByParams(partTimeParam);

        if (CollectionUtils.isEmpty(sysPartTimes)) {
            sysPartTimes = new ArrayList<>();
        }

        sysPartTimes.add(0,currPartTime);

        int idx = 0;


        for (SysPartTime partTime : sysPartTimes) {

            Map map = new HashMap();
//            map.put("id", partTime.getId());
            BeanUtil.copyPropertiesToMap(map,partTime);
            map.put("idx", idx++);

            String jobsId = (String) partTime.getJobsId();
            if (StringUtil.isNotBlank(jobsId)) {
                Jobs jobs = jobsService.findOne(jobsId);
                map.put("jobs",jobs);
            }

            String corpId = (String) partTime.getCorpId();
            if (StringUtil.isNotBlank(corpId)) {
                Org corp = orgService.findOne(corpId);
                map.put("corp",corp);
            }

            String orgId = (String) partTime.getOrgId();
            if (StringUtil.isNotBlank(orgId)) {
                Org org = orgService.findOne(orgId);
                map.put("org",org);
            }

            loginUserPartTimeInfo.add(map);

        }

        LoginUserCache.setLoginUserPartTimeInfo(loginUserPartTimeInfo);

        super.render(new ResponseData(loginUserPartTimeInfo));

    }

    /**
     * 切换法人
     */
    public void switchCorp(){

        Integer idx = MapUtils.getInteger(getParamMap(), "idx");

        String errMsg = "sys.switchCorp.error";

        ResponseData responseData = new ResponseData();
        if (idx == null) {
            ExceptionUtil.throwBaseRunTimeException(errMsg);

        }else {
            List<Map> loginUserPartTimeInfo = LoginUserCache.getLoginUserPartTimeInfo();

            if (CollectionUtils.isEmpty(loginUserPartTimeInfo) || (idx >= loginUserPartTimeInfo.size())) {
                ExceptionUtil.throwBaseRunTimeException(errMsg);
            }

            Map sysPartTimeMap = loginUserPartTimeInfo.get(idx);

            SysPartTime sysPartTime = new SysPartTime();
            BeanUtil.copyPropertiesToBean(sysPartTime, sysPartTimeMap);



            User loginUser = WebUtils.getLoginUser();
            loginUser.setCorpId(sysPartTime.getCorpId());
            loginUser.setCorpIdVer(sysPartTime.getCorpIdVer());

            loginUser.setOrgId(sysPartTime.getOrgId());
            loginUser.setOrgIdVer(sysPartTime.getOrgIdVer());

            loginUser.setJobsId(sysPartTime.getJobsId());
            loginUser.setJobsIdVer(sysPartTime.getJobsIdVer());

            WebUtils.setLoginUser(loginUser);

        }

        super.render(responseData);
    }



}
