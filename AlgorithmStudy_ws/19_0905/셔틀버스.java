import java.util.*;
/*
셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다.
*/

class Solution {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public String solution(int n, int t, int m, String[] timetable) {
        
        for (String time : timetable) {
            String[] hhmmStr = time.split(":");
            int hhmm = Integer.parseInt(hhmmStr[0]) * 60 + Integer.parseInt(hhmmStr[1]);
            pq.add(hhmm);
        }
        
        int nowBussArriveTime = 540;
        int lastKrewTime = 0;
        int boardingCount = 0;
        int myBoardingTime = 0;
        
        for (int i = 0; i < n; i++) {
            boardingCount = 0;
            
            while (!pq.isEmpty() && pq.peek() <= nowBussArriveTime) {
                boardingCount++;
                lastKrewTime = pq.poll();
                if (boardingCount == m)
                    break;
            }
            nowBussArriveTime += t;
        }
        
        // 다탐 일찍가야댐
        if (boardingCount == m) {
            myBoardingTime = lastKrewTime - 1;
        } else {
            myBoardingTime = nowBussArriveTime - t;
        }
        
        return (
            String.format("%02d", myBoardingTime / 60)
            + ":"
            + String.format("%02d", myBoardingTime % 60));
    }
}