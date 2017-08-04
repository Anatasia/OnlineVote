package com.xj.votetest.dao;

import com.xj.votetest.pojo.VoteOption;
import com.xj.votetest.pojo.VoteSubject;
import com.xj.votetest.pojo.VoteUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xujuan1 on 2017/8/2.
 */
@Component
public interface MaintainMapper {
    //获取投票信息列表
    List<VoteSubject> getVoteSubjects();
    List<VoteSubject> getVoteSubjectsByUid(int uid);
    List<VoteOption> getOptionsByVsid(int vsid);

    //设置投票信息状态
    void setVoteSubjectStateByVsid(VoteSubject vs);

    //删除投票信息
    void deleteSubjectByVsid(int vsid);
    void deleteOptionsByVsid(int vsid);
    void deleteItemByVsid(int vsid);

    //获取系统用户信息列表
    List<VoteUser> getAllUsers(int uid);

    //用户管理
    void deleteUser(int uid);
    void grantUser(int uid);
    void revokeUser(int uid);
    int getUtypeByUid(int uid);
}
