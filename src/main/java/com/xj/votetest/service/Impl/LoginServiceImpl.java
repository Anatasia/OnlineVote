package com.xj.votetest.service.Impl;

import com.xj.votetest.common.Constants;
import com.xj.votetest.dao.VoteUserMapper;
import com.xj.votetest.common.InputVerify;
import com.xj.votetest.pojo.VoteUser;
import com.xj.votetest.common.ResultCodeEnum;
import com.xj.votetest.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xujuan1 on 2017/7/26.
 */
@Service
public class LoginServiceImpl implements LoginService{
    private Logger logger = Logger.getLogger(LoginServiceImpl.class);
    @Autowired
    private VoteUserMapper voteUserMapper;

    @Override
    public int register(HttpServletRequest request) {
        logger.info("registering....");
        int res = ResultCodeEnum.REGISTER_SUCCESS.value();
        try {
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            String confirmPwd = request.getParameter("confirmPwd");

            if(uname==null||uname.isEmpty()||uname.equals("")) return ResultCodeEnum.UNAME_NULL.value();
            if(pwd==null||pwd.isEmpty()||pwd.equals("")) return ResultCodeEnum.PWD_NULL.value();
            if(confirmPwd==null||confirmPwd.isEmpty()||confirmPwd.equals("")) return ResultCodeEnum.COMFIRMPWD_NULL.value();
            if(!pwd.equals(confirmPwd)) {
                logger.info("用户名密码不一致");
                return ResultCodeEnum.PWD_NOT_EQUAL_WITH_CONFIRMPWD.value();
            }
            //验证用户名、密码是否包含恶意字符
            InputVerify inputVerify = new InputVerify();
            if(inputVerify.hasInjectionData(uname)||inputVerify.hasInjectionData(pwd)) return ResultCodeEnum.MALICIOUS_CODE_FOUND.value();
            logger.info("用户输入验证通过：uname="+uname+" pwd="+pwd+" confirmpwd="+confirmPwd);

            VoteUser voteUser = new VoteUser();
            voteUser.setUname(uname);
            voteUser.setPwd(pwd);
            voteUser.setUtype(Constants.NORMAL_USER);
            int count = voteUserMapper.addNewUser(voteUser);
            if(count!=1){
                logger.info("count = "+ count+"  用户添加失败！");
                res = ResultCodeEnum.INTERNEL_ERROR.value();
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            res = ResultCodeEnum.INTERNEL_ERROR.value();
        }
        return res;
    }

    @Override
    public VoteUser login(HttpServletRequest request) {
        logger.info("logining...");
        VoteUser voteUser = new VoteUser();
        try {
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            logger.info("uname="+uname+"  pwd="+pwd);
            if(uname==null||uname.isEmpty()||uname.equals("")) return null;
            if(pwd==null||pwd.isEmpty()||pwd.equals("")) return null;
            logger.info("uname="+uname+"  pwd="+pwd);

            voteUser.setUname(uname);
            voteUser.setPwd(pwd);
            voteUser  = voteUserMapper.findUserByUnameAndPwd(voteUser);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

        return voteUser;
    }
}
