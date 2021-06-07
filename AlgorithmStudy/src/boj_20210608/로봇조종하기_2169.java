package boj_20210608;

/*

로봇 조종하기 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	9789	3366	2257	33.274%
문제
NASA에서는 화성 탐사를 위해 화성에 무선 조종 로봇을 보냈다. 
실제 화성의 모습은 굉장히 복잡하지만, 로봇의 메모리가 얼마 안 되기 때문에 지형을 N×M 배열로 단순화 하여 생각하기로 한다.

지형의 고저차의 특성상, 로봇은 움직일 때 배열에서 왼쪽, 오른쪽, 아래쪽으로 이동할 수 있지만, 위쪽으로는 이동할 수 없다. 
또한 한 번 탐사한 지역(배열에서 하나의 칸)은 탐사하지 않기로 한다.

각각의 지역은 탐사 가치가 있는데, 로봇을 배열의 왼쪽 위 (1, 1)에서 출발시켜 오른쪽 아래 (N, M)으로 보내려고 한다. 
이때, 위의 조건을 만족하면서, 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램을 작성하시오.

입력
첫째 줄에 N, M(1≤N, M≤1,000)이 주어진다. 
다음 N개의 줄에는 M개의 수로 배열이 주어진다. 
배열의 각 수는 절댓값이 100을 넘지 않는 정수이다. 이 값은 그 지역의 가치를 나타낸다.

출력
첫째 줄에 최대 가치의 합을 출력한다.

예제 입력 1 
5 5
10 25 7 8 13
68 24 -78 63 32
12 -69 100 -29 -25
-16 -22 -57 -33 99
7 -76 -11 77 15
예제 출력 1 
319

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇조종하기_2169 {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();

		printDp();

		System.out.println(dp[N - 1][M - 1]);

		br.close();
	}

	// 처음에는 temp를 선언하지 않고 
	// DP에 왼쪽을 먼저 담고 후에 오른쪽에서 돌아오며 비교하였는데
	// 그렇게 되면 왔던길을 되돌아가는 경우가 생긴다.
	// 따라서 조건에 위배된다
	private static void solve() {
		dp[0][0] = map[0][0];
		// 가장 윗 행 (왼 -> 오)
		for (int i = 1; i < M; i++) {
			dp[0][i] = dp[0][i - 1] + map[0][i];
		}

		for (int i = 1; i < N; i++) {
			int[][] temp = new int[2][M];
			// 왼 -> 오
			temp[0][0] = dp[i - 1][0] + map[i][0];
			for (int j = 1; j < M; j++) {
				temp[0][j] = Math.max(dp[i - 1][j], temp[0][j - 1]) + map[i][j];
			}

			// 오 -> 왼
			temp[1][M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
			for (int j = M - 2; j >= 0; j--) {
				temp[1][j] = Math.max(dp[i - 1][j], temp[1][j + 1]) + map[i][j];					
			}
			
			for (int j = 0; j < M; j++) {
				dp[i][j] = Math.max(temp[0][j], temp[1][j]);
			}
		}
	}

	private static void printDp() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
