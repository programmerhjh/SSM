package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by acer on 2017/8/1.
 */
@Controller
public class GlobalController {

    @RequestMapping("404")
    public String noFound(){
        return "404";
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
