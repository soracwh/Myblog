package com.sora.blog.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/24 11:13 下午
 * @Version 1.0
 */
public class StringUtil {
    public static List<Integer> stringSub(String s){
        List<Integer> list = new ArrayList<>();
        while(s.indexOf(',')!=-1){
            String sl = s.substring(0,s.indexOf(','));
            list.add(Integer.valueOf(sl));
            s = s.substring(s.indexOf(',')+1);
        }
        list.add(Integer.valueOf(s));
        return list;
    }

    public static String ListToString(List<Long> list){
        if(list==null||list.isEmpty()){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Long aLong : list) {
            sb.append(aLong);
            sb.append(',');
        }
        return sb.substring(0,sb.length()-1);
    }


    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        System.out.println(StringUtil.ListToString(list));
    }
}
