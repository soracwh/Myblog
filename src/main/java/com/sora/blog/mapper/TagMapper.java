package com.sora.blog.mapper;

import com.sora.blog.pojo.Tag;
import com.sora.blog.pojo.query.TagSearch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TagMapper {

    int insertByName(String name);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    int updateTag(Tag tag);

    int deleteTag(Long id);

    /**
     * 首页tag，与博客关联
    * */
    List<TagSearch> getIndexTag();
}
