package cn.flydiy.core.repository;

import cn.flydiy.core.entity.DictItem;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
public interface DictItemRepository extends BaseRepository<DictItem> {
    /**
     * 根据枚举项的code查询对应的枚举值,当有搜索条件时加上搜索条件title
     *
     * @param code  枚举项code编码
     * @param title 搜索关键字
     * @return
     */
    List<DictItem> queryEnumItemByEnumCode(String code, String title);

    /**
     * 根据字典值value和字典项code查询对应字典值信息
     * @param value
     * @param code
     * @return
     */
    List<DictItem> queryDictItemByItemValueAndDictCode(String value, String code);

    /**
     * 根据枚举项的code值删除对应的枚举值
     *
     * @param code
     */
    void deleteEnumItemByEnumCode(String... code);

    void updateNewCodeByOldCode(String oldCode, String code);

    List<DictItem> queryAllDictItem();

    List<DictItem> queryDictItemByCodes(String[] codes);
}
