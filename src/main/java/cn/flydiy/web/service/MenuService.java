package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.dto.MenuDto;
import cn.flydiy.web.entity.Menu;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface MenuService extends BaseService<Menu> {


    List<MenuDto> queryMyMenus();

    List<Map> queryByGroupId(String id);

    List<Map> findAllSort();

    void swapSort(String id1, String id2);

    void updateEntity(Menu menu,String langs);

    List<Menu> queryByMenuIds(String... menuIds);

    List<Menu> queryByName(String menuName);
}