package com.xj.dao;

import com.xj.pojo.VoteUser;
import org.springframework.stereotype.Component;

/**
 * Created by xujuan1 on 2017/7/19.
 */
@Component
public interface VoteUserMapper {
    VoteUser findUserByName(String uname);
    void addNewUser(VoteUser user);
}
