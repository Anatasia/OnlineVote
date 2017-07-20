package com.xj.controller;

import com.xj.base.BaseController;
import com.xj.common.AjaxResult;
import com.xj.pojo.VoteUser;
import com.xj.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xujuan1 on 2017/7/17.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
    private Logger logger = Logger.getLogger(LoginController.class);
    @Resource
    private VoteUser user;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/voteUser_login", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, HttpServletResponse response){
       AjaxResult result = new AjaxResult(1);
        try{
            logger.info("deal login info ");
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            user.setUname(uname);
            user.setPwd(pwd);
            VoteUser voteUser = loginService.verifyUser(user);
            if(null == voteUser){
                result.setCode(-1);
                result.setMsg("账户密码错误");
            }else{
                setSessionUser(voteUser);
                setCookieUser(uname, pwd, true,response);
            }

        }catch(Exception e){
            result.setCode(-1);
            logger.info(e.getStackTrace());
            result.setMsg("内部错误");
        }

        return result;
    }

    @RequestMapping(value="/voteUser_register",method={RequestMethod.POST})
    @ResponseBody
    public AjaxResult register(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try{
            logger.info("deal with /login/voteUser_register ");
            String res = loginService.register(request);
            result.setMsg(res);
        }catch(Exception e){
            result.setCode(-1);
            logger.info(e.getStackTrace());
            result.setMsg("内部错误");
        }
        return result;
    }

    public void setCookieUser(String userName, String password, boolean remberPwd, HttpServletResponse response) {
        // 将用户名密码保存在cookie中
        if (remberPwd) {
            Cookie cookieUser = new Cookie("cookie_user", userName + "-" + password);
            // 设定有效时间 以秒(s)为单位
            cookieUser.setMaxAge(2 * 60 * 60);
            // 设置Cookie路径和域名
            cookieUser.setPath("/");
            response.addCookie(cookieUser);
        } else {
            Cookie cookieUser = new Cookie("cookie_user", null);
            // 设定有效时间 以秒(s)为单位
            cookieUser.setMaxAge(0);
            // 设置Cookie路径和域名
            cookieUser.setPath("/");
            response.addCookie(cookieUser);
        }
    }

}



