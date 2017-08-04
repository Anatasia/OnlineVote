package com.xj.votetest.service;

import com.xj.votetest.pojo.VoteSubject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/7/27.
 */
@Service
public interface VoteService {
    List<VoteSubject> getAllSubject();
    int addNewVoteSubject(HttpServletRequest request);
    VoteSubject findVoteSubjectByVsid(HttpServletRequest request);

    //更新投票结果
    int insertVoteItem(HttpServletRequest request);
    VoteSubject findVoteOptionInfo(HttpServletRequest request);
}
