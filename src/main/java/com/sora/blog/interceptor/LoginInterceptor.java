package com.sora.blog.interceptor;

import com.sora.blog.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author Sora Chen
 * @Date 2022/2/6 22:52
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null||user.getType()!=1){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
