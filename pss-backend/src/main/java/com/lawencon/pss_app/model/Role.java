package com.lawencon.pss_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lawencon.pss_app.constant.RoleEnum;

@Entity
@Table(name = "roles")
public class Role {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

//    @Enumerated(EnumType.STRING)
    @Column(name = "role_code", nullable = false, length = 5)
    private String roleCode;

    public Role() {
    }
    
    public Role(RoleEnum roleEnum) {
        this.id = roleEnum.getId();
        this.roleName = roleEnum.getRoleName();
        this.roleCode = roleEnum.getRoleCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}