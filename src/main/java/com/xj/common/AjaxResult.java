package com.xj.common;

import java.io.Serializable;

/**
 * Ajax返回类
 */
public class AjaxResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private Object obj;

    public AjaxResult(int code) {
        this.code = code;
    }

    public AjaxResult(int code, String msg) {
        this(code);
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
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
}
