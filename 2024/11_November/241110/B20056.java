/*
 * https://www.acmicpc.net/problem/20056
 * 소요 시간 : 52분 37초
 */
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class B20056 {
    
    static class fireBall {
        int y, x;
        int weight, direction, speed;

        public fireBall(int y, int x, int weight, int direction, int speed) {
            this.y = y;
            this.x = x;
            this.weight = weight;
            this.direction = direction;
            this.speed = speed;
        }

        @Override
        public String toString() {
            return "y: " + y + ", x: " + x + ", weight: " + weight + ", direction: " + direction + ", speed: " + speed;
        }
    }

    private static final int[][] dir = 
        {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
        {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Queue<fireBall> fireBallQueue = new ArrayDeque<>();
    private static Map<Integer, List<fireBall>> fireBallMap = new HashMap<>();
    private static int mapLength, cmdCount;

    public static void main(String[] args) throws Exception {
        inputArguments();
        int totalWeight = solve();
        System.out.println(totalWeight);
    }

    private static void inputArguments() throws Exception {
        String[] input = in.readLine().split(" ");
        mapLength = Integer.parseInt(input[0]);
        int fireBallCount = Integer.parseInt(input[1]);
        cmdCount = Integer.parseInt(input[2]);

        for (int i = 0; i < fireBallCount; i++) {
            generateFireBall();
        }        
    }

    private static void generateFireBall() throws Exception {
        String[] input = in.readLine().split(" ");
        int y = Integer.parseInt(input[0]);
        int x = Integer.parseInt(input[1]);
        int weight = Integer.parseInt(input[2]);
        int speed = Integer.parseInt(input[3]);
        int direction = Integer.parseInt(input[4]);

        fireBallQueue.offer(new fireBall(y, x, weight, direction, speed));
    }

    private static int solve() {
        for (int i = 0; i < cmdCount; i++) {
            moveFireBalls();
            checkMapBlocks();
        }

        return getTotalWeight();
    }

    private static void moveFireBalls() {
        fireBallMap.clear();
        while(!fireBallQueue.isEmpty()) {
            moveFireBall(fireBallQueue.poll());
        }
    }

    private static void moveFireBall(fireBall ball) {
        fireBall moveBall = ball;
        int cy = moveBall.y, cx = moveBall.x;

        int dy = moveResult(cy, dir[moveBall.direction][0] * moveBall.speed);
        int dx = moveResult(cx, dir[moveBall.direction][1] * moveBall.speed);

        moveBall.y = dy;
        moveBall.x = dx;

        int idx = moveBall.y * mapLength + moveBall.x;
        List<fireBall> list = fireBallMap.getOrDefault(idx, new ArrayList<>());
        list.add(moveBall);
        fireBallMap.put(idx, list);        
    }

    private static int moveResult(int value, int distance) {
        int result = value + distance;

        while (!(1 <= result && result <= mapLength)) {
            if (result > mapLength) {
                result = result % mapLength;
            } 
            else if (result < 1) {
                result += mapLength;
            }
        }
        
        return result;
    }

    private static void checkMapBlocks() {
        for (Map.Entry<Integer, List<fireBall>> entry : fireBallMap.entrySet()) {
            List<fireBall> list = entry.getValue();
            if (list.size() > 1) {
                dividedBalls(list);
            } else {
                fireBallQueue.offer(list.get(0));
            }
        }
    }

    private static void dividedBalls(List<fireBall> list) {
        int y = list.get(0).y, x = list.get(0).x;
        int resultWeight, resultSpeed;
        double doubledWeight = 0, doubledSpeed = 0;
        int startDirection = equalsOddsOrEvens(list) ? 0 : 1;

        for (fireBall ball : list) {
            doubledWeight += ball.weight;
            doubledSpeed += ball.speed;
        }

        resultWeight = (int)Math.floor(doubledWeight / 5);
        resultSpeed = (int)Math.floor(doubledSpeed / list.size());

        if (resultWeight > 0) {
            for (int i = startDirection; i < 8; i+=2) {
                fireBall newBall = new fireBall(y, x, resultWeight, i, resultSpeed);
                fireBallQueue.offer(newBall);
            }
        }
    }

    private static boolean equalsOddsOrEvens(List<fireBall> list) {
        int d = list.get(0).direction % 2;

        for (int i = 1; i < list.size(); i++) {
            if (d != list.get(i).direction % 2) {
                return false;
            }
        }

        return true;
    }

    private static int getTotalWeight() {
        int result = 0;

        while (!fireBallQueue.isEmpty()) {
            result += fireBallQueue.poll().weight;
        }

        return result;
    }
}
