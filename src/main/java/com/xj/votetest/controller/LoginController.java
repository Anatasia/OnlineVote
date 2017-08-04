package com.xj.votetest.controller;

import com.xj.votetest.base.BaseController;
import com.xj.votetest.common.ResultCodeEnum;
import com.xj.votetest.pojo.VoteUser;
import com.xj.votetest.service.LoginService;
import com.xj.votetest.common.AjaxResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xujuan1 on 2017/7/26.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
    private Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;

    //用户注册
    @RequestMapping(value = "/voteUser_register.do", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult register(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        logger.info("deal with /login/voteUser_register ");
        int res = loginService.register(request);
        logger.info("res = " + res );
        if(res!= ResultCodeEnum.REGISTER_SUCCESS.value()){
            result.setCode(-1);
            result.setMsg(ResultCodeEnum.resultCodeDesc.get(res));
        }
        return result;
    }

    //用户登录
    @RequestMapping(value = "/voteUser_login", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult login(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        logger.info("deal with /login/voteUser_login ");
        VoteUser user = loginService.login(request);

        if(user==null){
            result.setMsg("登录失败");
            result.setCode(-1);
        }else{
            setSessionUser(user);
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
                logger.info("session info:" + user.getUname());
                result.setObj(user);
            }
        }catch (Exception e){
            result.setCode(-1);
            result.setMsg(e.toString());
        }
        return  result;
    }

    //用户退出
    @RequestMapping(value = "/loginout.do",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult loginOut(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        removeSessionUser();
        return result;
    }
}
