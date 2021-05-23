package boj_20210330_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

1학년 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	12586	4694	3570	36.918%
문제
상근이가 1학년 때, 덧셈, 뺄셈을 매우 좋아했다. 
상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고, 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다. 
예를 들어, "8 3 2 4 8 7 2 4 0 8 8"에서 등식 "8+3-2-4+8-7-2-4-0+8=8"을 만들 수 있다.

상근이는 올바른 등식을 만들려고 한다. 
상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다. 
따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다. 
예를 들어, "8+3+2-4-8-7+2+4+0+8=8"은 올바른 등식이지만, 8+3+2-4-8-7이 음수이기 때문에, 상근이가 만들 수 없는 등식이다.

숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100) 둘째 줄에는 0 이상 9 이하의 정수 N개가 공백으로 구분해 주어진다.

출력
첫째 줄에 상근이가 만들 수 있는 올바른 등식의 개수를 출력한다. 이 값은 263-1 이하이다.

예제 입력 1 
11
8 3 2 4 8 7 2 4 0 8 8
예제 출력 1 
10

*/

public class boj_1학년_5557 {
	private static int N;
	private static int arr[];
	private static long dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new long[N + 1][21];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[1][arr[1]] = 1;
		for (int i = 2; i < N; i++) {
			for (int j = 0; j <= 20; j++) {
				if (dp[i - 1][j] != 0) {
					if (j + arr[i] <= 20)
						dp[i][j + arr[i]] += dp[i - 1][j];
					if (j - arr[i] >= 0)
						dp[i][j - arr[i]] += dp[i - 1][j];
				}
			}
		}

		System.out.println(dp[N - 1][arr[N]]);

		print();

		br.close();
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 20; j++) {
				if (dp[i][j] >= 10)
					System.out.print(dp[i][j] + " ");
				else
					System.out.print(dp[i][j] + "  ");
			}
			System.out.println();
		}
	}
}
