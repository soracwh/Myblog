package com.sora.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/4 15:50
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String addPicture;
    private String flag;
    private Integer views;
    private String description;
    //赞赏功能
    private boolean appreciation;
    //转载
    private boolean shareStatement;
    //是否可评论
    private boolean commentable;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    private String tagIds;

    //与其他实体类的对应关系

    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();
}
