package boj_20201229_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

욕심쟁이 판다 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	22704	7313	4730	30.116%
문제
n*n의 크기의 대나무 숲이 있다. 욕심쟁이 판다는 어떤 지역에서 대나무를 먹기 시작한다. 
그리고 그 곳의 대나무를 다 먹어 치우면 상, 하, 좌, 우 중 한 곳으로 이동을 한다. 
그리고 또 그곳에서 대나무를 먹는다. 그런데 단 조건이 있다. 
이 판다는 매우 욕심이 많아서 대나무를 먹고 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다. 
만약에 그런 지점이 없으면 이 판다는 불만을 가지고 단식 투쟁을 하다가 죽게 된다(-_-)

이 판다의 사육사는 이런 판다를 대나무 숲에 풀어 놓아야 하는데, 어떤 지점에 처음에 풀어 놓아야 하고, 
어떤 곳으로 이동을 시켜야 둘 다 소중한 생명이지만 판다가 최대한 오래 살 수 있는지 고민에 빠져 있다. 
우리의 임무는 이 사육사를 도와주는 것이다. 
n*n 크기의 대나무 숲이 주어져 있을 때, 이 판다가 최대한 오래 살려면 어떤 경로를 통하여 움직여야 하는지 구하여라.

입력
첫째 줄에 대나무 숲의 크기 n(1 ≤ n ≤ 500)이 주어진다. 
그리고 둘째 줄부터 n+1번째 줄까지 대나무 숲의 정보가 주어진다. 
대나무 숲의 정보는 공백을 사이로 두고 각 지역의 대나무의 양이 정수 값으로 주어진다. 
대나무의 양은 1,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에는 판다가 최대한 살 수 있는 일수(K)를 출력한다.

예제 입력 1 
4
14 9 12 10
1 11 5 4
7 15 2 13
6 3 16 8
예제 출력 1 
4

*/

// 36% 에서 시간 초과
// 안풀린다..
public class GreedPersonPanda_1937_2 {
	private static int N;
	private static int map[][];
	private static int dp[][];
	private static int max = Integer.MIN_VALUE;
	private static int indexMax = 0;
	private static boolean visited[][];
	private static int count = 0;

	private static int dx[] = { 1, -1, 0, 0 };
	private static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
//					indexMax = 0;
					visited[i][j] = true;
					max = Math.max(savePanda(i, j), max);
//					dp[i][j] = indexMax;
//					System.out.println("i : " + i + ", j : " + j + ", dp : " + dp[i][j]);

				}
			}
		}
//		printDP();
		System.out.println(max);

		br.close();
	}

	private static int savePanda(int x, int y) {
		if (dp[x][y] != 0)
			return dp[x][y];

//		max = Math.max(max, cnt);
//		indexMax = Math.max(indexMax, cnt);
//		printDP();
//		System.out.println();
//		count = 0;

		dp[x][y] = 1;

		for (int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];

			if (X >= 0 && X < N && Y >= 0 && Y < N && map[X][Y] > map[x][y]) {
				dp[x][y] = Math.max(savePanda(X, Y) + 1, dp[x][y]);
				visited[X][Y] = true;
//				if(dp[X][Y] != 0) {
//					indexMax = Math.max(indexMax, cnt+dp[X][Y]);
//					dp[X][Y] += dp[x][y];
//					continue;
//				}
//				savePanda(X, Y, cnt+1);
//				count += 1;
//				dp[X][Y] += count;
//				System.out.println(indexMax);
			}
		}
		return dp[x][y];

	}

	private static void printDP() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(dp[i][j]);
			}
			System.out.println();
		}
	}
}
