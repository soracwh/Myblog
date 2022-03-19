package com.sora.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.blog.pojo.query.BlogSearch;
import com.sora.blog.pojo.query.TypeSearch;
import com.sora.blog.service.BlogService;
import com.sora.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/3/7 9:49 下午
 * @Version 1.0
 */

@Controller
@RequestMapping("/types")
public class TypeIndexController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public String type(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                       @PathVariable String id, Model model){
//        System.out.println(id);
        Long tId = -1L;
        try {
            tId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            tId = -1L;
        }
        List<TypeSearch> types= typeService.getIndexType();
        if (tId == -1 ) {
            tId = types.get(0).getId();
        }
        PageHelper.startPage(pageNum,5);
        List<BlogSearch> list = blogService.blogForIndex(tId);
        PageInfo<BlogSearch> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("newBlog",blogService.getSimpleBlog(true));
        model.addAttribute("activeId",tId);
        return "types";
    }

}
