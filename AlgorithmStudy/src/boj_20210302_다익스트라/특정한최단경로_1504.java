package boj_20210302_다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*

특정한 최단 경로 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	27707	7238	4669	24.270%
문제
방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 
또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다.
하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 
1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 
둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 
다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1)

출력
첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.

예제 입력 1 
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
예제 출력 1 
7

4 6
1 2 3
1 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3

7 7
1 2 3
3 2 5
1 3 1
6 5 3
7 5 8
5 4 2
6 4 3
2 6

4 5
1 2 3
1 3 1
1 4 1
2 3 3
3 4 4
2 3

3 3
1 3 20 
1 2 15
2 3 6 
1 3

*/

public class 특정한최단경로_1504 {
	private static int N, E;
	private static List<List<Pair>> list;
	private static int distance[][];
	private static int INF = Integer.MAX_VALUE;
	private static boolean visited[][];
	private static int v1, v2;

	private static class Pair implements Comparable<Pair> {
		int index, distance;

		public Pair(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return distance - o.distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		distance = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		list = new ArrayList<List<Pair>>();

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Pair>());
			Arrays.fill(distance[i], INF);
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.get(a).add(new Pair(b, c));
			list.get(b).add(new Pair(a, c));
		}

		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			distance[i][i] = 0;
			if (i == 1 || i == v1 || i == v2) {
				solve(i);
			}
		}

		int m = Math.min(distance[v1][v2], distance[v2][v1]);
		int a = distance[1][v1] + m + distance[v2][N];
		int b = distance[1][v2] + m + distance[v1][N];

		if (m == INF || (a == INF && b == INF) || (a < 0 && b < 0)) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(a, b));
		}

		print();
		System.out.println(a + " " + b + " " + m);

		br.close();
	}

	private static void solve(int s) {
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();

		q.add(new Pair(s, 0));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int now = p.index;

			if (visited[s][now])
				continue;
			visited[s][now] = true;

			for (Pair pair : list.get(now)) {
				if (distance[s][pair.index] > distance[s][now] + pair.distance) {
					distance[s][pair.index] = distance[s][now] + pair.distance;
					q.add(new Pair(pair.index, distance[s][pair.index]));
				}
			}

		}

	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(distance[i][j] + " ");
			}
			System.out.println();
		}
	}
}
