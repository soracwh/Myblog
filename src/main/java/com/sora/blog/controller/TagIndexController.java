package com.sora.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.blog.pojo.query.BlogSearch;
import com.sora.blog.pojo.query.TagSearch;
import com.sora.blog.service.BlogService;
import com.sora.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/3/11 7:32 下午
 * @Version 1.0
 */

@Controller
public class TagIndexController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/tags/{id}")
    public String tag(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                      @PathVariable String id, Model model){
//        System.out.println(id);
        Long tId = -1L;
        try {
            tId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            tId = -1L;
        }
        List<TagSearch> tags= tagService.getIndexTag();
        if(tId==-1){
            tId = tags.get(0).getId();
        }
        PageHelper.startPage(pageNum,5);
        List<BlogSearch> list = blogService.blogForTagId(tId);
        PageInfo<BlogSearch> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags",tags);
        model.addAttribute("activeId",tId);
        model.addAttribute("newBlog",blogService.getSimpleBlog(true));
        return "tags";
    }
}
