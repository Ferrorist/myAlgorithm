import java.io.*;
import java.util.Arrays;
public class B10101 {
    private static BufferedReader in;
    private static int[] angles = new int[3];
    public static void main(String[] args) throws Exception {
        initReader();
        inputArguments();
        checkAngles();
    }
    
    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void inputArguments() throws Exception {
        for(int i = 0; i < angles.length; i++)
            angles[i] = Integer.parseInt(in.readLine());

        Arrays.sort(angles);
    }

    private static void checkAngles() {
        int sum = 0;
        boolean Equilateral = true;
        for(int angle : angles){
            sum += angle;
            if(Equilateral && angle != 60)  Equilateral = !(Equilateral);
        }

        if(sum != 180)     System.out.println("Error");
        else if(Equilateral) System.out.println("Equilateral");
        else if(angles[0] == angles[1] || angles[1] == angles[2])   System.out.println("Isosceles");
        else System.out.println("Scalene");
    }

}
