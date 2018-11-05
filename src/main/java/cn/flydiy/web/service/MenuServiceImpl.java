package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.web.dto.MenuDto;
import cn.flydiy.web.entity.Menu;
import cn.flydiy.web.entity.MenuGroup;
import cn.flydiy.web.repository.MenuGroupRepository;
import cn.flydiy.web.repository.MenuRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    MenuLangService menuLangService;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuGroupRepository menuGroupRepository;

    @Override
    public List<MenuDto> queryMyMenus() {

        List<MenuGroup> menuGroupList = menuGroupRepository.findAll();

        List<Menu> menuList = menuRepository.findAll();

        Map<String, List<Menu>> menuGroupId_Menus = menuList
                .stream()
                .filter(menu -> !StringUtil.isEmpty(menu.getMenuGroupId()))
                .sorted(Comparator.comparingInt(Menu::getSort))
                .collect(Collectors.groupingBy(Menu::getMenuGroupId));


        List<MenuDto> menuDtoList = menuGroupList.stream().map(menuGroup -> {

            MenuDto menuDto = new MenuDto();
            BeanUtil.copyPropertiesToBean(menuDto, menuGroup);

            return menuDto;
        }).collect(Collectors.toList());

        Map<String, List<MenuDto>> parentGroupId_menuGroups = menuDtoList
                .stream()
                .filter(menuGroup -> !StringUtil.isEmpty(menuGroup.getParentId()))
                .sorted(Comparator.comparingInt(MenuGroup::getSort))
                .collect(Collectors.groupingBy(MenuDto::getParentId));


        List<MenuDto> allMenus = menuDtoList
                .stream()
                .map(menuDto -> {

                    String menuGroupId = menuDto.getId();
                    List<MenuDto> child = parentGroupId_menuGroups.get(menuGroupId);
                    if (child != null) {
                        menuDto.setChildList(child);
                    }
                    List<Menu> menus = menuGroupId_Menus.get(menuGroupId);

                    if (menus != null) {
                        menuDto.setMenuList(menus);
                    }
                    return menuDto;
                })
                .filter(menuDto -> menuDto.getGroupLevel() == 1)
                .sorted(Comparator.comparingInt(MenuGroup::getSort))
                .collect(Collectors.toList());

        return allMenus;
    }

    @Override
    public List<Map> queryByGroupId(String id) {
        return menuRepository.queryByGroupId(id);
    }

    @Override
    public List<Map> findAllSort() {
        return menuRepository.findAllSort();
    }

    @Override
    public void swapSort(String id1, String id2) {
        Menu one = findOne(id1);
        Menu two = findOne(id2);
        Integer temp = one.getSort();
        one.setSort(two.getSort());
        _updateEntityById(one);
        two.setSort(temp);
        _updateEntityById(two);
    }

    @Override
    public void updateEntity(Menu menu,String langs) {
        Menu updateMenu = findOne(menu.getId());
        // 更新名称
        updateMenu.setName(menu.getName());
        // 更新页面
        updateMenu.setUrl(menu.getUrl());
        // 更新图标
        updateMenu.setIcon(menu.getIcon());
        // 更新图标
        updateMenu.setColor(menu.getColor());
        //更新序号
        updateMenu.setSort(menu.getSort());
        // 更新是否角标
        updateMenu.setIsLog(menu.getIsLog());
        _updateEntityById(updateMenu);

        // 更新多语言
        String id = menu.getId();
        menuLangService.updateLangsByMenuId(id,langs);
    }

    @Override
    public List<Menu> queryByMenuIds(String... menuIds) {
        return menuRepository.queryByMenuIds(menuIds);
    }

    @Override
    public List<Menu> queryByName(String menuName) {
        return menuRepository.queryByName(menuName);
    }
}
