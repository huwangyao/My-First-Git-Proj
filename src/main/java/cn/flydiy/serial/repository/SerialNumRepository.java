package cn.flydiy.serial.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.serial.entity.SerialNum;

import java.util.List;
import java.util.Map;


/**
* 生成单号
* Created by flydiy on 2017-04-12 09:03:28.
*/
public interface SerialNumRepository extends BaseRepository<SerialNum>{


    SerialNum queryByTableAndCol(String table, String col, String type, String orgId,String corp);

    void updateSerialAndDate(String id, long serialNumber, String date);
}
