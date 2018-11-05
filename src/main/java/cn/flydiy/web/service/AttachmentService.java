package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.dto.ImageSizeDto;
import cn.flydiy.web.entity.Attachment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface AttachmentService extends BaseService<Attachment> {
    Attachment uploadFile(byte[] bytes, String filename, String bizTable, String contentType);

    Attachment uploadBase64(String base64, String filename, String bizTable, String contentType);

    List<Map> upload(MultipartFile[] file, String bizTable, List<ImageSizeDto> imageSizeDtos);

    /**
     * 获取文件上传路径
     */
    String getFilePath();

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
    List<Map> queryByBizTableAndBizId(String bizTable,String bizId);

    /**
     * 删除文件(数据库信息+文件服务器文件内容)
     * @param ids
     * @param urls
     */
    void deleteFiles(String[] ids, String[] urls);

    /**
     * 获取下载文件
     * @param url
     * @return
     */
    File getDownLoadFile(String url);

    /**
     * 更新图片文件内容
     * @param attachment
     * @param imgData
     */
    void updateImgFile(Attachment attachment, String imgData);

    /**
     * 根据url查询附件表信息
     * @param url
     * @return
     */
    Attachment queryAttachByUrl(String url);

    /**
     * 根据url数组查询对应的文件信息
     * @param urls
     * @return
     */
    List<Map> queryByUrls(List<String> urls);
}