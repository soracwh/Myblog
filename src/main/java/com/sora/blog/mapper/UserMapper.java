package com.sora.blog.mapper;

import com.sora.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/5 13:59
 * @Version 1.0
 */

@Mapper
@Repository
public interface UserMapper {

    User queryByUsername(String username);

    int insertUser(User user);

    @Update("update blog.t_user set password = #{password} where id = #{id}")
    int updateUser(User user);

    @Select("select id,nickname from blog.t_user where id>=1 and id<=6")
    List<User> getSixUser();
}
