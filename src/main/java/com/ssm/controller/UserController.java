package com.ssm.controller;

import Validator.UserValidator;
import com.ssm.model.User;
import com.ssm.service.UserService;
import implement.IndustrySMS;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tool.EncoderByMD5;
import tool.JsonToMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


/**
 * Created by acer on 2017/6/24.
 */
@Controller
@RequestMapping("/login-module")
public class UserController{

    private Logger log = Logger.getLogger(UserController.class);
    private EncoderByMD5 encoderByMD5 = new EncoderByMD5();
    @Resource
    private UserService userService;

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping("/login-page")
    public String showLoginPage(){
        log.info("登陆页面");
        return "login-module/login-page";
    }

    @RequestMapping("/checkPhone")
    public String checkPhonePage(){
        log.info("短信验证页面");
        return "login-module/checkPhone";
    }

    @RequestMapping("/show-page")
    public String showPage(){
        log.info("主页");
        return "login-module/show-page";
    }


    @RequestMapping("/loginning")
    public @ResponseBody Map<String, Object> userLoginCheck(HttpSession session, @RequestBody String loginData, BindingResult bindingResult) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("登录验证");
        Map<String, Object> userFormation = JsonToMap.toHashMap(loginData);
        try {
            User isExistUser = userService.checkUserExist((String) userFormation.get("name"), encoderByMD5.encrypt( userFormation.get("password").toString()));
            if(!UserValidator.loginParam(isExistUser,bindingResult)){
                userFormation.put("paramError",1);
                return userFormation;
            }
            if (isExistUser != null) {
                userFormation.put("OK","OK");
                if (isExistUser.getPhone() == null) {
                    log.info("短信验证");
                    session.setAttribute("user", isExistUser);
                    userFormation.put("MessageValidation",true);
                    return userFormation;
                }
                log.info("登陆成功");
                session.setAttribute("user", isExistUser);
                return userFormation;
            }
        } catch (Exception e) {
            log.info("登陆异常..请稍后再试");
            userFormation.put("loginError",e.getMessage());
            return userFormation;
        }
        log.info("登录失败");
        return userFormation;
    }

    @RequestMapping("/registered")
    public String showProperty(){
        log.info("用户注册");
        return "login-module/registered";
    }

    @RequestMapping("/forgot-password")
    public String showForgotPassword(){
        return "login-module/forgot-password";
    }

    @RequestMapping("/validatePhone")
    public @ResponseBody String validatePhone(@RequestBody String phone){
        IndustrySMS.setTo(phone);
        IndustrySMS.execute();
        System.out.println("再次获取验证码：" + IndustrySMS.getValidateNum());
        return IndustrySMS.getValidateNum();
    }

    @RequestMapping("/addPhone")
    public @ResponseBody String addPhone(HttpSession session) {
        log.info("添加用户电话号码");
        System.out.println(IndustrySMS.getTo() + session.getAttribute("user"));
        if (!(IndustrySMS.getTo() == null || IndustrySMS.getTo() == "" || session.getAttribute("user") == null)){
            log.info("用户已登录");
            userService.addPassValidatePhone(IndustrySMS.getTo(),((User)session.getAttribute("user")).getId());
            return "true";
        } else {
            session.removeAttribute("user");
            return "false";
        }
    }

    /**
     * 检测用户名是否存在
     * @param data
     * @return
     */
    @RequestMapping("checkUsernameIsExist")
    public @ResponseBody String checkUsernameIsExist(@RequestBody String data){
        log.info("检测用户名是否存在");
        String username = JsonToMap.toHashMap(data).get("username").toString();
        if(userService.checkUsernameIsExist(username) < 1){
            return "true";
        }else{
            return "false";
        }
    }

    @RequestMapping("registerUser")
    public @ResponseBody String registerUser(@RequestBody String data,HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("添加注册信息");
        Map<String, Object> userFormation = JsonToMap.toHashMap(data);
        String username = userFormation.get("username").toString();
        String password = encoderByMD5.encrypt(userFormation.get("password").toString());
        userService.registeredUser(username,password);
        User user = userService.checkUserExist(username,password);
        if(user != null){
            session.setAttribute("user",user);
            return "true";
        }else{
            return "false";
        }
    }
}
