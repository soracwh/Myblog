package com.sora.blog.controller;

import com.sora.blog.pojo.query.BlogQuery;
import com.sora.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Author Sora Chen
 * @Date 2022/3/11 9:09 下午
 * @Version 1.0
 */

@Controller
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/archives")
    public String archives(Model model){
        Map<String, List<BlogQuery>> blogMap =  blogService.sortBlogByYear();
        model.addAttribute("total",blogService.blogAllCount());
        model.addAttribute("blogMap",blogMap);
        model.addAttribute("newBlog",blogService.getSimpleBlog(true));
        return "archives";
    }
}
