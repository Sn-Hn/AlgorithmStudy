package boj_20210302_다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 미확인도착지_9370 {
	private static int T;
	private static int n, m, t;
	private static int s, g, h;
	private static List<List<Pair>> list;
	private static int distance[][];
	private static int INF = Integer.MAX_VALUE;
	private static StringBuilder sb = new StringBuilder();

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
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			list = new ArrayList<List<Pair>>();
			distance = new int[n + 1][n + 1];
			for (int j = 0; j <= n; j++) {
				list.add(new ArrayList<Pair>());
				Arrays.fill(distance[j], INF);
			}
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				list.get(a).add(new Pair(b, d));
				list.get(b).add(new Pair(a, d));
			}

			dijkstra(s);
			dijkstra(g);
			dijkstra(h);

			int m = distance[g][h];

			List<Integer> answer = new ArrayList<Integer>();

			for (int j = 0; j < t; j++) {
				int d = Integer.parseInt(br.readLine());
				int startG = distance[s][g] + m + distance[h][d];
				int startH = distance[s][h] + m + distance[g][d];
				int result = Math.min(startG, startH);
				if (result == distance[s][d]) {
					answer.add(d);
				}
			}
			Collections.sort(answer);
			for (int k : answer)
				sb.append(k + " ");
			sb.append("\n");

		}

		System.out.println(sb.toString());

		br.close();
	}

	private static void dijkstra(int start) {
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		boolean visited[] = new boolean[n + 1];
		distance[start][start] = 0;
		q.add(new Pair(start, 0));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int now = p.index;

			if (visited[now])
				continue;
			visited[now] = true;

			for (Pair pair : list.get(now)) {
				if (distance[start][pair.index] > distance[start][now] + pair.distance) {
					distance[start][pair.index] = distance[start][now] + pair.distance;
					q.add(new Pair(pair.index, distance[start][pair.index]));
				}
			}
		}
	}
}
