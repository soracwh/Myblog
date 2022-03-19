package com.sora.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.blog.pojo.Blog;
import com.sora.blog.pojo.Tag;
import com.sora.blog.pojo.query.BlogSearch;
import com.sora.blog.service.BlogService;
import com.sora.blog.service.BlogToTagService;
import com.sora.blog.service.TagService;
import com.sora.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/1/28 20:43
 * @Version 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogToTagService blogToTagService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                        Model model){
        PageHelper.startPage(pageNum,6);
        List<BlogSearch> list = blogService.blogForIndex();
        PageInfo<BlogSearch> pageInfo = new PageInfo<>(list);
        model.addAttribute("total",blogService.blogAllCount());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.getIndexType(6));
        model.addAttribute("tags",tagService.getIndexTag());
        //最新推荐博客
        model.addAttribute("simpleBlog",blogService.getSimpleBlog(false));
        //最新博客
        model.addAttribute("newBlog",blogService.getSimpleBlog(true));
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         @RequestParam(value = "query")String query, Model model){
        PageHelper.startPage(pageNum,6);
        List<BlogSearch> list = blogService.blogSearch(query);
        PageInfo<BlogSearch> pageInfo =new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        model.addAttribute("newBlog",blogService.getSimpleBlog(true));
        model.addAttribute("total",blogService.blogSearchForIndexTotal(query));
        return "blog_search";
    }

    @GetMapping("/blog/{id}")
    public String blog(Model model, @PathVariable Long id){
        Blog blog = blogService.getAndConvert(id);
        blog.setViews(blog.getViews()+1);
        blogService.updateView(blog);
        List<Tag> tags = blogToTagService.getTagByBlogId(id);
        model.addAttribute("blog",blog);
        model.addAttribute("tags",tags);
        model.addAttribute("newBlog",blogService.getSimpleBlog(true));
        return "blog";
    }

}
