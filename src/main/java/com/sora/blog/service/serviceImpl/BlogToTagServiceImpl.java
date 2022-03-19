package com.sora.blog.service.serviceImpl;

import com.sora.blog.mapper.BlogToTagMapper;
import com.sora.blog.pojo.Blog;
import com.sora.blog.pojo.Tag;
import com.sora.blog.service.BlogToTagService;
import com.sora.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/25 6:50 下午
 * @Version 1.0
 */

@Service
public class BlogToTagServiceImpl implements BlogToTagService {

    @Autowired
    BlogToTagMapper blogToTagMapper;

    @Transactional
    @Override
    public void insertRelation(Long blogId, List<Tag> list) {
        if(list==null)return;
        for (Tag tag : list) {
            blogToTagMapper.insertRelation(blogId,tag.getId());
        }
    }

    @Override
    @Transactional
    public int delete(Long blogId) {
        return blogToTagMapper.delete(blogId);
    }

    @Override
    public void setTagIds(Blog blog) {
        List<Long> list = blogToTagMapper.getTagForBlog(blog.getId());
        blog.setTagIds(StringUtil.ListToString(list));
    }

    @Override
    public List<Tag> getTagByBlogId(Long blogId) {
        return blogToTagMapper.getTagByBlogId(blogId);
    }
}
