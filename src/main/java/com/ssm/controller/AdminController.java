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
 * 管理员控制层
 * Created by acer on 2017/7/28.
 */

@Controller
@RequestMapping("/admin-module")
public class AdminController {

    //无关联数据库的文件上传下载的分页工具对象
    private UnDataBasePageTool unDataBasePageTool1 = new UnDataBasePageTool();
    private UnDataBasePageTool unDataBasePageTool2 = new UnDataBasePageTool();
    private UnDataBasePageTool unDataBasePageTool3 = new UnDataBasePageTool();

    //文件上传下载的初始化页数标识，为0即为利用无关联数据库的上传下载分页工具初始化第一页相关数据
    //不为0即为在原有已在上传下载分页工具初始化的基础上获取页面后面对应想获取的页数的数据
    private int count = 0;

    //文件下载页面的对应显示的list
    private List<String> listTime;
    private List<String> listName;
    private List<String> listSize;

    //log日志工具
    private Logger log = Logger.getLogger(BBSController.class);

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Resource
    private PostService postService;

    @Resource
    private CommentService commentService;

    /**
     * 管理员登陆
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @time 2017年8月1日10:40:39
     */
    @RequestMapping("adminLogin")
    public String adminLogin(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email == null || password == null){
            return "admin-module/adminLogin";
        }
        //根据管理员邮箱账号和MD5加密的密码进行登陆验证
        Admin admin = adminService.checkAdmin(email,new EncoderByMD5().encrypt(password));
        if(admin != null){
            request.getSession().setAttribute("admin",admin);
            log.info("后台管理首页");
            return "redirect:../admin-module/index";
        }
        return "admin-module/adminLogin";
    }


    /**
     * 管理员登出方法
     * @param session
     * @return
     * @time 2017年8月1日10:46:39
     */
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("admin");
        log.info("管理员注销");
        return "admin-module/adminLogin";
    }

    /**
     * 后台管理页面首页
     * @param session
     * @return
     * @time 2017年8月2日10:45:31
     */
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


    /**
     * 用户管理页面所需数据
     * @param response
     * @throws IOException
     * @time 2017年8月2日11:46:39
     */
    @RequestMapping("userManage")
    public void userManage(HttpServletResponse response) throws IOException {
        log.info("用户管理");
        //开始分页，当前页码为1，大小为10行数据并计数
        PageHelper.startPage(1,10,true);
        //获取分页用户数据
        List<User> users = userService.selectAllUser();
        //Page类强转用于后面的PageInfo类的参数使用
        Page<User> pageTemp = (Page<User>) users;
        PageInfo<User> page = new PageInfo<User>(pageTemp,pageTemp.getPages());
        //JSON时间格式设置
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台所需的json串
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 根据用户管理页面的前台所点的页面进行对目标页面的查找
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月2日13:04:39
     */
    @RequestMapping("userManagePage")
    public void userManagePage(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取目标页面
        Integer targetPage = Integer.valueOf(JsonToMap.toHashMap(data).get("targetPage").toString());
        //开始分页，当前页码为1，大小为10行数据并计数
        PageHelper.startPage(targetPage,10,true);
        //获取分页用户数据
        List<User> users = userService.selectAllUser();
        //Page类强转用于后面的PageInfo类的参数使用
        Page<User> pageTemp = (Page<User>) users;
        PageInfo<User> page = new PageInfo<User>(pageTemp,pageTemp.getPages());
        //JSON时间格式设置
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台所需的json串
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 对应帖子管理的目标页面的数据生成
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日11:00:43
     */
    @RequestMapping("postManagePage")
    public void postManagePage(@RequestBody String data, HttpServletResponse response) throws IOException {
        //获取目标页面
        Integer targetPage = Integer.valueOf(JsonToMap.toHashMap(data).get("targetPage").toString());
        //开始分页，当前页码为1，大小为10行数据并计数
        PageHelper.startPage(targetPage,10,true);
        //获取分页用户和对应用户帖子数据
        List<PostCustom> postCustoms = postService.selectPostAndUser();
        //Page类强转用于后面的PageInfo类的参数使用
        Page<PostCustom> pageTemp = (Page<PostCustom>) postCustoms;
        PageInfo<PostCustom> page = new PageInfo<PostCustom>(pageTemp,pageTemp.getPages());
        //JSON时间格式设置
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台所需的json串
        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 用于评论管理的指定页面的数据获取
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日14:02:58
     */
    @RequestMapping("commentManagePage")
    public void commentManagePage(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取目标页面

        Integer targetPage = Integer.valueOf(JsonToMap.toHashMap(data).get("targetPage").toString());
        //开始分页，当前页码为1，大小为10行数据并计数

        PageHelper.startPage(targetPage,10,true);
        //获取分页评论和对应用户和用户帖子数据

        List<CommentCustom> commentCustoms = commentService.selectCommentAndPostAndUser();
        //Page类强转用于后面的PageInfo类的参数使用

        Page<CommentCustom> pageTemp = (Page<CommentCustom>) commentCustoms;
        PageInfo<CommentCustom> page = new PageInfo<CommentCustom>(pageTemp,pageTemp.getPages());
        //JSON时间格式设置

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台所需的json串

        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 帖子管理页面初始化数据
     * @param response
     * @throws IOException
     * @time 2017年8月02日13:04:59
     */
    @RequestMapping("postManage")
    public void postManage(HttpServletResponse response) throws IOException {
        log.info("帖子管理");
        //初始化帖子管理页面

        PageHelper.startPage(1,10,true);
        //获取分页用户和对应用户帖子数据

        List<PostCustom> postCustoms = postService.selectPostAndUser();
        //Page类强转用于后面的PageInfo类的参数使用

        Page<PostCustom> pageTemp = (Page<PostCustom>) postCustoms;
        PageInfo<PostCustom> page = new PageInfo<PostCustom>(pageTemp,pageTemp.getPages());
        //JSON时间格式设置

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台所需的json串

        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 评论管理页面初始化数据
     * @param response
     * @throws IOException
     * @time 2017年8月02日11:08:35
     */
    @RequestMapping("commentManage")
    public void commentManage(HttpServletResponse response) throws IOException {
        log.info("评论管理");
        //初始化评论管理页面
        //开始分页，当前页码为1，大小为10行数据并计数

        PageHelper.startPage(1,10,true);
        //获取分页评论和对应用户和用户帖子数据

        List<CommentCustom> commentCustoms = commentService.selectCommentAndPostAndUser();
        //Page类强转用于后面的PageInfo类的参数使用

        Page<CommentCustom> pageTemp = (Page<CommentCustom>) commentCustoms;
        PageInfo<CommentCustom> page = new PageInfo<CommentCustom>(pageTemp,pageTemp.getPages());
        //JSON时间格式设置

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台所需的json串

        String result =  JSON.toJSONString(page, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 管理文件上传下载的初始化数据
     * @param pageNum
     * @param response
     * @throws IOException
     * @time 2017年8月02日11:12:25
     */
    @RequestMapping("upDownFilesOrTable")
    public void upDownFilesOrTable(@RequestBody(required = false) String pageNum,HttpServletResponse response) throws IOException {
        log.info("管理文件上传下载或数据表的导入导出");
        //新建一个hashMap存储没有分页插件的文件上传下载页面的数据展示
        Map map = new LinkedHashMap();
        //初始化目标页数
        int pointPage = 1;
        
        if(pageNum != null){
            //获取json串中的目标页数
            pointPage = Integer.parseInt(JsonToMap.toHashMap(pageNum).get("pointPage").toString());
        }

        //获取本地存储数据文件信息
        FileTool.getFiles();

        //如果为0即初始化文件信息数据的list
        // 不为0就通过json串中的目标页面来获取分别list信息的数据
        if(count == 0){
            List listFileTime = FileTool.fileTimeList;
            listTime = unDataBasePageTool1.getInitPage(listFileTime,1,8);
            List listFileName = FileTool.fileList;
            listName = unDataBasePageTool2.getInitPage(listFileName,1,8);
            List listFileSize = FileTool.fileSizeList;
            listSize = unDataBasePageTool3.getInitPage(listFileSize,1,8);
            count++;
            //往map里添加当前页面
            map.put("currentPage",1);
        }else {
            listTime = unDataBasePageTool1.getAppointPage(pointPage);

            listName = unDataBasePageTool2.getAppointPage(pointPage);

            listSize = unDataBasePageTool3.getAppointPage(pointPage);
            //往map里添加当前页面
            map.put("currentPage",pointPage);
        }

        //新建一个list数组来保存其余3个对应不同信息的list
        List[] lists = new List[]{listName, listTime,listSize};
        //往map加入这个list数组
        map.put("data",lists);
        //往map加入数据分页完的最大页数
        map.put("pages",unDataBasePageTool1.getMaxPage());
        //通过map来创建json对象
        JSONObject jsonObject = new JSONObject(map);
        //生成json并规定时间格式
        String result = jsonObject.toJSONString(map,SerializerFeature.WriteDateUseDateFormat);

        response.setContentType("text/plain; charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param data
     * @throws Exception
     * @time 2017年8月02日11:24:10
     */
    @RequestMapping("downloadFile")
    public void downloadFile(HttpServletRequest request ,HttpServletResponse response,@RequestBody String data) throws Exception {
        //生成其json串对应的map
        Map map = JsonToMap.toHashMap(data);
        //获取文件名
        String fileName = map.get("fileName").toString();
        //获取配置文件信息中的文件下载网址
        String ctxPath = GetPropertyUtil.getWebsiteAddress("localSaveAddress") + GetPropertyUtil.getFileAddress("FilesDownload") + fileName;
        Writer writer = response.getWriter();
        writer.write(ctxPath);
        writer.flush();
        writer.close();
    }

    /**
     * 删除文件
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日11:29:10
     */
    @RequestMapping("removeFile")
    public void removeFile(@RequestBody String data,HttpServletResponse response) throws IOException {
        log.info("文件删除");
        //生成其json串对应的map
        Map map = JsonToMap.toHashMap(data);
        //获取文件名
        String fileName = map.get("fileName").toString();
        //文件是否删除成功
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

    /**
     * 上传文件
     * @param request
     * @param response
     * @throws IOException
     *
     * @time 2017年8月02日11:30:01
     */
    @RequestMapping("uploadFile")
    public void uploadFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //Spring自带的文件上传工具类
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是文件
        if(commonsMultipartResolver.isMultipart(request)){
            //强转为MultipartHttpServletRequest的请求
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取请求中所有文件名组成的迭代器
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){//如果有下一个文件
                //获取其二进制的文件
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                //把文件打包成zip
                TurnZip.compressFile(file);
            }
        }
        //数据发生改变，页面初始化数据标识改为0
        count = 0;
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /**
     * 下载Excel文件
     * @param request
     * @param response
     * @throws Exception
     * @time 2017年8月02日11:33:17
     */
    @RequestMapping("downloadExcelFile")
    public void downloadExcelFile(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //获取其Excel文件通过UUID生成的文件名（获取所有的帖子并生成对应的Excel文件）
        String key = ExcelUtil.listToExcel(postService.getAllPost());
        //获取配置文件中的对应的文件下载网址
        String ctxPath = GetPropertyUtil.getWebsiteAddress("localSaveAddress")+GetPropertyUtil.getFileAddress("ExcelDownload") + key;
        Writer writer = response.getWriter();
        writer.write(ctxPath);
        writer.flush();
        writer.close();
    }

    /**
     * 将帖子的Excel文件数据导入数据库
     * @param request
     * @param response
     * @throws IOException
     * @time 2017年8月02日11:35:42
     */
    @RequestMapping("uploadExcel")
    public void uploadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //声明文件名
        String fileName = null;
        //Spring自带的文件上传工具类

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是文件
        if(commonsMultipartResolver.isMultipart(request)){
            //强转为MultipartHttpServletRequest的请求

            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取请求中所有文件名组成的迭代器

            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){//如果有下一个文件
                //获取其二进制的文件

                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                //根据原上传的文件名在配置文件中的文件存储目录下创建新的文件
                File newFile = new File(GetPropertyUtil.getFileAddress("Files")+file.getOriginalFilename());
                //如果名字不重复
                if (!newFile.exists()){
                    //创建文件
                    newFile.createNewFile();
                }
                //把文件复制进文件存储目录
                file.transferTo(newFile);
                //文件名为原上传文件的文件名
                fileName = file.getOriginalFilename();
            }
        }
        //获取数据库的帖子数据
        List<Post> list = ExcelUtil.getAllByExcel(GetPropertyUtil.getFileAddress("Files")+fileName);
        //删除文件名对应的文件
        FileDownUpUtil.deleteFile(fileName);
        //往数据库插入Excel表中的帖子数据
        postService.insertPostList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /**
     * 删除用户
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日14:15:52
     */
    @RequestMapping("deleteUser")
    public void deleteUser(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取前台json串的用户ID
        Integer id = Integer.parseInt(JsonToMap.toHashMap(data).get("id").toString());
        //删除用户
        userService.deleteUser(id);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /**
     * 删除帖子
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日14:17:04
     */
    @RequestMapping("deletePost")
    public void deletePost(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取前台json的帖子ID
        Integer id = Integer.parseInt(JsonToMap.toHashMap(data).get("id").toString());
        //删除帖子
        postService.deletePost(id);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /**
     * 删除评论
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日14:18:13
     */
    @RequestMapping("deleteComment")
    public void deleteComment(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取前台json串中的评论ID
        Integer id = Integer.parseInt(JsonToMap.toHashMap(data).get("id").toString());
        //删除评论
        commentService.deleteComment(id);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /**
     * 批量删除用户
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日14:19:38
     */
    @RequestMapping("deleteAllUser")
    public void deleteAllUser(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取前台json的用户ID数据list
        String temp = JsonToMap.toHashMap(data).get("data").toString();
        //通过逗号分隔ID
        String[] allId = temp.split(",");
        //构造一个整型List装一组用户ID
        List<Integer> list = null;
        for(String s:allId){
            list.add(Integer.parseInt(s));//String ID 强转 Integer
        }
        //批量删除指定用户
        userService.deleteUserList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /**
     * 批量删除帖子
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日14:22:53
     */
    @RequestMapping("deleteAllPost")
    public void deleteAllPost(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取前台json的帖子ID数据list

        String temp = JsonToMap.toHashMap(data).get("data").toString();
        //通过逗号分隔ID

        String[] allId = temp.split(",");
        //构造一个整型List装一组帖子ID

        List<Integer> list = null;
        for(String s:allId){
            list.add(Integer.parseInt(s));//String ID 强转 Integer
        }
        //批量删除指定帖子
        postService.deletePostList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /**
     * 批量删除评论
     * @param data
     * @param response
     * @throws IOException
     * @time 2017年8月02日14:24:21
     */
    @RequestMapping("deleteAllComment")
    public void deleteAllComment(@RequestBody String data,HttpServletResponse response) throws IOException {
        //获取前台json的评论ID数据list

        String temp = JsonToMap.toHashMap(data).get("data").toString();
        //通过逗号分隔ID

        String[] allId = temp.split(",");
        //构造一个整型List装一组评论ID

        List<Integer> list = null;
        for(String s:allId){
            list.add(Integer.parseInt(s));//String ID 强转 Integer
        }
        //批量删除指定评论
        commentService.deleteCommentList(list);
        Writer out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }
}
