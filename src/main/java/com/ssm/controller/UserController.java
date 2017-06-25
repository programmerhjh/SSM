package com.ssm.controller;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by acer on 2017/6/24.
 */
@Controller
@RequestMapping("/login-module")
public class UserController {
    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/login-page")
    public String showUser(ModelMap model){
        log.info("查询用户信息");
        User user = userService.selectUserById(1);
        model.addAttribute("user",user);
        return "login-module/login-page";
    }
    @RequestMapping("/registered")
    public String showProperty(ModelMap model){

        return "login-module/registered";
    }
    @RequestMapping("/forgot-password")
    public String showForgotPassword(ModelMap model){

        return "login-module/forgot-password";
    }
}
