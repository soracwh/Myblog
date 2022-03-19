package com.sora.blog.util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Sora Chen
 * @Date 2022/3/16 7:13 下午
 * @Version 1.0
 */
public class DebtUtil {

    public static void Change(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            OneRow(i, matrix);
        }
    }
    public static void OneRow(int i,int[][] matrix){
        int row = matrix[0].length;
        int sum = 0;
        for (int m : matrix[i]) {
            sum += m;
        }
        int[][] array = new int[row][2];
        for (int j = 0; j < row; j++) {
            array[j][0] = j;
            array[j][1] = matrix[i][j];
        }
        if (sum >= 0){
            Arrays.sort(array, Comparator.comparingInt(o -> o[1]));
            int left = 0;
            int right = 0;
            if(array[left][1] == 0)return;
            while(right<row && array[right][1] <= 0){
                right++;
            }
            while(left<row && array[left][1] < 0){
                while(array[left][1] <= 0){
                    if (array[left][1] == 0) {
                        left++;
                        break;
                    }
                    if (-array[left][1] >= array[right][1]){
                        array[left][1] = array[left][1] + array[right][1];
                        ChangeTwo(array[left][0],array[right][0],array[right][1],matrix);
                        array[right][1]=0;
                        right++;
                    }else {
                        array[right][1] -= -array[left][1];
                        ChangeTwo(array[left][0],array[right][0],-array[left][1],matrix);
                        array[left][1] = 0;
                        left++;
                    }
                }
            }
        }else {
            Arrays.sort(array, (o1, o2) -> o2[1]-o1[1]);
            int left = 0;
            int right = 0;
            while(right<row && array[right][1] >= 0){
                right++;
            }
            while(left<row && array[left][1] > 0){
                while(array[left][1] >= 0){
                    if (array[left][1] == 0) {
                        left++;
                        break;
                    }
                    if ( array[left][1] >= -array[right][1]){
                        array[left][1] = array[left][1] + array[right][1];
                        ChangeTwo(array[left][0],array[right][0],array[right][1],matrix);
                        array[right][1]=0;
                        right++;
                    }else{
                        array[right][1] += array[left][1];
                        ChangeTwo(array[left][0],array[right][0],-array[left][1],matrix);
                        array[left][1]=0;
                        left++;
                    }
                }
            }
        }
        for (int j = 0; j < row; j++) {
            matrix[i][array[j][0]] = array[j][1];
            matrix[array[j][0]][i] = -array[j][1];
        }
    }

    public static void ChangeTwo(int i, int j, int num,int[][] matrix){
        matrix[i][j]+=num;
        matrix[j][i]-=num;
    }
    public static void main(String[] args) {
        //debt.matrix = new int[][]{{0,50,-100},{-50,0,20},{100,-20,0}};
        //[[0, 0, 0, 0, 1800, 0], [0, 0, 30, 0, 385, 1200], [0, -30, 0, -1045, 0, 0], [0, 0, 1045, 0, 465, 45], [-1800, -385, 0, -465, 0, 0], [0, -1200, 0, -45, 0, 0]]
        //[[0, 0, 0, 2115, 885, 0], [0, 0, 0, 1915, 0, 0], [0, 0, 0, 470, 0, 0], [-2115, -1915, -470, 0, 0, -555], [-885, 0, 0, 0, 0, 0], [0, 0, 0, 555, 0, 0]]
        //珍珠，老贝，汪峰，自己，削鸡，小老弟
        int[][] matrix = new int[][]{{0,-555,-470,-1915,-2115,0}, {555,0,0,0,100,0}, {470,0,0,0,0,0},{1915,0,0,0,0,0},{2115,-100,0,0,0,885},{0,0,0,0,-885,0}};
        DebtUtil.Change(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
