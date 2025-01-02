/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/17686
 */
import java.util.*;
class Solution {
    
    class File implements Comparable<File> {
        int idx;
        String head, number;
        
        File (int idx, String head, String number) {
            this.idx = idx;
            this.head = head;
            this.number = number;
        }
        
        
        public void setIdx(int idx) {
            this.idx = idx;
        }
        
        private int compareHead(File o) {
            String fileUppercase = this.head.toUpperCase();
            String compareUppercase = o.head.toUpperCase();
            return fileUppercase.compareTo(compareUppercase);
        }
        
        private int compareTail(File o) {
            return Integer.parseInt(this.number) - Integer.parseInt(o.number);
        }
        
        @Override
        public int compareTo(File o) {
            int h = compareHead(o), t = compareTail(o);
            if(h != 0) return h;
            else if(t != 0) return t;
            else return this.idx - o.idx;
        }
    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        Queue<File> queue = new PriorityQueue<>();
        
        for(int i = 0; i < files.length; i++) {
            queue.offer(generateFile(i, files[i]));   
        }
        
        for(int i = 0; i < files.length; i++) {
            answer[i] = files[queue.poll().idx];
        }
        
        
        return answer;
    }
    
    public File generateFile(int idx, String name) {
        int headIdx = findHEADIdx(name);
        String head = name.substring(0, headIdx);
        String number = getNumber(name, headIdx);
        
        return new File(idx, head, number);
    }
    
    public int findHEADIdx(String name) {
        int idx = 0;
        while (idx < name.length()) {
            char c = name.charAt(idx);
            if(c >= '0' && c <= '9') break;
            else idx++;
        }
        
        return idx;
    }
    
    public String getNumber(String name, int startIdx) {
        int endIdx = startIdx;
        for(int i = 0; i < 5; i++) {
            if(name.length() <= startIdx + i) break;
            char c = name.charAt(startIdx + i);
            
            if((int)(c - '0') >= 0 && (int)(c - '0') <= 9) {
                endIdx = startIdx + i;
            }
            else break;
        }
        
        return name.substring(startIdx, endIdx + 1);
    }
}