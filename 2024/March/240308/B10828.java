import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B10828 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cmd_count = Integer.parseInt(in.readLine());
        Deque<Integer> stack = new ArrayDeque(cmd_count);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < cmd_count; i++){
            String[] input = in.readLine().split(" ");
            switch(input[0]){
                case "push":
                    stack.push(Integer.parseInt(input[1]));
                    break;
                case "pop":
                    if(stack.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    int value = stack.isEmpty() ? 1 : 0;
                    sb.append(value).append("\n");
                    break;
                case "top":
                    if(stack.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(stack.peek()).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }
}