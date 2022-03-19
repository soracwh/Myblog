package com.sora.blog.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Sora Chen
 * @Date 2022/3/2 7:17 下午
 * @Version 1.0
 */

//index右边最新推荐
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSimple {

    private Long id;
    private String title;
}
