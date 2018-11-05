
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.TypeService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.HangjTypeEditDto;

/**
* 编辑类型弹窗
* Modify by v_wyaohu(胡王耀) on 2018-6-14 14:39:13.
*/
@org.springframework.stereotype.Service
public class HangjTypeEditServiceImpl implements HangjTypeEditService {

    @Autowired
    private TypeService typeService;



    @Override
    public void saveDto(HangjTypeEditDto dto) {
        cn.flydiy.hangj.entity.Type type = dto.getType();
        
        typeService.saveEntity(type);
    }

    @Override
    public void commit(HangjTypeEditDto dto) {
        cn.flydiy.hangj.entity.Type type = dto.getType();
        if(cn.flydiy.common.util.StringUtil.isEmpty(type.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.Type type = typeService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Type.class);
        BeanUtil.copyPropertiesToMap(map,type);
        result.put("type",map);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Type> types = typeService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Type.class);

        for (cn.flydiy.hangj.entity.Type type : types) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,type);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(HangjTypeEditDto dto) {
        cn.flydiy.hangj.entity.Type type = dto.getType();

        
        typeService.updateEntity(type);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Type.class);
        if(_isMasterData){
            dto.setType(type);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(HangjTypeEditDto dto) {
        cn.flydiy.hangj.entity.Type type = dto.getType();

        
        typeService.updateEntityNoSaveHistory(type);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Type.class);

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Type.class);
        cn.flydiy.hangj.entity.Type type = typeService.findOne(id);
        typeService.delete(id);
    }

    @Override
    public void batchCommit(List<HangjTypeEditDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (HangjTypeEditDto dto : dtos) {
                commit(dto);
            }
        }
    }



}


