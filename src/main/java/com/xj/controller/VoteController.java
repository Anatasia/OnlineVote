package com.xj.controller;

import com.xj.base.BaseController;
import com.xj.common.AjaxResult;
import com.xj.common.Constants;
import com.xj.common.ResultCodeEnum;
import com.xj.pojo.VoteItem;
import com.xj.pojo.VoteSubject;
import com.xj.pojo.VoteUser;
import com.xj.service.VoteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xujuan1 on 2017/7/18.
 */
@Controller
@RequestMapping("/vote")
public class VoteController extends BaseController{
    private Logger logger = Logger.getLogger(VoteController.class);
    @Autowired
    private VoteService voteService;
    @RequestMapping(value = "/voteSubject_findAll", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult getVoteSubject(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        logger.info("deal with /vote/voteSubject_findAll");
        result.setObj(voteService.getAllSubject());
        return  result;
    }

    @RequestMapping(value = "/voteSubject_getLoginUser", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult getLoginUser(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try{
            logger.info("deal with /vote/voteSubject_getLoginUser");
            VoteUser user = getSessionUser();
            if(null==user){
                result.setCode(0);
                result.setMsg("用户登录信息过期");
            }else {
                logger.info("session info:" + user.getUname());
                result.setObj(user);
            }
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg(e.toString());
        }
        return  result;
    }

    @RequestMapping(value = "/voteSubject_findByVsid", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult getVoteSubjectByVsid(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        logger.info("deal with /vote/voteSubject_findByVsid");
        int vsid = Integer.parseInt(request.getParameter("vsid"));
        logger.info("vsid = " + vsid);
        //查询vsid对应的主题
        VoteSubject vs = voteService.findVoteSubjectByVsid(vsid);
        result.setObj(vs);
        return result;
    }


    //投票，并将结果写入数据库
    @RequestMapping(value = "/voteSubject_vote",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult voteOption(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try{
            int voteResult = voteService.insertVoteItem(request);
            if(voteResult!=ResultCodeEnum.VOTE_SUCCESS.value()){
                result.setCode(-1);
            }
            result.setMsg(ResultCodeEnum.resultCodeDesc.get(voteResult));
        }catch (Exception e){
            result.setCode(-1);
            logger.info(e.getStackTrace());
            result.setMsg(e.toString());
        }
        return  result;
    }

    //查看投票
    @RequestMapping(value = "/voteSubject_findByVsidWithOptionInfo", method ={RequestMethod.POST})
    @ResponseBody
    public AjaxResult findVoteOptionInfo(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try {
            logger.info("deal with /vote/voteSubject_findByVsidWithOptionInfo");
            VoteSubject vs = voteService.findVoteOptionInfo(request);
            result.setObj(vs);
        }catch (Exception e){
            result.setCode(-1);
            logger.info(e.getMessage());
            result.setMsg(e.toString());
        }
        return result;
    }

    //添加新投票
    @RequestMapping(value = "/voteSubject_add", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addVoteSubject(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try{
            logger.info("deal with /vote/voteSubject_add");
            int insertRes = voteService.addNewVoteSubject(request);
            if(insertRes!=ResultCodeEnum.INSERT_NEWVOTEINFO_SUCCESS.value()){
                result.setMsg(ResultCodeEnum.resultCodeDesc.get(insertRes));
                result.setCode(-1);
            }
        }catch (Exception e){
            result.setCode(-1);
            logger.info(e.getMessage());
            result.setMsg(e.toString());
        }
        return result;
    }

}
