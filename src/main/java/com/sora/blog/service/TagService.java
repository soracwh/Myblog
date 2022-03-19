package com.sora.blog.service;

import com.sora.blog.pojo.Tag;
import com.sora.blog.pojo.query.TagSearch;

import java.util.List;


public interface TagService {
    int insertByName(String name);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getTagByIds(String tagIds);

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<TagSearch> getIndexTag();

}
