package cn.flydiy.sys.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.constant.Constant;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.sys.entity.Menu;
import cn.flydiy.sys.entity.RoleMenu;
import cn.flydiy.sys.entity.RoleUser;
import cn.flydiy.sys.repository.MenuRepository;
import cn.flydiy.sys.repository.RoleMenuRepository;
import cn.flydiy.sys.repository.RoleUserRepository;
import cn.flydiy.web.repository.MenuGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by flydiy on 2017-03-24 14:52:40.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    MenuGroupRepository menuGroupRepository;



    @Autowired
    RoleMenuRepository roleMenuRepository;
    @Autowired
    RoleUserRepository roleUserRepository;


    @Override
    public List<Map> queryByGroupId(String id) {
        return menuRepository.queryByGroupId(id);
    }

    @Override
    public List<Map> findAllSort() {
//        return menuRepository.findAllSort();
        List<Menu> menus=findMenuByUser();
        if(menus==null||menus.size()==0){
            return Collections.EMPTY_LIST;
        }
//        menus.stream().distinct().sorted(Comparator.comparingInt(menu -> menu.getSort())).collect(Collectors.toList());//排序
        return BeanUtil.convertBeansToMaps(menus);
    }


    /**
     * 根据用户查找所拥有的菜单
     * @return
     */
    @Override
    public List<Menu> findMenuByUser() {
        List<Menu> menus;
        if(StringUtil.notEquals(Constant.ADMIN_ID,WebUtils.getLoginUser().getId())) {
            List<String> menuIds = findMenuIdByUser();
            if (menuIds == null || menuIds.size() == 0) {
                return Collections.EMPTY_LIST;
            }
            //找到菜单并返回
            menus = menuRepository.findByIds(menuIds.toArray(new String[menuIds.size()]));
            if (menus == null || menus.size() == 0) {
                return Collections.EMPTY_LIST;
            }
        }else {
            menus = menuRepository.findAll();//系统管理员，拥有全部菜单
        }

        return menus;
    }

    //返回用户所拥有的菜单的id
    @Override
    public List<String> findMenuIdByUser(){
        //查到用户关联的角色id
        List<String> roleIds = roleUserRepository.findRoleUsersByUserId(WebUtils.getLoginUser().getId())
                .stream()
                .map(RoleUser::getRoleId)
                .distinct()
                .collect(Collectors.toList());
        if(roleIds==null||roleIds.size()==0){
            return Collections.EMPTY_LIST;
        }

        //查到角色拥有的菜单
        List<RoleMenu> roleMenus = roleMenuRepository.queryRoleMenuByRoleIds(
                roleIds.toArray(new String[roleIds.size()]));

        //取出菜单id
        List<String> menuIds = roleMenus.stream()
                .map(RoleMenu::getMenuId)
                .distinct()
                .collect(Collectors.toList());

        return menuIds;
    }



}