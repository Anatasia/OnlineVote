package com.xj.test;

import com.xj.pojo.VoteSubject;
import com.xj.service.VoteService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by xujuan1 on 2017/7/18.
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestVoteService {
    private Logger logger = Logger.getLogger(TestVoteService.class);

    @Resource
    private VoteService voteService;

    @Test
    public void testGetAllSubject(){
        List<VoteSubject> voteSubjects = voteService.getAllSubject();
        for(VoteSubject voteSubject:voteSubjects){
            logger.info(voteSubject.getVsid() +"  " + voteSubject.getTitle()+"  "+voteSubject.getStype());
        }
    }

    @Test
    public void testFindVoteSubjectByVsid(){
        VoteSubject vs = voteService.findVoteSubjectByVsid(1);
        logger.info("vsid:"+vs.getVsid()+ "  title:" +vs.getTitle()+"   optioncount:"+vs.getOptioncount() +"   usercount:"+vs.getUsercount());
    }
}
