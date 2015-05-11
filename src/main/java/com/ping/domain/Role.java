package com.ping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 5918150653828406872L;

    private String roleId;
    /*
     * 角色标识, 程序中判断使用, eg."admin"
     */
    private String role;
    /*
     * 角色描述, UI界面显示使用
     */
    private String description;
    /*
     * 角色拥有的资源
     */
    private String resourceIds;
    /*
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available;

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    @Id
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "role_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "role_resourceIds")
    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Column(name = "role_available")
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((resourceIds == null) ? 0 : resourceIds.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (resourceIds == null) {
            if (other.resourceIds != null)
                return false;
        } else if (!resourceIds.equals(other.resourceIds))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", role=" + role + ", description="
                + description + ", resourceIds=" + resourceIds + ", available="
                + available + "]";
    }

}
