package com.xj.votetest.controller;

import com.xj.votetest.base.BaseController;
import com.xj.votetest.common.AjaxResult;
import com.xj.votetest.common.ResultCodeEnum;
import com.xj.votetest.pojo.VoteSubject;
import com.xj.votetest.pojo.VoteUser;
import com.xj.votetest.service.MaintainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/8/2.
 */
@Controller
@RequestMapping("/maintain")
public class MaintainController extends BaseController{
    private Logger logger = Logger.getLogger(MaintainController.class);
    @Autowired
    MaintainService maintainService;

    @RequestMapping(value = "/voteSubject_findAll.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult getVoteSubject(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try{
            List<VoteSubject> voteSubjects = maintainService.getVoteSubjects(request);
            result.setObj(voteSubjects);
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("发生错误");
            logger.info(e.toString());
        }
        return result;
    }

    @RequestMapping(value = "/expireSubject.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult expireSubject(HttpServletRequest request){
        //设置主题失效，不能进行投票
        AjaxResult result = new AjaxResult(1);
        try {
            maintainService.setVoteSubjectStateByVsid(request);
        }catch (Exception e){
            logger.info(e.toString());
            result.setCode(-1);
        }
        return result;
    }


    @RequestMapping(value = "/activeSubject.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult activeSubject(HttpServletRequest request){
        //设置主题失效，不能进行投票
        AjaxResult result = new AjaxResult(1);
        try {
            maintainService.setVoteSubjectStateByVsid(request);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result.setCode(-1);
        }
        return result;
    }

    @RequestMapping(value = "/deleteSubject.do", method={RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteSubject(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try {
            maintainService.deleteSubjectByVsid(request);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result.setCode(-1);
        }
        return result;
    }


    //获取用户登录信息
    @RequestMapping(value = "/voteSubject_getLoginUser.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult getLoginUser(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try{
            logger.info("deal with /login/voteSubject_getLoginUser");
            VoteUser user = getSessionUser();
            if(null==user){
                result.setCode(-1);
                result.setMsg("用户登录信息过期");
            }else {
                if(user.getUtype()!=1){
                    result.setCode(-1);
                    result.setMsg("用户没有相关权限");
                }
                logger.info("session info:" + user.getUname());
                result.setObj(user);
            }
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg(e.toString());
            logger.error(e.getMessage(),e);
        }
        return  result;
    }

    //获取系统当前所有用户
    @RequestMapping(value = "/voteUser_findAll.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult getAllUsers(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try{
            List<VoteUser> users = maintainService.getAllUsers(request);
            result.setObj(users);
        }catch (Exception e){
            logger.info(e.toString());
            result.setCode(-1);
        }
       return result;

    }

    //删除用户
    @RequestMapping(value = "/deleteUser.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteUser(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try {
            maintainService.deleteUser(request);
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("删除用户失败");
            logger.info(e.toString());
        }
        return result;
    }



    @RequestMapping(value = "/grantUser.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult grantUser(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try {
            int res = maintainService.grantUser(request);
            if(res== ResultCodeEnum.GRANT_FAILD.value()){
                result.setCode(-1);
                result.setMsg(ResultCodeEnum.resultCodeDesc.get(ResultCodeEnum.GRANT_FAILD.value()));
            }
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("用户授权出现问题");
            logger.info(e.toString());
        }
        return result;
    }


    @RequestMapping(value = "/revokeUser.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult revokeUser(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try {
            int res = maintainService.revokeUser(request);
            if(res== ResultCodeEnum.REVOKE_FAILD.value()){
                result.setCode(-1);
                result.setMsg(ResultCodeEnum.resultCodeDesc.get(ResultCodeEnum.REVOKE_FAILD.value()));
            }
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("收回用户权限出现问题");
            logger.info(e.toString());
        }
        return result;
    }

}
