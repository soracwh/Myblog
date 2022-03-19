package com.sora.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.blog.pojo.Type;
import com.sora.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/10 22:40
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @PostMapping("/types/type_insert")
    public String insert(@RequestParam(value = "types") String name, RedirectAttributes redirectAttributes){
        Type typeIf = typeService.getTypeByName(name);
        if(typeIf!=null){
            redirectAttributes.addFlashAttribute("message","不能添加重复的分类");
        }else{
            int flag = typeService.insertName(name);
            if(flag==1){
                redirectAttributes.addFlashAttribute("message","添加成功");
            }else{
                redirectAttributes.addFlashAttribute("message","添加失败");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        int flag = typeService.deleteType(id);
        if(flag!=0){
            attributes.addFlashAttribute("message", "删除成功");
        }else{
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/update")
    public String update(@RequestParam(value = "id") Long typeId,
                         @RequestParam(value = "types") String typeName,
                         RedirectAttributes attributes){
        Type typeIf = typeService.getType(typeId);
        Type typeNew = typeService.getTypeByName(typeName);
        if(typeIf!=null&&typeNew==null&& typeService.updateType(typeId,typeName)!=0){
            attributes.addFlashAttribute("message","修改成功");
        }else{
            attributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/types";
    }

}
