package com.sora.blog.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Sora Chen
 * @Date 2022/2/28 10:43 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearch {
    private Long id;
    private String username;
    private String avatar;
}
