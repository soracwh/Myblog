package com.sora.blog.service.serviceImpl;

import com.sora.blog.mapper.BlogMapper;
import com.sora.blog.mapper.BlogToTagMapper;
import com.sora.blog.pojo.Blog;
import com.sora.blog.pojo.query.BlogQuery;
import com.sora.blog.pojo.query.BlogSearch;
import com.sora.blog.pojo.query.BlogSimple;
import com.sora.blog.service.BlogService;
import com.sora.blog.util.MarkDownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogToTagMapper blogToTagMapper;

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        assert blog != null;
        String content = blog.getContent();
        blog.setContent(MarkDownUtil.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    @Transactional
    public long insertBlog(Blog blog) {
        if(blog.getId()!=null){
            blog.setUpdateTime(new Date());
            return blogMapper.updateBlog(blog.getId(),blog);
        }else{
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            return blogMapper.insertBlog(blog);
        }

    }

    @Override
    @Transactional
    public int updateBlog(Long id, Blog blog) {
        Blog blog1 = blogMapper.getBlogById(id);

        assert blog1!=null;
        return blogMapper.updateBlog(id,blog);
    }

    @Override
    @Transactional
    public int updateView(Blog blog) {
       return blogMapper.updateView(blog);
    }

    @Override
    @Transactional
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> searchBlog(HashMap map) {
        return blogMapper.searchBlog(map);
    }

    //index
    @Override
    public List<BlogSearch> blogForIndex() {
        return blogMapper.blogForIndex();
    }

    @Override
    public List<BlogSearch> blogSearch(String query) {
        return blogMapper.blogSearchForIndex(query);
    }

    @Override
    public List<BlogSearch> blogForIndex(Long typeId) {
        return blogMapper.blogForIndex(typeId);
    }

    //在service层实现对tags的查询
    @Override
    public List<BlogSearch> blogForTagId(Long tagId) {
        List<BlogSearch> list = blogMapper.blogForTagId(tagId);
        for (BlogSearch blogSearch : list) {
            blogSearch.setTags(blogToTagMapper.getTagByBlogId(blogSearch.getId()));
        }
        return list;
    }

    @Override
    public int blogAllCount() {
        return blogMapper.blogAllCount();
    }

    @Override
    public List<BlogSimple> getSimpleBlog(boolean flag) {
        if(flag){
            return blogMapper.getNewBlog();
        }
        return blogMapper.getSimpleBlog();
    }

    @Override
    public int blogSearchForIndexTotal(String query) {
        return blogMapper.blogSearchForIndexTotal(query);
    }

    @Override
    public Map<String, List<BlogQuery>> sortBlogByYear() {
        List<String> list = blogMapper.getAllYear();
        LinkedHashMap<String, List<BlogQuery>> map = new LinkedHashMap<>();
        for (String year : list) {
            List<BlogQuery> b = blogMapper.getByYear(year);
            map.put(year,b);
        }
        return map;
    }
}
