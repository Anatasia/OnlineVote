package com.xj.votetest.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by xujuan1 on 2017/7/27.
 */
@Component
public class VoteOption {
    private int voteid;
    private String voteoption;
    private int vsid;
    private Integer votecount;

    public Integer getVotecount() {
        return votecount;
    }

    public void setVotecount(Integer votecount) {
        this.votecount = votecount;
    }


    public String getVoteoption() {
        return voteoption;
    }

    public void setVoteoption(String voteoption) {
        this.voteoption = voteoption;
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


}
