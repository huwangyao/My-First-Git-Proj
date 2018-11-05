package cn.flydiy.core.service;

import cn.flydiy.core.entity.ApiAddressVar;

import java.util.List;

/**
 * Created by player on 2018/5/11.
 */
public interface ApiAddressVarService extends BaseService<ApiAddressVar>{
    void batchSave(List<ApiAddressVar> addressVars);

    List<ApiAddressVar> queryAll();

}
