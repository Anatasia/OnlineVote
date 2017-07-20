package com.xj.service.impl;

import com.xj.dao.VoteUserMapper;
import com.xj.pojo.VoteUser;
import com.xj.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xujuan1 on 2017/7/19.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger = Logger.getLogger(LoginServiceImpl.class);
    @Autowired
    private VoteUserMapper voteUserMapper;

    @Override
    public VoteUser verifyUser(VoteUser user) {
        VoteUser voteUser = voteUserMapper.findUserByName(user.getUname());
        if(voteUser!=null&&voteUser.getPwd().equals(user.getPwd())){
            return voteUser;
        }
        else return null;
    }

    @Autowired
    private VoteUser voteUser;
    @Override
    public String register(HttpServletRequest request) {
        String res = "注册成功";
        try {
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            String confirmPwd = request.getParameter("confirmPwd");
            if(uname==null||uname.isEmpty()||uname.equals("")) return "请输入用户名";
            if(pwd==null||pwd.isEmpty()||pwd.equals("")) return "请输入密码";
            if(confirmPwd==null||confirmPwd.isEmpty()||confirmPwd.equals("")) return "请输入确认密码";
            if(pwd.equals(confirmPwd)) {
                voteUser.setUname(uname);
                voteUser.setPwd(pwd);
                voteUserMapper.addNewUser(voteUser);
            }else{
                return "密码不一致，请重新输入密码";
            }
        }catch (NullPointerException e){
            logger.info(e.toString());
            res = "参数名称错误";
        }finally {
            return res;
        }
    }

}
