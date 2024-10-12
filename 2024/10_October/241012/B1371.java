import java.io.*;
public class B1371 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] count = new int[26];
        String input = "";
        while((input = in.readLine()) != null && !input.isEmpty()) {
            for(int i = 0; i < input.length(); i++) {
                if(input.charAt(i) == ' ') continue;
                int idx = (int)(input.charAt(i) - 'a');
                count[idx]++;
            }
        }
        int answer = 0, answer_idx = count[0];
        for(int i = 1; i < count.length; i++) {
            if(answer_idx < count[i]) {
                answer = i; answer_idx = count[i];
            }
        }

        System.out.println((char)('a' + answer));
    }
}