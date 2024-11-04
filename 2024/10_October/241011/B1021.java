/*
 * https://www.acmicpc.net/problem/1021
 */
import java.io.*;
import java.util.*;
public class B1021 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Deque<Integer> deque;
    private static int[] outputList;
    public static void main(String[] args) throws Exception {
        initArguments();
        int answer = solve();
        System.out.println(answer);
    }

    private static void initArguments() throws Exception {
        deque = new ArrayDeque<>();
        String[] input = in.readLine().split(" ");
        int size = Integer.parseInt(input[0]), outputSize = Integer.parseInt(input[1]);
        for(int i = 1; i <= size; i++)  deque.offerLast(i);
        
        outputList = new int[outputSize];
        input = in.readLine().split(" ");
        for(int i = 0; i < outputSize; i++) outputList[i] = Integer.parseInt(input[i]);
    }

    private static int solve() {
        int answer = 0;
        for(int i = 0; i < outputList.length; i++){
            int value = outputList[i];
            if(value != deque.peekFirst()) {
                int left = getMoveCount(deque, value, false);
                int right = getMoveCount(deque, value, true);

                if(left >= right)   processMove(deque, value, true);
                else processMove(deque, value, false);
                answer += Math.min(left, right);
            }

            deque.pollFirst();
        }

        return answer;
    }

    private static int getMoveCount(Deque<Integer> deque, int value, boolean isRight) {
        int count = 0;
        Deque<Integer> tempDeque = new ArrayDeque<>(deque);
        while(value != tempDeque.peekFirst()) {
            if(isRight) moveRight(tempDeque);
            else moveLeft(tempDeque);
            count++;
        }

        return count;
    }

    private static void processMove(Deque<Integer> deque, int value, boolean isRight){
        while(value != deque.peekFirst()){
            if(isRight) moveRight(deque);
            else moveLeft(deque);
        }
    }

    private static void moveLeft(Deque<Integer> deque) {
        int value = deque.pollFirst();
        deque.offerLast(value);
    }

    private static void moveRight(Deque<Integer> deque) {
        int value = deque.pollLast();
        deque.offerFirst(value);
    }
}
