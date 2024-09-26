import java.io.*;
import java.util.*;
public class B1076 {
    private static BufferedReader in;
    private static String[] input;
    private static Map<String, Integer> values, multiples;
    public static void main(String[] args) throws Exception {
        initReader();
        initArguments();
        solve();
    }

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void initArguments() throws Exception {
        input = new String[3];
        for(int i = 0; i < 3; i++)  input[i] = in.readLine();

        values = new HashMap<>();
        multiples = new HashMap<>();

        String[] colors = {
            "black", "brown", "red", "orange", 
            "yellow", "green", "blue", "violet", 
            "grey", "white"};
        
        int v = 0, m = 1;
        for(String color : colors){
            values.put(color, v++); multiples.put(color, m);
            m *= 10;
        }
    }

    private static void solve() {
        long value = (values.get(input[0]) * 10 + values.get(input[1]));
        value *= multiples.get(input[2]);
        System.out.println(value);
    }
}
