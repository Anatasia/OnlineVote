package com.xj.dao;

import com.xj.pojo.VoteItem;
import com.xj.pojo.VoteOption;
import com.xj.pojo.VoteSubject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xujuan1 on 2017/7/18.
 */
@Component
public interface VoteSubjectMapper {
    List<VoteSubject> getVoteSubjectList();
    List<VoteOption> findOptionsByVsid(int vsid);
    List<VoteOption> findOptionsByVsidAndVoteCount(int vsid);
    VoteSubject findVoteSubjectByVsid(int vsid);
    int insertVoteResult(List<VoteItem> item);
    //将新增的投票信息加入数据库
    int addToTableVoteSubject(VoteSubject vs);
    int getVsid(VoteSubject vs);
    int addToTableVoteOption(List<VoteOption> list);
    //查询用户投票信息
    int findUserVoteInfoByVsid(VoteItem item);
    int findVoteOptionCount(int vsid);
    //删除投票信息
    int deleteVoteSubjectByVsid(int vsid);
}
