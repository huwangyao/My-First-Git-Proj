
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

import cn.flydiy.hangj.dto.ExpertCreateDto;

/**
* 新建行家
* Modify by v_wyaohu(胡王耀) on 2018-6-14 11:58:36.
*/
@org.springframework.stereotype.Service
public class ExpertCreateServiceImpl implements ExpertCreateService {

    @Autowired
    private InfoService infoService;



    @Override
    public void saveDto(ExpertCreateDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();
        
        infoService.saveEntity(info);
    }

    @Override
    public void commit(ExpertCreateDto dto) {
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
    public void updateDto(ExpertCreateDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();

        
        infoService.updateEntity(info);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        if(_isMasterData){
            dto.setInfo(info);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(ExpertCreateDto dto) {
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
    public void batchCommit(List<ExpertCreateDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (ExpertCreateDto dto : dtos) {
                commit(dto);
            }
        }
    }



        //ExpertCreate的手工代码
        @Override
    public void saveExpert(ExpertCreateDto expertCreateDto) {
        cn.flydiy.hangj.entity.Info info = expertCreateDto.getInfo();
        // 获取账号
        String account = "";
        String name = info.getName();
        int index = name.indexOf("(");
        if(index!=-1){
            account = name.substring(0,index);
        }else{
            account = name;
        }
        // 找用户的信息
        cn.flydiy.hangj.dto.StaffDto loginStaffDto = cn.flydiy.hangj.cache.StaffInfoCache.staffCache.queryByEngName(account);
        if(loginStaffDto == null){
            throw new cn.flydiy.common.exception.BaseRunTimeException("esf.login.user.is.nothing",new String[]{account});
        }else{
            info.setPic(loginStaffDto.getPhotoUrl());  // 图片
            info.setUserId(loginStaffDto.getStaffID());  // 用户ID
            info.setName(loginStaffDto.getName());  // RTX姓名
            info.setOrgStr(loginStaffDto.getOrganizationFullName());  // 组织架构全名
            info.setPosition(loginStaffDto.getPositionName());  // 职位名
            String hireDate = loginStaffDto.getLastHireDate();
            if(StringUtil.isNotEmpty(hireDate)){    // 入职时间
                hireDate = hireDate+" 00:00:00";
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    info.setEntryDate(sdf.parse(hireDate));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        infoService.saveEntity(info);
    }
}


