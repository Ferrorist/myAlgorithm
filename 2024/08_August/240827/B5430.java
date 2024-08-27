/*
 * https://www.acmicpc.net/problem/5430
 * 소요 시간 : 21분 8초
 */
import java.io.*;
import java.util.*;
public class B5430 {
    static StringBuilder sb;
    static BufferedReader in;
    static int Testcase;
    static final char REVERSE = 'R', DELETE = 'D';
    static boolean reverse;
    public static void main(String[] args) throws Exception {
        init();
        while(Testcase-- > 0)  solve();
        System.out.println(sb);
    }

    private static void init() throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        Testcase = Integer.parseInt(in.readLine());
    }

    private static void solve() throws Exception {
        String cmd = in.readLine();
        int arr_length = Integer.parseInt(in.readLine());
        String input = in.readLine().replace("[", "").replace("]", "");

        Deque<Integer> deque = new ArrayDeque<>();

        if(input != "" && !input.isEmpty()) {
            String[] arrInput = input.split(",");
            for(String num : arrInput)  deque.offerLast(Integer.parseInt(num));
        }

        reverse = false;
        if(process(cmd, deque)){
            printResult(deque);
        }
    }

    private static boolean process(String cmd, Deque<Integer> deque) {
        for(int i = 0; i < cmd.length(); i++){
            char current = cmd.charAt(i);
            if(current == REVERSE)  reverse = !(reverse);
            else { // D
                if(deque.isEmpty()){
                    sb.append("error\n");
                    return false;
                }

                if(reverse) deque.pollLast();
                else deque.pollFirst();
            }
        }

        return true;
    }

    private static void printResult(Deque<Integer> deque) {
        sb.append("[");
        while(!deque.isEmpty()){
            if(reverse) sb.append(deque.pollLast());
            else sb.append(deque.pollFirst());

            if(deque.size() >= 1)   sb.append(",");    
        }
        sb.append("]\n");
    }
}
