/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42746
 */
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++)
            arr[i] = Integer.toString(numbers[i]);
        
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2){
                String o1TOo2 = o1 + o2;
                String o2TOo1 = o2 + o1;
                return o2TOo1.compareTo(o1TOo2);
            }
        });
        StringBuilder answer = new StringBuilder();
        for(String num : arr)   answer.append(num);
        
        while(answer.length() > 0 && answer.toString().charAt(0) == '0')   answer.deleteCharAt(0);
        return answer.length() > 0 ? answer.toString() : "0";
    }
}