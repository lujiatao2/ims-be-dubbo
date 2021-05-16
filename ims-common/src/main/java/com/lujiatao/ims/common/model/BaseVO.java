package com.lujiatao.ims.common.model;

import java.io.Serializable;

public class BaseVO implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public BaseVO() {
        this(0, "", null);
    }

    public BaseVO(Object data) {
        this(0, "", data);
    }

    public BaseVO(int code, String msg) {
        this(code, msg, null);
    }

    public BaseVO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
