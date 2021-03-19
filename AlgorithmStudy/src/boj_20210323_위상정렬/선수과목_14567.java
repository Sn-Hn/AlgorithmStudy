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

선수과목 (Prerequisite) 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
5 초	256 MB	676	459	360	70.450%
문제
올해 Z대학 컴퓨터공학부에 새로 입학한 민욱이는 학부에 개설된 모든 전공과목을 듣고 졸업하려는 원대한 목표를 세웠다. 
어떤 과목들은 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수할 수 있게 되어 있다. 
공학인증을 포기할 수 없는 불쌍한 민욱이는 선수과목 조건을 반드시 지켜야만 한다. 
민욱이는 선수과목 조건을 지킬 경우 각각의 전공과목을 언제 이수할 수 있는지 궁금해졌다. 
계산을 편리하게 하기 위해 아래와 같이 조건을 간소화하여 계산하기로 하였다.

한 학기에 들을 수 있는 과목 수에는 제한이 없다.
모든 과목은 매 학기 항상 개설된다.
모든 과목에 대해 각 과목을 이수하려면 최소 몇 학기가 걸리는지 계산하는 프로그램을 작성하여라.

입력
첫 번째 줄에 과목의 수 N(1≤N≤1000)과 선수 조건의 수 M(0≤M≤500000)이 주어진다. 
선수과목 조건은 M개의 줄에 걸쳐 한 줄에 정수 A B 형태로 주어진다.
A번 과목이 B번 과목의 선수과목이다.
A<B인 입력만 주어진다. (1≤A<B≤N)

출력
1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있는지를 한 줄에 공백으로 구분하여 출력한다.

예제 입력 1 
3 2
2 3
1 2
예제 출력 1 
1 2 3
예제 입력 2 
6 4
1 2
1 3
2 5
4 5
예제 출력 2 
1 2 2 1 3 1

*/

public class 선수과목_14567 {
	private static int N, M;
	private static int inDegree[];
	private static int result[];
	
	private static List<List<Integer>> graph = new ArrayList<List<Integer>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inDegree = new int[N+1];
		result = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			inDegree[b]++;
		}
		
		topologySort();
		
		br.close();
	}
	
	private static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				q.add(i);
			}
			result[i]++;
		}
		
		for(int i = 1; i <= N; i++) {
			
			if(q.isEmpty()) {
				return;
			}
			
			int now = q.poll();
			
			// 순서
			// result[i] = now;
			
			for(int next : graph.get(now)) {
				inDegree[next]--;
				
				if(inDegree[next] == 0) {
					q.add(next);
					// 이전 선수과목에 +1
					result[next] = result[now] + 1;
				}
			}
		}
		
		print();
	}
	
	private static void print() {
		for(int i = 1; i <= N; i++) {
			System.out.print(result[i] + " ");
		}
	}
}
