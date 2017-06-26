package com.ssm.controller;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * Created by acer on 2017/6/24.
 */
@Controller
@RequestMapping("/login-module")
public class UserController{
    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/login-page")
    public String showLoginPage(){
        log.info("首页");
        return "login-module/login-page";
    }

    @RequestMapping("/loginning")
    public String userLoginCheck(HttpSession session,ModelMap model, User user){
        log.info("登录验证");
        User isExistUser = userService.checkUserExist(user.getName(),user.getPassword());
        if(isExistUser != null){
            if(isExistUser.getPhone() == null){
                log.info("短信验证");
                return "login-module/organizing_data";
            }
            session.setAttribute("user",user);
            return "login-module/show-page";
        }else{
            log.info("登录失败");
            return "login-module/login-page";
        }
    }

    @RequestMapping("/registered")
    public String showProperty(ModelMap model){
        log.info("用户注册");

        return "login-module/registered";
    }
    @RequestMapping("/forgot-password")
    public String showForgotPassword(ModelMap model){

        return "login-module/forgot-password";
    }

}
