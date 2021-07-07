package study_210704;

/*

인내의 도미노 장인 호석 출처전체 채점
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
1 초   512 MB   557   217   178   40.181%
문제
사람을 화나게 하는 법은 다양하다.
그 중에서도 악질은 바로 열심히 세워놓은 도미노를 넘어뜨리는 것이다.
이번에 출시된 보드 게임인 "너 죽고 나 살자 게임"은 바로 이 점을 이용해서 2명이 공격과 수비를 하는 게임이다.
공격수는 도미노를 계속 넘어뜨리고 수비수는 도미노를 계속 세우려고 한다.
본게임은 다음과 같이 진행된다.

N 행 M 열의 2차원 격자 모양의 게임판의 각 격자에 도미노를 세운다.
각 도미노는 1 이상 5 이하의 높이를 가진다.
매 라운드는 공격수가 먼저 공격하고, 수비수는 공격이 끝난 뒤에 수비를 한다.
공격수는 특정 격자에 놓인 도미노를 동, 서, 남, 북 중 원하는 방향으로 넘어뜨린다.
길이가 K인 도미노가 특정 방향으로 넘어진다면, 그 방향으로 K-1 개의 도미노들 중에 아직 넘어지지 않은 것들이 같은 방향으로 연달아 넘어진다.
이 때, 당연히 도미노의 특성상, 연쇄적으로 도미노가 넘어질 수 있다.
이미 넘어진 도미노가 있는 칸을 공격한 경우에는 아무 일이 일어나지 않는다.
수비수는 넘어져 있는 도미노들 중에 원하는 것 하나를 다시 세울 수 있다.
넘어지지 않은 도미노를 세우려고 하면 아무 일이 일어나지 않는다.
총 R 번의 라운드동안 3, 4번 과정이 반복된다.
매 라운드마다 공격수가 해당 라운드에 넘어뜨린 도미노의 개수를 세고, R 라운드에 걸친 총합이 곧 공격수의 점수가 된다.


도미노 공격에 대한 예시 그림이다. 그림의 빨간 숫자들은 넘어진 도미노들을 의미한다.

예를 들어 {3, 1, 2, 2, 2} 높이의 도미노가 일렬로 서 있는 과정에서 3번을 오른쪽으로 밀면 왼쪽의 3개가 오른쪽으로 넘어지게 된다.
이에 따라 새롭게 넘어진 도미노들의 연쇄작용이 발생하는데, 이 과정에서 4번째에 위치한 길이 2짜리 도미노도 넘어지게 된다.
이어서 마지막 도미노까지 쓰러지게 되는 것이다.

인내를 빼면 시체라고 자부하는 호석이는 당신의 공격을 이겨내고자 수비수를 자처했다.
초기 도미노 판의 상태와, 각 라운드에서 둘의 행동의 기록을 받아서 공격수의 점수와 게임판의 최종 상태를 출력하는 프로그램을 작성하라.

입력
첫 번째 줄에는 게임판의 행 개수 N, 열 개수 M, 라운드 횟수 R 이 주어진다.

이어서 N개의 줄에 게임판의 상태가 주어진다. 1행부터 주어지며, M 개의 숫자는 각 격자에 놓인 도미노의 길이를 의미한다.

이어서 R×2 개의 줄에 걸쳐서 공격수와 수비수의 행동이 주어진다.
각 라운드는 두 줄로 이뤄지며, 첫 줄은 공격수, 두번째 줄은 수비수의 행동이 주어진다.
공격수의 행동은 "X Y D"로 주어진다. 이는 X행 Y열의 도미노를 D 방향으로 민다는 뜻이다.
D는 E, W, S, N 중 하나이며 각각 동, 서, 남, 북 방향을 의미한다. 수비수의 행동은 "X Y"로 주어진다.
이는 X행 Y열의 도미노를 다시 세운다는 뜻이다.

만약 이미 넘어진 격자의 도미노를 공격수가 넘어뜨리려 할 때에는 아무 일도 일어나지 않는다.
또한 만약 넘어지지 않은 도미노를 수비수가 세우려고 할 때에도 아무 일도 일어나지 않는다.

출력
첫 줄에 공격수의 점수를 출력한다.

이어서 게임판의 상태를 N 줄에 걸쳐서 출력한다.
각 격자마다 넘어진 것은 F, 넘어지지 않은 것은 S 를 공백으로 구분하여 출력한다.

제한
입력되는 모든 값은 양의 정수이다.

1 ≤ N, M ≤ 100
1 ≤ R ≤ 10,000
1 ≤ 도미노의 길이 ≤ 5
공격수와 수비수는 격자를 벗어나는 행동은 하지 않는다.
예제 입력 1
5 5 3
1 1 1 1 1
1 2 2 1 1
3 1 2 2 2
1 3 2 1 1
1 3 3 1 1
3 1 E
3 5
5 3 N
3 3
5 2 N
3 1
예제 출력 1
11
S F S S S
S F S S S
S F S F S
S F F S S
S F F S S


<첫 번째 라운드>

공격수: 3을 오른쪽으로 밀어서 5 개가 연쇄적으로 쓰러짐
수비수: 가장 오른쪽(3, 5)에 넘어진 것을 일으킴
<두 번째 라운드>

공격수: (5, 3) 을 위로 밀어서 2개가 연쇄적으로 쓰러짐
수비수: (3, 3)의 도미노를 세움
<세 번째 라운드>

공격수: (5, 2)을 위로 밀어서 4개가 연쇄적으로 쓰러짐
수비수: (3, 1)의 도미노를 세움
출처
Contest > 류호석배 알고리즘 코딩 테스트 > 제1회 류호석배 알고리즘 코딩 테스트 2번

문제를 검수한 사람: BaaaaaaaaaaarkingDog, dlstj0923, tony9402
문제를 만든 사람: rhs0266
알고리즘 분류
보기

채점 및 기타 정보
11개 이상의 데이터를 맞아야 를 받는다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 인내의도미노장인호석_20165 {
	private static int N;
	private static int M;
	private static int R;
	private static int[][] domino;
	private static boolean[][] visited;
	private static StringBuilder result = new StringBuilder();
	private static int finalScore = 0;

	// 동 서 남 북
	private static int[] dx = { 0, 0, 1, -1 };
	private static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		domino = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				domino[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (j == 1) {
					defence(x, y);
					continue;
				}
				char direction = st.nextToken().charAt(0);
				finalScore++;
				attack(x, y, direction);
			}
		}

		getResultCount();

		System.out.println(finalScore);
		System.out.println(result.toString());

		br.close();
	}

	private static void getResultCount() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (visited[i][j]) {
					result.append("F").append(" ");
					continue;
				}

				result.append("S").append(" ");
			}
			result.append("\n");
		}
	}

	private static void attack(int x, int y, char dir) {
		int X = x;
		int Y = y;
		int direction = direction(dir);
		visited[x][y] = true;
//	        System.out.println(finalScore + ", " + x + ", " + y);

		for (int i = 1; i < domino[x][y]; i++) {
			X += dx[direction];
			Y += dy[direction];
			if (!isValidated(X, Y)) {
				break;
			}

			if (visited[X][Y]) {
				continue;
			}

			finalScore++;

			attack(X, Y, dir);
			visited[X][Y] = true;
		}
	}

	private static boolean isValidated(int X, int Y) {
		if (X > 0 && Y > 0 && X <= N && Y <= M) {
			return true;
		}

		return false;
	}

	private static void defence(int x, int y) {
		visited[x][y] = false;
	}

	private static int direction(char dir) {
		if (dir == 'E') {
			return 0;
		}

		if (dir == 'W') {
			return 1;
		}

		if (dir == 'S') {
			return 2;
		}

		// dir == 'N'
		return 3;
	}
}