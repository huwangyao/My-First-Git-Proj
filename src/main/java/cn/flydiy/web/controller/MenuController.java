package cn.flydiy.web.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.web.dto.MenuDto;
import cn.flydiy.web.entity.Menu;
import cn.flydiy.web.entity.MenuLang;
import cn.flydiy.web.service.MenuLangService;
import cn.flydiy.web.service.MenuService;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@WebController
public class MenuController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuLangService menuLangService;


    public void queryMyMenus() {
        List<MenuDto> menuDtos = menuService.queryMyMenus();
        ResponseData responseData = new ResponseData(menuDtos);
        super.render(responseData);
    }

    public void saveEntity(){
        Menu menu = getParamObj(Menu.class);
        String langs = MapUtils.getString(getParamMap(),"langs");
        menuService.save(menu);// 保存多语言内容
        MenuLang menuLang = new MenuLang();
        menuLang.setLang(langs);
        menuLang.setMenuId(menu.getId());
        menuLangService.save(menuLang);

        super.render(new ResponseData());
    }

    public void updateEntity(){
        String langs = MapUtils.getString(getParamMap(),"langs");
        Menu menu = getParamObj(Menu.class);
        menuService.updateEntity(menu,langs);
        super.render(new ResponseData());
    }

    public void deleteById(){
        Map map = getParamMap();
        String id = MapUtils.getString(map, "id");
        menuService._deleteByIds(id);
        menuLangService.deleteByMenuIdAndTenantId(id);
        super.render(new ResponseData());
    }

    public void swapSort(){
        Map map = getParamMap();
        String id1 = MapUtils.getString(map,"id1");
        String id2 = MapUtils.getString(map,"id2");
        menuService.swapSort(id1,id2);

        super.render(new ResponseData());
    }
}