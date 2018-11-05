package cn.flydiy.sys.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.sys.entity.Menu;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;


/**
* 
* Created by flydiy on 2017-03-24 14:52:40.
*/
@Validated
public interface MenuService extends BaseService<Menu>{


    List<Map> queryByGroupId(String id);

    List<Map> findAllSort();


    List<Menu> findMenuByUser();

    //返回用户所拥有的菜单的id
    List<String> findMenuIdByUser();
}