package com.ssm.controller;

import com.ssm.model.User;
import com.ssm.modelCustom.UserExpand;
import com.ssm.service.UserService;
import implement.IndustrySMS;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import tool.EncoderByMD5;
import tool.GetPropertyUtil;
import tool.JsonToMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 用户控制器
 * Created by acer on 2017/6/24.
 */
@Controller
@RequestMapping("/login-module")
public class UserController{

    //log日志输出
    private Logger log = Logger.getLogger(UserController.class);
    //md5加密工具
    private EncoderByMD5 encoderByMD5 = new EncoderByMD5();
    @Resource
    private UserService userService;


    /**
     * 用户登出
     * @param session
     * @return
     * @time 2017年7月11日17:49:54
     */
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "login-module/login-page";
    }

    /**
     * 添加用户信息
     * @param user
     * @param bindingResult
     * @param session
     * @return
     * @time 2017年7月13日17:50:29
     */
    @RequestMapping("addUserData")
    public String addUserData(@Valid UserExpand user, BindingResult bindingResult,HttpSession session){
        //Spring validate
        if(bindingResult.hasErrors()){
            List<ObjectError> allError = bindingResult.getAllErrors();
            for (ObjectError objectError : allError){
                System.out.println(objectError.getDefaultMessage());
            }
            //存在错误就跳转到500页面
            return "redirect:../500";
        }
        //标识用户已完成资料完善
        userService.updateUserHasCompleteFormation(user);
        session.setAttribute("user",user);
        log.info("添加个人信息成功");
        return "redirect:../bbs-module/index";
    }

    /**
     * 完善个人资料页面
     * @return
     * @time 2017年7月14日17:55:46
     */
    @RequestMapping("/organizing_data")
    public String organizingDataPage(Model model,HttpSession session){
        if(session.getAttribute("user") != null){
            //UserExpand包装类包含了User的头像图片地址
            UserExpand userTemp = (UserExpand) session.getAttribute("user");
            //检查用户是否存在
            userTemp = userService.checkUserExist(userTemp.getName(), userTemp.getPassword());//更新用户信息
            //用于页面回显
            model.addAttribute("user",userTemp);
            return "login-module/organizing_data";
        }
        log.info("用户未登录");
        return "login-module/login-page";
    }

    /**
     * 包括頭像的上傳和文件上傳
     * @param request
     * @param response
     * @throws IOException
     * @time 2017年7月15日17:55:31
     */
    @RequestMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //返回的结果字符串
        String resultParam = "";
        //构建返回的json串
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是文件
        if(commonsMultipartResolver.isMultipart(request)){
            //把request转换为二进制的HttpServletRequest
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取二进制的request中所有文件名称的迭代器
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                //文件名后缀
                String newFileSuffix = "." + file.getContentType().substring(file.getContentType().indexOf("/") + 1);
                //通过UUID和日期构造新的文件名
                String fileName = UUID.randomUUID() + new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(new Date()).toString() + file.getOriginalFilename().hashCode();// 文件名称
                //本地存储地址
                String localPath = GetPropertyUtil.getFileAddress("Pic") + fileName + newFileSuffix;
                File newFile = new File(localPath);
                //复制文件
                file.transferTo(newFile);
                //添加访问地址
                resultParam = GetPropertyUtil.getWebsiteAddress("localSaveAddress") + GetPropertyUtil.getFileAddress("PicDownload") + fileName + newFileSuffix;
            }
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(resultParam);  //返回url地址
        out.flush();
        out.close();
    }


    /**
     * 登陆页面
     * @return
     * @time 2017年7月02日18:00:18
     */
    @RequestMapping("/login-page")
    public String showLoginPage(){
        log.info("登陆页面");
        return "login-module/login-page";
    }

    /**
     * 短信验证页面，无UI美化
     * @return
     * @time 2017年7月25日18:00:54
     */
    @RequestMapping("/checkPhone")
    public String checkPhonePage(){
        log.info("短信验证页面");
        return "login-module/checkPhone";
    }

    /**
     * 用户登录验证
     * @param session
     * @param loginData
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @time 2017年7月03日18:01:22
     */
    @RequestMapping("/loginning")
    public @ResponseBody Map<String, Object> userLoginCheck(HttpSession session, @RequestBody String loginData) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("登录验证");
        //获取前台的用户名和密码
        Map<String, Object> userFormation = JsonToMap.toHashMap(loginData);
        try {
            //检查用户是否存在
            UserExpand isExistUser = userService.checkUserExist((String) userFormation.get("name"), encoderByMD5.encrypt( userFormation.get("password").toString()));
            if (isExistUser != null) {
                //存在则检测是不是已有手机号码
                userFormation.put("OK","OK");
                if (isExistUser.getPhone() == null) {
                    //无手机号则提示需进行短信验证
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
            //如有异常处理
            log.info("登陆异常..请稍后再试");
            userFormation.put("loginError",e.getMessage());
            return userFormation;
        }
        //若不存在则登录失败
        log.info("登录失败");
        return userFormation;
    }

    /**
     * 用户注册页面
     * @return
     * @time 2017年7月04日18:03:45
     */
    @RequestMapping("/registered")
    public String showProperty(){
        log.info("用户注册");
        return "login-module/registered";
    }

    /**
     * 忘记密码页面
     * @return
     * @time 2017年7月05日18:04:03
     */
    @RequestMapping("/forgot-password")
    public String showForgotPassword(){
        return "login-module/forgot-password";
    }

    /**
     * 发送验证码到前台
     * @param phone
     * @return
     * @time 2017年7月06日18:04:41
     */
    @RequestMapping("/validatePhone")
    public @ResponseBody String validatePhone(@RequestBody String phone){
        //IndustrySMS为第三方用于发短信的工具类
        IndustrySMS.setTo(phone);
        IndustrySMS.execute();
        System.out.println("再次获取验证码：" + IndustrySMS.getValidateNum());
        return IndustrySMS.getValidateNum();
    }

    /**
     * 添加用户电话号码
     * @param session
     * @return
     * @time 2017年7月08日18:05:13
     */
    @RequestMapping("/addPhone")
    public @ResponseBody String addPhone(HttpSession session) {
        log.info("添加用户电话号码");
        //如用户登录且验证电话号码通过就保存进数据库
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
            //i 为 1 则完善过资料，为0则还未完善资料
            int i = userService.checkUserHasCompleteFormation(user);
            if(i == 1){
                return "yes";
            }else if(i == 0){
                return "no";
            }
        }
        return "noLogin";
    }

    /**
     * 添加用户新密码
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @time 2017年7月11日18:08:52
     */
    @RequestMapping("addNewPassword")
    public @ResponseBody String addNewPassword(@RequestBody String data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("添加用户新密码");
        if(data != null){
            //获取用户的用户名和新密码
            Map<String,Object> temp = JsonToMap.toHashMap(data);
            String name = temp.get("name").toString();
            String password = encoderByMD5.encrypt(temp.get("password").toString());
            userService.addNewPassword(name,password);
            return "success";
        }
        log.info("系统错误，data为空");
        return "error";
    }

    /**
     * 检查用户是否存在
     * @param data
     * @return
     * @time 2017年7月13日18:09:33
     */
    @RequestMapping("checkUserIsExist")
    public @ResponseBody String checkUserIsExist(@RequestBody String data){
        log.info("检查用户是否存在");
        if(data != null){
            //获取用户的用户名和电话来查询用户是否存在
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

    /**
     * 添加注册信息
     * @param data
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @time 2017年7月09日18:10:19
     */
    @RequestMapping("registerUser")
    public @ResponseBody String registerUser(@RequestBody String data,HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("添加注册信息");
        //获取用户的用户名和密码
        Map<String, Object> userFormation = JsonToMap.toHashMap(data);
        String username = userFormation.get("username").toString();
        String password = encoderByMD5.encrypt(userFormation.get("password").toString());
        //往数据库添加用户
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
