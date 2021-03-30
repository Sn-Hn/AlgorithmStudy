package boj_20210330_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

가장 큰 정사각형 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	22999	7104	5085	29.295%
문제
n×m의 0, 1로 된 배열이 있다. 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구하는 프로그램을 작성하시오.

0	1	0	0
0	1	1	1
1	1	1	0
0	0	1	0
위와 같은 예제에서는 가운데의 2×2 배열이 가장 큰 정사각형이다. 

입력
첫째 줄에 n, m(1 ≤ n, m ≤ 1,000)이 주어진다. 다음 n개의 줄에는 m개의 숫자로 배열이 주어진다.

출력
첫째 줄에 가장 큰 정사각형의 넓이를 출력한다.

예제 입력 1 
4 4
0100
0111
1111
0111
예제 출력 1 
4

->
00000
00100
00111
01110
00010


*/

public class 가장큰정사각형_1915 {
	private static int N, M;
	private static int map[][];
	private static int dp[][];
	private static int answer = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		
		String inputStr = "";
		for(int i = 1; i <= N; i++) {
			inputStr = br.readLine();
			for(int j = 1; j <= M; j++) {
				map[i][j] = inputStr.charAt(j-1) - '0';
				dp[i][j] = map[i][j];
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(map[i][j] == 1) {
					dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
					answer = Math.max(answer, dp[i][j]);
				}
			}
		}
		
		System.out.println(answer * answer);
		
		print();
		
		br.close();
	}
	
	private static void print() {
		for(int i = 1; i <= N; i++ ) {
			for(int j = 1; j <= M; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
