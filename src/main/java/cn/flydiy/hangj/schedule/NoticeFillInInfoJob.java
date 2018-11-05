package cn.flydiy.hangj.schedule;

import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.HangjiaJobService;
import cn.flydiy.schedule.entity.ScheduleJob;
import cn.flydiy.schedule.scheduler.BaseScheduler;

//  1、对行家已接受预约的话题，如果还没填写地点，则每天10点通知他。（通知中心+邮件+企业微信+短信）
//  2、行家接受预约后一周，如果还没填写地点，就自动关闭，并且发送通知。（通知中心+邮件+企业微信+短信）
//  3、每天10点执行
public class NoticeFillInInfoJob extends BaseScheduler {

    @Override
    protected void onSchedulerRun(ScheduleJob scheduleJob) {
        HangjiaJobService bean = SpringContext.getBean(HangjiaJobService.class);
        bean.noticeFillInInfo();

    }
}
