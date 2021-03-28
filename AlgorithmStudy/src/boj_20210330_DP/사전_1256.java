package boj_20210330_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

사전 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	10305	2741	2035	27.996%
문제
동호와 규완이는 212호에서 문자열에 대해 공부하고 있다. 김진영 조교는 동호와 규완이에게 특별 과제를 주었다. 
특별 과제는 특별한 문자열로 이루어 진 사전을 만드는 것이다. 사전에 수록되어 있는 모든 문자열은 N개의 "a"와 M개의 "z"로 이루어져 있다. 그리고 다른 문자는 없다. 사전에는 알파벳 순서대로 수록되어 있다.

규완이는 사전을 완성했지만, 동호는 사전을 완성하지 못했다. 
동호는 자신의 과제를 끝내기 위해서 규완이의 사전을 몰래 참조하기로 했다. 
동호는 규완이가 자리를 비운 사이에 몰래 사전을 보려고 하기 때문에, 문자열 하나만 찾을 여유밖에 없다.

N과 M이 주어졌을 때, 규완이의 사전에서 K번째 문자열이 무엇인지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N, M, K가 순서대로 주어진다. N과 M은 100보다 작거나 같은 자연수이고, K는 1,000,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 규완이의 사전에서 K번째 문자열을 출력한다. 만약 규완이의 사전에 수록되어 있는 문자열의 개수가 K보다 작으면 -1을 출력한다.

예제 입력 1 
2 2 2
예제 출력 1 
azaz

*/

public class 사전_1256 {
	private static int N, M, K;
	private static long dp[][] = new long[101][101];
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp[0][0] = 0;
		for(int i = 1; i <= 100; i++) {
			dp[i][0] = 1;
			dp[0][i] = 1;
		}
		
		for(int i = 1; i <= 100; i++) {
			for(int j = 1; j <= 100; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
				if(dp[i][j] > 1000000000) dp[i][j] = 1000000001;
			}
		}
		
		if(dp[N][M] < K) {
			System.out.println(-1);
			return;
		}
		
		int aCnt = N;
		int zCnt = M;
		
		long k = K;
		
		while(true) {
			if(aCnt == 0) {
				for(int j = 1; j <= zCnt; j++) {
					sb.append("z");
				}
				break;
			}else if(zCnt == 0) {
				for(int j = 1; j <= aCnt; j++) {
					sb.append("a");					
				}
				break;
			}
			
			long aStart = dp[aCnt-1][zCnt];
			
			if(k > aStart) {
				k -= aStart;
				zCnt--;
				sb.append("z");
			}else {
				aCnt--;
				sb.append("a");
			}
			
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	private static void print() {
		for(int i = 1; i <= 100; i++) {
			for(int j = 1; j <= 100; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}