package cn.flydiy.web.service;

import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.web.entity.MenuLang;
import cn.flydiy.web.repository.MenuLangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by flying on 16-12-26.
 */
@Service
public class MenuLangServiceImpl extends BaseServiceImpl<MenuLang> implements MenuLangService {

    @Autowired
    MenuLangRepository menuLangRepository;


    @Override
    public MenuLang queryByMenuIdAndCurrentTenantId(String menuId) {
        List<MenuLang> list = menuLangRepository.queryByMenuIdAndCurrentTenantId(menuId);
        MenuLang menuLang = new MenuLang();
        if(list.size()>0){
            menuLang = list.get(0);
        }
        return menuLang;
    }

    @Override
    public void updateLangsByMenuId(String menuId, String langs) {
        MenuLang menuLang = this.queryByMenuIdAndCurrentTenantId(menuId);
        // 更新
        if(StringUtil.isNotEmpty(menuLang.getId())){
            menuLang.setLang(langs);
            this._updateEntityById(menuLang);
        }else{
            // 保存
            menuLang.setLang(langs);
            menuLang.setMenuId(menuId);
            this.save(menuLang);
        }
    }

    @Override
    public void deleteByMenuIdAndTenantId(String menuId) {
        menuLangRepository.deleteByMenuIdAndTenantId(menuId);
    }

    @Override
    public List<Map> queryAllByTenantId() {
        return menuLangRepository.queryAllByTenantId();
    }
}
