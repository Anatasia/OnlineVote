package com.xj.service;

import com.xj.pojo.VoteUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xujuan1 on 2017/7/19.
 */
public interface LoginService {
    VoteUser verifyUser(VoteUser user);
    String register(HttpServletRequest request);
    void loginOut(HttpServletRequest request);
}
