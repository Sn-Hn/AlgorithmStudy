package study_210718;

/*

벼락치기 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	2543	1122	886	44.190%
문제
ChAOS(Chung-ang Algorithm Organization and Study) 회장이 되어 일이 많아진 준석이는 시험기간에도 일 때문에 공부를 하지 못하다가 시험 전 날이 되어버리고 말았다. 
다행히도 친절하신 교수님께서 아래와 같은 힌트를 시험 전에 공지해 주셨다. 
내용은 아래와 같다.

여러 단원을 융합한 문제는 출제하지 않는다.
한 단원에 한 문제를 출제한다. 
단, 그 단원에 모든 내용을 알고 있어야 풀 수 있는 문제를 낼 것이다.
이런 두가지 힌트와 함께 각 단원 별 배점을 적어 놓으셨다. 
어떤 단원의 문제를 맞추기 위해서는 그 단원의 예상 공부 시간만큼, 혹은 그보다 더 많이 공부하면 맞출 수 있다고 가정하자. 
이때, ChAOS 회장 일로 인해 힘든 준석이를 위하여 남은 시간 동안 공부해서 얻을 수 있는 최대 점수를 구하는 프로그램을 만들어 주도록 하자.

입력
첫째 줄에는 이번 시험의 단원 개수 N(1 ≤ N ≤ 100)과 시험까지 공부 할 수 있는 총 시간 T(1 ≤ T ≤ 10000)가 공백을 사이에 두고 주어진다.

둘째 줄부터 N 줄에 걸쳐서 각 단원 별 예상 공부 시간 K(1 ≤ K ≤ 1000)와 그 단원 문제의 배점 S(1 ≤ S ≤ 1000)가 공백을 사이에 두고 주어진다.

출력
첫째 줄에 준석이가 얻을 수 있는 최대 점수를 출력한다.

예제 입력 1 
3 310
50 40
100 70
200 150
예제 출력 1 
220

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벼락치기_14728 {
	private static int N;
	private static int T;
	private static int[][] study;
	private static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		study = new int[N + 1][2];
		dp = new int[N + 1][T + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			study[i][0] = Integer.parseInt(st.nextToken());
			study[i][1] = Integer.parseInt(st.nextToken());
		}
		
		getMaxGrade();
		
		System.out.println(dp[N][T]);
		
		br.close();
	}
	
	private static void getMaxGrade() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= T; j++) {
				if (study[i][0] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - study[i][0]] + study[i][1]);
				}
			}
		}
	}
}
