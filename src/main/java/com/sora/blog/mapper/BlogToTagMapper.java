package com.sora.blog.mapper;

import com.sora.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/25 6:46 下午
 * @Version 1.0
 */

@Mapper
@Repository
public interface BlogToTagMapper {
    int insertRelation(Long blogId,Long tagId);

    int delete(Long blogId);

    List<Long> getTagForBlog(Long blogId);

    List<Tag> getTagByBlogId(Long blogId);
}
