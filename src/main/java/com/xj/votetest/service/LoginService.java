package com.xj.votetest.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xujuan1 on 2017/7/26.
 */
@Service
public interface LoginService {
    int register(HttpServletRequest request);
}
