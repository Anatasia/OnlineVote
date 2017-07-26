package com.xj.votetest.controller;

import com.xj.votetest.common.ResultCodeEnum;
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
public class LoginController {
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
}
