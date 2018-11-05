package cn.flydiy.serial.service;

import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.serial.entity.QrCodeInfo;
import cn.flydiy.serial.repository.QrCodeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by player on 2017/9/20.
 */
@Service
public class QrCodeInfoServiceImpl extends BaseServiceImpl<QrCodeInfo> implements QrCodeInfoService{

    @Autowired
    private QrCodeInfoRepository qrCodeInfoRepository;

    @Override
    public List<QrCodeInfo> queryByOnlyCode(String onlyCode) {
        return qrCodeInfoRepository.queryByOnlyCode(onlyCode);
    }
}
