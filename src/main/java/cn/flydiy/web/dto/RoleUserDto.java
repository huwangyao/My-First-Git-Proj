package cn.flydiy.web.dto;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.web.entity.Role;
import cn.flydiy.web.entity.RoleUser;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoo on 16-12-27.
 */
public class RoleUserDto extends BaseDto {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -1974055869861072817L;

    List<RoleUser> roleUser = new ArrayList<>();

    Role role = new Role();

    public List<RoleUser> getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(List<RoleUser> roleUser) {
        this.roleUser = roleUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
