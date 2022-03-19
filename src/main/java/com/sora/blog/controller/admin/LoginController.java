package com.sora.blog.controller.admin;

import com.sora.blog.pojo.User;
import com.sora.blog.service.UserService;
import com.sora.blog.util.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author Sora Chen
 * @Date 2022/2/5 14:21
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "/admin/loginm";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user =userService.checkUser(username);
        if(user==null){
            attributes.addFlashAttribute("message","用户不存在");
            return "redirect:/admin";
        }else{
            if(user.getPassword().equals(MD5util.code(password))){
                if(user.getType()==1){
                    user.setPassword(null);
                    session.setAttribute("user",user);
                    return "/admin/blog_index";
                }else{
                    attributes.addFlashAttribute("message", "权限不足");
                    return "redirect:/admin";
                }
            }else{
                attributes.addFlashAttribute("message", "密码错误");
                return "redirect:/admin";
            }
        }
    }

    @GetMapping("/loginout")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
