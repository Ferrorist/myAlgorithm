import java.io.*;
import java.util.*;
public class B12891 {
    private static BufferedReader in;
    private static String DNA;
    private static int targetLength;
    private static int[] counts;
    private static final char[] elements = {'A', 'C', 'G', 'T'};
    public static void main(String[] args) throws Exception {
        initReader();
        inputArguments();
        solve();
    }

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void inputArguments() throws Exception {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int DNA_length = Integer.parseInt(st.nextToken());
        targetLength = Integer.parseInt(st.nextToken());
        DNA = in.readLine();
        st = new StringTokenizer(in.readLine());
        counts = new int[4];
        for(int i = 0; i < counts.length; i++)    counts[i] = Integer.parseInt(st.nextToken());
    }

    private static void solve() {
        int answer = 0;
        int[] current = new int[4];
        for(int i = 0; i < targetLength-1; i++) {
            int index = Arrays.binarySearch(elements, DNA.charAt(i));
            if(index >= 0)  current[index]++;
        }

        int start = 0, end = targetLength - 1;
        while(end < DNA.length()) {
            current[Arrays.binarySearch(elements, DNA.charAt(end++))]++;
            if(checkArray(current))   answer++;
            current[Arrays.binarySearch(elements, DNA.charAt(start++))]--;
        }

        System.out.println(answer);
    }

    private static boolean checkArray(int[] a){
        if(a.length != counts.length)    return false;

        for(int i = 0 ; i < a.length; i++) {
            if(a[i] < counts[i]) return false;
        }

        return true;
    }
}