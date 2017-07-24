package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ssm.model.Post;
import com.ssm.service.PostService;
import com.ssm.service.UserService;
import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.PostSpecificVo;
import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import tool.JsonToMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static tool.JsonToMap.toHashMap;

/**
 * Created by acer on 2017/7/17.
 */
@Controller
@RequestMapping("/bbs-module")
public class BBSController {

    private Logger log = Logger.getLogger(BBSController.class);

    @Resource
    private PostService postService;

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
        System.out.println(postSpecificVo);
        System.out.println(postSpecificVo.getUser().toString());
        System.out.println(postSpecificVo.getCommentAndReplyVos().toArray());
        request.getSession().setAttribute("post",postSpecificVo);
        return "bbs-module/postSpecific";
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
                String localPath = "F:/upload/"+ fileName + newFileSuffix;
                File newFile = new File(localPath);
                file.transferTo(newFile);
                if(count == 0)
                    resultParam += "\"" + ("http://localhost:8080/img/" + fileName + newFileSuffix).replaceAll("\\\\","/")  + "\"";
                else
                    resultParam += "," + "\"" + ("http://localhost:8080/img/" + fileName + newFileSuffix).replaceAll("\\\\","/") + "\"";
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

}
