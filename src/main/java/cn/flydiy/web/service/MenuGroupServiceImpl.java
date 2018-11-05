package cn.flydiy.web.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.constant.Constant;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.entity.MenuGroup;
import cn.flydiy.web.repository.MenuGroupRepository;
import cn.flydiy.sys.service.MenuService;//菜单权限只要注释掉这个就能取消
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class MenuGroupServiceImpl extends BaseServiceImpl<MenuGroup> implements MenuGroupService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MenuGroupRepository menuGroupRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private cn.flydiy.web.service.MenuService genMenuService;

    @Autowired
    private MenuLangService menuLangService;

    @Override
    public void deleteMenuGroupById(String id) {
        //根据菜单组Id查询是否存在下级菜单
        List<Map> childGroup = menuGroupRepository.queryByParentId(id);
        if(CollectionUtils.isNotEmpty(childGroup)){
            throw new BaseRunTimeException("can not delete exsit child menuGroup");
        }

        //根据菜单Id查询是是否存在菜单项
        childGroup = menuService.queryByGroupId(id);
        if(CollectionUtils.isNotEmpty(childGroup)){
            throw new BaseRunTimeException("can not delete exsit child menu");
        }

        //TODO 移除菜单组的缓存
//        MenuGroup item = (MenuGroup) menuGroupDao.get(id);

        _deleteByIds(id);

        //MenuCacheUtil.removeCacheMenuGroup(item);
//        updateCacheMenuGroup(item.getTYPE());
    }

    @Override
    public List<Map> queryGenMenuTreeData() {
        //TODO 应该以后是按组织来查
        List<Map> allGroup = BeanUtil.convertBeansToMaps(menuGroupRepository.findAllSort());

        List<Map> allMenu = genMenuService.findAllSort();
        List<Map> allMenuLang = menuLangService.queryAllByTenantId();

        //  每个菜单或者菜单组增加字段langs
        String tenantId = WebUtils.getLoginUserTenantId();
        for(Map menuLang:allMenuLang){
            String menuId = (String)menuLang.get("menuId");
            String langs = (String)menuLang.get("lang");
            // 遍历菜单
            boolean isPass = false;
            for(Map menu:allMenu){
                String allMenuId = (String)menu.get("id");
                String menuTenantId = (String)menu.get("tenantId");
                if(StringUtil.equalsIgnoreCase(menuId,allMenuId) && StringUtil.equalsIgnoreCase(tenantId,menuTenantId)){
                    menu.put("langs",langs);
                    isPass = true;
                    break;
                }
            }
            // 遍历菜单组
            if(!isPass){
                for(Map group:allGroup){
                    String allGroupId = (String)group.get("id");
                    String groupTenantId = (String)group.get("tenantId");
                    if(StringUtil.equalsIgnoreCase(menuId,allGroupId) && StringUtil.equalsIgnoreCase(tenantId,groupTenantId)){
                        group.put("langs",langs);
                        break;
                    }
                }
            }
        }

        return getGenChildByParent(allGroup,allMenu,"-1");
    }

    @Override
    public List<MenuGroup> queryRootByTenantId(String parentId, String tenantId) {
        return menuGroupRepository.queryRootByTenantId( parentId, tenantId);
    }

    @Override
    public List<Map> queryMenuTreeData() {
        //TODO 应该以后是按组织来查
//        List<MenuGroup> allGroup = menuGroupRepository.findAllSort();
        List<Map> allGroup = BeanUtil.convertBeansToMaps(menuGroupRepository.findAllSort());

        //实际上是返回有权限的菜单
        List<Map> allMenu = menuService.findAllSort();
        if(allMenu==null||allMenu.size()==0){
            return Collections.EMPTY_LIST;
        }
        List<Map> allMenuLang = menuLangService.queryAllByTenantId();

        //  每个菜单或者菜单组增加字段langs
        String tenantId = WebUtils.getLoginUserTenantId();
        for(Map menuLang:allMenuLang){
            String menuId = (String)menuLang.get("menuId");
            String langs = (String)menuLang.get("lang");
            // 遍历菜单
            boolean isPass = false;
            for(Map menu:allMenu){
                String allMenuId = (String)menu.get("id");
                String menuTenantId = (String)menu.get("tenantId");
                if(StringUtil.equalsIgnoreCase(menuId,allMenuId) && StringUtil.equalsIgnoreCase(tenantId,menuTenantId)){
                    menu.put("langs",langs);
                    isPass = true;
                    break;
                }
            }
            // 遍历菜单组
            if(!isPass){
                for(Map group:allGroup){
                    String allGroupId = (String)group.get("id");
                    String groupTenantId = (String)group.get("tenantId");
                    if(StringUtil.equalsIgnoreCase(menuId,allGroupId) && StringUtil.equalsIgnoreCase(tenantId,groupTenantId)){
                        group.put("langs",langs);
                        break;
                    }
                }
            }
        }

        List<String> filterIds = cn.flydiy.core.common.ResourceUtils.getCurrentMenuGroupIds();

        List<Map> childByParent = getChildByParent(allGroup, allMenu, "-1");
        //如果这个菜单树不是空的 且 需要显示二级菜单是需要的(因为不存在的话代表是老版本 且 这个人不是管理员)
        if(CollectionUtils.isNotEmpty(filterIds) && CollectionUtils.isNotEmpty(childByParent) && StringUtil.notEquals(Constant.ADMIN_ID,WebUtils.getLoginUser().getId())){
            List<Map> children = (List<Map>) childByParent.get(0).get("children");
            ListIterator<Map> iterator = children.listIterator();
            while (iterator.hasNext()){
                Map next = iterator.next();
                String id = MapUtils.getString(next, "id");
                if (!filterIds.contains(id) && "group".equals(MapUtils.getString(next,"type"))) {
                    iterator.remove();
                }
            }
            if(filterIds.size() == 1 && CollectionUtils.isNotEmpty(children)){
                childByParent.get(0).put("children",children.get(0).get("children"));
            }
        }

        return childByParent;
    }

    @Override
    public void updateEntity(MenuGroup menuGroup) {
        MenuGroup updateEntity = findOne(menuGroup.getId());
        // 更新名称
        updateEntity.setIcon(menuGroup.getIcon());
        updateEntity.setColor(menuGroup.getColor());
        updateEntity.setName(menuGroup.getName());
        updateEntity.setIcon(menuGroup.getIcon());
        updateEntity.setSort(menuGroup.getSort());
        _updateEntityById(updateEntity);
    }

    @Override
    public void updateEntityForDiy(MenuGroup menuGroup,String langs) {
        MenuGroup updateEntity = findOne(menuGroup.getId());
        // 更新名称
        updateEntity.setIcon(menuGroup.getIcon());
        updateEntity.setColor(menuGroup.getColor());
        updateEntity.setName(menuGroup.getName());
        updateEntity.setSort(menuGroup.getSort());
        _updateEntityById(updateEntity);

        // 更新多语言信息
        String id = updateEntity.getId();
        menuLangService.updateLangsByMenuId(id,langs);

    }

    private List<Map> getGenChildByParent(List<Map> allGroup,List<Map> allMenu,String parentId){
        List<Map> treeData = new ArrayList<>();

        for(Map menuGroup:allGroup){
            //判断当前ID是否等于父ID
            String parentid = (String)menuGroup.get("parentId");
            if(parentId.equals(parentid)){
                Map tMap=new HashMap();
                String menuGroupId = (String) menuGroup.get("id");
                tMap.put("id", menuGroupId);
                tMap.put("label", (String)menuGroup.get("name"));
                tMap.put("parentId", (String)menuGroup.get("parentId"));
                tMap.put("icon", (String)menuGroup.get("icon"));
                tMap.put("sort", (Integer)menuGroup.get("sort"));
                tMap.put("groupLevel",(Integer)menuGroup.get("groupLevel"));
                tMap.put("color",(String)menuGroup.get("color"));
                tMap.put("type","group");
                String langs = (String)menuGroup.get("langs");
                if(StringUtil.isNotEmpty(langs)){
                    tMap.put("langs",langs);
                }
                List<Map> childMap=getGenChildByParent(allGroup,allMenu, menuGroupId);
                List<Map> childMenu = new ArrayList<>();
                for (Map menu : allMenu) {
                    if (menuGroupId.equals(MapUtils.getString(menu,"menuGroupId"))){
                        menu.put("type","menu");
                        childMenu.add(menu);
                    }
                }
                Collections.sort(childMenu, new Comparator<Map>() {
                    @Override
                    public int compare(Map o1, Map o2) {
                        return MapUtils.getInteger(o1,"sort",0)-MapUtils.getInteger(o2,"sort",0);
                    }
                });
                childMap.addAll(childMenu);
                tMap.put("children", childMap);
                treeData.add(tMap);
            }
        }
        return treeData;
    }

    private List<Map> getChildByParent(List<Map> allGroup, List<Map> allMenu, String parentId){
        List<Map> treeData = new ArrayList<>();

        for(Map menuGroup:allGroup){
            //判断当前ID是否等于父ID
            if(parentId.equals((String)menuGroup.get("parentId"))){

                List<Map> childMap=getChildByParent(allGroup,allMenu,(String)menuGroup.get("id"));
                List<Map> childMenu = new ArrayList<>();
                String menuGroupId = (String)menuGroup.get("id");
                for (Map menu : allMenu) {
                    if (menuGroupId.equals(MapUtils.getString(menu,"menuGroupId"))){
                        menu.put("type","menu");
                        childMenu.add(menu);
                    }
                }
                Collections.sort(childMenu, new Comparator<Map>() {
                    @Override
                    public int compare(Map o1, Map o2) {
                        return MapUtils.getInteger(o1,"sort",0)-MapUtils.getInteger(o2,"sort",0);
                    }
                });
                childMap.addAll(childMenu);
                if(childMap.size()==0){//没有子菜单则不显示此菜单
                    continue;
                }

                Map tMap=new HashMap();
                tMap.put("children", childMap);
                tMap.put("id", menuGroupId);
                tMap.put("label", (String)menuGroup.get("name"));
                tMap.put("parentId", (String)menuGroup.get("parentId"));
                tMap.put("icon", (String)menuGroup.get("icon"));
                tMap.put("sort", (Integer)menuGroup.get("sort"));
                tMap.put("groupLevel",(Integer)menuGroup.get("groupLevel"));
                tMap.put("color",(String)menuGroup.get("color"));
                tMap.put("type","group");
                String langs = (String)menuGroup.get("langs");
                if(StringUtil.isNotEmpty(langs)){
                    tMap.put("langs",langs);
                }
                treeData.add(tMap);
            }
        }
        return treeData;
    }
}
