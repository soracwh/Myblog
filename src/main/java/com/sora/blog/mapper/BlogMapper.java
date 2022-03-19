package com.sora.blog.mapper;

import com.sora.blog.pojo.Blog;
import com.sora.blog.pojo.query.BlogQuery;
import com.sora.blog.pojo.query.BlogSearch;
import com.sora.blog.pojo.query.BlogSimple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface BlogMapper {

    Blog getBlogById(Long id);

    List<BlogQuery> getAllBlog();

    long insertBlog(Blog blog);

    int updateBlog(Long id,Blog blog);

    @Update("update blog.t_blog set views = #{views} where id = #{id}")
    int updateView(Blog blog);

    int deleteBlog(Long id);

    List<BlogQuery> searchBlog(HashMap map);

    /**
     * 首页模版查询
    */
    List<BlogSearch> blogForIndex();
    /**
     * 根据typeid查询
    * */
    List<BlogSearch> blogForIndex(@Param("typeId") Long typeId);
    /**
     * 根据tagid查询
     * */
    List<BlogSearch> blogForTagId(@Param("tagId") Long tagId);

    //条件查询
    List<BlogSearch> blogSearchForIndex(@Param("query") String query);

    int blogSearchForIndexTotal(@Param("query") String query);

    @Select("select count(id) from blog.t_blog")
    int blogAllCount();

    @Select("select b.id,b.title from blog.t_blog b order by b.recommend desc,b.update_time desc limit 5")
    List<BlogSimple> getSimpleBlog();

    @Select("select b.id,b.title from blog.t_blog b order by b.update_time desc limit 3")
    List<BlogSimple> getNewBlog();

    @Select("select date_format(b.update_time,'%Y') as year from blog.t_blog b group by year order by year desc")
    List<String> getAllYear();

    List<BlogQuery> getByYear(@Param("year") String year);
}
