package study_210620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*

철로 출처다국어
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	3052	1090	799	39.109%
문제
집과 사무실을 통근하는 n명의 사람들이 있다. 
각 사람의 집과 사무실은 수평선 상에 있는 서로 다른 점에 위치하고 있다. 
임의의 두 사람 A, B에 대하여, A의 집 혹은 사무실의 위치가 B의 집 혹은 사무실의 위치와 같을 수 있다. 
통근을 하는 사람들의 편의를 위하여 일직선 상의 어떤 두 점을 잇는 철로를 건설하여, 기차를 운행하려고 한다. 
제한된 예산 때문에, 철로의 길이는 d로 정해져 있다. 
집과 사무실의 위치 모두 철로 선분에 포함되는 사람들의 수가 최대가 되도록, 철로 선분을 정하고자 한다.

양의 정수 d와 n 개의 정수쌍, (hi, oi), 1 ≤ i ≤ n,이 주어져 있다. 
여기서 hi와 oi는 사람 i의 집과 사무실의 위치이다. 
길이 d의 모든 선분 L에 대하여, 집과 사무실의 위치가 모두 L에 포함되는 사람들의 최대 수를 구하는 프로그램을 작성하시오.



그림 1. 8 명의 집과 사무실의 위치

그림 1 에 있는 예를 고려해보자. 
여기서 n = 8, 
(h1, o1) = (5, 40), 
(h2, o2) = (35, 25), 
(h3, o3) = (10, 20), 
(h4, o4) = (10, 25), 
(h5, o5) = (30, 50), 
(h6, o6) = (50, 60), 
(h7, o7) = (30, 25), 
(h8, o8) = (80, 100)이고, d = 30이다. 
이 예에서, 위치 10 과 40 사이의 빨간색 선분 L이, 가장 많은 사람들에 대하여 집과 사무실 위치 모두 포함되는 선분 중 하나이다. 
따라서 답은 4 이다.

입력
입력은 표준입력을 사용한다. 
첫 번째 줄에 사람 수를 나타내는 양의 정수 n (1 ≤ n ≤ 100,000)이 주어진다. 
다음 n개의 각 줄에 정수 쌍 (hi, oi)가 주어진다. 
여기서 hi와 oi는 −100,000,000이상, 100,000,000이하의 서로 다른 정수이다. 
마지막 줄에, 철로의 길이를 나타내는 정수 d (1 ≤ d ≤ 200,000,000)가 주어진다.

출력
출력은 표준출력을 사용한다. 
길이 d의 임의의 선분에 대하여, 집과 사무실 위치가 모두 그 선분에 포함되는 사람들의 최대 수를 한 줄에 출력한다. 

예제 입력 1 
8
5 40
35 25
10 20
10 25
30 50
50 60
30 25
80 100
30
예제 출력 1 
4
예제 입력 2 
4
20 80
70 30
35 65
40 60
10
예제 출력 2 
0
예제 입력 3 
5
-5 5
30 40
-5 5
50 40
5 -5
10
예제 출력 3 
3

*/

public class 철로_13334 {
	private static int N;
	private static int d;
	private static Rail[] railways;
	
	private static class Rail implements Comparable<Rail> {
		int start;
		int end;
		
		public Rail(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Rail o) {
			// TODO Auto-generated method stub
			return end - o.end;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		railways = new Rail[N];
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if (start > end) {
				int temp = end;
				end = start;
				start = temp;
			}
			
			railways[i] = new Rail(start, end);
		}
		
		d = Integer.parseInt(br.readLine());
	
		Arrays.sort(railways);
		
		System.out.println(solve());
		
		br.close();
	}
	
	private static int solve() {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		int max = -1;
		for (int i = 0; i < N; i++) {
			q.add(railways[i].start);
			
			while (!q.isEmpty() && q.peek() < railways[i].end - d) {
				q.poll();
			}
			
			max = Math.max(max, q.size());
		}
		
		return max;
	}
}
