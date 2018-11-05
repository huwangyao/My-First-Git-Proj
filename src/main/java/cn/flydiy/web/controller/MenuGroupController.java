package cn.flydiy.web.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.web.entity.MenuGroup;
import cn.flydiy.web.entity.MenuLang;
import cn.flydiy.web.service.MenuGroupService;
import cn.flydiy.web.service.MenuLangService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.List;

/**
 * Created by admin on 2017/2/27.
 */
@WebController
public class MenuGroupController extends BaseController{

    @Autowired
    private MenuGroupService menuGroupService;
    @Autowired
    private MenuLangService menuLangService;

    //创建菜单组
    public void saveEntity(){
        MenuGroup menuGroup = getParamObj(MenuGroup.class);
        String langs = MapUtils.getString(getParamMap(),"langs");
        //设置父菜单组编号
        if("0".equals(menuGroup.getParentId())){
            menuGroup.setParentId("-1");
        }
        if("-1".equals(menuGroup.getParentId())){
            menuGroup.setGroupLevel(1);
        }else{
            menuGroup.setGroupLevel(2);
        }
        menuGroupService.save(menuGroup);
        // 保存多语言内容
        MenuLang menuLang = new MenuLang();
        menuLang.setLang(langs);
        menuLang.setMenuId(menuGroup.getId());
        menuLangService.save(menuLang);
        super.render(new ResponseData(menuGroup));
    }

    public void updateEntity(){
        MenuGroup menuGroup = getParamObj(MenuGroup.class);
        menuGroupService.updateEntity(menuGroup);
        super.render(new ResponseData());
    }

    public void updateEntityForDiy(){
        String langs = MapUtils.getString(getParamMap(),"langs");
        MenuGroup menuGroup = getParamObj(MenuGroup.class);
        menuGroupService.updateEntityForDiy(menuGroup,langs);
        super.render(new ResponseData());
    }

    public void deleteMenuGroupById(){
        Map map = getParamMap();
        String id = MapUtils.getString(map,"id");
        menuGroupService.deleteMenuGroupById(id);
        menuLangService.deleteByMenuIdAndTenantId(id);
        super.render(new ResponseData());
    }

    public void queryMenuTreeData(){
        List<Map> menuTreeData = menuGroupService.queryMenuTreeData();
        super.render(new ResponseData(menuTreeData));
    }
}
