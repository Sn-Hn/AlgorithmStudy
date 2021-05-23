package boj_20210309_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*

불우이웃돕기 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	469	141	115	29.040%
문제
다솜이는 불우이웃 돕기 활동을 하기 위해 무엇을 할지 생각했다. 
마침 집에 엄청나게 많은 랜선이 있다는 것을 깨달았다. 
마침 랜선이 이렇게 많이 필요 없다고 느낀 다솜이는 랜선을 지역사회에 봉사하기로 했다.

다솜이의 집에는 N개의 방이 있다. 각각의 방에는 모두 한 개의 컴퓨터가 있다. 각각의 컴퓨터는 랜선으로 연결되어 있다. 
어떤 컴퓨터 A와 컴퓨터 B가 있을 때, A와 B가 서로 랜선으로 연결되어 있거나, 또 다른 컴퓨터를 통해서 연결이 되어있으면 서로 통신을 할 수 있다.

다솜이는 집 안에 있는 N개의 컴퓨터를 모두 서로 연결되게 하고 싶다.

N개의 컴퓨터가 서로 연결되어 있는 랜선의 길이가 주어질 때, 다솜이가 기부할 수 있는 랜선의 길이의 최댓값을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 컴퓨터의 개수 N이 주어진다. 둘째 줄부터 랜선의 길이가 주어진다. 
i번째 줄의 j번째 문자가 0인 경우는 컴퓨터 i와 컴퓨터 j를 연결하는 랜선이 없음을 의미한다. 
그 외의 경우는 랜선의 길이를 의미한다. 
랜선의 길이는 a부터 z는 1부터 26을 나타내고, A부터 Z는 27부터 52를 나타낸다. N은 100보다 작거나 같은 자연수이다.

출력
첫째 줄에 다솜이가 기부할 수 있는 랜선의 길이의 최댓값을 출력한다. 
만약, 모든 컴퓨터가 연결되어 있지 않으면 -1을 출력한다.

예제 입력 1 
3
abc
def
ghi
예제 출력 1 
40

*/

// a-z 1 ~ 26 -> -96
// A-Z 27 ~ 52 -> -38
public class 불우이웃돕기_1414 {
	private static int N;
	private static char len[][];
	private static int parent[];
	private static int maxLen = 0;
	private static int max = 0;

	private static class Node implements Comparable<Node> {
		int x, y, len;

		public Node(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return len - o.len;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		len = new char[N][N];
		parent = new int[N];
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		for (int i = 0; i < N; i++) {
			len[i] = br.readLine().toCharArray();
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = 0;
				if (len[i][j] >= 'a' && len[i][j] <= 'z') {
					num = len[i][j] - 96;
				} else if (len[i][j] == '0') {
					num = 0;
				} else {
					num = len[i][j] - 38;
				}
				max += num;
				if (i != j && len[i][j] != '0') {
					q.add(new Node(i, j, num));
				}
			}
		}

		solve(q);

		br.close();
	}

	private static void solve(PriorityQueue<Node> q) {
		int count = 0;
		while (!q.isEmpty() && count < N - 1) {
			Node node = q.poll();

			if (!findCycle(node.x, node.y)) {
				union(node.x, node.y);
				maxLen += node.len;
				count++;
			}
		}

		if (count == N - 1) {
			System.out.println(max - maxLen);
		} else {
			System.out.println(-1);
		}
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
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
		return find(a) == find(b);
	}
}
