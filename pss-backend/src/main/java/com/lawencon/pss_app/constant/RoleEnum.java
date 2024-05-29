package com.lawencon.pss_app.constant;

import com.lawencon.pss_app.constant.RoleEnum;

public enum RoleEnum {
    SUPER_ADMIN(1,"Super Admin", "SA"),
    PAYROLL_SERVICE(2,"Payroll Service", "PS"),
    CLIENT(3,"Client", "CL");

	private final Integer id;
    private final String roleName;
    private final String roleCode;

    RoleEnum(Integer id, String roleName, String roleCode) {
        this.id = id;
        this.roleName = roleName;
        this.roleCode = roleCode;
    }

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }
    
    public static RoleEnum getById(int id) {
        for (RoleEnum role : values()) {
            if (role.ordinal() + 1 == id) { 
                return role;
            }
        }
        return null;
    }
}