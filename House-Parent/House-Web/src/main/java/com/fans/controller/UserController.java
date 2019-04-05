package com.fans.controller;

import com.fans.model.User;
import com.fans.result.ResultMsg;
import com.fans.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/accounts/register")
    public ModelAndView accountsRegister(User user, ModelMap modelMap) {
        if (user == null || user.getName() == null) {
            return new ModelAndView("/user/accounts/register");
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


}
