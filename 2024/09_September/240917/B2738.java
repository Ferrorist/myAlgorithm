import java.io.*;
import java.util.StringTokenizer;
public class B2738 {
    static BufferedReader in;
    static int y, x;
    static int[][] A, B;
    public static void main(String[] args) throws Exception {
        initReader();
        initArguments();
        solve();
    }

    static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    static void initArguments() throws Exception {
        String[] input = in.readLine().split(" ");
        y = Integer.parseInt(input[0]);
        x = Integer.parseInt(input[1]);

        A = initArray();
        B = initArray();
    }

    static int[][] initArray() throws Exception {
        int[][] value = new int[y][x];

        for(int cy = 0; cy < y; cy++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int cx = 0; cx < x; cx++){
                value[cy][cx] = Integer.parseInt(st.nextToken());
            }
        }

        return value;
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();

        for(int cy = 0; cy < y; cy++) {
            for(int cx = 0; cx < x; cx++) {
                sb.append(A[cy][cx] + B[cy][cx]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
