package boj_20210427_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 우수마을_1949 {
	private static int N;
	private static int[] population;
	private static List<Integer> tree[];
	private static int[][] dp;
	private static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		tree = new ArrayList[N+1];
		dp = new int[N+1][2];
		
		for(int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N; i++) {
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

		for(int child : tree[cur]) {
			if(child != parent) {
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
