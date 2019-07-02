package com.fans.controller;

import com.fans.common.CommonConstants;
import com.fans.model.User;
import com.fans.result.ResultMsg;
import com.fans.service.interfaces.AgencyService;
import com.fans.service.interfaces.UserService;
import com.fans.utils.HashUtils;
import com.fans.utils.WebInitialize;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description:
 * @Author fan
 * @Date 2019-04-04 13:57
 * @Version 1.0
 **/
@RestController
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "agencyService")
    private AgencyService agencyService;

    @RequestMapping(value = "/accounts/register")
    public ModelAndView accountsRegister(User user, ModelMap modelMap) {
        if (user == null || user.getName() == null) {
            modelMap.put("agencyList", agencyService.getAllAgency());
            return new ModelAndView("/user/accounts/register", modelMap);
        }

        // 用户验证
        ResultMsg resultMsg = UserHelper.validate(user);
        if (resultMsg.isSuccess() && userService.addAccount(user)) {
            modelMap.put("email", user.getEmail());
            return new ModelAndView("/user/accounts/registerSubmit");
        } else {
            return new ModelAndView("redirect:/accounts/register?" + resultMsg.asUrlParams());
        }
    }

    @RequestMapping(value = "/accounts/verify", method = RequestMethod.GET)
    public ModelAndView verify(String key) {
        boolean result = userService.enable(key);
        if (result) {
            return new ModelAndView("redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams());
        } else {
            return new ModelAndView("redirect:/accounts/register?" + ResultMsg.errorMsg("激活失败,请确认链接是否过期"));
        }
    }

    @RequestMapping(value = "/accounts/signin")
    public ModelAndView signIn() {
        WebInitialize webInitialize = new WebInitialize().invoke();
        ModelAndView model = new ModelAndView();
        HttpServletRequest request = webInitialize.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String target = request.getParameter("target");
        //如果登陆名或者密码为空则跳转登陆页
        if (username == null || password == null) {
            request.setAttribute("target", target);
            model.setViewName("/user/accounts/signin");
            return model;
        }
        User user = userService.auth(username, password);
        if (user == null) {
            String viewName = "redirect:/accounts/signin?"
                    .concat("target=")
                    .concat(target)
                    .concat("&username=")
                    .concat(username)
                    .concat("&")
                    .concat(ResultMsg.errorMsg("用户名密码错误").asUrlParams());
            model.setViewName(viewName);
        } else {
            HttpSession session = webInitialize.getSession();
            session.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
            session.setAttribute(CommonConstants.PLAIN_USER_ATTRIBUTE, user);
            if (StringUtils.isNotBlank(target)) {
                model.setViewName("redirect:".concat(target));
            } else {
                model.setViewName("redirect:/index");
            }
        }
        return model;
    }

    @RequestMapping(value = "/accounts/logout")
    public ModelAndView logout() {
        WebInitialize webInitialize = new WebInitialize().invoke();
        HttpSession session = webInitialize.getSession();
        session.invalidate();
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/accounts/profile")
    public ModelAndView profile(User updateUser) {
        if (updateUser.getEmail() == null) {
            return new ModelAndView("/user/accounts/profile");
        }
        userService.updateUser(updateUser, updateUser.getEmail());
        User query = new User();
        query.setEmail(updateUser.getEmail());
        List<User> userList = userService.getUserByQuery(query);
        WebInitialize webInitialize = new WebInitialize().invoke();
        webInitialize.getSession().setAttribute(CommonConstants.USER_ATTRIBUTE, userList.get(0));
        return new ModelAndView("redirect:/accounts/profile?"
                .concat(ResultMsg.successMsg("更新成功").asUrlParams()));

    }

    @RequestMapping(value = "/accounts/changePassword")
    public ModelAndView setPwd(@RequestParam Map<String, Object> inParam) {
        ModelAndView modelAndView = new ModelAndView();
        String email = inParam.get("email").toString();
        String password = inParam.get("password").toString();
        String newPassword = inParam.get("newPassword").toString();
        String confirmPassword = inParam.get("confirmPassword").toString();
        User user = userService.auth(email, password);
        if (user == null || !newPassword.equals(confirmPassword)) {
            modelAndView.setViewName("redirect:/accounts/profile?"
                    .concat(ResultMsg.errorMsg("密码错误").asUrlParams()));
            return modelAndView;
        }
        User updateUser = new User();
        updateUser.setPasswd(HashUtils.encryPassword(newPassword));
        userService.updateUser(updateUser, email);
        modelAndView.setViewName("redirect:/accounts/profile?"
                .concat(ResultMsg.successMsg("更新成功").asUrlParams()));
        return modelAndView;
    }
}

