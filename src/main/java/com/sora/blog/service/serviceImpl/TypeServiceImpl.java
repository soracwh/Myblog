package com.sora.blog.service.serviceImpl;

import com.sora.blog.mapper.TypeMapper;
import com.sora.blog.pojo.Type;
import com.sora.blog.pojo.query.TypeSearch;
import com.sora.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/10 21:28
 * @Version 1.0
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int insertName(String name) {
        return typeMapper.insertName(name);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    @Transactional
    public int updateType(Long id, String name) {
        return typeMapper.updateType(id,name);
    }

    @Override
    public List<TypeSearch> getIndexType() {
        return typeMapper.getIndexType();
    }

    @Override
    public List<TypeSearch> getIndexType(Integer num) {
        return typeMapper.getIndexType(num);
    }

    @Override
    @Transactional
    public Integer deleteType(Long id) {
        return typeMapper.deleteType(id);
    }

}
