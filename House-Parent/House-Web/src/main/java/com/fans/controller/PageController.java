package com.fans.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName PageController
 * @Description:
 * @Author fan
 * @Date 2019-04-04 17:51
 * @Version 1.0
 **/
@RestController
public class PageController {
    @RequestMapping("/index")
    public ModelAndView showIndex() {
        return new ModelAndView("/homepage/index");
    }

    @RequestMapping("/error/{page}")
    public ModelAndView toPage(@PathVariable String page) {
        return new ModelAndView("/error/" + page);
    }
}
