package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 트리디자이너호석_22253 {
	static int N;
	static int[] nums;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		nums = new int[N+1];
		visited = new boolean[N + 1];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums[i+1] = Integer.parseInt(input[i]);
		}

		for (int i = 0; i < N - 1; i++) {
			input = br.readLine().split(" ");
			int u = Integer.parseInt(input[0]);
			int v = Integer.parseInt(input[1]);
			adj[u].add(v);
			adj[v].add(u);
		}

		findLeafLine(1);
	}

	private static void findLeafLine(int root) {

	}
}
