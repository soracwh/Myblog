package com.sora.blog.mapper;

import com.sora.blog.pojo.Type;
import com.sora.blog.pojo.query.TypeSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/10 21:30
 * @Version 1.0
 */
@Mapper
@Repository
public interface TypeMapper {

    int insertName(String name);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    int updateType(@Param("id") Long id, @Param("name") String name);

    Integer deleteType(Long id);

    //首页查询
    List<TypeSearch> getIndexType();

    List<TypeSearch> getIndexType(@Param("num") Integer num);
}
