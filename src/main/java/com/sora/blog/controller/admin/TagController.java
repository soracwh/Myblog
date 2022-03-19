package com.sora.blog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.blog.pojo.Tag;
import com.sora.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/tags")
    public String list(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                       Model model){
        String orderBy = "id, name";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Tag> list =tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        model.addAttribute(pageInfo);
        return "admin/tags";
    }


    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        int flag = tagService.deleteTag(id);
        if(flag>0){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/tags";
    }


    @PostMapping("tags/insert")
    public String insert(@RequestParam(value = "tag")String name,
                         RedirectAttributes attributes){
         Tag tag = tagService.getTagByName(name);
         if(tag!=null){
             attributes.addFlashAttribute("message","不能添加重复的分类");
         }else{
             int flag = tagService.insertByName(name);
             if(flag!=0){
                 attributes.addFlashAttribute("message","添加成功");
             }else{
                 attributes.addFlashAttribute("message","添加失败");
             }
         }
         return "redirect:/admin/tags";
    }

    //带着id跳转到input界面
    @RequestMapping("/tags/{id}")
    public String input(@PathVariable Long id, Model model){
        Tag tag= tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tag_input";
    }

    @PostMapping("/tags/{id}/update")
    public String update(@Valid Tag tag, BindingResult result,
                         Long id, RedirectAttributes attributes,
                         Model model){
        Tag tag1 = tagService.getTagByName(tag.getName());
//        if(tag1!=null){
//            result.rejectValue("name","nameError","已经有该标签");
//
//        }
        //model中的tag依旧在页面中
        if(tag1!=null){
            model.addAttribute("message","已有该标签");
            return "/admin/tag_input";
        }
        int flag = tagService.updateTag(tag);
        if(flag!=0){
            attributes.addFlashAttribute("message","操作成功");
        }else{
           attributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/tags";
    }
}
