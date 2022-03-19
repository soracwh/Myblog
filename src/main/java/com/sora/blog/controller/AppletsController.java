package com.sora.blog.controller;

import com.sora.blog.pojo.User;
import com.sora.blog.service.AppletsService;
import com.sora.blog.service.UserService;
import com.sora.blog.util.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/3/12 2:13 下午
 * @Version 1.0
 */

@Controller
@RequestMapping("/applets")
public class AppletsController {

    @Autowired
    private UserService userService;
    @Autowired
    private AppletsService appletsService;

    @RequestMapping
    public String applets(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        boolean activeFlag = false;
        //判别权限
        if(user!=null&&user.getType()<=2){
            activeFlag = true;
            List<Integer> debt = appletsService.getDebtByUserId(user.getId());
            int count = 0;
            for (Integer integer : debt) {
                count+=integer;
            }
            List<User> userDebt = userService.getFiveUser(user.getId());
            model.addAttribute("debt",debt);
            model.addAttribute("userDebt",userDebt);
            model.addAttribute("allDebt",appletsService.getAllDebt());
            model.addAttribute("allUser",userService.getSixUser());
            model.addAttribute("total",count);
        }
        model.addAttribute("activeFlag",activeFlag);
        return "applets";
    }

    @PostMapping("/login")
    public String appletsLogin(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               RedirectAttributes attributes,HttpSession session){
        User user =userService.checkUser(username);
        if(user==null){
            attributes.addFlashAttribute("message","用户不存在");
        }else{
            if(user.getPassword().equals(MD5util.code(password))){
                if(user.getType()<=2){
                    user.setPassword(null);
                    List<Integer> debt = appletsService.getDebtByUserId(user.getId());
                    int count = 0;
                    for (Integer integer : debt) {
                        count+=integer;
                    }
                    //debt.forEach(System.out::println);
                    attributes.addFlashAttribute("debt",debt);
                    List<User> userDebt = userService.getFiveUser(user.getId());
                    attributes.addFlashAttribute("userDebt",userDebt);
                    attributes.addFlashAttribute("allDebt",appletsService.getAllDebt());
                    attributes.addFlashAttribute("allUser",userService.getSixUser());
                    attributes.addFlashAttribute("total",count);
                    //移除user，再加入新的user
                    session.removeAttribute("user");
                    session.setAttribute("user",user);
                }else{
                    attributes.addFlashAttribute("message", "权限不足");
                }
            }else{
                attributes.addFlashAttribute("message", "密码错误");
            }
        }
        return "redirect:/applets";
    }

    @PostMapping("/update")
    public String update(@RequestParam(value = "debtId") Long debtId,
                         @RequestParam(value = "debtNum") Integer debtNum,HttpSession session){
        User user = (User) session.getAttribute("user");
        appletsService.updateDebt(user,debtId,debtNum);
        return "redirect:/applets";
    }

}
