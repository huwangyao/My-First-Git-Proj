package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.Attachment;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
public interface AttachmentRepository extends BaseRepository<Attachment> {
    /**
     * 根据ID数组更新文件bizId
     * @param ids
     * @param bizId
     */
    void updateBizIdByIds(String[] ids,String bizId);

    /**
     * 根据bizCode和bizId查询对应的文件信息
     * @param bizTable
     * @param bizId
     * @return
     */
    List<Attachment> queryByBizTableAndBizId(String bizTable, String bizId);

    /**
     * 根据url删除attachment表信息
     * @param urls
     */
    void deleteByUrls(String[] urls);


    List<Attachment> queryAttachByUrl(String url);

    List<Attachment> queryByUrls(String... urls);
}
