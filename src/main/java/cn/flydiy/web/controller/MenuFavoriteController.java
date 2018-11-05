package cn.flydiy.web.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.entity.MenuFavorite;
import cn.flydiy.web.service.MenuFavoriteService;
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
public class MenuFavoriteController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    MenuFavoriteService menuFavoriteService;

    public void queryByUserId(){
        List<Map>  menuFavoriteList= menuFavoriteService.queryByUserId(WebUtils.getLoginUser().getId());
        super.render(new ResponseData(menuFavoriteList));
    }

    public void updateMenuFavorite(){
        Map<String, Object> map = getParamMap();
        List<String> menuIds = (List<String>) map.get("menuIds");
        menuFavoriteService.updateMenuFavorite(menuIds);
        super.render(new ResponseData());
    }

    //实际是根据菜单和当前用户
    public void deleteByMenuId(){
        Map<String, Object> map = getParamMap();
        String menuId = MapUtils.getString(map,"menuId");
        menuFavoriteService.deleteByMenuId(menuId);
        super.render(new ResponseData());
    }

    public void addMenuFavorite(){
        Map<String, Object> map = getParamMap();
        String menuId = MapUtils.getString(map,"menuId");
        menuFavoriteService.addMenuFavorite(menuId);
        super.render(new ResponseData());
    }
}