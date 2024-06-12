/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12980
 * 소요 시간 : 
 */
public class Solution {
    public int solution(int n) {
        return Integer.bitCount(n);
    }
}

/*
 * 한 번에 K 칸 앞으로 점프 
 * or 
 * 현재까지 온 거리 * 2 에 해당하는 위치로 
 * 순간이동
 * 
 * 순간이동 -> 건전지 사용량 줄지 않음.
 * K 칸 점프 -> K 만큼 건전지 사용량 발생
 * 순간 이동이 더 효율적
 * 
 * 아이언 슈트 착용하고 N 만큼 떨어져 있는 장소로 가려함.
 * 건전지 사용량 줄이기 위해 점프로 이동하는 것 최소
 * 
 * 사용해야 하는 건전지 사용량의 최솟값
 * 
 * 순간 이동하면 현재까지 온 거리 * 2가 된다?
 * 
 * 깊이 우선 탐색? DP?
 * 
 * 6 -> 2
 * 0 -> 1 (점프. 0 + 1) -> 2 (순간이동. 1*2) -> 3 (점프. 2 + 1) -> 6 (순간이동. 3 * 2)
 * N : 10억 이하의 자연수
 * K : 1 이상의 자연수
 * 5 -> 101
 * 6 -> 110
 * 5000 -> 5
 */