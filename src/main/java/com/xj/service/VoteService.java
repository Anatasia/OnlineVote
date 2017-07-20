package com.xj.service;

import com.xj.pojo.VoteSubject;
import com.xj.pojo.VoteUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/7/18.
 */
@Service
public interface VoteService {
    //获取所有投票主题
    public List<VoteSubject> getAllSubject();

    //根据vsid查询VoteSubject
    public VoteSubject findVoteSubjectByVsid(int vsid);

    //更新投票结果
    void insertVoteItem(HttpServletRequest request);
    VoteSubject findVoteOptionInfo(HttpServletRequest request);

    //添加新的投票
    void addNewVoteSubject(HttpServletRequest request);
}
