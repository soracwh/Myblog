package com.sora.blog.mapper;

import com.sora.blog.pojo.Debt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/3/12 3:38 下午
 * @Version 1.0
 */

@Mapper
@Repository
public interface DebtMapper {

    List<Debt> getAllDebt();

    @Select("select debt from blog.t_debt where user_debtor_id = #{userId}")
    String getDebtByUserId(@Param("userId") Long userId);

    void updateByUserId(Debt debt);
}
