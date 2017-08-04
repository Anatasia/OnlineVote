package com.xj.votetest.service;

import com.xj.votetest.pojo.VoteSubject;
import com.xj.votetest.pojo.VoteUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/8/2.
 */
@Service
public interface MaintainService {
    List<VoteSubject> getVoteSubjects(HttpServletRequest request);
    void setVoteSubjectStateByVsid(HttpServletRequest request);
    void deleteSubjectByVsid(HttpServletRequest request);
    List<VoteUser> getAllUsers(HttpServletRequest request);
    void deleteUser(HttpServletRequest request);
    int grantUser(HttpServletRequest request);
    int revokeUser(HttpServletRequest request);
}
