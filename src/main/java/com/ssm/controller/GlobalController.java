package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by acer on 2017/8/1.
 */
@Controller
public class GlobalController {

    @RequestMapping("404")
    public String noFound(){
        return "404";
    }

    @RequestMapping("noFound")
    public void tempNoFound(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://39.108.68.200:8080/404");
    }

    @RequestMapping("systemError")
    public void tempSystemError(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://39.108.68.200:8080/500");
    }

    @RequestMapping("500")
    public String systemError(){
        return "500";
    }

    @RequestMapping("index")
    public String index(){
        return "redirect:bbs-module/index";
    }
}
