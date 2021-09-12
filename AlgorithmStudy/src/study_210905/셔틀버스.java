package study_210905;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

[1차] 셔틀버스
문제 설명
셔틀버스
카카오에서는 무료 셔틀버스를 운행하기 때문에 판교역에서 편하게 사무실로 올 수 있다. 카카오의 직원은 서로를 '크루'라고 부르는데, 아침마다 많은 크루들이 이 셔틀을 이용하여 출근한다.

이 문제에서는 편의를 위해 셔틀은 다음과 같은 규칙으로 운행한다고 가정하자.

셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다.
셔틀은 도착했을 때 도착한 순간에 대기열에 선 크루까지 포함해서 대기 순서대로 태우고 바로 출발한다. 예를 들어 09:00에 도착한 셔틀은 자리가 있다면 09:00에 줄을 선 크루도 탈 수 있다.
일찍 나와서 셔틀을 기다리는 것이 귀찮았던 콘은, 일주일간의 집요한 관찰 끝에 어떤 크루가 몇 시에 셔틀 대기열에 도착하는지 알아냈다. 콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각을 구하여라.

단, 콘은 게으르기 때문에 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다. 또한, 모든 크루는 잠을 자야 하므로 23:59에 집에 돌아간다. 따라서 어떤 크루도 다음날 셔틀을 타는 일은 없다.

입력 형식
셔틀 운행 횟수 n, 셔틀 운행 간격 t, 한 셔틀에 탈 수 있는 최대 크루 수 m, 크루가 대기열에 도착하는 시각을 모은 배열 timetable이 입력으로 주어진다.

0 ＜ n ≦ 10
0 ＜ t ≦ 60
0 ＜ m ≦ 45
timetable은 최소 길이 1이고 최대 길이 2000인 배열로, 하루 동안 크루가 대기열에 도착하는 시각이 HH:MM 형식으로 이루어져 있다.
크루의 도착 시각 HH:MM은 00:01에서 23:59 사이이다.
출력 형식
콘이 무사히 셔틀을 타고 사무실로 갈 수 있는 제일 늦은 도착 시각을 출력한다. 도착 시각은 HH:MM 형식이며, 00:00에서 23:59 사이의 값이 될 수 있다.

입출력 예제
n	t	m	timetable	answer
1	1	5	["08:00", "08:01", "08:02", "08:03"]	"09:00"
2	10	2	["09:10", "09:09", "08:00"]	"09:09"
2	1	2	["09:00", "09:00", "09:00", "09:00"]	"08:59"
1	1	5	["00:01", "00:01", "00:01", "00:01", "00:01"]	"00:00"
1	1	1	["23:59"]	"09:00"
10	60	45	["23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"]	"18:00"

*/

// https://programmers.co.kr/learn/courses/30/lessons/17678
public class 셔틀버스 {
	private static final int FIRST_BUS = 540;
	public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int cnt = timetable.length;
        int[] crewArrivalTime = new int[cnt];
        int[] buses = new int[n];
        for (int i = 0; i < cnt; i++) {
        	crewArrivalTime[i] = timesToMinutes(timetable[i]);
        }
        
        Arrays.sort(crewArrivalTime);
        buses[0] = FIRST_BUS;
        for (int i = 1; i < n; i++) {
        	buses[i] = buses[i - 1] + t;
        }
        
        answer = attend(crewArrivalTime, buses, m);
        
        return answer;
    }
	
	private static String attend(int[] crew, int[] buses, int m) {
		int size = 0;
		int[] lastTime = new int[buses.length];
		int busCnt = buses.length - 1;
		int cnt = 0;
		
		LOOP:
		for (int i = 0; i < crew.length; i++) {
			if (cnt == busCnt + 1) {
				break;
			}
			
			while (buses[cnt] < crew[i]) {
				cnt ++;
				size = 0;
				
				if (cnt == busCnt + 1) {
                    break LOOP;
                }
			}
			
			size ++;
			if (size == m) {
				lastTime[cnt] = crew[i];
				cnt ++;
				size = 0;
			}
		}
		int lastArrivalMinutesTime = lastTime[busCnt] == 0 ? buses[busCnt] : lastTime[busCnt] - 1;
		
		print(lastTime);
		System.out.println();
		
		return minutesToTimes(lastArrivalMinutesTime);
	}
	
	private static int timesToMinutes(String time) {
		String[] minuteTime = time.split(":");
		
		return Integer.parseInt(minuteTime[0]) * 60 + Integer.parseInt(minuteTime[1]);
	}
	
	private static String minutesToTimes(int minutes) {
		int hour = minutes / 60;
		int minute = minutes % 60;
		
		String ho = hour < 10 ? "0" + hour : "" + hour;
		String mi = minute < 10 ? "0" + minute : "" + minute;
		
		return ho + ":" + mi;
	}
	
	private static void print(int[] lastTime) {
		for (int i = 0; i < lastTime.length; i++) {
			System.out.print(lastTime[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		int n = 2;
		int t = 1;
		int m = 2;
		String[] timetable = {"09:01", "09:01", "09:01", "09:01"};
		
		System.out.println(new 셔틀버스().solution(n, t, m, timetable));
	}
}
