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

// LCS (Longest Common Subsequence) : 가장 긴 공통 부분집합
// https://www.youtube.com/watch?v=nyXkaHUahHk (X)
// https://www.youtube.com/watch?v=EAXDUxVYquY
// 2차원 배열 dp를 만들어 비교를 해준다.
// DP[][]
//      A C A Y K P
// 1) C 0 1 1 1 1 1  -> ACAYKP와 C를 각각 순서대로 비교한다. 비교를 했을 때 같지 않다면 0, 같다면 전 열의 값에 +1을 해준다.
// 2) A 1 1 2 2 2 2  -> 비교를 했을 때 같다면 해당 열과 행을 뺀 값에서 + 1을 해준다. 
// 3) P 1 1 2 2 2 3     : 여기서 점화식을 도출할 수 있다.
// 4) C 1 2 2 2 2 2
// 5) A 2 2 3 3 3 3
// 6) K 2 2 3 3 4 4

// 비교를 했을 때 같지 않다면 전 열과 전 행 중 최대 값, 같다면 최대 값 + 1을 해준다.
// 그 이유는 위의 배열에서 열이 증가할 때 해당 행와 해당 열만을 비교하는 것이 아니고
// 0번째 열부터 현재까지의 열과 0번째 행부터 현재까지의 행을 비교하는 것이다.
// 2)를 보면 CA(row)와 A를 비교하는데 그 중 A와 A를 비교 - A와 A가 같음 +1 -> 다음 열
// CA와 AC를 비교하는데 그 중 A와 C를 비교 -> A와 C가 같지 않으므로 C(전 행) or A(전 열) 1개
// CA와 ACA를 비교하는데 그 중 A와 A를 비교 -> A와 A가 같음 +1 근데 C(전 행) or A(전 열)이 이미 1개로 들어가 있으므로 여기서 최대값을 뽑아 +1을 해준다. 최대값을 뽑는 이유는 가장 긴 공통 부분집합을 뽑기 위해 (X)
// -> 전 행과 전 열의 최대값에 +1을 해주면 실패가 난다. 연속된 문자가 나왔을 때 그것도 +1해주기 때문에 실패한다.
// -> 따라서 전 행과 전 열을 제외한 수에서 +1을 해주면 된다. dp[i-1][j-1] + 1  (73)
// .....
// 점화식은 해당 열과 해당 행을 비교 후 (70)
// 같다면 해당 행과 열을 제외한 수의 +1 (73)
// 같지 않다면 전 열과 전 행의 최대값을 넣어준다. (71)
public class LCS_9251 {
	private static int dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
		
		dp = new int[a.length()+1][b.length()+1];
		
		
		// bottom-up
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
