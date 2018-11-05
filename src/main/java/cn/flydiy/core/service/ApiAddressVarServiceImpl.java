package cn.flydiy.core.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.core.entity.ApiAddressVar;
import cn.flydiy.core.repository.ApiAddressVarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by player on 2018/5/11.
 */
@Service
public class ApiAddressVarServiceImpl extends BaseServiceImpl<ApiAddressVar> implements ApiAddressVarService {

    @Autowired
    private ApiAddressVarRepository apiAddressVarRepository;

    @Override
    public void batchSave(List<ApiAddressVar> addressVars) {
        apiAddressVarRepository.deleteAll();
        Set<String> keys = new HashSet<>();
        for (ApiAddressVar addressVar : addressVars) {
            if(!keys.add(addressVar.getCode())){
                throw new BaseRunTimeException("含有重复的key",new String[]{addressVar.getCode()});
            }
        }
        apiAddressVarRepository.save(addressVars.toArray(new ApiAddressVar[addressVars.size()]));
    }

    @Override
    public List<ApiAddressVar> queryAll() {
        return apiAddressVarRepository.findAll();
    }
}
