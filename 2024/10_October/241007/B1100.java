import java.io.*;
public class B1100 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final int SIZE = 8;
    public static void main(String[] args) throws Exception {
        int answer = 0;
        for(int y = 0; y < SIZE; y++){
            String input = in.readLine();
            for(int x = y % 2; x < SIZE; x+=2) {
                if(input.charAt(x) == 'F') answer++;
            }
        }

        System.out.println(answer);
    }    
}
