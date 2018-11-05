package cn.flydiy.core.service;

import cn.flydiy.core.entity.Formula;

import java.util.List;

/**
 * Created by player on 2017/10/26.
 */
public interface FormulaService extends BaseService<Formula>{
    List<Formula> queryByTableCode(String tableCode);
}
