/*
 * https://www.acmicpc.net/problem/17298
 * 소요 시간 : 24분 59초
 */
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // 수열의 크기 ( 1 <= N <= 1_000_000)
        int N = Integer.parseInt(in.readLine()); 
        String[] input = in.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        
        int[] arr = new int[N];
        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        for(int i = 0; i < N; i++)    arr[i] = Integer.parseInt(input[i]);
        Stack<Integer> stack = new Stack<>();

        for(int i = N-1; i >= 0; i--){
            while(!stack.isEmpty()){
                if(stack.peek() <= arr[i])    stack.pop();
                else {
                    answer[i] = stack.peek();
                    break;
                }
            }
            stack.push(arr[i]);
        }
        for(int i = 0; i < N; i++) sb.append(answer[i]).append(" ");
        
        System.out.println(sb);
    }
}