package boj_20210427_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*

우수 마을 성공분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	3606	1582	1068	47.509%
문제
N개의 마을로 이루어진 나라가 있다. 편의상 마을에는 1부터 N까지 번호가 붙어 있다고 하자. 
이 나라는 트리(Tree) 구조로 이루어져 있다. 
즉 마을과 마을 사이를 직접 잇는 N-1개의 길이 있으며, 각 길은 방향성이 없어서 A번 마을에서 B번 마을로 갈 수 있다면 B번 마을에서 A번 마을로 갈 수 있다. 
또, 모든 마을은 연결되어 있다. 
두 마을 사이에 직접 잇는 길이 있을 때, 두 마을이 인접해 있다고 한다.

이 나라의 주민들에게 성취감을 높여 주기 위해, 다음 세 가지 조건을 만족하면서 N개의 마을 중 몇 개의 마을을 '우수 마을'로 선정하려고 한다.

'우수 마을'로 선정된 마을 주민 수의 총 합을 최대로 해야 한다.
마을 사이의 충돌을 방지하기 위해서, 만일 두 마을이 인접해 있으면 두 마을을 모두 '우수 마을'로 선정할 수는 없다. 즉 '우수 마을'끼리는 서로 인접해 있을 수 없다.
선정되지 못한 마을에 경각심을 불러일으키기 위해서, '우수 마을'로 선정되지 못한 마을은 적어도 하나의 '우수 마을'과는 인접해 있어야 한다.
각 마을 주민 수와 마을 사이의 길에 대한 정보가 주어졌을 때, 주어진 조건을 만족하도록 '우수 마을'을 선정하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 N이 주어진다. (1≤N≤10,000) 
둘째 줄에는 마을 주민 수를 나타내는 N개의 자연수가 빈칸을 사이에 두고 주어진다. 
1번 마을부터 N번 마을까지 순서대로 주어지며, 주민 수는 10,000 이하이다. 
셋째 줄부터 N-1개 줄에 걸쳐서 인접한 두 마을의 번호가 빈칸을 사이에 두고 주어진다.

출력
첫째 줄에 '우수 마을'의 주민 수의 총 합을 출력한다.

예제 입력 1 
7
1000 3000 4000 1000 2000 2000 7000
1 2
2 3
4 3
4 5
6 2
6 7
예제 출력 1 
14000

*/

public class 우수마을_1949 {
	private static int N;
	private static int[] population;
	private static List<Integer> tree[];
	private static int[][] dp;
	private static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		tree = new ArrayList[N + 1];
		dp = new int[N + 1][2];

		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			tree[parent].add(child);
			tree[child].add(parent);
		}

		dfs(1, -1);

		System.out.println(Math.max(dp[1][1], dp[1][0]));

		br.close();
	}

	private static void dfs(int cur, int parent) {

		for (int child : tree[cur]) {
			if (child != parent) {
				dfs(child, cur);
				// 현재 노드가 우수마을이라면 자식 노드는 우수마을이 될 수 없다.
				dp[cur][1] += dp[child][0];
				// 현재 노드가 우수마을이 아니라면 자식 노드는 우수마을이 될 수도 안 될 수도 있으므로 큰 값을 넣는다.
				dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
			}
		}

		dp[cur][1] += population[cur];
	}

}