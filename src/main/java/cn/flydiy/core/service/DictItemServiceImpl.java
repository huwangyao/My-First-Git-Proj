package cn.flydiy.core.service;

import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.dto.DictItemDto;
import cn.flydiy.core.entity.Dict;
import cn.flydiy.core.entity.DictItem;
import cn.flydiy.core.repository.DictItemRepository;
import cn.flydiy.common.util.BeanUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoo on 2017/2/3.
 */
@Service
public class DictItemServiceImpl extends BaseServiceImpl<DictItem> implements DictItemService {

    private static final Logger logger = LogManager.getLogger();


    @Autowired
    DictItemRepository dictItemRepository;

    @Override
    public List<DictItem> queryAllDictItem() {
        List<DictItem> dictItemList = dictItemRepository.queryAllDictItem();
        return dictItemList;
    }

    @Override
    public List<Map> queryEnumItemByEnumCode(String value,String code, String title) {
        List<DictItem> resultList = new ArrayList<DictItem>();
        if(StringUtil.isNotEmpty(value)){
            DictItem dictItem = this.queryDictItemByItemValueAndDictCode(value,code);
            resultList.add(dictItem);
        }else{
            resultList = dictItemRepository.queryEnumItemByEnumCode(code, title);
        }
        List<Map> result = BeanUtil.convertBeansToMaps(resultList);
        for(Map map : result){
            String name = (String) map.get("name");
            map.put("label",name);
        }

        return result;
    }

    @Override
    public DictItem queryDictItemByItemValueAndDictCode(String value, String code) {
        List<DictItem> dictItemsList = dictItemRepository.queryDictItemByItemValueAndDictCode(value,code);
        if(dictItemsList.size()<1){
            return new DictItem();
        }
        return dictItemsList.get(0);
    }

    @Override
    public List<Map> queryEnumItemByEnumCode(String code, String title) {
        List<DictItem> resultList = dictItemRepository.queryEnumItemByEnumCode(code, title);
        return BeanUtil.convertBeansToMaps(resultList);
    }

    @Override
    public void saveEnumItemAttrs(DictItemDto enumItemDto) {
        Dict dict = enumItemDto.getDict();
        // 先删除这个枚举项的所有值
        dictItemRepository.deleteEnumItemByEnumCode(dict.getCode());
        // 重新保存枚举值
        List<DictItem> diyItemAttrs = enumItemDto.getDictItems();
        dictItemRepository.save(diyItemAttrs.toArray(new DictItem[]{}));
    }


    @Override
    public void updateNewCodeByOldCode(String oldCode, String code) {
        dictItemRepository.updateNewCodeByOldCode(oldCode,code);
    }

    @Override
    public Map queryDictItemListByDictCodes(String[] codes) {
        Map map = new HashMap();
        // 将所有字典值查出来
//        List<DictItem> allDictItem = this.queryAllDictItem();
        // 遍历传入的code值
        if(codes.length>0){
            List<DictItem> dictItems = this.queryDictItemByCodes(codes);
            for(String code:codes){
                List<DictItem> result = new ArrayList();
                for(DictItem dictItem:dictItems){
                    if(StringUtil.equalsIgnoreCase(code,dictItem.getCode())){
                        result.add(dictItem);
                    }
                }
                map.put(code,BeanUtil.convertBeansToMaps(result));
            }
            for(String code:codes){
                List<Map> itemList = (List<Map>)map.get(code);
                for(Map temp:itemList){
                    String name = (String) temp.get("name");
                    temp.put("label",name);
                }
            }
        }
        return map;
    }

    @Override
    public List<DictItem> queryDictItemByCodes(String[] codes) {
        return dictItemRepository.queryDictItemByCodes(codes);
    }

    public void deleteByCodes(String... codes) {
        dictItemRepository.deleteEnumItemByEnumCode(codes);
    }
}
