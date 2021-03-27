package boj_20210330_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

내려가기 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	4 MB (하단 참고)	16713	6172	4730	36.531%
문제
N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다.
내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.

먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 
그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 
바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다. 
이 제약 조건을 그림으로 나타내어 보면 다음과 같다.



별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며, 빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다. 
숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 
점수는 원룡이가 위치한 곳의 수의 합이다.

입력
첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 
다음 N개의 줄에는 숫자가 세 개씩 주어진다. 
숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.

출력
첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.

예제 입력 1 
3
1 2 3
4 5 6
4 9 0
예제 출력 1 
18 6

*/

public class 내려가기_2096 {
	private static int N;
	private static int map[][];
	private static int minDp[];
	private static int maxDp[];
	private static int resultMax[];
	private static int resultMin[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][4];
		minDp = new int[4];
		maxDp = new int[4];
		resultMin = new int[4];
		resultMax = new int[4];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		minDp[0] = Integer.MAX_VALUE;
		for(int i = 1; i <= 3; i++ ) {
			maxDp[i] = map[1][i];
			minDp[i] = map[1][i];
		}
		int mx = 0;
		int mn = -1;
		for(int i = 2; i <= N; i++) {
			mx = Integer.MIN_VALUE;
			mn = Integer.MAX_VALUE;
			for(int j = 1; j <= 3; j++) {
				mx = Math.max(maxDp[j-1], maxDp[j]);
				mn = Math.min(minDp[j-1], minDp[j]);
				
				if(j != 3) {
					mx = Math.max(mx, maxDp[j+1]);
					mn = Math.min(mn, minDp[j+1]);					
				}
				
				mx += map[i][j];
				mn += map[i][j];
				
				resultMax[j] = mx;
				resultMin[j] = mn;
			}
			
			for(int j = 1; j <= 3; j++) {
				maxDp[j] = resultMax[j];
				minDp[j] = resultMin[j];
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 1; i <= 3; i++) {
			max = Math.max(max, maxDp[i]);
			min = Math.min(min, minDp[i]);
		}
		
		System.out.println(max + " " + min);

		print();
		
		br.close();
	}
	
	private static void print() {
		for(int i = 1; i <= N; i++) {
			System.out.print(minDp[i] + " ");
		}
		System.out.println();
	}
	
}
