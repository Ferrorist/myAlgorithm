/*
 * https://www.acmicpc.net/problem/1032
 */
import java.io.*;
class B1032 {
    private static BufferedReader in;
    private static String[] files;
    public static void main(String[] args) throws Exception {
        initReader();
        inputArguments();
        solve();
    }

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void inputArguments() throws Exception {
        int N = Integer.parseInt(in.readLine());
        files = new String[N];

        for(int i = 0; i < N; i++)  files[i] = in.readLine();
    }

    private static void solve() {
        String current = new String(files[files.length - 1]);

        for(int i = files.length - 2; i >= 0; i--){
            if(files[i].equals(files[i+1])) continue;
            else current = maskString(current, files[i]);
        }

        System.out.println(current);
    }

    private static String maskString(String a, String b){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) sb.append("?");
            else sb.append(a.charAt(i));
        }

        return sb.toString();
    }
}