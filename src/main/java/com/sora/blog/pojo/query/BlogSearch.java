package com.sora.blog.pojo.query;

import com.sora.blog.pojo.Tag;
import com.sora.blog.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/23 9:07 下午
 * @Version 1.0
 */
//首页现实博客实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogSearch {
    private Long id;
    private String title;
    private String description;
    private Integer views;
    private Type type;
    private String addPicture;
    private Date updateTime;
    private UserSearch userSearch;

    private List<Tag> tags;
}
