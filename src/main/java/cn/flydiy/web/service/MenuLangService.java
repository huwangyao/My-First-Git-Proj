package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.entity.MenuLang;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface MenuLangService extends BaseService<MenuLang> {

    MenuLang queryByMenuIdAndCurrentTenantId(String menuId);

    void updateLangsByMenuId(String menuId,String langs);

    void deleteByMenuIdAndTenantId(String menuId);

    List<Map> queryAllByTenantId();
}