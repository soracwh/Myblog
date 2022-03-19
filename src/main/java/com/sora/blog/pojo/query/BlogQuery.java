package com.sora.blog.pojo.query;

import com.sora.blog.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Sora Chen
 * @Date 2022/2/20 9:59 下午
 * @Version 1.0
 */

//后台博客实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private String flag;
    private Date updateTime;
    private Boolean recommend;
    private Long typeId;
    private Type type;

}
