package com.sora.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Sora Chen
 * @Date 2022/3/12 3:36 下午
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Debt {
    private Integer id;
    private User user;
    private String debt;
}
