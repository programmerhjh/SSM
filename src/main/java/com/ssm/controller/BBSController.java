package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ssm.model.Post;
import com.ssm.model.User;
import com.ssm.modelCustom.PostArticleCustom;
import com.ssm.modelCustom.ReplyCustom;
import com.ssm.modelCustom.ReplyPostCustom;
import com.ssm.modelCustom.UserExpand;
import com.ssm.service.CommentService;
import com.ssm.service.PostService;
import com.ssm.service.ReplyService;
import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.PostSpecificVo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import tool.GetPropertyUtil;
import tool.JsonToMap;
import tool.PageTool;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 论坛功能控制器
 * Created by acer on 2017/7/17.
 */
@Controller
@RequestMapping("/bbs-module")
public class BBSController {

    //log日志
    private Logger log = Logger.getLogger(BBSController.class);

    @Resource
    private PostService postService;

    @Resource
    private CommentService commentService;

    @Resource
    private ReplyService replyService;

    /**
     * 论坛搜索功能
     * @param pageData
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     * @time 2017年7月30日14:27:20
     */
    @RequestMapping("search")
    public String search(@RequestBody(required = false) String pageData,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //前台通过get请求提交查询数据，首先对请求进行utf-8编码设置
        request.setCharacterEncoding("utf-8");
        //获取前台的搜索内容
        String data = request.getParameter("search");
        //因为服务器平台为linux原因，需要对获取的搜索内容利用字节数组进行编码还原
        byte source [] = data.getBytes("iso8859-1");
        //再把字节数组重新编码为utf-8，便可在后台正常显示
        data = new String (source,"UTF-8");
        //声明一个帖子的包装类list用来存储搜索到的帖子的分页数据
        List<PostArticleCustom> list;
        //搜索内容不为空则继续搜索
        //空则返回全部帖子
        if(data!=null){
            list = postService.searchPostData(data,PageTool.device(pageData));
        }else {
            list = postService.searchPostData("",PageTool.device(pageData));
        }
        //强转为Page类以便后面使用PageInfo工具类
        Page pageTemp = (Page) list;
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(list,pageTemp.getPages());
        //删除请求中的分页数据
        request.removeAttribute("page");
        //添加新的分页数据
        request.setAttribute("page",page);

        return "bbs-module/search";
    }

    /**
     * 搜索页的分页ajax请求
     * @param pageData 请求的页码
     * @param request
     * @param response
     * @throws IOException
     * @time 2017年7月31日14:44:54
     */
    @RequestMapping("searchPage")
    public void searchPage(@RequestBody(required = false) String pageData,HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求的页码
        String data = request.getParameter("search");
        //新建一个用于存储帖子包装类的list
        List<PostArticleCustom> list;
        if(data!=null){
            list = postService.searchPostData(data,PageTool.device(pageData));
        }else {
            list = postService.searchPostData("",PageTool.device(pageData));
        }
        //强转为Page类以便后面使用PageInfo工具类

        Page pageTemp = (Page) list;
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(list,pageTemp.getPages());
        //添加新的分页数据

        request.setAttribute("page",page);
        //通过PageInfo来转化为json对象
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        //Json的时间格式的设置
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台需要的json串并格式化时间
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        // text/plain的意思是将文件设置为纯文本的形式，浏览器在获取到这种文件时并不会对其进行处理。
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();

    }

