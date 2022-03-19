package com.sora.blog.service;

import com.sora.blog.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/3/6 3:09 下午
 * @Version 1.0
 */

@Service
public interface CommentService {
    List<Comment> getCommentsByBlogId(Long blogId);

    int insertComment(Comment comment);
}
