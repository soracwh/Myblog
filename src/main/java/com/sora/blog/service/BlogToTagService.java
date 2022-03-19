package com.sora.blog.service;

import com.sora.blog.pojo.Blog;
import com.sora.blog.pojo.Tag;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/25 6:49 下午
 * @Version 1.0
 */
public interface BlogToTagService {
    void insertRelation(Long blogId, List<Tag> list);

    int delete(Long blogId);

    void setTagIds(Blog blog);

    List<Tag> getTagByBlogId(Long blogId);
}
