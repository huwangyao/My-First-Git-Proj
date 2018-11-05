package cn.flydiy.hangj.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections4.MapUtils;
import cn.flydiy.hangj.repository.NoticeRepository;


/**
* 通知记录表
* Modify by v_wyaohu on 2018-8-31 17:10:58.
*/
@org.springframework.stereotype.Service
public class NoticeServiceImpl extends cn.flydiy.core.service.BaseServiceImpl<cn.flydiy.hangj.entity.Notice>   implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<Map> queryMapByIds(String... ids){
        return noticeRepository.queryMapByIds(ids);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Notice> queryByParams(cn.flydiy.hangj.entity.Notice notice){
        return noticeRepository.queryByParams(notice);
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Notice notice){
        return noticeRepository.queryMapByParams(notice);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Notice> queryPageByParams(cn.flydiy.hangj.entity.Notice notice){
        return noticeRepository.queryPageByParams(notice);
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Notice notice){
        return noticeRepository.queryPageMapByParams(notice);
    }

    @Override
    public void saveEntity(cn.flydiy.hangj.entity.Notice... notice) {
        checkNotice(notice);
        for(cn.flydiy.hangj.entity.Notice entity:notice){
        }
        save(notice);
        for(cn.flydiy.hangj.entity.Notice entity:notice){
        }
    }

    @Override
    public void updateEntity(cn.flydiy.hangj.entity.Notice... notice) {
        checkNotice(notice);
        update(notice);
    }

    @Override
    public void updateEntityNoSaveHistory(cn.flydiy.hangj.entity.Notice... notice) {
        checkNotice(notice);
        _updateAndNoSaveHistory(notice);
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Notice updateParam) {
        noticeRepository.updateByParam(updateParam);
    }

    private void checkNotice(@NotNull cn.flydiy.hangj.entity.Notice... notice){
        if(notice.length==1){
        }else if(notice.length>1){
        }
    }



    public void setIsNewToZero(String... id) {
        noticeRepository.setIsNewToZero(id);
    }

}
