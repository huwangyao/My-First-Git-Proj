
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.HomePageDto;

/**
* 首页
* Modify by v_sunlli(李小阳) on 2018-8-14 15:17:25.
*/
@org.springframework.stereotype.Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private InfoService infoService;



    @Override
    public void saveDto(HomePageDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();
        
        infoService.saveEntity(info);
    }

    @Override
    public void commit(HomePageDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();
        if(cn.flydiy.common.util.StringUtil.isEmpty(info.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.Info info = infoService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        BeanUtil.copyPropertiesToMap(map,info);
        result.put("info",map);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Info> infos = infoService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);

        for (cn.flydiy.hangj.entity.Info info : infos) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,info);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(HomePageDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();

        
        infoService.updateEntity(info);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        if(_isMasterData){
            dto.setInfo(info);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(HomePageDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();

        
        infoService.updateEntityNoSaveHistory(info);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        cn.flydiy.hangj.entity.Info info = infoService.findOne(id);
        infoService.delete(id);
    }

    @Override
    public void batchCommit(List<HomePageDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (HomePageDto dto : dtos) {
                commit(dto);
            }
        }
    }



    public List<Map> dataTableForHangj_homePage(cn.flydiy.hangj.entity.Info info) {
        List<Map> result = infoService.queryPageMapByParams(info);
        return result;
    }
}


