/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/49994
 * 소요 시간 : 19분 23초
 */
class Solution {
    static int up_value = 8, down_value = 4, right_value = 2, left_value = 1;
    // 1 <= dirs.length() <= 500
    public int solution(String dirs) {
        int answer = 0;
        int[][] map = new int[11][11];
        
        int cur_y = 5, cur_x = 5; // (0 + 5, 0 + 5);
        
        char[] moves = dirs.toCharArray();
        for(char c : moves){
            int y = cur_y, x = cur_x;
            switch(c){
                case 'U':
                    y -= 1;
                    if(y < 0) continue;
                    if((map[cur_y][cur_x] & up_value) == 0){
                        map[cur_y][cur_x] += up_value;
                        map[y][x] += down_value;
                        answer++;
                    }
                    break;
                case 'D':
                    y += 1;
                    if(y > 10) continue;
                    if((map[cur_y][cur_x] & down_value) == 0){
                        map[cur_y][cur_x] += down_value;
                        map[y][x] += up_value;
                        answer++;
                    }
                    break;
                case 'R':
                    x += 1;
                    if(x > 10) continue;
                    if((map[cur_y][cur_x] & right_value) == 0){
                        map[cur_y][cur_x] += right_value;
                        map[y][x] += left_value;
                        answer++;
                    }
                    break;
                case 'L':
                    x -= 1;
                    if(x < 0) continue;
                    if((map[cur_y][cur_x] & left_value) == 0){
                        map[cur_y][cur_x] += left_value;
                        map[y][x] += right_value;
                        answer++;
                    }
                    break;
            }
            cur_y = y;  cur_x = x;
        }
        
        return answer;
    }
}