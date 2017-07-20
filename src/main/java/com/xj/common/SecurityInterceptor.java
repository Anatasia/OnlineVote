package com.xj.common;

import com.xj.base.BaseController;
import com.xj.pojo.VoteUser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xujuan1 on 2017/7/19.
 * 功能：自定义拦截器
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter{
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        HttpSession session = request.getSession(true);
//        VoteUser user = (VoteUser) session.getAttribute(BaseController.SESSION_USER);
        // session 用户是否登陆
//        if (null == user) {
//            response.sendRedirect(request.getContextPath() + "/login.html");
//            return false;
//        }
        return true;
    }

}
