package boj_20210520_복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import sun.java2d.pipe.BufferedBufImgOps;

/*

자동차경주 스페셜 저지출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	1120	276	226	36.868%
문제
자동차 경주로는 <그림 1>의 예와 같이 표현된다. 
화살표는 각 지점을 잇는 도로를 의미하며 모든 도로는 일방통행 도로로 화살표 방향으로만 움직일 수 있다.



자동차 경주의 코스는 1번 지점에서 출발하여 다시 1번 지점으로 되돌아오는 것이다. 
단, 중간에는 1번 지점을 지나서는 안 된다. 
경주로는 1번 지점을 제외한 어느 지점에서 출발하여도 1번 지점을 지나가지 않고서는 같은 지점으로 돌아올 수 없도록 되어 있다. 
또한 1번 지점에서 다른 모든 지점으로 갈 수 있고, 다른 모든 지점에서 1번 지점으로 갈 수 있다.

각 도로에는 <그림 2>의 예와 같이 그 도로를 지날 때 얻는 점수가 있다.



1번 지점에서 출발하여 가장 많은 점수를 얻어 다시 1번 지점으로 돌아오는 팀이 우승을 하게 된다. 
가장 많은 점수를 얻어 1번 지점으로 돌아오는 경로를 찾아 그 얻는 점수와 경로를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에는 지점의 개수 N이 주어진다. 
각 지점에는 1부터 N까지의 서로 다른 번호가 부여된다. 
둘째 줄에는 도로의 개수 M이 주어진다. 
이어 M개의 줄에는 p ,q ,r의 형식으로 도로의 정보가 주어지는데 이는 p번 지점부터 q번 지점으로 갈 수 있는 도로가 있고 그 도로에 부여된 점수가 r이라는 뜻이다. 
N은 1000이하의 자연수이고, p와 q는 1이상의 N이하의 자연수이며 r은 100이하의 자연수 이다. 
p와 q는 같지 않다.

출력
가장 많은 점수를 얻은 경로를 찾아, 첫째 줄에는 그 얻는 점수를 출력하고 둘째 줄에는 그 경로를 출력한다. 
경로를 출력할 때는 지나는 지점들의 번호를 사이에 한 칸의 공백을 두어 출력한다. 
출력하는 경로는 반드시 1번 지점에서 시작하여 1번 지점으로 끝나야 한다. 
만약 같은 점수를 얻는 경로가 둘 이상일 경우 그 중 하나만 출력하면 된다.

예제 입력 1 
8
13
1 2 5
1 3 4
2 5 2
2 6 1
3 6 3
5 6 7
5 8 9
6 8 3
4 1 6
6 4 8
7 4 5
6 7 2
8 7 4
예제 출력 1 
32
1 2 5 6 8 7 4 1

*/

public class 자동차경주_2611 {
	private static int N;
	private static int M;
	private static int[] inDegree;
	private static int[] cost;
	private static boolean[] isChecked;
	private static List<Integer> returnRoot = new ArrayList<Integer>();
	private static List<List<Node>> graph = new ArrayList<List<Node>>();
	private static StringBuilder sb = new StringBuilder();

	private static class Node {
		int child;
		int score;

		public Node(int child, int score) {
			this.child = child;
			this.score = score;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		inDegree = new int[N + 1];
		cost = new int[N + 1];
		isChecked = new boolean[N + 1];
		initGraph();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			inDegree[b]++;

			returnRoot = Arrays.asList(a, b, c);
		}
		
		int maxScore = topologySort();
		
		System.out.println(maxScore + "\n" + sb.toString());

		br.close();
	}

	private static int topologySort() {
		PriorityQueue<Node> q = new PriorityQueue<Node>(Collections.reverseOrder());
		q.add(new Node(1, 0));
		
		int maxScore = 0;

		while (!q.isEmpty()) {
			Node now = q.poll();
			if(!isChecked[now.child]) {
				sb.append(now).append(" ");
			}
			
			boolean check = false;

			for (Node next : graph.get(now.child)) {
				int nextChild = next.child;
				inDegree[nextChild]--;

				if (inDegree[nextChild] == 0) {
					q.add(new Node(nextChild, next.child));
					if(check) {
						isChecked[nextChild] = true;
						continue;
					}
					maxScore += next.score;
					check = true;
				}
			}
		}
		
		return maxScore;
	}

	private static void initGraph() {
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
	}
}
