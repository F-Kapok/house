package com.fans.interceptor;

import com.fans.common.CommonConstants;
import com.fans.common.UserContext;
import com.fans.model.User;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName AuthInterceptor
 * @Description:
 * @Author fan
 * @Date 2019-04-07 12:11
 * @Version 1.0
 **/
@Component(value = "authInterceptor")
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, value) -> {
            String errorMsg = "errorMsg";
            String successMsg = "successMsg";
            String target = "target";
            if (errorMsg.equals(key) || successMsg.equals(key) || target.equals(key)) {
                request.setAttribute(key, Joiner.on(",").join(value));
            }
        });
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(CommonConstants.USER_ATTRIBUTE);
        if (user != null) {
            UserContext.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
