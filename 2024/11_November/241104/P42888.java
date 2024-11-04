/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42888
 * 소요 시간 : 14분 10초
 */
import java.util.*;
class Solution {
    private class Log {
        String userId;
        boolean isEntered;
        
        Log (String userId, boolean isEntered) {
            this.userId = userId;
            this.isEntered = isEntered;
        }
    }
    
    List<Log> roomLogs;
    Map<String, String> userNicknames;
    
    public String[] solution(String[] records) {
        String[] answer = {};
        roomLogs = new ArrayList<>();
        userNicknames = new HashMap<>();
        
        for (String record : records) {
            String[] splitRecord = record.split(" ");
            String commend = splitRecord[0];
            String userId = splitRecord[1];
            
            if ("Leave".equals(commend)) {
                recodeLeaveLogs(userId);
                
            } else {
                if ("Enter".equals(commend)) {
                    recodeEnterLogs(userId);
                }
                String nickname = splitRecord[2];
                setNickname(userId, nickname);
            }
        }
        
        return systemLogs();
    }
    
    private void recodeEnterLogs(String userId) {
        roomLogs.add(new Log(userId, true));
    }
    
    private void recodeLeaveLogs(String userId) {
        roomLogs.add(new Log(userId, false));
    }
    
    private void setNickname(String userId, String nickName) {
        userNicknames.put(userId, nickName);
    }
    
    private String[] systemLogs() {
        List<String> systemlogs = new ArrayList<>();
        
        for (Log log : roomLogs) {
            String nickname = userNicknames.get(log.userId);
            if (log.isEntered) {
                systemlogs.add(nickname + "님이 들어왔습니다.");
            } else {
                systemlogs.add(nickname + "님이 나갔습니다.");
            }
        }
        
        return systemlogs.toArray(new String[systemlogs.size()]);
    }
}