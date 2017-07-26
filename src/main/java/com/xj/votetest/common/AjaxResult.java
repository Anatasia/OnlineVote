package com.xj.votetest.common;

/**
 * Created by xujuan1 on 2017/7/26.
 */
public class AjaxResult {
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
