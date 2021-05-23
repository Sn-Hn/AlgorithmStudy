package boj_20210420_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*

캐슬 디펜스 분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
1 초   512 MB   16711   5874   3369   30.264%
문제
캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다. 게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다.
격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다. 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.

성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다.
각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다.
궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다.
같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다.
궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 모든 적이 격자판에서 제외되면 게임이 끝난다.

게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다.
따라서, 이 게임은 궁수의 위치가 중요하다. 격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.

격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.

입력
첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다.
둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.

출력
첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.

제한
3 ≤ N, M ≤ 15
1 ≤ D ≤ 10
예제 입력 1
5 5 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
예제 출력 1
3
예제 입력 2
5 5 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
예제 출력 2
3
예제 입력 3
5 5 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
예제 출력 3
5
예제 입력 4
5 5 5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
예제 출력 4
15
예제 입력 5
6 5 1
1 0 1 0 1
0 1 0 1 0
1 1 0 0 0
0 0 0 1 1
1 1 0 1 1
0 0 1 0 0
예제 출력 5
9
예제 입력 6
6 5 2
1 0 1 0 1
0 1 0 1 0
1 1 0 0 0
0 0 0 1 1
1 1 0 1 1
0 0 1 0 0
예제 출력 6
14

4 5 3
1 1 1 0 0
1 0 1 0 1
0 0 0 0 1
1 1 1 1 1



*/

// 조건
// 0. 궁수는 성이 있는 칸에 배치할 수 있다.
// 1. 하나의 칸에는 한명의 궁수만 존재
// 2. 각각 턴마다 궁수는 하나씩 공격
// 3. 모든 궁수는 동시에 공격
// 4. 궁수는 거리가 D이하인 적 중에서 가장 가까운 적을 공격
// 5. 그러한 적이 여럿이면 가장 왼쪽에 있는 적을 공격
// 6. 같은 적이 서로 다른 궁수에게 공격당할 수 있다.
// 7. 공격받은 적은 게임에서 제외
// 8. 공격이 끝나면 적이 아래로 한 칸 이동
// 9. 성이 있는 칸으로 이동하면 게임에서 제외
// 10. 모든 적이 격자판에서 제외되면 게임 끝
// 11. 격자판의 두 위치(x1, y1), (x2, y2)의 거리는 |x1 - x2| + |y1 - y2| 이다.
//     즉, (1, 2), (2, 3)의 거리는 1 + 1 = 2이다.

// 궁수는 무조건 성에 놓는 것이 좋다.
public class 캐슬디펜스_17135 {
	private static int N, M, D;
	private static int map[][];
	private static int copyMap[][];
	private static int copyMap2[][];
	private static boolean[] visited;
	private static int max = -1;
	private static int result[] = new int[3];

	private static Pair[] archersList;

	private static int dx[] = { 0, 0, -1 };
	private static int dy[] = { -1, 1, 0 };

	private static class Pair {
		int x, y, index;

		public Pair(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M];
		copyMap = new int[N + 1][M];
		copyMap2 = new int[N + 1][M];
		visited = new boolean[M];
		archersList = new Pair[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
			}
		}

		archerPosition(0);

		System.out.println(max);

		for (int i : result)
			System.out.print(i + " ");
		System.out.println();

		br.close();
	}

	private static int attack() {
		Pair[] attackEnemy = new Pair[3];

		Queue<Pair> q = new LinkedList<Pair>();
		for (Pair pair : archersList) {
			q.add(pair);
		}

		int index = N - 1;
		int sum = 0;
		Pair samePair = null;
		int cnt = 0;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			if (isEnd()) {
				break;
			}

			if (p.x == 0 || p.index == -1)
				break;

			Pair pair = null;
			int dis = 0;
			int min = 10000;

			LOOP: for (int j = M - 1; j >= 0; j--) {
				for (int i = p.index; i >= 0; i--) {
					if (p.x - i > D)
						break;
					if (copyMap[i][j] != 0) {
						dis = Math.abs(i - p.x) + Math.abs(j - p.y);
						if (dis <= min && dis <= D) {
							min = dis;
							pair = new Pair(i, j, i);
						}
					}
				}
			}

			if (min <= D) {
				attackEnemy[cnt] = pair;
			}
			cnt++;
			if (cnt == 3) {
				cnt = 0;
				for (int i = 0; i < attackEnemy.length; i++) {
					if (attackEnemy[i] != null && copyMap[attackEnemy[i].x][attackEnemy[i].y] == 1) {
						copyMap[attackEnemy[i].x][attackEnemy[i].y] = 0;
						sum++;
					}
				}

			}

			q.add(new Pair(p.x - 1, p.y, p.index - 1));
		}

		return sum;
	}

	private static void archerPosition(int depth) {
		if (depth == 3) {
			copyMap();
//            max = Math.max(max, attack());
			int attack = attack();
			if (max < attack) {
				max = attack;
				for (int i = 0; i < 3; i++) {
					result[i] = archersList[i].y;
				}
			}
			return;
		}

		for (int i = 0; i < M; i++) {
			if (!visited[i]) {
				visited[i] = true;
				archersList[depth] = new Pair(N, i, N - 1);
				archerPosition(depth + 1);
				visited[i] = false;
			}
		}
	}

	private static boolean isEnd() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 1) {
					return false;
				}
			}
		}

		return true;
	}

	private static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
				copyMap2[i][j] = map[i][j];
			}
		}
	}

	private static void copyMap2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = copyMap2[i][j];
			}
		}
	}
}