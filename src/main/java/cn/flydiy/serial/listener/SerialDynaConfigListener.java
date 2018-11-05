package cn.flydiy.serial.listener;

import cn.flydiy.core.listener.DynaConfigListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by flying on 16-11-26.
 */
@Component
public class SerialDynaConfigListener extends DynaConfigListener {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Set<String> getNoAuthFilterPath() {
        Set<String> paths = new HashSet<>();
        paths.add("/qrCode!analyzeRule");   //解析规则重定向
        paths.add("/qrCode!generateQrCode");//生成二维码
        paths.add("/qrCode!generateQrCodeByRule");//根据规则生成二维码
        paths.add("/qrCode!generateBarCode");//生成条形码
        return paths;
    }
}
