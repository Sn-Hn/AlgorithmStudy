package boj_20210420_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

양 구출 작전 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	2527	713	522	28.370%
문제
N개의 섬으로 이루어진 나라가 있습니다. 섬들은 1번 섬부터 N번 섬까지 있습니다.

1번 섬에는 구명보트만 있고 다른 섬에는 양들 또는 늑대들이 살고 있습니다.

늘어나는 늑대의 개체 수를 감당할 수 없던 양들은 구명보트를 타고 늑대가 없는 나라로 이주하기로 했습니다.

각 섬에서 1번 섬으로 가는 경로는 유일하며 i번 섬에는 pi번 섬으로 가는 다리가 있습니다. 

양들은 1번 섬으로 가는 경로로 이동하며 늑대들은 원래 있는 섬에서 움직이지 않고 섬으로 들어온 양들을 잡아먹습니다. 늑대는 날렵하기 때문에 섬에 들어온 양을 항상 잡을 수 있습니다. 그리고 늑대 한 마리는 최대 한 마리의 양만 잡아먹습니다.

얼마나 많은 양이 1번 섬에 도달할 수 있을까요?

입력
첫 번째 줄에 섬의 개수 N (2 ≤ N ≤ 123,456) 이 주어집니다.

두 번째 줄부터 N-1개에 줄에 2번 섬부터 N번 섬까지 섬의 정보를 나타내는 ti, ai, pi (1 ≤ ai ≤ 109, 1 ≤ pi ≤ N) 가 주어집니다.

ti가 'W' 인 경우 i번 섬에 늑대가 ai마리가 살고 있음을, ti가 'S'인 경우 i번 섬에 양이 ai마리가 살고 있음을 의미합니다. pi는 i번째 섬에서 pi번 섬으로 갈 수 있는 다리가 있음을 의미합니다.

출력
첫 번째 줄에 구출할 수 있는 양의 수를 출력합니다.

예제 입력 1 
4
S 100 3
W 50 1
S 10 1
예제 출력 1 
60
예제 입력 2 
7
S 100 1
S 100 1
W 100 1
S 1000 2
W 1000 2
S 900 6
예제 출력 2 
1200


2, 3, 5번에 사는 모든 양들은 1번 섬으로 갈 수 있지만 7번 섬에 사는 양들은 1번 섬으로 가기 위하여 6번 섬을 거쳐야 하는데 6번 섬에 사는 늑대들의 수가 7번 섬에 사는 양들의 수보다 많으므로 7번 섬에 사는 모든 양들은 1번 섬으로 갈 수 없습니다.

*/

public class 양구출작전_16437 {
	private static int N;
	private static long island[];
	private static Node nodes[];

	private static class Node {
		int n;
		Node next;

		public Node(int n, Node node) {
			this.n = n;
			next = node;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		island = new long[N + 1];
		nodes = new Node[N + 1];
		StringTokenizer st;

		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			char ch = st.nextToken().charAt(0);
			if (ch == 'S') {
				island[i] = Integer.parseInt(st.nextToken());
			} else {
				island[i] = Integer.parseInt(st.nextToken()) * -1;
			}

			int node = Integer.parseInt(st.nextToken());
			nodes[node] = new Node(i, nodes[node]);
		}

		System.out.println(dfs(1));

		br.close();
	}

	private static long dfs(int n) {
		for (Node next = nodes[n]; next != null; next = next.next) {
			island[n] += dfs(next.n);
		}

		if (island[n] < 0) {
			return 0;
		}
		return island[n];
	}
}
