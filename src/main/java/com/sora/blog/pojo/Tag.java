package com.sora.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/4 16:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;

    @NotBlank(message = "标签不能为空")
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
