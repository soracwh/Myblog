package com.sora.blog.controller;

import com.sora.blog.pojo.User;
import com.sora.blog.service.UserService;
import com.sora.blog.util.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author Sora Chen
 * @Date 2022/3/12 7:41 下午
 * @Version 1.0
 */

@Controller
public class LoginIndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/register/new")
    public String newUser(@RequestParam(value = "passwordConfirm") String passwordConfirm,
                          User user, HttpSession session, RedirectAttributes attributes){
        if(!user.getPassword().equals(passwordConfirm)){
            attributes.addFlashAttribute("message","两次输入密码不匹配");
            return "redirect:/register";
        }else{
            User userCheck = userService.checkUser(user.getUsername());
            if(userCheck!=null){
                attributes.addFlashAttribute("message","用户名已被注册");
                return "redirect:/register";
            }else{
                User userNew = userService.insertUser(user);
                userNew.setPassword(null);
                session.setAttribute("user",userNew);
                return "redirect:/";
            }
        }
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session){
        User userCheck = userService.checkUser(user.getUsername());
        if(Objects.equals(MD5util.code(user.getPassword()), userCheck.getPassword())){
            userCheck.setPassword(null);
            session.setAttribute("user",userCheck);
        }
        return "redirect:/";
    }

    @GetMapping("/loginout")
    public String loginOutIndex(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/";
        }
        return "/update";
    }

    @PostMapping("/update/password")
    public String updatePassword(@RequestParam(value = "oldPassword") String oldPassword,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "passwordConfirm") String passwordConfirm,
                                 HttpSession session,RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        if(user==null)
            return "redirect:/";
        if(!password.equals(passwordConfirm)){
            attributes.addFlashAttribute("message","两次输入密码不匹配");
            return "redirect:/update";
        }
        User userCheck = userService.checkUser(user.getUsername());
        if(!Objects.equals(userCheck.getPassword(),MD5util.code(oldPassword))){
            attributes.addFlashAttribute("message","密码错误");
            return "redirect:/update";
        }
        User userNew = userService.updateUser(userCheck,password);
        if(userNew==null){
            attributes.addFlashAttribute("message","修改失败");
            return "redirect:/update";
        }else{
            session.removeAttribute("user");
            attributes.addFlashAttribute("message","修改成功，请重新登陆");
            return "redirect:/";
        }
    }
}
