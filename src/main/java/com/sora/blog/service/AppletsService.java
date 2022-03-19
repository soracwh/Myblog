package com.sora.blog.service;

import com.sora.blog.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Author Sora Chen
 * @Date 2022/3/12 4:05 下午
 * @Version 1.0
 */
public interface AppletsService {

    List<Integer> getDebtByUserId(Long userId);

    Map<User,List<Integer>> getAllDebt();

    int updateDebt(User user,Long debtId,Integer debtNum);
}
