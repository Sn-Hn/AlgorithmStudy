package boj_20210105_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

레이저 통신 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	4974	1672	1133	31.247%
문제
크기가 1×1인 정사각형으로 나누어진 W×H 크기의 지도가 있다. 지도의 각 칸은 빈 칸이거나 벽이며, 두 칸은 'C'로 표시되어 있는 칸이다.

'C'로 표시되어 있는 두 칸을 레이저로 통신하기 위해서 설치해야 하는 거울 개수의 최솟값을 구하는 프로그램을 작성하시오. 레이저로 통신한다는 것은 두 칸을 레이저로 연결할 수 있음을 의미한다.

레이저는 C에서만 발사할 수 있고, 빈 칸에 거울('/', '\')을 설치해서 방향을 90도 회전시킬 수 있다. 

아래 그림은 H = 8, W = 7인 경우이고, 빈 칸은 '.', 벽은 '*'로 나타냈다. 왼쪽은 초기 상태, 오른쪽은 최소 개수의 거울을 사용해서 두 'C'를 연결한 것이다.

7 . . . . . . .         7 . . . . . . .
6 . . . . . . C         6 . . . . . /-C
5 . . . . . . *         5 . . . . . | *
4 * * * * * . *         4 * * * * * | *
3 . . . . * . .         3 . . . . * | .
2 . . . . * . .         2 . . . . * | .
1 . C . . * . .         1 . C . . * | .
0 . . . . . . .         0 . \-------/ .
  0 1 2 3 4 5 6           0 1 2 3 4 5 6
입력
첫째 줄에 W와 H가 주어진다. (1 ≤ W, H ≤ 100)

둘째 줄부터 H개의 줄에 지도가 주어진다. 지도의 각 문자가 의미하는 것은 다음과 같다.

.: 빈 칸
*: 벽
C: 레이저로 연결해야 하는 칸
'C'는 항상 두 개이고, 레이저로 연결할 수 있는 입력만 주어진다.

출력
첫째 줄에 C를 연결하기 위해 설치해야 하는 거울 개수의 최솟값을 출력한다.

예제 입력 1 
7 8
.......
......C
......*
*****.*
....*..
....*..
.C..*..
.......
예제 출력 1 
3

*/

public class LaserCommunication_6087 {
	private static int W, H;
	private static char map[][];
	private static int visited[][];
	private static List<Pair> laserPosition = new ArrayList<Pair>();
	private static int min = Integer.MAX_VALUE;

	private static int dx[] = { 0, 0, 1, -1 };
	private static int dy[] = { 1, -1, 0, 0 };

	private static class Pair {
		int x, y, mirrorCnt, direction;

		public Pair(int x, int y, int mirrorCnt, int direction) {
			this.x = x;
			this.y = y;
			this.mirrorCnt = mirrorCnt;
			this.direction = direction;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visited = new int[H][W];

		for (int i = 0; i < H; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				// 레이저 위치 저장
				if (map[i][j] == 'C') {
					laserPosition.add(new Pair(i, j, 0, -1));
				}
			}
		}

		findMirror();
		System.out.println(min);

		br.close();
	}

	private static void findMirror() {
		Queue<Pair> q = new LinkedList<Pair>();
		// 전달방향을 모르니 초기 direction을 -1로 줌
		q.add(new Pair(laserPosition.get(0).x, laserPosition.get(0).y, 0, -1));
		visited[q.peek().x][q.peek().y] = 0;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			// 다른 레이저에게 도달했을 때 최소값 갱신
			if (p.x == laserPosition.get(1).x && p.y == laserPosition.get(1).y) {
				min = Math.min(min, p.mirrorCnt);
			}

			for (int i = 0; i < 4; i++) {
				int X = p.x + dx[i];
				int Y = p.y + dy[i];

				if (X >= 0 && X < H && Y >= 0 && Y < W && map[X][Y] != '*') {
					// 초기 값이거나 전달방향이 같다면 거울의 수는 그대로
					if (p.direction == -1 || p.direction == i) {
						// X, Y까지의 거울의 수가 현재 거울의 수보다 크다면 갱신
						if (visited[X][Y] >= p.mirrorCnt) {
							visited[X][Y] = p.mirrorCnt;
							q.add(new Pair(X, Y, p.mirrorCnt, i));
						}
						// 전달 방향이 달라졌다면 거울의 수는 하나 증가
					} else {
						// X, Y까지의 거울의 수가 현재 거울의 수보다 크다면 갱신
						if (visited[X][Y] >= p.mirrorCnt + 1) {
							visited[X][Y] = p.mirrorCnt + 1;
							q.add(new Pair(X, Y, p.mirrorCnt + 1, i));
						}
					}

				}

			}
		}

	}
}
