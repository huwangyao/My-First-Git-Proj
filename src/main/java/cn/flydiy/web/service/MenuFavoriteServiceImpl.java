package cn.flydiy.web.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.entity.MenuFavorite;
import cn.flydiy.web.repository.MenuFavoriteRepository;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flydiy.sys.entity.Menu;//改调用sys模块的Menu

import cn.flydiy.sys.service.MenuService;//菜单权限只要注释掉这个就能取消

import javax.persistence.Column;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class MenuFavoriteServiceImpl extends BaseServiceImpl<MenuFavorite> implements MenuFavoriteService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    MenuFavoriteRepository menuFavoriteRepository;

    @Autowired
    MenuLangService menuLangService;

    @Autowired
    MenuService menuService;

    @Override
    public List<Map> queryByUserId(String userId) {
        List<Map> favorites = menuFavoriteRepository.queryMapByUserId(userId);

        //以下为快捷菜单权限校验
        List<String> menuIdByUser = menuService.findMenuIdByUser();//取到有权限的菜单的id
        if(menuIdByUser==null||menuIdByUser.size()==0){
            return Collections.EMPTY_LIST;
        }

        favorites=favorites.stream().filter(favorite-> menuIdByUser.contains(favorite.get("menuId"))).collect(Collectors.toList());//过滤掉没有权限的快捷菜单

        //优化,直接查出需要的菜单
        String[] menuIds = CollectionUtils.getValuesByKey(favorites, "menuId");
        List<Menu> menus = menuService.findByIds(menuIds);//查出菜单

        List<Map> menuLang = menuLangService.queryAllByTenantId();

        for (Map favorite : favorites) {
            String menuId = MapUtils.getString(favorite,"menuId");
            for (Menu menu : menus) {
                if(StringUtil.equalsIgnoreCase(menuId,menu.getId())){
                    favorite.put("label",menu.getName());
                    favorite.put("icon",menu.getIcon());
                    favorite.put("color",menu.getColor());
                    favorite.put("url",menu.getUrl());
                }
            }
            for(Map map:menuLang){
                String mapMenuId = MapUtils.getString(map,"menuId");
                if(StringUtil.equalsIgnoreCase(menuId,mapMenuId)){
                    favorite.put("langs",(String)map.get("lang"));
                }
            }
        }
        return favorites;
    }

    @Override
    public void updateMenuFavorite(List<String> menuIds) {
        String userId = WebUtils.getLoginUser().getId();
        menuFavoriteRepository.deleteByUserId(userId);
        List<MenuFavorite> menuFavoriteList = new ArrayList<>();
        int num = 0;
        for (String menuId : menuIds) {
            MenuFavorite menuFavorite = new MenuFavorite();
            menuFavorite.setMenuId(menuId);
            menuFavorite.setUserId(userId);
            menuFavorite.setSort(num++);
            menuFavoriteList.add(menuFavorite);
        }
        menuFavoriteRepository.save(menuFavoriteList.toArray(new MenuFavorite[]{}));
    }

    @Override
    public void deleteByMenuId(String menuId) {
        //考虑要不要删除之后排序
//        List<MenuFavorite> menuFavoriteList = menuFavoriteRepository.queryByUserId(WebUtils.getLoginUser().getId());
        menuFavoriteRepository.deleteByMenuId(menuId);
    }

    @Override
    public void addMenuFavorite(String menuId) {
        List<MenuFavorite> menuFavoriteList = menuFavoriteRepository.queryByUserId(WebUtils.getLoginUser().getId());
        Integer maxSort = menuFavoriteList.get(menuFavoriteList.size()-1).getSort();
        MenuFavorite menuFavorite = new MenuFavorite();
        menuFavorite.setSort(maxSort+1);
        menuFavorite.setMenuId(menuId);
        menuFavorite.setUserId(WebUtils.getLoginUser().getId());
        save(menuFavorite);
    }


}
