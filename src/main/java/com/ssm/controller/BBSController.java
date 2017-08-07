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
 * Created by acer on 2017/7/17.
 */
@Controller
@RequestMapping("/bbs-module")
public class BBSController {

    private Logger log = Logger.getLogger(BBSController.class);

    @Resource
    private PostService postService;

    @Resource
    private CommentService commentService;

    @Resource
    private ReplyService replyService;

    @RequestMapping("search")
    public String search(@RequestBody(required = false) String pageData,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String data = request.getParameter("search");
        byte source [] = data.getBytes("iso8859-1");
        data = new String (source,"UTF-8");
        System.out.println(data);
        List<PostArticleCustom> list;
        if(data!=null){
            list = postService.searchPostData(data,PageTool.device(pageData));
        }else {
            list = postService.searchPostData("",PageTool.device(pageData));
        }
        Page pageTemp = (Page) list;
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(list,pageTemp.getPages());
        request.removeAttribute("page");
        request.setAttribute("page",page);

        return "bbs-module/search";
    }

    @RequestMapping("searchPage")
    public void searchPage(@RequestBody(required = false) String pageData,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = request.getParameter("search");
        List<PostArticleCustom> list;
        if(data!=null){
            list = postService.searchPostData(data,PageTool.device(pageData));
        }else {
            list = postService.searchPostData("",PageTool.device(pageData));
        }
        Page pageTemp = (Page) list;
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(list,pageTemp.getPages());
        request.setAttribute("page",page);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();

    }

    @RequestMapping("addPost")
    public String addPost(Post post) throws ParseException {
        post.setPostCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        postService.insertPost(post);
        return "redirect:../bbs-module/index";
    }

    @RequestMapping("postSpecific")
    public String postSpecific(HttpServletRequest request){
        Integer postId;
        String postIdTemp = request.getParameter("postId");
        if(postIdTemp == null){
            return "bbs-module/index";
        }
        try {
            postId = Integer.parseInt(postIdTemp);
        }catch (Exception e){
            log.info("postId参数异常");
            e.printStackTrace();
            return "bbs-module/index";
        }
        PostSpecificVo postSpecificVo = postService.getPostSpecific(postId);
        request.getSession().setAttribute("post",postSpecificVo);
        return "bbs-module/postSpecific";
    }

    @RequestMapping("addReplyForComment")
    public @ResponseBody String addReplyForComment(HttpServletRequest request,HttpServletResponse response,@RequestBody String data) throws IOException {
        Map map = JsonToMap.toHashMap(data);
        Integer commentId = Integer.valueOf(map.get("commentId").toString());
        String replyReply = map.get("replyReply").toString();
        Integer postId = Integer.parseInt(map.get("postId").toString());
        Integer userId = ((UserExpand) request.getSession().getAttribute("user")).getId();
        if(commentId == null || replyReply == null || postId == null || userId == null){
            return null;
        }else {
            replyService.addReplyForComment(userId,postId,commentId,replyReply);
            ReplyCustom reply = replyService.getReplyInstance();
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(reply);
            jsonObject.put("replyCreatetime",reply.getReplyCreatetime().toString().substring(0,reply.getReplyCreatetime().toString().length()-2));
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("addClickTime")
    public @ResponseBody String addClickTime(@RequestBody String data){
        System.out.println(data);
        Map<String,Object> map =  JsonToMap.toHashMap(data);
        String username = map.get("username").toString();
        String postname = map.get("postname").toString();

        postService.addClickTime(username,postname);
        return "OK";
    }

    @RequestMapping("article-list")
    public String articleList(@RequestBody(required = false) String pageData, HttpServletRequest request,HttpServletResponse response) throws IOException {

        List<PostArticleCustom> postList = postService.selectAllPostList(PageTool.device(pageData));

        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList);
        request.setAttribute("page",page);

        return "bbs-module/article-list";
    }

    @RequestMapping("articlePage")
    public void articlePage(@RequestBody(required = false) String pageData, HttpServletRequest request,HttpServletResponse response) throws IOException{
        List<PostArticleCustom> postList = postService.selectAllPostList(PageTool.device(pageData));
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList);
        request.setAttribute("page",page);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();
    }

