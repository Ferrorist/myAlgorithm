/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/87377
 * 소요 시간 : 1시간 5분 54초
 */
import java.util.*;
class Solution {
    private class Spot implements Comparable<Spot>{
        long x, y;
        
        Spot(long x, long y){
            this.x = x; this.y = y;
        }
        
        boolean equals(Spot o){
            return (this.x == o.x && this.y == o.y);
        }
        
        @Override
        public int compareTo(Spot o){
            if(this.y == o.y)   return (this.x == o.x) ? 0 : (this.x > o.x) ? 1 : -1;
            return (this.y == o.y) ? 0 : (this.y < o.y) ? 1 : -1;
        }
    }
    
    
    long minX, minY, maxX, maxY;
    public String[] solution(int[][] line) {
        initStats();
        Set<Spot> spotSet = findSpots(line);
        Queue<Spot> PrioritySpotQueue = adjustSpots(spotSet);
        List<String> graphList = getGraphs(PrioritySpotQueue);
        
        String[] answer = new String[graphList.size()];
        for(int i = 0; i < graphList.size(); i++)   answer[i] = graphList.get(i);
        return answer;
    }
    
    // line[i] => [A, B, C] => [Ax + By + C = 0]
    private Set<Spot> findSpots(int[][] line) {
        Set<Spot> returnSet = new HashSet<>();
        
        for(int i = 0; i < line.length - 1; i++){
            long A = line[i][0], B = line[i][1], E = line[i][2];
            for(int j = i+1; j < line.length; j++){
                long C = line[j][0], D = line[j][1], F = line[j][2];
                double 분모 = (double)(A*D - B*C);
                if(분모 == 0) continue;
                double SpotX = (B*F - E*D) / 분모, SpotY = (E*C - A*F) / 분모;
                
                if(!isInt(SpotX) || !isInt(SpotY)) continue;
                
                long SpotLX = (long)SpotX, SpotLY = (long)SpotY;
                
                returnSet.add(new Spot(SpotLX, SpotLY));
                
                minX = Math.min(minX, SpotLX);  minY = Math.min(minY, SpotLY);
                maxX = Math.max(maxX, SpotLX);  maxY = Math.max(maxY, SpotLY);
            }
        }
        
        return returnSet;
    }
    
    private boolean isInt(double d){
        return (d - Math.floor(d)) == 0;
    }
    
    private void initStats(){
        minX = minY = Long.MAX_VALUE;
        maxX = maxY = Long.MIN_VALUE;
    }
    
    private Queue<Spot> adjustSpots(Set<Spot> set){
        Queue<Spot> queue = new PriorityQueue<>();
        
        long adjustX = -minX, adjustY = -minY;
        maxX += adjustX;    maxY += adjustY;
        
        Iterator<Spot> iterator = set.iterator();
        while(iterator.hasNext()){
            Spot spot = iterator.next();
            spot.x += adjustX;  spot.y += adjustY;
            queue.offer(spot);
        }
        
        return queue;
    }
    
    private List<String> getGraphs(Queue<Spot> queue){
        List<String> list = new ArrayList<>();

        for(long y = 0; y <= maxY; y++){
            StringBuilder sb = new StringBuilder();
            for(long x = 0; x <= maxX; x++){    sb.append(".");
            }
            list.add(sb.toString());
        }
        
        while(!queue.isEmpty()){
            Spot spot = queue.poll();
            spot.y = maxY - spot.y;
            StringBuilder sb = new StringBuilder(list.get((int)spot.y));
            
            sb.setCharAt((int)spot.x, '*');
            list.set((int)spot.y, sb.toString());
        }
        
        return list;
    }
    
    private void printMinMax() {
        System.out.println("minX: " + minX + ", minY: " + minY);
        System.out.println("maxX: " + maxX + ", maxY: " + maxY);
    }
}