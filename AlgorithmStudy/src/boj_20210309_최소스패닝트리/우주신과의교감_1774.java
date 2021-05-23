package boj_20210309_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*

우주신과의 교감 성공출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	6312	1996	1238	29.226%
문제
황선자씨는 우주신과 교감을 할수 있는 채널러 이다. 하지만 우주신은 하나만 있는 것이 아니기때문에 황선자 씨는 매번 여럿의 우주신과 교감하느라 힘이 든다. 이러던 와중에 새로운 우주신들이 황선자씨를 이용하게 되었다.

하지만 위대한 우주신들은 바로 황선자씨와 연결될 필요가 없다. 이미 황선자씨와 혹은 이미 우주신끼리 교감할 수 있는 우주신들이 있기 때문에 새로운 우주신들은 그 우주신들을 거쳐서 황선자 씨와 교감을 할 수 있다.

우주신들과의 교감은 우주신들과 황선자씨 혹은 우주신들 끼리 이어진 정신적인 통로를 통해 이루어 진다. 하지만 우주신들과 교감하는 것은 힘든 일이기 때문에 황선자씨는 이런 통로들이 긴 것을  좋아하지 않는다. 왜냐하면 통로들이 길 수록 더 힘이 들기 때문이다.

또한 우리들은 3차원 좌표계로 나타낼 수 있는 세상에 살고 있지만 우주신들과 황선자씨는 2차원 좌표계로 나타낼 수 있는 세상에 살고 있다. 통로들의 길이는 2차원 좌표계상의 거리와 같다.

이미 황선자씨와 연결된, 혹은 우주신들과 연결된 통로들이 존재한다. 우리는 황선자 씨를 도와 아직 연결이 되지 않은 우주신들을 연결해 드려야 한다. 새로 만들어야 할 정신적인 통로의 길이들이 합이 최소가 되게 통로를 만들어 “빵상”을 외칠수 있게 도와주자.

입력
첫째 줄에 우주신들의 개수(N<=1,000) 이미 연결된 신들과의 통로의 개수(M<=1,000)가 주어진다.

두 번째 줄부터 N개의 줄에는 황선자를 포함하여 우주신들의 좌표가 (0<= X<=1,000,000), (0<=Y<=1,000,000)가 주어진다. 그 밑으로 M개의 줄에는 이미 연결된 통로가 주어진다. 번호는 위의 입력받은 좌표들의 순서라고 생각하면 된다. 좌표는 정수이다.

출력
첫째 줄에 만들어야 할 최소의 통로 길이를 출력하라. 출력은 소수점 둘째짜리까지 출력하여라.

예제 입력 1 
4 1
1 1
3 1
2 3
4 3
1 4
예제 출력 1 
4.00

*/

public class 우주신과의교감_1774 {
	private static int N, M;
	private static int parent[];
	private static List<Node> list = new ArrayList<Node>();
	private static PriorityQueue<Node> q = new PriorityQueue<Node>();

	private static class Node implements Comparable<Node> {
		int x, y;
		double distance;

		public Node(int x, int y, double distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (distance > o.distance)
				return 1;
			else
				return -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list.add(new Node(0, 0, 0));
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list.add(new Node(a, b, 0));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		allLines();

		solve();

		br.close();
	}

	private static void solve() {
		double cost = 0;
		int count = 0;
		while (!q.isEmpty() && count < N - 1) {
			Node node = q.poll();

			if (!findCycle(node.x, node.y)) {
				union(node.x, node.y);
				count++;
				cost += node.distance;
			}
		}

		System.out.println(String.format("%.2f", cost));
	}

	private static void allLines() {
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				Node nodeA = list.get(i);
				Node nodeB = list.get(j);

				int w = nodeA.x - nodeB.x;
				int h = nodeA.y - nodeB.y;

				double distance = Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2));
				q.add(new Node(i, j, distance));
			}
		}
	}

	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	private static boolean findCycle(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return true;
		return false;
	}
}
