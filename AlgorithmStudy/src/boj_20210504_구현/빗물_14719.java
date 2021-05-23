package boj_20210504_구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

빗물 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	3286	1736	1389	54.449%
문제
2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.



비는 충분히 많이 온다. 고이는 빗물의 총량은 얼마일까?

입력
첫 번째 줄에는 2차원 세계의 세로 길이 H과 2차원 세계의 가로 길이 W가 주어진다. (1 ≤ H, W ≤ 500)

두 번째 줄에는 블록이 쌓인 높이를 의미하는 0이상 H이하의 정수가 2차원 세계의 맨 왼쪽 위치부터 차례대로 W개 주어진다.

따라서 블록 내부의 빈 공간이 생길 수 없다. 또 2차원 세계의 바닥은 항상 막혀있다고 가정하여도 좋다.

출력
2차원 세계에서는 한 칸의 용량은 1이다. 고이는 빗물의 총량을 출력하여라.

빗물이 전혀 고이지 않을 경우 0을 출력하여라.

예제 입력 1 
4 4
3 0 1 4
예제 출력 1 
5
예제 입력 2 
4 8
3 1 2 3 4 1 1 2
예제 출력 2 
5
예제 입력 3 
3 5
0 0 0 2 0
예제 출력 3 
0

*/

public class 빗물_14719 {
	private static int H, W;
	private static int height[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		height = new int[W];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		solve();

		br.close();
	}

	// 핵심은 각 인덱스마다 고이는 물을 계산해야 한다.
	// 즉, 완전 탐색
	private static void solve() {
		int sum = 0;
		int left = 0;
		int right = 0;
		// 처음과 마지막은 물이 고일 수 없다.
		// 각 인덱스를 한 바퀴 돈다.
		for (int i = 1; i < W - 1; i++) {
			left = 0;
			right = 0;
			// 위에서 세운 인덱스를 기준으로 왼쪽, 오른쪽을 탐색하며 가장 큰 블록을 찾는다.
			for (int j = 0; j < i; j++) {
				left = Math.max(height[j], left);
			}

			for (int j = i + 1; j < W; j++) {
				right = Math.max(height[j], right);
			}
			left = Math.min(left, right);
			if (left > height[i]) {
				sum += left - height[i];
			}
		}
		if (sum < 0) {
			sum = 0;
		}
		System.out.println(sum);

	}

}
