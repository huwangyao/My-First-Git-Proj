package cn.flydiy.core.repository;

import cn.flydiy.core.entity.Dict;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
public interface DictRepository extends BaseRepository<Dict> {
    /**
     * 查询枚举项
     *
     * @param title 搜索条件
     * @return
     */
    List<Dict> queryEnumTerm(String title);

    List<Dict> queryEnumByCode(String... code);

    void deleteByCodes(String... codes);
}
