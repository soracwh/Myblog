package com.sora.blog.service;

import com.sora.blog.pojo.Type;
import com.sora.blog.pojo.query.TypeSearch;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/10 21:15
 * @Version 1.0
 */
public interface TypeService {

    int insertName(String name);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    int updateType(Long id, String name);

    Integer deleteType(Long id);

    List<TypeSearch> getIndexType();

    List<TypeSearch> getIndexType(Integer num);

}
