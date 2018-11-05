package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.NoticeService;
import cn.flydiy.hangj.entity.Notice;

/**
* 通知记录表控制器
* Modify by v_wyaohu on 2018-8-31 17:10:58.
*/
@cn.flydiy.core.annotation.WebController
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    //保存
    public void saveEntity() {
        Notice notice = getParamObj(Notice.class);
        noticeService.saveEntity(notice);
        super.render(new ResponseData(notice));
    }

    //批量保存或者更新
    public void batchSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("notice");
        List<Notice> notices = BeanUtil.convertMapsToBeans(listMap, Notice.class);
        List<Notice> saveArr = new java.util.ArrayList<>();
        List<Notice> updateArr = new java.util.ArrayList<>();
        for (Notice notice : notices) {
            if (notice.getId() == null) {
            
                saveArr.add(notice);
            }else {
                updateArr.add(notice);
            }
        }
        noticeService.saveEntity(saveArr.toArray(new Notice[]{}));
        noticeService.updateEntity(updateArr.toArray(new Notice[]{}));
        super.render(new ResponseData(notices));
    }

    //批量整个保存
    public void batchWholeSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("notice");
        List<Notice> notices = BeanUtil.convertMapsToBeans(listMap, Notice.class);
        noticeService.saveWhole(notices.toArray(new Notice[]{}));
        super.render(new ResponseData(notices));
    }

    //根据id查询
    public void queryById() {
        Map map = getParamMap();
        Notice result =  noticeService.findOne(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
    }

    //更新
    public void updateEntity() {
        Notice notice = getParamObj(Notice.class);
        noticeService.updateEntity(notice);
        super.render(new ResponseData(notice));
    }

    public void saveOrUpdate(){
        Notice notice = getParamObj(Notice.class);
        if (notice.getId() == null) {
        
            noticeService.save(notice);
        }else{
            noticeService._updateEntityById(notice);
        }
        super.render(new ResponseData(notice));
    }

    //根据id删除
    public void deleteById() {
        Map map = getParamMap();
        noticeService.delete(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
    }

    //批量删除
    public void batchDelete(){
        Map map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        noticeService.delete(ids.toArray(new String[]{}));
        super.render(new ResponseData());
    }

    public void queryByParam(){
        Notice condition = getParamObj(Notice.class);
        List<Notice> result = noticeService.queryByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryPageByParam(){
        Notice condition = getParamObj(Notice.class);
        List<Notice> result = noticeService.queryPageByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryByIds(){
        Map<String, Object> map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        List<Notice> result = noticeService.findByIds(ids.toArray(new String[]{}));
        super.render(new ResponseData(result));
    }

    public void updateByParam(){
        Notice updateParam = getParamObj(Notice.class);
        noticeService.updateByParam(updateParam);
        super.render(new ResponseData());
    }


}