    /**
     * 添加帖子
     * @param post
     * @return
     * @throws ParseException
     * @time 2017年8月02日15:10:50
     */
    @RequestMapping("addPost")
    public String addPost(Post post) throws ParseException {

        //添加当前发帖时间
        post.setPostCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new Date())));
        //往数据库插入新帖子
        postService.insertPost(post);
        return "redirect:../bbs-module/index";
    }

    /**
     * 帖子详情
     * @param request
     * @return
     * @time 2017年7月31日15:15:15
     */
    @RequestMapping("postSpecific")
    public String postSpecific(HttpServletRequest request){
        Integer postId;
        String postIdTemp = request.getParameter("postId");
        if(postIdTemp == null){
            return "bbs-module/index";
        }
        try {
            //把String型的帖子ID转为整型ID
            postId = Integer.parseInt(postIdTemp);
        }catch (Exception e){
            log.info("postId参数异常");
            e.printStackTrace();
            return "bbs-module/index";
        }
        //用于页面显示帖子详情的Vo类
        PostSpecificVo postSpecificVo = postService.getPostSpecific(postId);
        //存储用于页面显示的帖子详情数据
        request.getSession().setAttribute("post",postSpecificVo);
        return "bbs-module/postSpecific";
    }

    /**
     * 回复评论，楼中楼
     * @param request
     * @param response
     * @param data
     * @return
     * @throws IOException
     * @time 2017年7月25日15:18:25
     */
    @RequestMapping("addReplyForComment")
    public @ResponseBody String addReplyForComment(HttpServletRequest request,HttpServletResponse response,@RequestBody String data) throws IOException {
        Map map = JsonToMap.toHashMap(data);
        //获取当前回复的评论ID
        Integer commentId = Integer.valueOf(map.get("commentId").toString());
        //获取当前回复评论的回复内容
        String replyReply = map.get("replyReply").toString();
        //获取当前帖子ID
        Integer postId = Integer.parseInt(map.get("postId").toString());
        //获取当前用户ID
        Integer userId = ((UserExpand) request.getSession().getAttribute("user")).getId();
        if(commentId == null || replyReply == null || postId == null || userId == null){
            return null;
        }else {
            //插入回复
            replyService.addReplyForComment(userId,postId,commentId,replyReply);
            //获取回复包装类的对象
            ReplyCustom reply = replyService.getReplyInstance();
            //将回复包装类的对象转换为json对象
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(reply);
            //往json对象中添加回复的时间，截取了“.0”
            jsonObject.put("replyCreatetime",reply.getReplyCreatetime().toString().substring(0,reply.getReplyCreatetime().toString().length()-2));
            return jsonObject.toJSONString();
        }
    }

    /**
     * 添加点赞数
     * @param data
     * @return
     * @time 2017年7月26日15:25:17
     */
    @RequestMapping("addClickTime")
    public @ResponseBody String addClickTime(@RequestBody String data){
        //获取文章名和作者名
        Map<String,Object> map =  JsonToMap.toHashMap(data);
        String username = map.get("username").toString();
        String postname = map.get("postname").toString();
        //标识是否点赞过
        boolean flag;
        if(map.get("flag").equals("true")){
            flag = true;
        }else{
            flag = false;
        }
        //修改点赞数
        postService.addClickTime(username,postname,flag);
        return "OK";
    }

    /**
     * 文章列表
     * @param pageData
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @time 2017年7月26日15:34:12
     */
    @RequestMapping("article-list")
    public String articleList(@RequestBody(required = false) String pageData, HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取全部帖子的前6个帖子，默认为pageSize为6
        List<PostArticleCustom> postList = postService.selectAllPostList(PageTool.device(pageData));

        //开始分页
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList);
        //更新page数据
        request.setAttribute("page",page);

        return "bbs-module/article-list";
    }

    /**
     * 文章列表分页ajax
     * @param pageData
     * @param request
     * @param response
     * @throws IOException
     * @time 2017年7月12日15:41:12
     */
    @RequestMapping("articlePage")
    public void articlePage(@RequestBody(required = false) String pageData, HttpServletRequest request,HttpServletResponse response) throws IOException{
        //获取全部帖子的前6个帖子，默认为pageSize为6

        List<PostArticleCustom> postList = postService.selectAllPostList(PageTool.device(pageData));
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList);
        //更新请求中的page
        request.setAttribute("page",page);
        //把page转化为json对象
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        //格式化json的日期
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        //生成前台所需的json
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();
    }

    /**
     * 添加评论
     * @param data
     * @return
     * @time 2017年7月26日15:43:17
     */
    @RequestMapping("addComment")
    public @ResponseBody String addComment(@RequestBody String data){
        //获取用户ID，帖子ID，评论内容
        Map map = JsonToMap.toHashMap(data);
        Integer userId = Integer.parseInt(map.get("userId").toString());
        Integer postId = Integer.parseInt(map.get("postId").toString());
        String commentText = map.get("commentText").toString();
        if(userId == null || postId == null || commentText == null){
            return null;
        }else {
            //插入评论
            commentService.addCommentForPost(userId,postId,commentText);
        }
        return "OK";
    }

    /**
     * 发帖内容中添加图片的回显
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @time 2017年7月26日15:47:57
     */
    @RequestMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //图片地址
        String resultParam = "";
        //构建返回的json串
        JSONObject result = new JSONObject();
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是文件
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            //标识是不是只为1张图片还是为多张图片
            int count = 0;
            while(iterator.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                //图片文件名存储在服务器的后缀
                String newFileSuffix = "." + file.getContentType().substring(file.getContentType().indexOf("/") + 1);
                //通过UUID和日期随机生成文件名
                String fileName = UUID.randomUUID() + new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(new Date()).toString() + file.getOriginalFilename().hashCode();// 文件名称
                //访问地址
                String localPath = GetPropertyUtil.getFileAddress("Pic")+ fileName + newFileSuffix;
                //生成文件
                File newFile = new File(localPath);
                //复制文件
                file.transferTo(newFile);
                if(count == 0) {
                    //图片地址
                    resultParam += "\"" + (GetPropertyUtil.getWebsiteAddress("localSaveAddress") + GetPropertyUtil.getFileAddress("PicDownload") + fileName + newFileSuffix).replaceAll("\\\\", "/") + "\"";
                }else {
                    //图片地址
                    resultParam += "," + "\"" + (GetPropertyUtil.getWebsiteAddress("localSaveAddress") + GetPropertyUtil.getFileAddress("PicDownload") + fileName + newFileSuffix).replaceAll("\\\\", "/") + "\"";
                }
                count++;
            }
        }
        //添加图片地址到json
        result.put("data","[" + resultParam + "]");
        result.put("errno",0);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result.toJSONString());  //返回url地址
        out.flush();
        out.close();
    }

    /**
     * 论坛主页
     * @param session
     * @return
     * @time 2017年7月14日15:55:45
     */
    @RequestMapping("index")
    public String articleIndex(HttpSession session){

        //获取热门和最新文章
        List<BBSIndexPostsQueryVo> hotPost = postService.queryHotPost();
        List<BBSIndexPostsQueryVo> lastPost = postService.queryLastPost();

        //保存到session中
        session.setAttribute("hotPost",hotPost);
        session.setAttribute("lastPost",lastPost);

        return "bbs-module/index";
    }

    /**
     * 转到发帖界面
     * @return
     * @time 2017年7月16日15:58:27
     */
    @RequestMapping("newPost")
    public String newPost(){
        return "bbs-module/newPost";
    }

    /**
     * 用户发表的文章列表
     * @param data
     * @param response
     * @param request
     * @return
     * @time 2017年7月16日15:58:16
     */
    @RequestMapping("myPost")
    public String myPost(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ){
        //获取用户的帖子列表，默认为一页6条
        List<PostArticleCustom> postList = postService.selectUserPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)postList;
        //分页
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList,pageTemp.getPages());
        request.setAttribute("page",page);
        return "bbs-module/myPost";
    }

    /**
     * 用户发表过的帖子列表分页ajax
     * @param data
     * @param response
     * @param request
     * @throws IOException
     * @time 2017年7月25日16:11:54
     */
    @RequestMapping("myPostPage")
    public void myPostPage(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ) throws IOException {
        //获取用户的帖子列表，默认为一页6条
        List<PostArticleCustom> postList = postService.selectUserPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)postList;
        //分页
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList,pageTemp.getPages());
        request.setAttribute("page",page);
        //构造json对象
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        //设置json日期格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        //生成前台所需的json
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();
    }

    /**
     * 用户的回复列表
     * @param data
     * @param response
     * @param request
     * @return
     * @time 2017年7月27日16:13:29
     */
    @RequestMapping("myReply")
    public String myReply(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ){
        //获取用户回复列表，默认为一页6条
        List<ReplyPostCustom> replyList = replyService.getReplyPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)replyList;
        //分页
        PageInfo<ReplyPostCustom> page = new PageInfo<ReplyPostCustom>(replyList,pageTemp.getPages());
        request.setAttribute("page",page);
        return "bbs-module/myReply";
    }

    /**
     * 用户的回复列表的分页ajax
     * @param data
     * @param response
     * @param request
     * @throws IOException
     * @time 2017年7月27日16:14:37
     */
    @RequestMapping("myReplyPage")
    public void myReplyPage(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ) throws IOException{
        //获取用户回复列表，默认为一页6条
        List<ReplyPostCustom> replyList = replyService.getReplyPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)replyList;
        //分页
        PageInfo<ReplyPostCustom> page = new PageInfo<ReplyPostCustom>(replyList,pageTemp.getPages());
        request.setAttribute("page",page);
        //构造json对象

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        //设置json日期格式

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //生成前台所需的json字符串
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();
    }

}
