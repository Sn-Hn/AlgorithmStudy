package boj_20210119_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

경사로 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	14420	7534	5354	53.890%
문제
크기가 N×N인 지도가 있다. 지도의 각 칸에는 그 곳의 높이가 적혀져 있다. 

오늘은 이 지도에서 지나갈 수 있는 길이 몇 개 있는지 알아보려고 한다. 길이란 한 행 또는 한 열 전부를 나타내며, 한쪽 끝에서 다른쪽 끝까지 지나가는 것이다. 

다음과 같은 N=6인 경우 지도를 살펴보자.



이때, 길은 총 2N개가 있으며, 아래와 같다.



길을 지나갈 수 있으려면 길에 속한 모든 칸의 높이가 모두 같아야 한다. 또는, 경사로를 놓아서 지나갈 수 있는 길을 만들 수 있다. 경사로는 높이가 항상 1이며, 길이는 L이다. 또, 개수는 매우 많아 부족할 일이 없다. 경사로는 낮은 칸과 높은 칸을 연결하며, 아래와 같은 조건을 만족해야한다.

경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
아래와 같은 경우에는 경사로를 놓을 수 없다.

경사로를 놓은 곳에 또 경사로를 놓는 경우
낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
경사로를 놓다가 범위를 벗어나는 경우
L = 2인 경우에 경사로를 놓을 수 있는 경우를 그림으로 나타내면 아래와 같다.



경사로를 놓을 수 없는 경우는 아래와 같다.



위의 그림의 가장 왼쪽부터 1번, 2번, 3번, 4번 예제라고 했을 때, 1번은 높이 차이가 1이 아니라서, 2번은 경사로를 바닥과 접하게 놓지 않아서, 3번은 겹쳐서 놓아서, 4번은 기울이게 놓아서 불가능한 경우이다.

가장 위에 주어진 그림 예의 경우에 지나갈 수 있는 길은 초록색으로, 지나갈 수 없는 길은 빨간색으로 표시되어 있으며, 아래와 같다. 경사로의 길이 L = 2이다.



지도가 주어졌을 때, 지나갈 수 있는 길의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (2 ≤ N ≤ 100)과 L (1 ≤ L ≤ N)이 주어진다. 둘째 줄부터 N개의 줄에 지도가 주어진다. 각 칸의 높이는 10보다 작거나 같은 자연수이다.

출력
첫째 줄에 지나갈 수 있는 길의 개수를 출력한다.

예제 입력 1 
6 2
3 3 3 3 3 3
2 3 3 3 3 3
2 2 2 3 2 3
1 1 1 2 2 2
1 1 1 3 3 1
1 1 2 3 3 2
예제 출력 1 
3
예제 입력 2 
6 2
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2
예제 출력 2 
7
예제 입력 3 
6 3
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2
예제 출력 3 
3
예제 입력 4 
6 1
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2
예제 출력 4 
11
힌트
예제 2의 경우 아래와 같은 초록색 길을 지나갈 수 있다.



예제 3의  경우에는 아래와 같은 초록색 길이 지나갈 수 있는 길이다.



마지막으로, 예제 4의 경우에는 아래와 같은 초록색 길이 지나갈 수 있는 길이다.


*/

// # 요약
// 행 또는 열이 길 하나가 될 수 있다
// L의 값에 따라 경사로를 놓을 수 있다
// 경사로가 올라가는 경사로인지 내려가는 경사로인지도 신경써주어야 한다
// 행 또는 열의 다음 값이 숫자 변화(1의 차이)가 있다면 경사로를 놓을 수 있는지 검사
// 경사로는 같은 곳에 또 놓을 수 없으니 visited로 체크
// 그렇게 행과 열의 길을 찾을 때 마다 count+1

public class 경사로_14890 {
	private static int N, L;
	private static int map[][];
	private static boolean visited[];
	private static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		int now = 0;
		int pre = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findRowWay();
		findColWay();

		System.out.println(count);

		br.close();
	}

	private static void findRowWay() {
		int now = 0;
		int pre = 0;
		for (int i = 0; i < N; i++) {
			boolean flag = false;
			visited = new boolean[N];
			pre = map[i][0];
			LOOP: for (int j = 1; j < N; j++) {
				now = map[i][j];

				if (pre == now) {
					flag = true;
					// 올라가는 경우
				} else if (now - pre == 1) {
					for (int k = j - 1; k > j - L - 1; k--) {
						if (k < 0 || visited[k] || map[i][k] != map[i][j - 1]) {
							flag = false;
							break LOOP;
						}
						visited[k] = true;
					}
					flag = true;

					// 내려가는 경우
				} else if (pre - now == 1) {
					for (int k = j; k < j + L; k++) {
						if (k >= N || visited[k] || map[i][k] != map[i][j]) {
							flag = false;
							break LOOP;
						}
						visited[k] = true;
					}
					flag = true;
				} else {
					flag = false;
					break;
				}

				pre = now;
			}
			if (flag) {
				count += 1;
			}
		}
	}

	private static void findColWay() {
		int now = 0;
		int pre = 0;
		for (int j = 0; j < N; j++) {
			boolean flag = false;
			visited = new boolean[N];
			pre = map[0][j];
			LOOP: for (int i = 1; i < N; i++) {
				now = map[i][j];

				if (pre == now) {
					flag = true;
					// 올라가는 경우
				} else if (now - pre == 1) {
					for (int k = i - 1; k > i - L - 1; k--) {
						if (k < 0 || visited[k] || map[k][j] != map[i - 1][j]) {
							flag = false;
							break LOOP;
						}
						visited[k] = true;
					}

					// 내려가는 경우
				} else if (pre - now == 1) {
					for (int k = i; k < i + L; k++) {
						if (k >= N || visited[k] || map[k][j] != map[i][j]) {
							flag = false;
							break LOOP;
						}
						visited[k] = true;
					}
				} else {
					flag = false;
					break;
				}

				pre = now;
			}
			if (flag) {
				count += 1;
			}
		}
	}

}
