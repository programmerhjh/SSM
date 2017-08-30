package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.model.Admin;
import com.ssm.model.Post;
import com.ssm.model.User;
import com.ssm.modelCustom.CommentCustom;
import com.ssm.modelCustom.PostCustom;
import com.ssm.service.AdminService;
import com.ssm.service.CommentService;
import com.ssm.service.PostService;
import com.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import tool.*;

import javax.annotation.Resource;
import javax.mail.Transport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acer on 2017/7/28.
 */

@Controller
@RequestMapping("/admin-module")
public class AdminController {

    private UnDataBasePageTool unDataBasePageTool1 = new UnDataBasePageTool();
    private UnDataBasePageTool unDataBasePageTool2 = new UnDataBasePageTool();
    private UnDataBasePageTool unDataBasePageTool3 = new UnDataBasePageTool();

    private int count = 0;

    private List<String> listTime;
    private List<String> listName;
    private List<String> listSize;

    private Logger log = Logger.getLogger(BBSController.class);

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Resource
    private PostService postService;

    @Resource
    private CommentService commentService;

    @RequestMapping("adminLogin")
    public String adminLogin(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email == null || password == null){
            return "admin-module/adminLogin";
        }
        Admin admin = adminService.checkAdmin(email,new EncoderByMD5().encrypt(password));
        if(admin != null){
            request.getSession().setAttribute("admin",admin);
            log.info("后台管理首页");
            return "redirect:../admin-module/index";
        }
        return "admin-module/adminLogin";
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("admin");
        log.info("管理员注销");
        return "admin-module/adminLogin";
    }

    @RequestMapping("index")
    public String index(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null)
        {
            return "admin-module/adminLogin";
        }
        log.info("后台管理首页");
        return "admin-module/index";
    }

    @RequestMapping("userManage")
    public void userManage(HttpServletResponse response) throws IOException {
        log.info("用户管理");
        PageHelper.startPage(1,10,true);
        List<User> users = userService.selectAllUser();
        Page<User> pageTemp = (Page<User>) users;
        PageInfo<User> page = new PageInfo<User>(pageTemp,pageTemp.getPages());
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @RequestMapping("userManagePage")
    public void userManagePage(@RequestBody String data,HttpServletResponse response) throws IOException {
        Integer targetPage = Integer.valueOf(JsonToMap.toHashMap(data).get("targetPage").toString());
        PageHelper.startPage(targetPage,10,true);
        List<User> users = userService.selectAllUser();
        Page<User> pageTemp = (Page<User>) users;
        PageInfo<User> page = new PageInfo<User>(pageTemp,pageTemp.getPages());
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @RequestMapping("postManagePage")
    public void postManagePage(@RequestBody String data, HttpServletResponse response) throws IOException {
        Integer targetPage = Integer.valueOf(JsonToMap.toHashMap(data).get("targetPage").toString());
        PageHelper.startPage(targetPage,10,true);
        List<PostCustom> postCustoms = postService.selectPostAndUser();
        Page<PostCustom> pageTemp = (Page<PostCustom>) postCustoms;
        PageInfo<PostCustom> page = new PageInfo<PostCustom>(pageTemp,pageTemp.getPages());
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @RequestMapping("commentManagePage")
    public void commentManagePage(@RequestBody String data,HttpServletResponse response) throws IOException {
        Integer targetPage = Integer.valueOf(JsonToMap.toHashMap(data).get("targetPage").toString());
        PageHelper.startPage(targetPage,10,true);
        List<CommentCustom> commentCustoms = commentService.selectCommentAndPostAndUser();
        Page<CommentCustom> pageTemp = (Page<CommentCustom>) commentCustoms;
        PageInfo<CommentCustom> page = new PageInfo<CommentCustom>(pageTemp,pageTemp.getPages());
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @RequestMapping("postManage")
    public void postManage(HttpServletResponse response) throws IOException {
        log.info("帖子管理");
        PageHelper.startPage(1,10,true);
        List<PostCustom> postCustoms = postService.selectPostAndUser();
        Page<PostCustom> pageTemp = (Page<PostCustom>) postCustoms;
        PageInfo<PostCustom> page = new PageInfo<PostCustom>(pageTemp,pageTemp.getPages());
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @RequestMapping("commentManage")
    public void commentManage(HttpServletResponse response) throws IOException {
        log.info("评论管理");
        PageHelper.startPage(1,10,true);
        List<CommentCustom> commentCustoms = commentService.selectCommentAndPostAndUser();
        Page<CommentCustom> pageTemp = (Page<CommentCustom>) commentCustoms;
        PageInfo<CommentCustom> page = new PageInfo<CommentCustom>(pageTemp,pageTemp.getPages());
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @RequestMapping("upDownFilesOrTable")
    public void upDownFilesOrTable(@RequestBody(required = false) String pageNum,HttpServletResponse response) throws IOException {
        log.info("管理文件上传下载或数据表的导入导出");
        Map map = new LinkedHashMap();
        int pointPage = 1;
        
        if(pageNum != null){
            pointPage = Integer.parseInt(JsonToMap.toHashMap(pageNum).get("pointPage").toString());
        }

        System.out.println(pageNum);
        FileTool.getFiles();

        if(count == 0){
            List listFileTime = FileTool.fileTimeList;
            listTime = unDataBasePageTool1.getInitPage(listFileTime,1,8);
            List listFileName = FileTool.fileList;
            listName = unDataBasePageTool2.getInitPage(listFileName,1,8);
            List listFileSize = FileTool.fileSizeList;
            listSize = unDataBasePageTool3.getInitPage(listFileSize,1,8);
            count++;
            map.put("currentPage",1);
        }else {
            listTime = unDataBasePageTool1.getAppointPage(pointPage);

            listName = unDataBasePageTool2.getAppointPage(pointPage);

            listSize = unDataBasePageTool3.getAppointPage(pointPage);
            map.put("currentPage",pointPage);
        }

        List[] lists = new List[]{listName, listTime,listSize};
        for (String  s: listName){
            System.out.println(s.toString());
        }
        map.put("data",lists);
        map.put("pages",unDataBasePageTool1.getMaxPage());
        JSONObject jsonObject = new JSONObject(map);
        String result = jsonObject.toJSONString(map,SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);

        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @RequestMapping("downloadFile")
    public void downloadFile(HttpServletRequest request ,HttpServletResponse response,@RequestBody String data) throws Exception {
        Map map = JsonToMap.toHashMap(data);
        String fileName = map.get("fileName").toString();
        String ctxPath = GetPropertyUtil.getWebsiteAddress("localSaveAddress") + GetPropertyUtil.getFileAddress("FilesDownload") + fileName;
        Writer writer = response.getWriter();
        writer.write(ctxPath);
        writer.flush();
        writer.close();
    }

    @RequestMapping("removeFile")
    public void removeFile(@RequestBody String data,HttpServletResponse response) throws IOException {
        log.info("文件删除");
        Map map = JsonToMap.toHashMap(data);
        String fileName = map.get("fileName").toString();
        boolean flag = FileDownUpUtil.deleteFile(fileName);
        if(flag){
            Writer out = response.getWriter();
            out.write("success");
            out.flush();
            out.close();
        }else {
            Writer out = response.getWriter();
            out.write("fail");
            out.flush();
            out.close();
        }
    }

    @RequestMapping("uploadFile")
    public void uploadFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是文件
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                TurnZip.compressFile(file);
            }
        }
        count = 0;
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    @RequestMapping("downloadExcelFile")
    public void downloadExcelFile(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String key = ExcelUtil.listToExcel(postService.getAllPost());
        String ctxPath = GetPropertyUtil.getWebsiteAddress("localSaveAddress")+GetPropertyUtil.getFileAddress("ExcelDownload") + key;
        Writer writer = response.getWriter();
        writer.write(ctxPath);
        writer.flush();
        writer.close();
    }

    @RequestMapping("uploadExcel")
    public void uploadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String fileName = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是文件
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());

                File newFile = new File(GetPropertyUtil.getFileAddress("Files")+file.getOriginalFilename());
                if (!newFile.exists()){
                    newFile.createNewFile();
                }
                file.transferTo(newFile);
                fileName = file.getOriginalFilename();
            }
        }
        List<Post> list = ExcelUtil.getAllByExcel(GetPropertyUtil.getFileAddress("Files")+fileName);
        FileDownUpUtil.deleteFile(fileName);
        postService.insertPostList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    @RequestMapping("deleteUser")
    public void deleteUser(@RequestBody String data,HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(JsonToMap.toHashMap(data).get("id").toString());
        userService.deleteUser(id);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    @RequestMapping("deletePost")
    public void deletePost(@RequestBody String data,HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(JsonToMap.toHashMap(data).get("id").toString());
        postService.deletePost(id);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    @RequestMapping("deleteComment")
    public void deleteComment(@RequestBody String data,HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(JsonToMap.toHashMap(data).get("id").toString());
        commentService.deleteComment(id);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    @RequestMapping("deleteAllUser")
    public void deleteAllUser(@RequestBody String data,HttpServletResponse response) throws IOException {
        String temp = JsonToMap.toHashMap(data).get("data").toString();
        String[] allId = temp.split(",");
        List<Integer> list = null;
        for(String s:allId){
            list.add(Integer.parseInt(s));
        }
        userService.deleteUserList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    @RequestMapping("deleteAllPost")
    public void deleteAllPost(@RequestBody String data,HttpServletResponse response) throws IOException {
        String temp = JsonToMap.toHashMap(data).get("data").toString();
        String[] allId = temp.split(",");
        List<Integer> list = null;
        for(String s:allId){
            list.add(Integer.parseInt(s));
        }
        postService.deletePostList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    @RequestMapping("deleteAllComment")
    public void deleteAllComment(@RequestBody String data,HttpServletResponse response) throws IOException {
        String temp = JsonToMap.toHashMap(data).get("data").toString();
        String[] allId = temp.split(",");
        List<Integer> list = null;
        for(String s:allId){
            list.add(Integer.parseInt(s));
        }
        commentService.deleteCommentList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }
}
