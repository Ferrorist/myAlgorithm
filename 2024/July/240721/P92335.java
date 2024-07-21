/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/92335
 * 소요 시간 : 19분 29초
 */
import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        Stack<Integer> calcStack = calc(n, k);

        long temp = 0; 
        while(!calcStack.isEmpty()){
            int pop = calcStack.pop();
            if(pop > 0) temp = temp * 10 + pop;
            else {
                if(temp >= 2 && isPrime(temp)) answer++;
                temp = 0;
            }
        }

        if(temp >= 2 && isPrime(temp)) answer++;
        return answer;
    }
    

    // n은 k 진수로 변환함.
    Stack<Integer> calc(int n, int k){
        Stack<Integer> stack = new Stack<>();
        do{  
            stack.push(n % k);
            n = n / k;
        }while(n > 0);
        
        return stack;
    }
    
    boolean isPrime(long n){
        int sqrt = (int)Math.sqrt(n);
        for(int i = 2; i <= sqrt; i++){
            if(n % i == 0)  return false;
        }
        return true;
    }
}