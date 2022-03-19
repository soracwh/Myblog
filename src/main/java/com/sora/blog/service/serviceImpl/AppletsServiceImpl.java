package com.sora.blog.service.serviceImpl;

import com.sora.blog.mapper.DebtMapper;
import com.sora.blog.pojo.Debt;
import com.sora.blog.pojo.User;
import com.sora.blog.service.AppletsService;
import com.sora.blog.util.DebtUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author Sora Chen
 * @Date 2022/3/12 4:06 下午
 * @Version 1.0
 */

@Service
public class AppletsServiceImpl implements AppletsService {

    @Autowired
    private DebtMapper debtMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Integer> getDebtByUserId(Long userId) {
        String debt = debtMapper.getDebtByUserId(userId);
        String[] split= debt.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if(i+1!= userId){
                list.add(Integer.valueOf(split[i]));
            }
        }
        return list;
    }

    @Override
    public Map<User, List<Integer>> getAllDebt() {
        List<Debt> debts = debtMapper.getAllDebt();
        LinkedHashMap<User, List<Integer>> map= new LinkedHashMap<>();
        for (Debt debt : debts) {
            User user = debt.getUser();
            String[] split= debt.getDebt().split(",");
            List<Integer> list = new ArrayList<>();
            for (String s : split) {
                list.add(Integer.valueOf(s));
            }
            map.put(user,list);
        }
        return map;
    }

    @Override
    @Transactional
    public int updateDebt(User user, Long debtId, Integer debtNum) {
        if(debtId>=user.getId()) debtId++;
        List<Debt> list = debtMapper.getAllDebt();
        int[][] matrix = new int[6][6];
        for (int i = 0; i < matrix.length; i++) {
            String[] split= list.get(i).getDebt().split(",");
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = Integer.parseInt(split[j]);
            }
        }
        matrix[Math.toIntExact(user.getId()-1)][Math.toIntExact(debtId)-1]+=debtNum;
        matrix[Math.toIntExact(debtId)-1][Math.toIntExact(user.getId()-1)]-=debtNum;
        DebtUtil.Change(matrix);
        List<Debt> debts = new ArrayList<>();
        Long index = 1L;
        for (int[] ma : matrix) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < ma.length-1; i++) {
                res.append(ma[i]);
                res.append(",");
            }
            res.append(ma[ma.length-1]);
            Debt debt = new Debt();
            debt.setDebt(res.toString());
            User user1 = new User();
            user1.setId(index);
            debt.setUser(user1);
            debts.add(debt);
            index++;
        }
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)){
            DebtMapper mapper = sqlSession.getMapper(DebtMapper.class);
            for (Debt debt : debts) {
                mapper.updateByUserId(debt);
            }
            sqlSession.commit();
        }
        return 0;
    }


}
