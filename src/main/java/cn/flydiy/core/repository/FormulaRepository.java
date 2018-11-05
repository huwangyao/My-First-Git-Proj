package cn.flydiy.core.repository;

import cn.flydiy.core.entity.Formula;

import java.util.List;

/**
 * Created by player on 2017/10/26.
 */
public interface FormulaRepository extends BaseRepository<Formula>{
    List<Formula> queryByTableCode(String tableCode);
}
