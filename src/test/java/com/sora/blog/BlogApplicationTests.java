package com.sora.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.blog.mapper.*;
import com.sora.blog.pojo.Debt;
import com.sora.blog.pojo.User;
import com.sora.blog.pojo.query.BlogQuery;
import com.sora.blog.pojo.query.BlogSearch;
import com.sora.blog.service.*;
import com.sora.blog.util.MD5util;
import org.apache.ibatis.session.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BlogToTagMapper blogToTagMapper;
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogToTagService blogToTagService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DebtMapper debtMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private AppletsService appletsService;


    @Test
    public void test(){

//        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)){
//            DebtMapper debtMapper = sqlSession.getMapper(DebtMapper.class);
//            Debt debt1 = new Debt();
//            User user1 = new User();
//            Debt debt2 = new Debt();
//            User user2 = new User();
//            user1.setId(1L);
//            user2.setId(2L);
//            debt1.setUser(user1);
//            debt2.setUser(user2);
//            debt1.setDebt("0,-555,-470,-1915,-2115,0");
//            debt2.setDebt("555,0,0,0,0,1");
//            System.out.println(debtMapper.updateByUserId(debt1));
//            System.out.println(debtMapper.updateByUserId(debt2));
//            sqlSession.commit();
//        }
        User user = new User();
        user.setId(5L);
        Long debtId = 2L;
        Integer debtNum = 100;
        appletsService.updateDebt(user,debtId,debtNum);
    }

}
