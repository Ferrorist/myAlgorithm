/*
 * https://www.acmicpc.net/problem/3190
 * 소요 시간 : 37분 29초
 */
import java.io.*;
import java.util.*;
public class B3190 {
    static class SnakeClass {
        int y, x;
        int direction;

        public SnakeClass(int y, int x, int direction){
            this.y = y;
            this.x = x;
            this.direction = direction;
        }
    }

    static class ChangeDirection {
        int second;
        char LD;

        public ChangeDirection(int second, char LD){
            this.second = second;
            this.LD = LD;
        }
    }


    // (y, x) , 우 하 좌 상
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final int EMPTY = 0, SNAKE = 1, APPLE = 2;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // 보드의 크기 ( 2 <= N <= 100 )
        int N = Integer.parseInt(in.readLine());
        int[][] board = new int[N+1][N+1];

        // 사과의 개수 ( 0 <= apple_count <= 100 )
        int apple_count = Integer.parseInt(in.readLine());

        String[] input;
        for(int i = 0; i < apple_count; i++){
            input = in.readLine().split(" ");
            int y = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            board[y][x] = APPLE;
        }

        // 뱀의 방향 변환 횟수
        int change_dir = Integer.parseInt(in.readLine());
        Queue<ChangeDirection> Change_queue = new ArrayDeque<>();
        
        for(int i = 0; i < change_dir; i++){
            input = in.readLine().split(" ");
            int second = Integer.parseInt(input[0]);
            char LD = input[1].charAt(0);
            Change_queue.offer(new ChangeDirection(second, LD));
        }

        Deque<SnakeClass> Game_queue = new ArrayDeque<>(); // 뱀
        board[1][1] = SNAKE;
        Game_queue.offerFirst(new SnakeClass(1, 1, 0));

        int answer = 0;
        

        // 게임 진행
        GameStart:
        while(true){
            answer++;
            SnakeClass head = Game_queue.peekFirst();
            
            int cdir = head.direction;
            int cy = head.y + dir[cdir][0];
            int cx = head.x + dir[cdir][1];

            // 벽과 부딪힘
            if(cy <= 0 || cx <= 0 || cy > N || cx > N)  break GameStart;
            else if(board[cy][cx] == SNAKE) break GameStart; // 몸과 부딪힘
            else if(board[cy][cx] == EMPTY){ // 사과가 없으므로 꼬리 위치 칸 비워주기
                SnakeClass tail = Game_queue.pollLast();
                board[tail.y][tail.x] = EMPTY;
            }
            
            if(!Change_queue.isEmpty() && Change_queue.peek().second == answer){
                char cLD = Change_queue.poll().LD;
                switch(cLD){
                    case 'L':
                        cdir = (cdir + dir.length - 1) % dir.length;
                        break;
                    case 'D':
                        cdir = (cdir + 1) % dir.length;
                        break;
                }
            }

            board[cy][cx] = SNAKE;
            Game_queue.offerFirst(new SnakeClass(cy, cx, cdir));
        }
        
        System.out.println(answer);
    }
}
