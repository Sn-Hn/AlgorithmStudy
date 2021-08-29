package study_210829;
/*

시간 관리하기 출처다국어
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	412	202	173	51.032%
문제
성실한 농부 존은 시간을 효율적으로 관리해야 한다는 걸 깨달았다. 
그는 N개의 해야할 일에 (1<=N<=1000) 숫자를 매겼다. (우유를 짜고, 마굿간을 치우고, 담장을 고치는 등의)

존의 시간을 효율적으로 관리하기 위해, 그는 끝내야만 하는 일 목록을 만들었다. 
완성될 때 필요한 시간을 T_i(1<=T_i<=1,000) 라고 하며, 끝내야하는 시간을 S_i(1<=S_i<=1,000,000) 이라 한다. 
농부 존은 하루의 시작을 t = 0으로 정했다. 그리고 일 할 때는 그 일을 마칠 때 까지 그 일만 한다. 

존은 늦잠 자는 걸 좋아한다. 
따라서 제 시간에 끝낼 수 있게 결정할 수 있는 한도에서 존이 가장 늦게 일어나도 되는 시간을 출력하라.

입력
첫 줄에는 일의 개수인 N을 받고

두 번째 줄부터 N+1줄까지 T_i와 S_i를 입력받는다. 

출력
존이 일을 할 수 있는 마지막 시간을 출력 하라. 
존이 제시간에 일을 끝낼 수 없다면 -1 을 출력하라.

예제 입력 1 
4
3 5
8 14
5 20
1 16
예제 출력 1 
2

7
2 15
3 15
5 15
5 15
5 30
5 30
5 30

4
3 15
4 15
5 21
8 27

3
3 15
4 15
5 16

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 시간관리하기_6068 {
	private static int N;
	private static PriorityQueue<Time> times = new PriorityQueue<Time>();
	private static int awakeTime = Integer.MAX_VALUE;
	
	private static class Time implements Comparable<Time> {
		int need;
		int end;
		
		public Time(int need, int end) {
			this.need = need;
			this.end = end;
		}
		
		@Override
		public int compareTo(Time o) {
			// TODO Auto-generated method stub
			return end - o.end;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int need = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			times.add(new Time(need, end));
		}
		
		if (isAwakeSleep()) {
			System.out.println(awakeTime);
		}else {
			System.out.println(-1);
		}
		
		br.close();
	}
	
	private static boolean isAwakeSleep() {
		int nowTime = 0;
		
		while (!times.isEmpty()) {
			Time time = times.poll();
			
			nowTime += time.need;
			System.out.println("awakeTime : " + awakeTime);
			
			if (nowTime > time.end) {
				return false;
			}
			
			awakeTime = Math.min(awakeTime, time.end - nowTime);
		}
		
		return true;
	}
}
