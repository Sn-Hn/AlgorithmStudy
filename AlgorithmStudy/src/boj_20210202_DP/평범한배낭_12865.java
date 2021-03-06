package boj_20210202_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*

평범한 배낭 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	25051	9486	6283	36.449%
문제
이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 
세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 
각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 
아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 
준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

입력
첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다.
두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.

입력으로 주어지는 모든 수는 정수이다.

출력
한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.

예제 입력 1 
4 7
6 13
4 8
3 6
5 12
예제 출력 1 
14

*/

//   1 2 3 4 5  6  7	-> 무게 (K)
// 1 0 0 0 0 0  13 13   -> 1번은 6kg에 가치는 13이므로 6(열)부터 13이 들어간다.
// 2 0 0 0 8 8  13 13   -> 2번은 4kg에 가치는 8이므로 4(열)부터 8이 들어간다. 6(열)부터는 이전의 물건 무게의 가치(1번 물건의 가치)와  자신의 무게를 빼고 남은 무게의 가치(6-4=2의 가치) + 자기 무게의 가치(v[2]=8) 중 큰 값을 넣는다.
// 3 0 0 6 8 8  13 14	-> ...반복
// 4 0 0 6 8 12	13 14
// 물건
// dp[N][M]이 가치합의 최대값이 된다.
public class 평범한배낭_12865 {
	private static int N, K;
	private static int dp[][];
	private static int w[];
	private static int v[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][K+1];
		w = new int[N+1];
		v = new int[K+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		// bottom-up
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				if(w[i] > j) {
					dp[i][j] = dp[i-1][j];
				// w[i] <= j
				}else {
					// 이전 물건의 가치와 자신의 무게를 빼고 남은 무게의 가치 + 자기 무게의 가치 중 큰 값을 구한다.
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i]] + v[i]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
		br.close();
	}
	
}
