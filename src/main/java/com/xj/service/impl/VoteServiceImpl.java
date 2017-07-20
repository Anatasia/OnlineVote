package com.xj.service.impl;

import com.xj.dao.VoteSubjectMapper;
import com.xj.pojo.VoteItem;
import com.xj.pojo.VoteOption;
import com.xj.pojo.VoteSubject;
import com.xj.pojo.VoteUser;
import com.xj.service.VoteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xujuan1 on 2017/7/18.
 */
@Service
public class VoteServiceImpl implements VoteService {
    private Logger logger = Logger.getLogger(VoteServiceImpl.class);
    @Resource
    private VoteSubjectMapper voteSubjectMapper;


    @Override
    public List<VoteSubject> getAllSubject() {
        return this.voteSubjectMapper.getVoteSubjectList();
    }

    @Override
    public VoteSubject findVoteSubjectByVsid(int vsid) {
        List<VoteOption> optionList = voteSubjectMapper.findOptionsByVsid(vsid);
        VoteSubject vs = voteSubjectMapper.findVoteSubjectByVsid(vsid);
        vs.setOptions(optionList);
        vs.setOptioncount(optionList.size());
        return vs;
    }

    //将投票结果存入数据库
    @Autowired
    private VoteItem voteItem;
    @Override
    public void insertVoteItem(HttpServletRequest request) {

        int vsid = Integer.parseInt(request.getParameter("vsid"));
        int voteid = Integer.parseInt(request.getParameter("chooseIds"));
        logger.info("vsid="+vsid+"; voteid="+voteid);
        VoteUser user = (VoteUser) request.getSession(true).getAttribute("user");
        voteItem.setUid(user.getUid());
        voteItem.setVoteid(voteid);
        voteItem.setVsid(vsid);

        voteSubjectMapper.insertVoteResult(voteItem);
    }

    @Override
    public VoteSubject findVoteOptionInfo(HttpServletRequest request) {
        int vsid = Integer.parseInt(request.getParameter("vsid"));
        VoteSubject voteSubject = voteSubjectMapper.findVoteSubjectByVsid(vsid);
        List<VoteOption> optionList = voteSubjectMapper.findOptionsByVsidAndVoteCount(vsid);
        voteSubject.setOptions(optionList);
        return voteSubject;
    }

    @Override
    public void addNewVoteSubject(HttpServletRequest request) {
        String title = request.getParameter("title");
        Integer stype = Integer.parseInt(request.getParameter("stype"));
        VoteSubject vs = new VoteSubject();
        vs.setTitle(title);
        vs.setStype(stype);
        //将VoteSubject基本信息插入VoteSubject表
        voteSubjectMapper.addToTableVoteSubject(vs);
        logger.info("将VoteSubject基本信息插入VoteSubject表");
        //查询新投票的vsid
        int vsid = voteSubjectMapper.getVsid(vs);
        logger.info("新投票信息：vsid="+vsid+"  title="+title+"  stype="+stype);

        Map<String,String[]> map = request.getParameterMap();
        String[] voteoptions = map.get("voteoptions");
        List<VoteOption> optionList = new ArrayList<VoteOption>();
        for(String voteOption:voteoptions){
            VoteOption vo = new VoteOption();
            vo.setVsid(vsid);
            vo.setVoteoption(voteOption);
            vo.setVoteorder(1);
            logger.info("投票选项为："+voteOption);
            optionList.add(vo);
        }
        //将optionList添加到数据库
        voteSubjectMapper.addToTableVoteOption(optionList);
    }

}
