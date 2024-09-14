/*
 * https://www.acmicpc.net/problem/14891
 * 소요 시간 : 55분 05초
 */
import java.io.*;
import java.util.*;
class B14891 {
    static String[] gear;
    static BufferedReader in;
    static final char S = '1';
    static final int clockwise = 1;
    public static void main(String[] args) throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));

        initGear();
        
        int spin_count = Integer.parseInt(in.readLine());
        StringTokenizer st;
        for(int i = 0; i < spin_count; i++){
            st = new StringTokenizer(in.readLine());
            int target = Integer.parseInt(st.nextToken());
            int spin_dir = Integer.parseInt(st.nextToken());

            spinGear(target - 1, spin_dir, true, true);
        }

        System.out.println(getScore());
    }

    static void initGear() throws Exception {
        gear = new String[4];
        for(int i = 0; i < 4; i++)  gear[i] = in.readLine();
    }

    static void spinGear(int gearIndex, int spinDir, boolean checkleft, boolean checkright) {
        // 왼쪽 확인
        while(checkleft && gearIndex - 1 >= 0){
            if(gear[gearIndex].charAt(6) != gear[gearIndex-1].charAt(2)){
                spinGear(gearIndex - 1, spinDir * (-1), true, false);
            }
            break;
        }

        // 오른쪽 확인
        while(checkright && gearIndex + 1 <= 3){
            if(gear[gearIndex].charAt(2) != gear[gearIndex+1].charAt(6)){
                spinGear(gearIndex + 1, spinDir * (-1), false, true);
            }
            break;
        }

        if(spinDir == clockwise){
            gear[gearIndex] = Character.toString(gear[gearIndex].charAt(7)) + gear[gearIndex].substring(0, 7);
        }
        else{
            gear[gearIndex] = gear[gearIndex].substring(1, 8) + Character.toString(gear[gearIndex].charAt(0));
        }
    }

    static int getScore() {
        int score = 0;
        for(int i = 3; i >= 0; i--){
            if(gear[i].charAt(0) == S)  score += (1 << i);
        }

        return score;
    }
}