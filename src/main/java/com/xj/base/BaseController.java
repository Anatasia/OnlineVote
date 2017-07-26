package com.xj.base;

import com.xj.common.Constants;
import com.xj.common.DateEditor;
import com.xj.pojo.VoteUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xujuan1 on 2017/7/19.
 */
public class BaseController {
    private Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    private HttpServletRequest request;

    public void setSessionUser(VoteUser user){
        request.getSession(true).setAttribute(Constants.SESSION_USER, user);
    }

    public VoteUser getSessionUser(){
        return (VoteUser) request.getSession().getAttribute(Constants.SESSION_USER);
    }

    public void setRequestAttr(String key, Object value){
        request.setAttribute(key,value);
    }

    @InitBinder
    public void  initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
}
