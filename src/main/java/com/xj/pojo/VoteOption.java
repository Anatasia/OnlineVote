package com.xj.pojo;

/**
 * Created by xujuan1 on 2017/7/18.
 */
public class VoteOption {

    private static final long serialVersionUID = 8219620160311146100L;
    private long voteid ;     //编号
    private String voteoption;   //选项名
    private Integer voteorder;
    private Integer vsid;
    private Integer votecount;




    public Integer getVotecount() {
        return votecount;
    }

    public void setVotecount(Integer votecount) {
        this.votecount = votecount;
    }

    public VoteOption(){}

    public long getVoteid() {
        return voteid;
    }


    public void setVoteid(long voteid) {
        this.voteid = voteid;
    }


    public String getVoteoption() {
        return voteoption;
    }


    public void setVoteoption(String voteoption) {
        this.voteoption = voteoption;
    }


    public Integer getVoteorder() {
        return voteorder;
    }


    public void setVoteorder(Integer voteorder) {
        this.voteorder = voteorder;
    }


    public Integer getVsid() {
        return vsid;
    }


    public void setVsid(Integer vsid) {
        this.vsid = vsid;
    }


    @Override
    public String toString() {
        return "VoteOption [voteid=" + voteid + ", voteoption=" + voteoption + ", voteorder=" + voteorder + ", vsid="
                + vsid + "]";
    }


    public VoteOption(long voteid, String voteoption, Integer voteorder, Integer vsid) {
        super();
        this.voteid = voteid;
        this.voteoption = voteoption;
        this.voteorder = voteorder;
        this.vsid = vsid;
    }

}
