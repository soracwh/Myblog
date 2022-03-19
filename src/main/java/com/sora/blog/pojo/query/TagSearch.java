package com.sora.blog.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author Sora Chen
 * @Date 2022/3/2 6:25 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagSearch {
    private Long id;
    private String name;

    private Integer blogNum;

}
