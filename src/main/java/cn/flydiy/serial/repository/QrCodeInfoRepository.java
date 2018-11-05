package cn.flydiy.serial.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.serial.entity.QrCodeInfo;

import java.util.List;

/**
 * Created by player on 2017/9/20.
 */
public interface QrCodeInfoRepository extends BaseRepository<QrCodeInfo>{
    List<QrCodeInfo> queryByOnlyCode(String onlyCode);
}
