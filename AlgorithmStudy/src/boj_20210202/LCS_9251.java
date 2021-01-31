package boj_20210202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

LCS 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	29448	12004	8846	40.396%
문제
LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 
모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

입력
첫째 줄과 둘째 줄에 두 문자열이 주어진다. 
문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

출력
첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.

예제 입력 1 
ACAYKP
CAPCAK
예제 출력 1 
4

*/

public class LCS_9251 {
	private static int dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
		
		dp = new int[a.length()+1][b.length()+1];
		
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				if(a.charAt(i-1) != b.charAt(j-1)) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}else {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
			}
		}
		
		System.out.println(dp[a.length()][b.length()]);
		
		
		
		br.close();
	}
}
