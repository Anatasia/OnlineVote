package com.xj.votetest.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xujuan1 on 2017/7/27.
 */
@Component
public class VoteSubject {
    private int vsid;
    private int stype;
    private String title;
    private int userCount;//已经投递了该投票的用户数
    private int optionCount;//该投票一共有多少个选项
    private List<VoteOption> options;//选项列表
    private  List<VoteOption> voteOptions;//用户选择的选项
    private int uid;//投票发起人
    private int state;//投票状态，1表示正在进行，2表示结束。

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    public int getVsid() {
        return vsid;
    }

    public void setVsid(int vsid) {
        this.vsid = vsid;
    }

    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(List<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }

    public List<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }


}
