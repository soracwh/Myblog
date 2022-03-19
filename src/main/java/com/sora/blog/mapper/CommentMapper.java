package com.sora.blog.mapper;

import com.sora.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/3/6 3:12 下午
 * @Version 1.0
 */
@Repository
@Mapper
public interface CommentMapper {

    List<Comment> getCommentsByBlogId(Long blogId);

    int insertComment(Comment comment);
}
