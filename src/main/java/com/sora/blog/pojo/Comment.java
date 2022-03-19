package com.sora.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/4 16:20
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String content;
    private Long id;
    private String nickname;
    private String email;
    private String avatar;
    private Date createTime;
    private Boolean adminFlag;

    private Long blogId;
    private Long parentId;

    //评论子类父类
    private List<Comment> replyComment = new ArrayList<>();
    private Comment parentComment;
}
