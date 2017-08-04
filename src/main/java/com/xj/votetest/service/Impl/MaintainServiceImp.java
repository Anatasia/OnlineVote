package com.xj.votetest.service.Impl;

import com.xj.votetest.common.Constants;
import com.xj.votetest.common.ResultCodeEnum;
import com.xj.votetest.dao.MaintainMapper;
import com.xj.votetest.pojo.VoteOption;
import com.xj.votetest.pojo.VoteSubject;
import com.xj.votetest.pojo.VoteUser;
import com.xj.votetest.service.MaintainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/8/2.
 */
@Service
public class MaintainServiceImp implements MaintainService {
    private Logger logger = Logger.getLogger(MaintainServiceImp.class);
    @Autowired
    MaintainMapper maintainMapper;

    @Override
    public synchronized List<VoteSubject>  getVoteSubjects(HttpServletRequest request) {
        List<VoteSubject> voteSubjects = null;
        try {
            VoteUser user = (VoteUser) request.getSession().getAttribute(Constants.SESSION_USER);

            if(user.getUtype()>10){//普通用户只能管理自己发布的投票
                voteSubjects = maintainMapper.getVoteSubjectsByUid(user.getUid());
            }else {
                voteSubjects = maintainMapper.getVoteSubjects();
            }
            logger.info("ready to get option info...");
            for(VoteSubject vs:voteSubjects){
                List<VoteOption> options = maintainMapper.getOptionsByVsid(vs.getVsid());
                if(options!=null){
                    vs.setOptions(options);
                }
            }
        }catch (Exception e){
            logger.info(e.toString());
        }
        return voteSubjects;
    }

    //设置投票信息状态
    @Override
    public void setVoteSubjectStateByVsid(HttpServletRequest request) {
        VoteSubject voteSubject = new VoteSubject();
        try {
            int vsid = Integer.parseInt(request.getParameter("vsid"));
            int state = Integer.parseInt(request.getParameter("state"));
            voteSubject.setVsid(vsid);
            voteSubject.setState(state);
            maintainMapper.setVoteSubjectStateByVsid(voteSubject);

        }catch (Exception e){
            logger.info(e.toString());
        }
    }

    //删除投票信息
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSubjectByVsid(HttpServletRequest request) {
        //try{
            int vsid = Integer.parseInt(request.getParameter("vsid"));
            maintainMapper.deleteItemByVsid(vsid);
            logger.info("删除voteitem");
            maintainMapper.deleteOptionsByVsid(vsid);
            maintainMapper.deleteSubjectByVsid(vsid);
//        }catch (Exception e){
//            logger.info(e.toString());
//        }
    }

    @Override
    public List<VoteUser> getAllUsers(HttpServletRequest request) {
        VoteUser user = (VoteUser) request.getSession().getAttribute(Constants.SESSION_USER);
        int uid = user.getUid();
        return maintainMapper.getAllUsers(uid);
    }

    @Override
    public void deleteUser(HttpServletRequest request) {
        int uid = Integer.parseInt(request.getParameter("uid"));
        maintainMapper.deleteUser(uid);
    }

    @Override
    public int grantUser(HttpServletRequest request) {
        int uid = Integer.parseInt(request.getParameter("uid"));
        int utype = maintainMapper.getUtypeByUid(uid);
        if(utype==100){
            maintainMapper.grantUser(uid);
            return ResultCodeEnum.GRANT_SUCCESS.value();
        }else{
            return ResultCodeEnum.GRANT_FAILD.value();
        }
    }

    @Override
    public int revokeUser(HttpServletRequest request) {
        int uid = Integer.parseInt(request.getParameter("uid"));
        int utype =maintainMapper.getUtypeByUid(uid);
        if(utype==10){
            maintainMapper.revokeUser(uid);
            return ResultCodeEnum.REVOKE_SUCCESS.value();
        }else{
            return ResultCodeEnum.REVOKE_FAILD.value();
        }
    }


}
