package cn.flydiy.core.service;

import cn.flydiy.core.entity.Formula;
import cn.flydiy.core.repository.FormulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by player on 2017/10/26.
 */
@Service
public class FormulaServiceImpl extends BaseServiceImpl<Formula> implements FormulaService{

    @Autowired
    private FormulaRepository formulaRepository;

    @Override
    public List<Formula> queryByTableCode(String tableCode) {
        return formulaRepository.queryByTableCode(tableCode);
    }
}
