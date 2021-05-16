package com.lujiatao.ims.common.constant;

public enum Role {

    ROLE_USER("注册用户"),
    ROLE_ADMIN("管理员");

    private String desc;

    Role(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
