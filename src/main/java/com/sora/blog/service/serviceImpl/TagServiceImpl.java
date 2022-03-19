package com.sora.blog.service.serviceImpl;


import com.sora.blog.mapper.TagMapper;
import com.sora.blog.pojo.Tag;
import com.sora.blog.pojo.query.TagSearch;
import com.sora.blog.service.TagService;
import com.sora.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public int insertByName(String name) {
        return tagMapper.insertByName(name);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getTagByIds(String tagIds) {
        List<Tag> list =new ArrayList<>();
        if(tagIds.equals("")){
            return null;
        }
        List<Integer> Ids = StringUtil.stringSub(tagIds);
        for (Integer id : Ids) {
            Long i = (long) id;
            list.add(tagMapper.getTag(i));
        }
        return list;
    }

    @Override
    public List<TagSearch> getIndexTag() {
        List<TagSearch> list = tagMapper.getIndexTag();
        list.sort((o1, o2) -> o2.getBlogNum()-o1.getBlogNum());
        return list;
    }
}
