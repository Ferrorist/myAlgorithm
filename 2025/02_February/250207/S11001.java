/*
 * https://softeer.ai/practice/11001
 */
import java.io.*;
import java.util.*;

public class S11001 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Queue<GPTNumber> queue = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        inputArguments();
        getResult();
    }

    private static void inputArguments() throws Exception {
        int count = Integer.parseInt(in.readLine());
        for(int i = 0; i < count; i++) {
            queue.offer(new GPTNumber(in.readLine()));
        }
    }

    private static void getResult() {
        StringBuilder out = new StringBuilder();
        while(!queue.isEmpty()) {
            out.append(queue.poll().getInput()).append("\n");
        }
        System.out.println(out);
    }
}

class GPTNumber implements Comparable<GPTNumber> {
    private String inputNumber;
    private int beforePoint = 0;
    private int afterPoint = 0;
    
    GPTNumber(String input) {
        inputNumber = input;
        setPoints();
    }

    public String getInput() {
        return inputNumber;
    }
    
    private void setPoints() {
        int idx = inputNumber.indexOf('.');
        if(idx == -1) {
            beforePoint = Integer.parseInt(inputNumber);
            afterPoint = 0;
        }
        else {
            beforePoint = Integer.parseInt(inputNumber.substring(0, idx));
            afterPoint = Integer.parseInt(inputNumber.substring(idx+1, inputNumber.length()));
        }
    }
    
    @Override
    public int compareTo(GPTNumber number) {
        if(this.beforePoint == number.beforePoint) {
            if(this.afterPoint == number.afterPoint) {
                return this.inputNumber.length() - number.inputNumber.length();
            }
            return this.afterPoint - number.afterPoint;
        }
        return this.beforePoint - number.beforePoint;
    }
}