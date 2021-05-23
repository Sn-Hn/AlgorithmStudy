package boj_20210413_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

⚾ 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초 (추가 시간 없음) (하단 참고)	512 MB	6379	3340	1998	49.285%
문제
⚾는 9명으로 이루어진 두 팀이 공격과 수비를 번갈아 하는 게임이다. 
하나의 이닝은 공격과 수비로 이루어져 있고, 총 N이닝 동안 게임을 진행해야 한다. 
한 이닝에 3아웃이 발생하면 이닝이 종료되고, 두 팀이 공격과 수비를 서로 바꾼다.

두 팀은 경기가 시작하기 전까지 타순(타자가 타석에 서는 순서)을 정해야 하고, 경기 중에는 타순을 변경할 수 없다. 
9번 타자까지 공을 쳤는데 3아웃이 발생하지 않은 상태면 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다. 
타순은 이닝이 변경되어도 순서를 유지해야 한다. 
예를 들어, 2이닝에 6번 타자가 마지막 타자였다면, 3이닝은 7번 타자부터 타석에 선다.

공격은 투수가 던진 공을 타석에 있는 타자가 치는 것이다. 
공격 팀의 선수가 1루, 2루, 3루를 거쳐서 홈에 도착하면 1점을 득점한다. 
타자가 홈에 도착하지 못하고 1루, 2루, 3루 중 하나에 머물러있을 수 있다. 
루에 있는 선수를 주자라고 한다. 이닝이 시작될 때는 주자는 없다.

타자가 공을 쳐서 얻을 수 있는 결과는 안타, 2루타, 3루타, 홈런, 아웃 중 하나이다. 
각각이 발생했을 때, 벌어지는 일은 다음과 같다.

안타: 타자와 모든 주자가 한 루씩 진루한다.
2루타: 타자와 모든 주자가 두 루씩 진루한다.
3루타: 타자와 모든 주자가 세 루씩 진루한다.
홈런: 타자와 모든 주자가 홈까지 진루한다.
아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.
한 야구팀의 감독 아인타는 타순을 정하려고 한다. 
아인타 팀의 선수는 총 9명이 있고, 1번부터 9번까지 번호가 매겨져 있다. 
아인타는 자신이 가장 좋아하는 선수인 1번 선수를 4번 타자로 미리 결정했다. 
이제 다른 선수의 타순을 모두 결정해야 한다. 
아인타는 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있다. 
가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구해보자.

입력
첫째 줄에 이닝 수 N(2 ≤ N ≤ 50)이 주어진다. 
둘째 줄부터 N개의 줄에는 각 선수가 각 이닝에서 얻는 결과가 1번 이닝부터 N번 이닝까지 순서대로 주어진다. 
이닝에서 얻는 결과는 9개의 정수가 공백으로 구분되어져 있다. 각 결과가 의미하는 정수는 다음과 같다.

안타: 1
2루타: 2
3루타: 3
홈런: 4
아웃: 0
각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재한다.

출력
아인타팀이 얻을 수 있는 최대 점수를 출력한다.

예제 입력 1 
2
4 0 0 0 0 0 0 0 0
4 0 0 0 0 0 0 0 0
예제 출력 1 
1
예제 입력 2 
2
4 0 0 0 1 1 1 0 0
0 0 0 0 0 0 0 0 0
예제 출력 2 
4
예제 입력 3 
2
0 4 4 4 4 4 4 4 4
0 4 4 4 4 4 4 4 4
예제 출력 3 
43
예제 입력 4 
2
4 3 2 1 0 4 3 2 1
1 2 3 4 1 2 3 4 0
예제 출력 4 
46
예제 입력 5 
9
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
예제 출력 5 
216
예제 입력 6 
9
1 2 4 3 0 2 1 0 3
1 2 1 2 0 0 0 0 1
3 4 2 3 1 2 3 4 0
0 1 2 3 4 2 1 0 0
0 0 0 0 0 0 1 4 4
0 4 0 4 0 4 0 4 0
0 4 2 2 2 2 2 2 2
1 1 1 1 1 1 1 1 0
0 2 0 3 0 1 0 2 0
예제 출력 6 
89

*/

public class 야구_17281 {
	private static int N;
	private static int hit[][];
	private static int order[];
	private static int field[];
	private static int out = 0;
	private static boolean visited[];
	private static int maxScore = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		hit = new int[N + 1][10];
		order = new int[10];
		visited = new boolean[10];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				hit[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		setHitterOrder(1);

		System.out.println(maxScore);
		br.close();
	}

	private static void game() {
		int sc = 0;
		int hitter = 1;
		for (int i = 1; i <= N; i++) {
			field = new int[4];
			while (true) {
				sc = score(hit[i][order[hitter]], sc);
//				System.out.println(i + " 이닝, 타자 : " + order[hitter] + ", score : " + sc + ", hit : " + hit[i][order[hitter]]);
				hitter++;
				if (hitter == 10) {
					hitter = 1;
				}
				if (out == 3) {
					out = 0;
					break;
				}
			}
		}
		maxScore = Math.max(maxScore, sc);
	}

	private static int score(int hit, int score) {
		if (hit == 0) {
			out++;
			return score;
		}

		field[0] = 1;
		// 안된 이유 1) 0 ~ 3까지 도는데 185에서 field[i+hit] = 1에서 다음수에 영향을 주기 때문
		for (int i = 3; i >= 0; i--) {
			if (field[i] == 1) {
				if (i + hit >= 4) {
					field[i] = 0;
					score += 1;
				} else {
					// 안된 이유 2) 타자가 출발했으면 그 자리는 0이 되어야 한다.
					field[i] = 0;
					field[i + hit] = 1;
				}
			}
		}

		return score;
	}

	private static void setHitterOrder(int depth) {
		if (depth == 10 && order[4] == 1) {
			game();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				setHitterOrder(depth + 1);
				visited[i] = false;
			}
		}
	}
}
