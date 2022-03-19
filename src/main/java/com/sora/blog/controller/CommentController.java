package com.sora.blog.controller;

import com.sora.blog.pojo.Comment;
import com.sora.blog.pojo.User;
import com.sora.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/3/6 10:35 上午
 * @Version 1.0
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments/{blogId}")
    public String addComment(@PathVariable Long blogId, Model model){
        List<Comment> list = commentService.getCommentsByBlogId(blogId);
        model.addAttribute("comments",list);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null&&user.getAvatar()!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminFlag(true);
        }else{
            comment.setAvatar("/static/image/avatar.jpg");
            comment.setAdminFlag(false);
        }
        commentService.insertComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }
}
