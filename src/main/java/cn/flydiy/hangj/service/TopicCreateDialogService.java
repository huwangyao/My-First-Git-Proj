package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.TopicCreateDialogDto;

/**
* 新建话题弹窗
* Modify by v_wyaohu(胡王耀) on 2018-6-19 12:00:23.
*/
public interface TopicCreateDialogService {

    void saveDto(TopicCreateDialogDto dto);

    void commit(TopicCreateDialogDto dto);

    void batchCommit(java.util.List<TopicCreateDialogDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(TopicCreateDialogDto dto);

    void updateDtoNoSaveHistory(TopicCreateDialogDto dto);

    void deleteById(String id);


        //
        List<Map> queryHangjTypeForHangj_topicCreateDialog(cn.flydiy.hangj.entity.Type type);
    }
