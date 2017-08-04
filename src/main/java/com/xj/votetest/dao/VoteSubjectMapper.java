package com.xj.votetest.dao;

import com.xj.votetest.pojo.VoteItem;
import com.xj.votetest.pojo.VoteOption;
import com.xj.votetest.pojo.VoteSubject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xujuan1 on 2017/7/27.
 */
@Component
public interface VoteSubjectMapper {
    List<VoteSubject> getVoteSubjectList();
    //添加新的投票
    int addToTableVoteSubject(VoteSubject voteSubject);
    int addToTableVoteOption(List<VoteOption> optionList);
    void deleteVoteSubjectByVsid(int vsid);

    List<VoteOption> findOptionsByVsid(int vsid);
    VoteSubject findVoteSubjectByVsid(int vsid);

    //查询用户投票信息
    int findUserVoteInfoByVsid(VoteItem item);
    int findVoteOptionCount(int vsid);

    int insertVoteResult(List<VoteItem> item);
    List<VoteOption> findOptionsByVsidAndVoteCount(int vsid);
}
