package boj_20210323_위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

작업 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	6428	2861	2043	41.899%
문제
수행해야 할 작업 N개 (3 ≤ N ≤ 10000)가 있다. 
각각의 작업마다 걸리는 시간(1 ≤ 시간 ≤ 100)이 정수로 주어진다.

몇몇 작업들 사이에는 선행 관계라는 게 있어서, 
어떤 작업을 수행하기 위해 반드시 먼저 완료되어야 할 작업들이 있다. 
이 작업들은 번호가 아주 예쁘게 매겨져 있어서, 
K번 작업에 대해 선행 관계에 있는(즉, K번 작업을 시작하기 전에 반드시 먼저 완료되어야 하는) 작업들의 번호는 모두 1 이상 (K-1) 이하이다.
작업들 중에는, 그것에 대해 선행 관계에 있는 작업이 하나도 없는 작업이 반드시 하나 이상 존재한다. (1번 작업이 항상 그러하다)

모든 작업을 완료하기 위해 필요한 최소 시간을 구하여라. 
물론, 서로 선행 관계가 없는 작업들은 동시에 수행 가능하다.

입력
첫째 줄에 N이 주어진다.

두 번째 줄부터 N+1번째 줄까지 N개의 줄이 주어진다. 2번째 줄은 1번 작업, 3번째 줄은 2번 작업, ..., N+1번째 줄은 N번 작업을 각각 나타낸다. 
각 줄의 처음에는, 해당 작업에 걸리는 시간이 먼저 주어지고, 그 다음에 그 작업에 대해 선행 관계에 있는 작업들의 개수(0 ≤ 개수 ≤ 100)가 주어진다. 
그리고 선행 관계에 있는 작업들의 번호가 주어진다.

출력
첫째 줄에 모든 작업을 완료하기 위한 최소 시간을 출력한다.

예제 입력 1 
7
5 0
1 1 1
3 1 2
6 1 1
1 2 2 4
8 2 2 4
4 3 3 5 6
예제 출력 1 
23

*/

public class 작업_2056 {
	private static int N;
	private static int time[];
	private static int inDegree[];
	private static int result[];
	private static List<List<Integer>> graph = new ArrayList<List<Integer>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		time = new int[N+1];
		inDegree = new int[N+1];
		result = new int[N+1];
		
		int cnt = 0;
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		int arr[];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			result[i] = time[i];
			cnt = Integer.parseInt(st.nextToken());
			inDegree[i] += cnt;
			arr = new int[cnt];
			for(int j = 0; j < cnt; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
				graph.get(arr[j]).add(i);
			}
		}
		topologySort();
		print();
		
		br.close();
	}
	
	private static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				q.add(i);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(q.isEmpty()) return;
			
			int now = q.poll();
			for(int next : graph.get(now)) {
				inDegree[next]--;
				
				if(result[next] < result[now] + time[next]) {
					result[next] = result[now] + time[next];
				}
				
				if(inDegree[next] == 0) {
					q.add(next);
//					time[next] += time[now];
//					result[next] = result[now] + time[next];
				}
			}
		}
		int max = 0;
		
		for(int i : result) {
			if(max < i) max = i;
		}
		
		System.out.println(max);
		
	}
	
	private static void print() {
		for(int i = 1; i <= N; i++) {
			System.out.print(result[i] + " ");
		}
	}
}
