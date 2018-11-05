package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.HangjTopicDialogDto;

/**
* 行家话题信息弹窗
* Modify by v_sunlli on 2018-8-23 11:19:58.
*/
public interface HangjTopicDialogService {

    void saveDto(HangjTopicDialogDto dto);

    void commit(HangjTopicDialogDto dto);

    void batchCommit(java.util.List<HangjTopicDialogDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(HangjTopicDialogDto dto);

    void updateDtoNoSaveHistory(HangjTopicDialogDto dto);

    void deleteById(String id);


        //
        List<Map> queryHangjTypeForHangj_hangjTopicDialog(cn.flydiy.hangj.entity.Type type);
    }
