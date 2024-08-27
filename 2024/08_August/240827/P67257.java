/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/67257
 * 소요 시간 : 57분 20초
 */
import java.util.*;
class Solution {
    List<int[]> permutation_list = new ArrayList<>();
    public long solution(String expression) {
        long answer = 0;
        String[] inputs = expression.split("(?<=[-+*/])|(?=[-+*/])");
        
        Set<Character> set = getOpers(inputs);
        char[] opers = getOperChars(set);
        getPermutation(opers);
        
        for(int[] arr : permutation_list){
            char[] order = new char[arr.length];
            for(int i = 0; i < arr.length; i++) order[i] = opers[arr[i]];
            answer = Math.max(answer, solve(order, inputs));
        }
        return answer;
    }
    
    private Set<Character> getOpers(String[] inputs){
        Set<Character> set = new HashSet<>();
        for(String str : inputs) {
            if(str != null && Character.isDigit(str.charAt(0)))    continue;
            set.add(str.charAt(0));
        }
        
        return set;
    }
    
    private char[] getOperChars(Set<Character> set){
        char[] opers = new char[set.size()];
        
        int opers_idx = 0;
        Iterator<Character> iter = set.iterator();
        while(iter.hasNext()){
            opers[opers_idx++] = iter.next();
        }
        
        return opers;
    }
    
    private void getPermutation(char[] opers) {
        for(int i = 0; i < opers.length; i++){
            boolean[] visited = new boolean[opers.length];
            int[] current = new int[opers.length];  Arrays.fill(current, -1);
            
            visited[i] = true;
            current[0] = i;
            permute(opers, visited, 1, current);
        }
    }
    
    private void permute(char[] opers, boolean[] visited, int depth, int[] current){
        if(depth == opers.length){
            permutation_list.add(current.clone());  return;
        }
                
        for(int i = 0; i < opers.length; i++){
            if(visited[i])  continue;
            visited[i] = true;  current[depth] = i;
            permute(opers, visited, depth + 1, current);
            visited[i] = false; current[depth] = -1;
        }
    }
    
    private long solve(char[] oper, String[] input) {
        Deque<String> deque = new ArrayDeque<>();
        String[] exp = input.clone();
        
        for(int i = 0; i < oper.length; i++){
            for(int exp_idx = 0; exp_idx < exp.length; exp_idx++){
                String current = exp[exp_idx];
                char firstChar = current.charAt(0);
                if(Character.isDigit(firstChar) || firstChar != oper[i])
                    deque.offerLast(current);
                else {
                    long left = Long.parseLong(deque.pollLast());
                    long right = Long.parseLong(exp[++exp_idx]);
                    long value = calc(left, right, oper[i]);
                    deque.offerLast(String.valueOf(value));
                }
            }
            
            exp = new String[deque.size()];
            int idx = 0;
            while(!deque.isEmpty()) exp[idx++] = deque.pollFirst();
        }
        
        return Math.abs(Long.parseLong(exp[0]));
    }
    
    private long calc(long left, long right, char op){
        switch(op){
            case '+':   return left + right;
            case '-':   return left - right;
            case '*':   return left * right;
        }
        return 0;
    }
}