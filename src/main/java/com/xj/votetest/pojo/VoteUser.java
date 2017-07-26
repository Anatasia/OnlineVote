package com.xj.votetest.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by xujuan1 on 2017/7/26.
 */
@Component
public class VoteUser {
    String uname;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    String pwd;
    int uid;
}
