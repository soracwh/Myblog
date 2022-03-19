package com.sora.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.blog.pojo.Blog;
import com.sora.blog.pojo.User;
import com.sora.blog.pojo.query.BlogQuery;
import com.sora.blog.service.BlogService;
import com.sora.blog.service.BlogToTagService;
import com.sora.blog.service.TagService;
import com.sora.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/6 17:59
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogToTagService blogToTagService;

    private void setModel(Model model){
        model.addAttribute("type",typeService.getAllType());
        model.addAttribute("tag",tagService.getAllTag());
    }

    @GetMapping("/blogs")
    public String manage(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         Model model){
        PageHelper.startPage(pageNum,5);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("type",typeService.getAllType());
        return "/admin/blogm";
    }

    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        int flag = blogService.deleteBlog(id);
        if (flag == 1) {
            blogToTagService.delete(id);
            attributes.addFlashAttribute("message","删除成功");
        }else {
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/blogs";
    }

    //跳转新增页面
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        setModel(model);
        return "/admin/blogp";
    }

    //修改页面，携带id
    @GetMapping("/blogs/{id}/input")
    public String inputId(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlogById(id);
        //给tagids赋值
        blogToTagService.setTagIds(blog);
        model.addAttribute("blog",blog);
        setModel(model);
        return "/admin/blogp";
    }

    @PostMapping("/blogs/search")
    public String searchBlog(@RequestParam(value = "type",required = false) String type,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "recommend",required = false) boolean recommend,
                             @RequestParam(defaultValue = "1",value = "page") Integer pageNum,
                             Model model){
        HashMap<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("title",title);
        if (recommend) {
            map.put("recommend", 1);
        } else {
            map.put("recommend", 0);
        }
        PageHelper.startPage(pageNum,5);
        List<BlogQuery> list = blogService.searchBlog(map);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogm :: blogList";
    }

    @PostMapping("/blogs/add")
    public String post(Blog blog, @RequestParam(value = "tagIds")String tagIds,
                       HttpSession session,RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        //在service层处理ids字符串
        blog.setTags(tagService.getTagByIds(tagIds));
        long b = blogService.insertBlog(blog);
        //改变blog-tag多对多映射数据库
        if(blog.getId()!=null){
            blogToTagService.delete(blog.getId());
        }
        blogToTagService.insertRelation(blog.getId(),blog.getTags());
        if(b!=0){
            attributes.addFlashAttribute("message","操作成功");
        }else {
            attributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/blogs";
    }

}
