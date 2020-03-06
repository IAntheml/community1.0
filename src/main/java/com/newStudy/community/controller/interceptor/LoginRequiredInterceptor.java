package com.newStudy.community.controller.interceptor;

import com.newStudy.community.annotation.LoginRequired;
import com.newStudy.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author shkstart
 * @create 2020-02-23-16:11
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Spring MVC提供的类型HandlerMethod，如果拦截到的是一个方法则是HandlerMethod类型
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取拦截到的方法对象
            Method method = handlerMethod.getMethod();
            //获取方法对象上的指定类型注解
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            if(loginRequired != null && hostHolder.getUser() == null){
                //因为该方法是接口声明的，所以不能向controller那样通过return模板
                //重定向到request.getContextPath()项目路径下的/login
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }
}
