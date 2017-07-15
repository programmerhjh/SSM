package com.ssm.controller;

import Validator.UserValidator;
import com.ssm.model.UserBehavior;
import com.ssm.model.UserQueryVo;
import com.ssm.model.User;
import com.ssm.service.UserService;
import implement.IndustrySMS;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tool.EncoderByMD5;
import tool.JsonToMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "login-module/login-page";
    }

    @RequestMapping("addUserData")
    public String addUserData(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> allError = bindingResult.getAllErrors();
            for (ObjectError objectError : allError){
                System.out.println(objectError.getDefaultMessage());
            }
            return "error";
        }
        userService.updateUserHasCompleteFormation(user);
        log.info("添加个人信息成功");
        return "login-module/show-page";
    }
    /**
     * 完善个人资料页面
     * @return
     */
    @RequestMapping("/organizing_data")
    public String organizingDataPage(Model model,HttpSession session){
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            model.addAttribute("user",user);
            return "login-module/organizing_data";
        }
        log.info("用户未登录");
        return "login-module/login-page";
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
    public @ResponseBody Map<String, Object> userLoginCheck(HttpSession session, @RequestBody String loginData) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("登录验证");
        Map<String, Object> userFormation = JsonToMap.toHashMap(loginData);
        try {
            User isExistUser = userService.checkUserExist((String) userFormation.get("name"), encoderByMD5.encrypt( userFormation.get("password").toString()));
            if (isExistUser != null) {
                userFormation.put("OK","OK");
                if (isExistUser.getPhone() == null) {
                    log.info("短信验证");
                    session.setAttribute("user", isExistUser);
                    session.setAttribute("isLogin",true);
                    userFormation.put("MessageValidation",true);
                    return userFormation;
                }
                log.info("登陆成功");
                session.setAttribute("user", isExistUser);
                session.setAttribute("isLogin",true);
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

    /**
     * 检查用户是否完善资料
     * @param session
     * @return
     */
    @RequestMapping("hasCompleteUserFormation")
    public @ResponseBody String hasCompleteUserFormation(HttpSession session){
        User user;
        if(session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
            int i = userService.checkUserHasCompleteFormation(user);
            if(i == 1){
                return "yes";
            }else if(i == 0){
                return "no";
            }
        }
        return "noLogin";
    }

    @RequestMapping("addNewPassword")
    public @ResponseBody String addNewPassword(@RequestBody String data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("添加用户新密码");
        if(data != null){
            Map<String,Object> temp = JsonToMap.toHashMap(data);
            String name = temp.get("name").toString();
            String password = encoderByMD5.encrypt(temp.get("password").toString());
            userService.addNewPassword(name,password);
            return "success";
        }
        log.info("系统错误，data为空");
        return "error";
    }

    @RequestMapping("checkUserIsExist")
    public @ResponseBody String checkUserIsExist(@RequestBody String data){
        log.info("检查用户是否存在");
        System.out.println(data);
        if(data != null){
            Map<String,Object> temp = JsonToMap.toHashMap(data);
            String username = temp.get("name").toString();
            String phone = temp.get("phone").toString();
            if(userService.checkUserExistByPhone(username,phone) == 1){
                return "exist";
            }else {
                log.info("用户不存在");
                return "no";
            }
        }
        log.info("系统错误..获取不到data值");
        return "false";
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
