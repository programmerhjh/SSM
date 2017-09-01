package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.GetPropertyUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局控制器
 * Created by acer on 2017/8/1.
 */
@Controller
public class GlobalController {

    /**
     * 404无法找到网页
     * @return
     * @time 2017年8月1日16:27:10
     */
    @RequestMapping("404")
    public String noFound(){
        return "404";
    }

    /**
     * 用于web.xml中配置的error-code为400的Servlet
     * @param response
     * @throws IOException
     * @time 2017年8月1日16:26:57
     */
    @RequestMapping("noFound")
    public void tempNoFound(HttpServletResponse response) throws IOException {
        response.sendRedirect(GetPropertyUtil.getWebsiteAddress("localServerAddress") + "/404");
    }

    /**
     * 用于web.xml中配置的error-code为500的Servlet
     * @param response
     * @throws IOException
     * @time 2017年8月1日16:28:23
     */
    @RequestMapping("systemError")
    public void tempSystemError(HttpServletResponse response) throws IOException {
        response.sendRedirect(GetPropertyUtil.getWebsiteAddress("localServerAddress") + "/500");
    }

    /**
     * 500未知错误
     * @return
     * @time 2017年8月1日16:29:12
     */
    @RequestMapping("500")
    public String systemError(){
        return "500";
    }

    /**
     * 重定向到论坛首页
     * @return
     * @time 2017年8月1日16:29:04
     */
    @RequestMapping("index")
    public String index(){
        return "redirect:bbs-module/index";
    }
}
