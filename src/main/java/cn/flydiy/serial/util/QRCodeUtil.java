package cn.flydiy.serial.util;

import cn.flydiy.base.util.SystemUtil;
import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.serial.entity.QrCodeInfo;
import cn.flydiy.serial.entity.QrCodeRule;
import cn.flydiy.serial.service.QrCodeInfoService;
import cn.flydiy.serial.service.QrCodeRuleService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.lang3.RandomStringUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.swing.text.html.ImageView;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * Created by player on 2017/9/6.
 */
public class QRCodeUtil {

    public static final String RANDOM = "RANDOM";

    public static final String SERIAL = "SERIAL";

    public static final int QR_CODE_WIDTH = 200;

    public static final int QR_CODE_HEIGHT = 200;

    private QRCodeUtil(){}

    public static byte[] QRCodeToBytes(String content){
        return QRCodeToBytes(content,QR_CODE_WIDTH,QR_CODE_HEIGHT);
    }

    public static byte[] QRCodeToBytes(String content,int width,int height){
        byte[] bytes = null;
        String format = "png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = null;// 生成矩阵
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
            bytes = outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static byte[] getQRCodeByRule(String tableCode,String attrCode,String businessId){
        QrCodeRuleService qrCodeRuleService = SpringContext.getBean(QrCodeRuleService.class);
        QrCodeRule qrCodeRule = qrCodeRuleService.queryByAttrCode(tableCode, attrCode);
        if (qrCodeRule == null){
            throw new BaseRunTimeException("没有配置该二维码生成规则",new String[]{tableCode,attrCode});
        }
        String routePath = qrCodeRule.getRoutePath();
        int width = qrCodeRule.getWidth()!=null ? qrCodeRule.getWidth() : QR_CODE_WIDTH;
        int height = qrCodeRule.getHeight()!=null ? qrCodeRule.getHeight() : QR_CODE_HEIGHT;
        return QRCodeToBytes(routePath,width,height);
    }

    public static QrCodeRule getQrCodeRule(String tableCode,String attrCode){
        QrCodeRuleService qrCodeRuleService = SpringContext.getBean(QrCodeRuleService.class);
        QrCodeRule qrCodeRule = qrCodeRuleService.queryByAttrCode(tableCode, attrCode);
        if (qrCodeRule == null){
            throw new BaseRunTimeException("没有配置该二维码生成规则",new String[]{tableCode,attrCode});
        }
        return qrCodeRule;
    }

    public static String getQrCodeRuleId(String tableCode,String attrCode){
        QrCodeRule qrCodeRule = getQrCodeRule(tableCode, attrCode);
        return qrCodeRule.getId();
    }

    public static String getOnlyCode(String tableCode,String attrCode,String businessId){
        String result = "";

        QrCodeInfoService qrCodeInfoService = SpringContext.getBean(QrCodeInfoService.class);

        QrCodeRule qrCodeRule = getQrCodeRule(tableCode, attrCode);
        if(RANDOM.equals(qrCodeRule.getType())){
            Integer randomLength = qrCodeRule.getRandomLength()==null ? 12 : qrCodeRule.getRandomLength();
            result = RandomStringUtils.randomAlphanumeric(randomLength);
            boolean flag = true;
            while (flag){
                List<QrCodeInfo> qrCodeInfos = qrCodeInfoService.queryByOnlyCode(result);
                if(CollectionUtils.isEmpty(qrCodeInfos)){
                    flag = false;
                }else {
                    result = RandomStringUtils.randomAlphanumeric(randomLength);
                }
            }
        }else if (SERIAL.equals(qrCodeRule.getType())){
            result = SerialNumberUtils.getSerialNum(tableCode, attrCode);
        }else {
            throw new BaseRunTimeException("没有特定类型");
        }

        QrCodeInfo qrCodeInfo = new QrCodeInfo();
        qrCodeInfo.setOnlyCode(result);
        qrCodeInfo.setQrCodeInfo(getQrCodeRuleId(tableCode,attrCode) + "-" + businessId);
        qrCodeInfoService.save(qrCodeInfo);
        return result;
    }

    /**
     *根据二维码字段获取图片二进制
     * @return
     */
    public static byte[] getImageByQrColumn(String onlyCode){
        String webAddress = SystemUtil.getWebAddress();
        String api = webAddress+"/api/serial/qrCode!analyzeRule?rule="+onlyCode;
        return QRCodeToBytes(api);
    }

    public static byte[] BarCodeToBytes(String content, Integer width, Integer height,boolean hasNumber) {
        byte[] bytes = null;

        if(!hasNumber){
            String format = "png";// 图像类型
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = null;// 生成矩阵
            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
                bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODABAR, width, height, hints);
                MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
                bytes = outputStream.toByteArray();
            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }
        }else {
            Code39Bean bean = new Code39Bean();

            // 精细度
            final int dpi = 200;
            // module宽度
            final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
            // 配置对象
            bean.setModuleWidth(moduleWidth);
            bean.setWideFactor(4);
            bean.doQuietZone(false);

            String format = "image/png";
            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {

                // 输出到流
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(outputStream, format, dpi,
                        BufferedImage.TYPE_BYTE_BINARY, false, 0);

                // 生成条形码
                bean.generateBarcode(canvas, content);

                // 结束绘制
                canvas.finish();

                bytes = outputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bytes;
    }


}
