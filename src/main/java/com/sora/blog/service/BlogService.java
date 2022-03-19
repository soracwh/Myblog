package com.sora.blog.service;

import com.sora.blog.pojo.Blog;
import com.sora.blog.pojo.query.BlogQuery;
import com.sora.blog.pojo.query.BlogSearch;
import com.sora.blog.pojo.query.BlogSimple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlogById(Long id);

    Blog getAndConvert(Long id);

    List<BlogQuery> getAllBlog();

    long insertBlog(Blog blog);

    int updateBlog(Long id,Blog blog);
    int updateView(Blog blog);

    int deleteBlog(Long id);

    List<BlogQuery> searchBlog(HashMap map);

    //首页
    List<BlogSearch> blogForIndex();

    //根据tag/typeid查询
    List<BlogSearch> blogForIndex(Long typeId);
    List<BlogSearch> blogForTagId(Long tagId);

    //条件查询
    List<BlogSearch> blogSearch(String query);
    int blogSearchForIndexTotal(String query);

    int blogAllCount();

    List<BlogSimple> getSimpleBlog(boolean flag);

    Map<String,List<BlogQuery>> sortBlogByYear();
}
