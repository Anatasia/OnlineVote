package com.xj.test;

import com.xj.pojo.VoteUser;
import com.xj.service.LoginService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xujuan1 on 2017/7/19.
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestLoginService {
    private Logger logger = Logger.getLogger(TestLoginService.class);
    @Autowired
    private LoginService loginService;

    @Test
    public void testVerifyUser(){
        VoteUser voteUser = new VoteUser();
        voteUser.setUname("aa");
        voteUser.setPwd("aa");
        VoteUser res = loginService.verifyUser(voteUser);
        logger.info(res);
    }

}
