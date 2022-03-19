package com.sora.blog.controller;

import com.sora.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Sora Chen
 * @Date 2022/3/11 10:21 下午
 * @Version 1.0
 */

@Controller
public class IntroductionController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/introduction")
    public String introduction(Model model){
        model.addAttribute("newBlog",blogService.getSimpleBlog(true));
        return "introduction";
    }
}
