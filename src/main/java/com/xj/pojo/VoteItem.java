package com.xj.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by xujuan1 on 2017/7/19.
 */
@Component
public class VoteItem {
    private int viid;

    public int getViid() {
        return viid;
    }

    public void setViid(int viid) {
        this.viid = viid;
    }

    public int getVoteid() {
        return voteid;
    }

    public void setVoteid(int voteid) {
        this.voteid = voteid;
    }

    public int getVsid() {
        return vsid;
    }

    public void setVsid(int vsid) {
        this.vsid = vsid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private int voteid;
    private int vsid;
    private int uid;
}
