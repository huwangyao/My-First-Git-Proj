package cn.flydiy.core.cache;

import cn.flydiy.core.common.SpringContext;
import cn.flydiy.metadata.entity.GenBizObject;
import cn.flydiy.metadata.entity.GenPageContent;
import cn.flydiy.metadata.service.GenBizObjectService;
import cn.flydiy.metadata.service.GenPageContentService;
import cn.flydiy.web.service.MenuService;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by player on 2018/1/18.
 */
public class CacheVoiceAssistant {

    public static final String DICT_CACHE_PREFIX = "DICT_CACHE_PREFIX";

    public static final String OBJ_FLAG = "obj";

    public static final String PAGE_FLAG = "page";

    public static final String MENU_FLAG = "menu";

    /**
     * 获取需要对比的字符串数组
     * @param prefix
     * @param flag
     * @return
     */
    public static List<String> getCompareArr(String prefix, String flag){
        List<String> compareArr = null;
        if(CacheManager.get(prefix,flag)==null){
            compareArr = new ArrayList<>();
            if(OBJ_FLAG.equals(flag)){
                List<GenBizObject> genBizObjects = SpringContext.getBean(GenBizObjectService.class).queryAll();
                for (GenBizObject genBizObject : genBizObjects) {
                    compareArr.add(genBizObject.getEntityName());
                }
            }else if(PAGE_FLAG.equals(flag)){
                List<GenPageContent> genPageContents = SpringContext.getBean(GenPageContentService.class).queryAllPageName();
                for (GenPageContent genPageContent : genPageContents) {
                    compareArr.add(genPageContent.getPageName());
                }
            }else if(MENU_FLAG.equals(flag)){
                List<Map> menus = SpringContext.getBean(MenuService.class).findAllSort();
                for (Map menu : menus) {
                    String menuName = MapUtils.getString(menu, "name");
                    compareArr.add(menuName);
                }
            }
            CacheManager.put(prefix,flag,compareArr);
        }else{
            compareArr = (List<String>) CacheManager.get(prefix,flag);
        }
        return compareArr;
    }
}
