package cn.flydiy.serial.controller;

import cn.flydiy.base.util.SystemUtil;
import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.serial.entity.QrCodeInfo;
import cn.flydiy.serial.entity.QrCodeRule;
import cn.flydiy.serial.service.QrCodeInfoService;
import cn.flydiy.serial.service.QrCodeRuleService;
import cn.flydiy.serial.util.QRCodeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by player on 2017/9/14.
 */
@WebController
public class QrCodeController extends BaseController{

    @Autowired
    private QrCodeRuleService qrCodeRuleService;

    @Autowired
    private QrCodeInfoService qrCodeInfoService;

    /**
     * 生成二维码
     */
    public void generateQrCode(){
        Map<String, Object> paramMap = getParamMap();
        String content = MapUtils.getString(paramMap, "content");
        Integer width = MapUtils.getInteger(paramMap, "width",200);
        Integer height = MapUtils.getInteger(paramMap, "height",200);
        byte[] bytes = QRCodeUtil.QRCodeToBytes(content, width, height);
        WebUtils.renderImage(bytes,"image/png");
    }

    /**
     * 生成条形码
     */
    public void generateBarCode(){
        Map<String, Object> paramMap = getParamMap();
        String content = MapUtils.getString(paramMap, "content");
        boolean hasNumber = MapUtils.getBoolean(paramMap, "hasNumber",false);
        Integer width = MapUtils.getInteger(paramMap, "width",200);
        Integer height = MapUtils.getInteger(paramMap, "height",200);
        byte[] bytes = QRCodeUtil.BarCodeToBytes(content, width, height,hasNumber);
        WebUtils.renderImage(bytes,"image/png");
    }

    /**
     * 解析规则
     * @throws IOException
     */
    public void analyzeRule() throws IOException {
        Map<String, Object> paramMap = getParamMap();
        String rule = MapUtils.getString(paramMap, "rule","");

        if(StringUtil.isEmpty(rule)){
            render(new ResponseData("该二维码无法解析!"));
            return ;
        }

        QrCodeInfoService qrCodeInfoService = SpringContext.getBean(QrCodeInfoService.class);
        List<QrCodeInfo> qrCodeInfos = qrCodeInfoService.queryByOnlyCode(rule);

        if(CollectionUtils.isEmpty(qrCodeInfos)){
          if (rule.length() == 63 && rule.split("-").length>1) {
          }else {
              render(new ResponseData("该二维码无法解析"));
              return ;
          }
        }else {
            rule = qrCodeInfos.get(0).getQrCodeInfo();
        }

        String[] split = rule.split("-");

        String ruleId = split[0];
        String businessId = split[1];
        QrCodeRule qrCodeRule = qrCodeRuleService.findOne(ruleId);
        String routePath = qrCodeRule.getRoutePath();
        String webAddress = SystemUtil.getWebAddress();
        WebUtils.getResponse().sendRedirect(webAddress+"/#"+routePath+"?id="+businessId);
    }

    /**
     * 根据规则生成二维码
     */
    public void generateQrCodeByRule(){
        Map<String, Object> paramMap = getParamMap();
        String onlyCode = MapUtils.getString(paramMap, "rule");
        byte[] bytes = QRCodeUtil.getImageByQrColumn(onlyCode);
        WebUtils.renderImage(bytes,"image/png");
    }
}
