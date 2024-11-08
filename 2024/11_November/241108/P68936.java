/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/68936
 * 소요 시간 : 19분 48초
 */
import java.util.*;
class Solution {
    Map<Integer, Integer> treeValues = new HashMap<>();
    public int[] solution(int[][] arr) {
        if (treeValues == null || !treeValues.isEmpty()) {
            treeValues = new HashMap<>();
        }
        int[] answer = new int[2];
        Integer result = solve(arr, arr.length, 0, 0);
        inputList(result);
        
        for(int i = 0; i < 2; i++) {
            answer[i] = treeValues.getOrDefault(i, 0);
        }
        
        return answer;
    }
    
    private Integer solve(int[][] arr, int length, int y, int x) {
        if(length <= 1) {
            return arr[y][x];
        }
        
        int half = length >>> 1;
        
        Integer a = solve(arr, half, y, x);
        Integer b = solve(arr, half, y + half, x);
        Integer c = solve(arr, half, y, x + half);
        Integer d = solve(arr, half, y + half, x + half);
        
        
        if(isEquals(a, b, c, d)) {
            return a;
        }
        else {
            inputList(a, b, c, d);
            return null;
        }
    }
    
    private boolean isEquals(Integer... values) {
        if (values.length == 1) {
            return true;
        }
        if (values[0] == null) {
            return false;
        }
        Integer initValue = values[0];
        for (int i = 1; i < values.length; i++) {
            if(values[i] == null || initValue != values[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    private void inputList(Integer... values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                treeValues.put(values[i], treeValues.getOrDefault(values[i], 0) + 1);
            }
        }
    }
}