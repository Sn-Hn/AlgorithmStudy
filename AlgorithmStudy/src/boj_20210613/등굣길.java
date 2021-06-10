package boj_20210613;

/*

등굣길
문제 설명
계속되는 폭우로 일부 지역이 물에 잠겼습니다. 
물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 
집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.

아래 그림은 m = 4, n = 3 인 경우입니다.

image0.png

가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타냅니다.

격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다.
오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.

제한사항
격자의 크기 m, n은 1 이상 100 이하인 자연수입니다.
m과 n이 모두 1인 경우는 입력으로 주어지지 않습니다.
물에 잠긴 지역은 0개 이상 10개 이하입니다.
집과 학교가 물에 잠긴 경우는 입력으로 주어지지 않습니다.
입출력 예
m	n	puddles	return
4	3	[[2, 2]]	4
입출력 예 설명

*/

// 최단 경로의 개수를 구해라
// 출발점에서 도착점까지 도착할 수 있다면 그게 바로 최단 경로
// Why ? 이동은 오른쪽과 아래쪽으로만 움직일 수 있기 때문
// 따라서 오른쪽, 아래쪽으로만 이동한다는 조건만 만족한다면 최단경로를 신경쓰지 않아도 된다.

// 맞왜틀 ?
public class 등굣길 {
	private static final int MOD = 1000000007;
	public static void main(String[] args) {
		int n = 3;
		int m = 4;
		int[][] puddles = {				
				{2, 2}
		};
//		int n = 5;
//		int m = 7;
//		int[][] puddles = {				
//				{2, 2},
//				{3, 4},
//				{1, 2},
//				{3, 5},
//				{5, 2},
//				{2, 1}
//		};
		
		// return 4;
		
		System.out.println(solution(m, n, puddles));
	}
	
	public static int solution(int m, int n, int[][] puddles) {
        int answer = getPath(n, m, puddles) % MOD;
        
        return answer;
    }
	
	private static int getPath(int N, int M, int[][] puddles) {
		int[][] dp = new int[N + 1][M + 1];
		dp[1][1] = 1;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++ ) {
				if (!isSubmergeWater(puddles, i, j)) {
					dp[i][j] += (dp[i][j - 1] + dp[i - 1][j]) % MOD;
				}
			}
		}
		
		printDp(dp, N, M);
		
		return dp[N][M];
	}
	
	private static boolean isSubmergeWater(int[][] puddles, int x, int y) {
		for (int i = 0; i < puddles.length; i++) {
			// 맞왜틀 -> 좌표가 (m, n);
			// 이런것도 잘봐야 할듯
			if (puddles[i][0] == y && puddles[i][1] == x) {
				return true;
			}
		}
		
		return false;
	}
	
	private static void printDp(int[][] dp, int N, int M) {
		for (int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
