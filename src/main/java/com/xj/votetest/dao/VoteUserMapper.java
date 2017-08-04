package com.xj.votetest.dao;

import com.xj.votetest.pojo.VoteUser;
import org.springframework.stereotype.Component;

/**
 * Created by xujuan1 on 2017/7/26.
 */
@Component
public interface VoteUserMapper {
    int addNewUser(VoteUser user);
    VoteUser findUserByUnameAndPwd(VoteUser user);
}
