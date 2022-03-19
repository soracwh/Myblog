package com.sora.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/4 16:17
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    private String name;

    //与其他实体类对应关系
    private List<Blog> blogs = new ArrayList<>();
}
