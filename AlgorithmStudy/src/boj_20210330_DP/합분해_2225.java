package boj_20210330_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

합분해 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	21845	9465	6814	41.845%
문제
0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.

덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.

입력
첫째 줄에 두 정수 N(1 ≤ N ≤ 200), K(1 ≤ K ≤ 200)가 주어진다.

출력
첫째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.

예제 입력 1 
20 2
예제 출력 1 
21



*/

public class 합분해_2225 {
	private static int N, K;
	private static int dp[][];
	private static int MAX_VALUE = 1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[K+1][N+1];
		
		for(int i = 0; i <= N; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 1; i <= K; i++) {
			dp[i][0] = 1;
		}
		
		for(int i = 2; i <= K; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1])%MAX_VALUE;
			}
		}
		
		print();
		
		System.out.println(dp[K][N]);
		
		br.close();
	}
	
	private static void print() {
		for(int i = 1; i <= K; i++) {
			for(int j = 0; j <= N; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
