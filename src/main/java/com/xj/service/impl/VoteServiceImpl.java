package com.xj.service.impl;

import com.xj.common.Constants;
import com.xj.common.ResultCodeEnum;
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
    public int insertVoteItem(HttpServletRequest request) {
        int vsid = Integer.parseInt(request.getParameter("vsid"));
        HttpSession session = request.getSession(true);
        VoteUser user = (VoteUser) session.getAttribute(Constants.SESSION_USER);
        //判断用户是否已投票
        voteItem.setVsid(vsid);
        voteItem.setUid(user.getUid());
        int count = voteSubjectMapper.findUserVoteInfoByVsid(voteItem);
        if(count>0){//用户已投票
            return ResultCodeEnum.USER_ALREADY_VOTE.value();
        }

        Map<String, String[]> map = request.getParameterMap();
        String[] chooseIds = map.get("chooseIds");
        List<VoteItem> voteItemList = new ArrayList<VoteItem>();
        for(String chooseId:chooseIds){
            VoteItem vi = new VoteItem();
            vi.setVsid(vsid);
            vi.setVoteid(Integer.parseInt(chooseId));
            vi.setUid(user.getUid());
            voteItemList.add(vi);
        }
        int res = voteSubjectMapper.insertVoteResult(voteItemList);
        if(res!=voteItemList.size()){//insert failed
            return ResultCodeEnum.VOTE_FAILED.value();
        }else return ResultCodeEnum.VOTE_SUCCESS.value();
    }

    //查看投票情况
    @Override
    public VoteSubject findVoteOptionInfo(HttpServletRequest request) {
        int vsid = Integer.parseInt(request.getParameter("vsid"));
        VoteSubject voteSubject = voteSubjectMapper.findVoteSubjectByVsid(vsid);
        List<VoteOption> optionList = voteSubjectMapper.findOptionsByVsidAndVoteCount(vsid);
        voteSubject.setOptions(optionList);
        int optionCount = voteSubjectMapper.findVoteOptionCount(vsid);
        voteSubject.setOptioncount(optionCount);
        return voteSubject;
    }

    //添加新的投票
    @Override
    public int addNewVoteSubject(HttpServletRequest request) {
        String title = request.getParameter("title");
        Integer stype = Integer.parseInt(request.getParameter("stype"));
        VoteSubject vs = new VoteSubject();
        vs.setTitle(title);
        vs.setStype(stype);
        //将VoteSubject基本信息插入VoteSubject表
        int insertCount = voteSubjectMapper.addToTableVoteSubject(vs);
        logger.info("将VoteSubject基本信息插入VoteSubject表");
        if(insertCount!=1){//投票信息插入失败
            return ResultCodeEnum.INSERT_VOTESUBJETC_FAILED.value();
        }

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
        try {
            int count = voteSubjectMapper.addToTableVoteOption(optionList);
            if(count!=optionList.size()){//投票选项添加失败
                voteSubjectMapper.deleteVoteSubjectByVsid(vsid);
                return ResultCodeEnum.INSERT_VOTEOPTIONS_FAILED.value();
            }
        }catch (Exception e){
            logger.info(e.toString());
            voteSubjectMapper.deleteVoteSubjectByVsid(vsid);
            return ResultCodeEnum.INSERT_VOTEOPTIONS_FAILED.value();
        }
        return ResultCodeEnum.INSERT_NEWVOTEINFO_SUCCESS.value();
    }

}
