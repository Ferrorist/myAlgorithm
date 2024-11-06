/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12987
 * 소요 시간 : 46분 6초
 */
import java.util.*;
class Solution {
    class Node implements Comparable<Node> {
        int valueA;
        int winCount;
        
        Node(int valueA, int winCount) {
            this.valueA = valueA;
            this.winCount = winCount;
        }
        
        @Override
        public int compareTo(Node o) {
            if (this.winCount == o.winCount) {
                return o.valueA - this.valueA;
            }
            return this.winCount - o.winCount;
        }
        
        @Override
        public String toString() {
            return "valueA: " + this.valueA + ", winCount: " + this.winCount;
        }
            
    }
    
    public int solution(final int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(B);
        List<Node> nodeList = new ArrayList<>();
        for (int target : A) {
            if(Arrays.binarySearch(B, target) >= 0) {
                nodeList.add(new Node(target, B.length - upperBound(B, target)));
            }
            else {
                nodeList.add(new Node(target, B.length + 1 + Arrays.binarySearch(B, target)));
            }
        }
        
        Collections.sort(nodeList);
        
        Queue<Integer> queueB = new ArrayDeque<>();
        for (int i = B.length - 1; i >= 0; i--) {
            queueB.offer(B[i]);
        }
        
        for(Node node : nodeList) {
            int valueB = queueB.peek();
            if(node.valueA < valueB) {
                queueB.poll();
                answer++;
            }
        }
    
        return answer;
    }
    
    private int upperBound(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;
        
        while(start < end) {
            int mid = (start + end) >>> 1;
            
            if(array[mid] <= target) {
                start = mid + 1;
            }
            else end = mid;
        }
        
        return start;
    }
}