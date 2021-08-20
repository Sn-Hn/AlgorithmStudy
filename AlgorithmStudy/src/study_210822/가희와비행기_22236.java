package study_210822;
/*

가희와 비행기 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	111	48	40	44.944%
문제
가희는 김포 공항에서 김해 공항까지 비행기를 타고 가려고 합니다.

비행기가 수평 방향으로 1만큼 이동하였을 때, 비행기의 고도는 1만큼 변화합니다. (상승 또는 하강)

비행기가 상승하거나 하강할 때에는 이동한 수평 거리 당, 고도 변화량이 동일합니다. 그림 1은 이 조건을 만족하지만, 그림 2는 그렇지 않습니다.

[그림 1] 비행기가 상승할 때와 하강할 때

[그림 2] 문제의 조건을 만족하지 않는 비행 경로 

김포 공항에서 김해 공항까지 수평 거리는 d입니다. 아래 조건을 만족하는 비행기가 비행할 수 있는 가짓수를 구해 주세요.

[그림 3] 김포공항에서 김해공항 방항으로 날아가는 비행기

김포 공항에서 이륙하면 김해 공항에 착륙할 때 까지 다른 어떠한 지점에도 착륙하지 않습니다. 고도가 0이 되었을 때, 착륙하였다고 합니다.
비행기는 수평 거리 d만큼 이동합니다.  수평거리 d만큼 이동했을 때 고도가 0인 지점에 김해 공항이 있습니다.
비행기는 이륙할 때부터 착륙할 때 까지 비행 방향을 바꾸지 않습니다. 즉, 김포 공항에서부터 김해 공항까지 일직선으로 날아갑니다.
비행기는 지구를 한 바퀴 이상 돌지 않으며, 김해 공항이 있는 방향 반대편(북서쪽)으로 비행하지 않습니다.
김포 공항과 김해 공항의 고도는 0이며, 비행기가 날아가는 경로 상에는 장애물이 존재하지 않습니다.

입력
첫 줄에 d와 m이 주어집니다.

출력
문제에 대한 답을 m으로 나눈 나머지를 출력해 주세요.

제한
1 ≤ d ≤ 4 × 103
d는 짝수입니다.
m은 소수이며, [2, 2×109] 에 속하는 정수입니다.
예제 입력 1 
2 1000000007
예제 출력 1 
1
예제 입력 2 
4 1000000007
예제 출력 2 
1

[그림 3] 불가능한 경우

김포공항에서 김해공항으로 가는 중간에 고도가 0인 (김포 공항으로부터 거리가 2인) 지점이 존재합니다. 이 경우는 가능하지 않으므로 답에서 빼야 합니다.

출처
Contest > 가희와 함께 하는 코딩 테스트 > 가희와 함께 하는 2회 코딩 테스트 5번

문제를 만든 사람: chogahui05
문제를 검수한 사람: melon940925, rhs0266

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가희와비행기_22236 {
	private static int d;
	private static int m;
	private static long[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dp = new long[d + 2];
		
		fly();
		
		br.close();
	}
	
	private static void fly() {
		dp[0] = 1;
		dp[2] = 1;
		
		for (int i = 4; i <= d; i += 2) {
			dp[i] = dp[i - 2];
		    for(int j=2; j<i; j=j+2)
		    {
			    dp[i] += dp[j] * dp[i - j - 2];
			    dp[i] %= m;
		    }
		}
		
		System.out.println(dp[d - 2]);
	}
}
