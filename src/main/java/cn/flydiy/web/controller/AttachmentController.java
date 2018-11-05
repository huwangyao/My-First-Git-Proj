package cn.flydiy.web.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.JsonUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.dto.ImageSizeDto;
import cn.flydiy.web.entity.Attachment;
import cn.flydiy.web.service.AttachmentService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by flying on 16-12-26.
 */
@WebController
public class AttachmentController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    AttachmentService attachmentService;

    private ServletContext servletContext;

    public void queryByBizIdAndbizTable(){
        Map<String, Object> map = getParamMap();
        String bizId = MapUtils.getString(map,"bizId");
        String bizTable = MapUtils.getString(map,"bizTable");

        List<Map> result = attachmentService.queryByBizTableAndBizId(bizTable,bizId);
        ResponseData responseData = new ResponseData(result);
        super.render(responseData);
    }

    public void queryByUrls(){
        Map<String, Object> map = getParamMap();
        List<String> urls = (List<String>) map.get("urls");
        List<Map> result = attachmentService.queryByUrls(urls);
        super.render(new ResponseData(result));
    }

    public void upload(@RequestParam("flyFile") MultipartFile[] file, @RequestParam("bizTable")String bizTable, @RequestParam(value = "imageSize",required = false)String imageSize){
        int i = 0;
        List<ImageSizeDto> imageSizeDtos = null;
        if(StringUtil.isNotEmpty(imageSize)){
            imageSizeDtos = JsonUtil.getListByJson(imageSize, ImageSizeDto.class);
        }
        List<Map> result = attachmentService.upload(file,bizTable, imageSizeDtos);
        ResponseData responseData = new ResponseData(result);
        super.render(responseData);

    }

    public void deleteFiles(){
        Map<String, Object> map = getParamMap();
        List<String> idList= (List<String>) map.get("ids");
        List<String> urlList= (List<String>) map.get("urls");
        String[] ids = idList.toArray(new String[]{});
        String[] urls = urlList.toArray(new String[]{});
        ResponseData data = new ResponseData();
        attachmentService.deleteFiles(ids,urls);
        super.render(data);
    }

    public void downFiles() throws IOException {
        Map<String, Object> map = getParamMap();
        String url= MapUtils.getString(map,"url");
        String filename= MapUtils.getString(map,"filename");
        // 通过文件路径获得File对象
        File file = attachmentService.getDownLoadFile(url);

        WebUtils.renderDownload(file,filename);
    }

    public void updateImgFile(){
        Map<String, Object> map = getParamMap();
        Attachment attachment = getParamObj(Attachment.class);
        String imgData= MapUtils.getString(map,"imgData");
        attachmentService.updateImgFile(attachment,imgData);
        ResponseData data = new ResponseData();
        super.render(data);
    }

    public void getImageByUrl() throws IOException{
        Map<String, Object> map = getParamMap();
        String url= MapUtils.getString(map,"url");
        // 通过文件路径获得File对象
        File file = attachmentService.getDownLoadFile(url);
        Attachment attachment = attachmentService.queryAttachByUrl(url);

        WebUtils.renderImage(FileUtils.readFileToByteArray(file),attachment.getMimeContentType());
    }

}