    @RequestMapping("addComment")
    public @ResponseBody String addComment(@RequestBody String data){
        Map map = JsonToMap.toHashMap(data);
        Integer userId = Integer.parseInt(map.get("userId").toString());
        Integer postId = Integer.parseInt(map.get("postId").toString());
        String commentText = map.get("commentText").toString();
        if(userId == null || postId == null || commentText == null){
            return null;
        }else {
            commentService.addCommentForPost(userId,postId,commentText);
        }
        return "OK";
    }

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
            int count = 0;
            while(iterator.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                String newFileSuffix = "." + file.getContentType().substring(file.getContentType().indexOf("/") + 1);
                String fileName = UUID.randomUUID() + new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(new Date()).toString() + file.getOriginalFilename().hashCode();// 文件名称
                String localPath = GetPropertyUtil.getFileAddress("Pic")+ fileName + newFileSuffix;
                File newFile = new File(localPath);
                file.transferTo(newFile);
                if(count == 0)
                    resultParam += "\"" + ("http://39.108.68.200:8088/images/" + fileName + newFileSuffix).replaceAll("\\\\","/")  + "\"";
                else
                    resultParam += "," + "\"" + ("http://39.108.68.200:8088/images/" + fileName + newFileSuffix).replaceAll("\\\\","/") + "\"";
                count++;
            }
        }
        result.put("data","[" + resultParam + "]");
        result.put("errno",0);
        response.setContentType("text/html;charset=utf-8");
        System.out.println(result.toJSONString());
        PrintWriter out = response.getWriter();
        out.print(result.toJSONString());  //返回url地址
        out.flush();
        out.close();
    }

    @RequestMapping("index")
    public String articleIndex(HttpSession session){

        List<BBSIndexPostsQueryVo> hotPost = postService.queryHotPost();
        List<BBSIndexPostsQueryVo> lastPost = postService.queryLastPost();

        session.setAttribute("hotPost",hotPost);
        session.setAttribute("lastPost",lastPost);

        return "bbs-module/index";
    }

    @RequestMapping("newPost")
    public String newPost(){
        return "bbs-module/newPost";
    }

    @RequestMapping("myPost")
    public String myPost(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ){
        List<PostArticleCustom> postList = postService.selectUserPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)postList;
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList,pageTemp.getPages());
        request.setAttribute("page",page);
        return "bbs-module/myPost";
    }

    @RequestMapping("myPostPage")
    public void myPostPage(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ) throws IOException {
        List<PostArticleCustom> postList = postService.selectUserPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)postList;
        PageInfo<PostArticleCustom> page = new PageInfo<PostArticleCustom>(postList,pageTemp.getPages());
        request.setAttribute("page",page);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();
    }

    @RequestMapping("myReply")
    public String myReply(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ){
        List<ReplyPostCustom> replyList = replyService.getReplyPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)replyList;
        PageInfo<ReplyPostCustom> page = new PageInfo<ReplyPostCustom>(replyList,pageTemp.getPages());
        request.setAttribute("page",page);
        return "bbs-module/myReply";
    }

    @RequestMapping("myReplyPage")
    public void myReplyPage(@RequestBody(required = false) String data,HttpServletResponse response,HttpServletRequest request ) throws IOException{
        List<ReplyPostCustom> replyList = replyService.getReplyPostList(((User)(request.getSession().getAttribute("user"))).getId(),PageTool.device(data));
        Page pageTemp = (Page)replyList;
        PageInfo<ReplyPostCustom> page = new PageInfo<ReplyPostCustom>(replyList,pageTemp.getPages());
        request.setAttribute("page",page);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(page);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String str = JSON.toJSONString(jsonObject, SerializerFeature.WriteDateUseDateFormat);
        response.setContentType("text/plain; charset=utf-8");
        Writer out = response.getWriter();
        out.write(str);
        out.flush();
        out.close();
    }

}
