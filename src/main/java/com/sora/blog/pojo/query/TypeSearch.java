package com.sora.blog.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Sora Chen
 * @Date 2022/2/28 9:14 下午
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeSearch {
    private Long id;
    private String name;

    private Integer blogNum;
}
