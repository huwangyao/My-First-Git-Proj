package cn.flydiy.hangj.schedule;

import cn.flydiy.common.util.JsonUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.HangjiaJobService;
import cn.flydiy.schedule.entity.ScheduleJob;
import cn.flydiy.schedule.scheduler.BaseScheduler;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 话题预约两周后如果还没处理，系统自动关闭。并发送通知和邮件
public class CancelOrderAfterTwoWeekJob extends BaseScheduler {

    @Override
    protected void onSchedulerRun(ScheduleJob scheduleJob) {
        HangjiaJobService bean = SpringContext.getBean(HangjiaJobService.class);
        bean.cancelOrderAfterTwoWeek();

    }
}
