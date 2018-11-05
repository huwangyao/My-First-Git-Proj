package cn.flydiy.web.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.*;
import cn.flydiy.core.common.ResourceUtils;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.web.dto.ImageSizeDto;
import cn.flydiy.web.entity.Attachment;
import cn.flydiy.web.repository.AttachmentRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment> implements AttachmentService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public String getFilePath(){
//        String filePath = SpringContext.getWebPath();
        String filePath = ResourceUtils.getUploadfilePath();
        if(StringUtil.isEmpty(filePath)){
            ExceptionUtil.throwBaseRunTimeException("sys.fileupload.path.error");
        }
        return filePath+"/attachment";
    }

//    @Override
    public File getDownLoadFile(String url) {
        String path = getFilePath();
        String pathname = path + "/" + url;
        File file = new File(pathname);
        if(!file.exists()){
            ExceptionUtil.throwBaseRunTimeException("sys.fileupload.noexists.error",new String[]{pathname});
        }
        return file;
    }

    @Override
    public void updateImgFile(Attachment attachment, String imgData) {
        // 将base64解码成图片
        // 将图片覆盖掉原路径的文件
        int index = imgData.indexOf("base64,");
        String newImgData = imgData.substring(index+7);
        String path = this.getFilePath()+"/"+attachment.getUrl();
        this.generateImage(newImgData,path);
    }

    @Override
    public Attachment queryAttachByUrl(String url) {
        Attachment attachment = new Attachment();
        List<Attachment> list = attachmentRepository.queryAttachByUrl(url);
        if(list.size()>0){
            attachment = list.get(0);
        }
        return attachment;
    }

    @Override
    public Attachment uploadFile(byte[] bytes, String filename, String bizTable, String mimeContentType) {

        // 上传路径
        String filePath = getFilePath();

        // 目标文件夹
        File dir = new File(filePath);
        if (!dir.exists()) {
            // 如果文件夹不存在，则创建文件夹
            dir.mkdirs();
        }

        // 文件名
        String uu = UUID.randomUUID().toString();
        String suffix = "";
        if (filename.contains(".")) {
            suffix = filename.substring(filename.lastIndexOf("."));
        }
        String uid = uu + suffix;

        // 完整路径
        File file1 = new File(dir, uid);

        try {
            FileUtils.writeByteArrayToFile(file1,bytes);
        } catch (Exception e) {
            ExceptionUtil.throwBaseRunTimeException("sys.fileupload.error", new String[]{filename}, e);
        }

        Attachment attachment = new Attachment(bizTable, filename, file1.getTotalSpace(), uid, mimeContentType);

        attachmentRepository.save(attachment);


        return attachment;
    }

    @Override
    public Attachment uploadBase64(String base64, String filename, String bizTable, String mimeContentType) {


        if (mimeContentType==null) {
            mimeContentType = "image/jpg";
        }

        return this.uploadFile(getBase64Bytes(base64), filename, bizTable, mimeContentType);

    }

    private static byte[] getBase64Bytes(String imgData) {

        int index = imgData.indexOf("base64,");
        String newImgData = imgData.substring(index + 7);
        //Base64解码
        byte[] b = Base64.decodeBase64(newImgData);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }

        return b;
    }

    @Override
    public List<Map> upload(MultipartFile[] file, String bizTable, List<ImageSizeDto> imageSizeDtos) {
        List<Map> result = new ArrayList();

        List<Attachment> saveList = new ArrayList();
        List<String> support = Arrays.asList(".jpg", ".png",".jpeg","gif");

        for(int i = 0;i<file.length;i++){
            MultipartFile multipartFile = file[i];

            String filePath = getFilePath();

            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = multipartFile.getOriginalFilename();
            String uu = UUID.randomUUID().toString();
            String suffix = "";
            if (filename.contains(".")) {
                suffix = filename.substring(filename.lastIndexOf("."));
            }
            String uid = uu + suffix;

            File file1 = new File(dir,uid);

            try {
                multipartFile.transferTo(file1);
            } catch (Exception e) {
                ExceptionUtil.throwBaseRunTimeException("sys.fileupload.error",new String[]{filename},e);
            }

            Attachment attachment = new Attachment(bizTable,filename,multipartFile.getSize(),uid,multipartFile.getContentType());

            attachmentRepository.save(attachment);
            saveList.add(attachment);

            //说明需要缩略图
            if(CollectionUtils.isNotEmpty(imageSizeDtos)){
                if(!support.contains(suffix)){
                    //如果不是支持的类型
                    throw new BaseRunTimeException("sys.noAllow.thumbnail.otherFile");
                }
                for (ImageSizeDto imageSizeDto : imageSizeDtos) {
                    String thumbnailUrl = uu + "_" + imageSizeDto.getWidth() + "_" + imageSizeDto.getHeight() + suffix;
                    try {
                        File thumbnailFile = new File(dir,thumbnailUrl);
                        Thumbnails.of(file1).size(imageSizeDto.getWidth(), imageSizeDto.getHeight()).toFile(thumbnailFile);
                        Attachment thumbnail = new Attachment(bizTable,filename,thumbnailFile.length(),thumbnailUrl,multipartFile.getContentType());
                        attachmentRepository.save(thumbnail);
//                        saveList.add(thumbnail);
                    } catch (IOException e) {
                        throw new BaseRunTimeException("sys.generate.thumbnail.error");
                    }
                }
            }
        }
        result = BeanUtil.convertBeansToMaps(saveList);
        this.getImgBaseData(result);

        return result;
    }

    public void getImgBaseData(List<Map> result){
        String path = getFilePath();
        for(Map map:result){
            String picData = "";
            String url = (String) map.get("url");
            String type = ((String)map.get("mimeContentType")).substring(0,6);
            if(StringUtil.equalsIgnoreCase(type,"image/")){
                File imageFile = new File(path +"/"+url);
                String imgData = this.getImgStr(path +"/"+url);
                map.put("imgData","data:image/png;base64,"+imgData);
            }
        }
    }

    @Override
    public void updateBizIdByIds(String[] ids, String bizId) {
        attachmentRepository.updateBizIdByIds(ids,bizId);
    }

    @Override
    public List<Map> queryByBizTableAndBizId(String bizTable, String bizId) {
        List<Map> result = BeanUtil.convertBeansToMaps(attachmentRepository.queryByBizTableAndBizId(bizTable,bizId));
        this.getImgBaseData(result);
        return result;
    }

    @Override
    public List<Map> queryByUrls(List<String> urls) {
        List<Map> result = BeanUtil.convertBeansToMaps(attachmentRepository.queryByUrls(urls.toArray(new String[]{})));
        this.getImgBaseData(result);
        return result;
    }

    @Override
    public void deleteFiles(String[] ids, String[] urls) {

        String filePath = getFilePath();
        File dir = new File(filePath);
        // 删除文件服务器文件
        for(int i = 0;i<urls.length;i++){
            File file = new File(dir,urls[i]);

            file.delete();
        }
        attachmentRepository.deleteByUrls(urls);

    }

    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public static String getImgStr(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        File file = new File(imgFile);
        if(!file.exists()){
            ExceptionUtil.throwBaseRunTimeException("sys.fileupload.noexists.error",new String[]{imgFile});
        }
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            }
        catch (IOException e)
        {
            e.printStackTrace();
            }
        return new String(Base64.encodeBase64(data));
        }
    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr 图片数据
     * @param imgFilePath 保存图片全路径地址
     * @return
     */
        public static boolean generateImage(String imgStr,String imgFilePath){
        //
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
                {
                if(b[i]<0)
                    {//调整异常数据
                    b[i]+=256;
                    }
                }
            //生成jpeg图片

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
            }
        catch (Exception e)
        {
            return false;
            }
        }

}
