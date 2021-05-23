package boj_20210126_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

주사위 윷놀이 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	6788	2583	1498	34.123%
문제
주사위 윷놀이는 다음과 같은 게임판에서 하는 게임이다.



처음에는 시작 칸에 말 4개가 있다.
말은 게임판에 그려진 화살표의 방향대로만 이동할 수 있다. 
말이 파란색 칸에서 이동을 시작하면 파란색 화살표를 타야 하고, 이동하는 도중이거나 파란색이 아닌 칸에서 이동을 시작하면 빨간색 화살표를 타야 한다. 
말이 도착 칸으로 이동하면 주사위에 나온 수와 관계 없이 이동을 마친다.
게임은 10개의 턴으로 이루어진다. 
매 턴마다 1부터 5까지 한 면에 하나씩 적혀있는 5면체 주사위를 굴리고, 도착 칸에 있지 않은 말을 하나 골라 주사위에 나온 수만큼 이동시킨다.
말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 
단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
말이 이동을 마칠 때마다 칸에 적혀있는 수가 점수에 추가된다.
주사위에서 나올 수 10개를 미리 알고 있을 때, 얻을 수 있는 점수의 최댓값을 구해보자.

입력
첫째 줄에 주사위에서 나올 수 10개가 순서대로 주어진다.

출력
얻을 수 있는 점수의 최댓값을 출력한다.

예제 입력 1 
1 2 3 4 1 2 3 4 1 2
예제 출력 1 
190
예제 입력 2 
1 1 1 1 1 1 1 1 1 1
예제 출력 2 
133
예제 입력 3 
5 1 2 3 4 5 5 3 2 4
예제 출력 3 
214
예제 입력 4 
5 5 5 5 5 5 5 5 5 5
예제 출력 4 
130

*/

// 말은 총 4개
// 나온 주사위 수를 더해가면서 그 수가 5의 배수가 되었다면 조건에 따라 처리 
// 말이 하나만 출발했을 때 ~ 4개 전부 출발했을 때를 전부 비교해가면서 최대값을 출력
// 말이 이동할 때마다 점수판에 있는 점수 획득

public class 주사위윷놀이_17825 {
	private static int dice[] = new int[10];
	private static int horse[] = new int[4];

	// -1은 해당 말이 도착
	private static int dice10[] = { 0, 13, 16, 19, 25 };
	private static int dice20[] = { 0, 22, 24, 25 };
	private static int dice25[] = { 0, 30, 35, 40, -1, -1 };
	private static int dice30[] = { 0, 28, 27, 26, 25 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		br.close();
	}

	private static void dice() {
		int sum = 0;
		int diceDir = 0;
		int diceNum = 0;
		// 말이 지름길로 빠지는 것을 체크하기 위한 변수
		// 지름길로 빠지는 순간 정상 궤도로 나갈 수 없기 때문에 boolean으로 체크
		boolean flag = false;
		for (int i = 0; i < 10; i++) {
			if (diceNum == 10) {
				diceNum = dice10[dice[i]];
				sum += diceNum;
				flag = true;
			} else if (diceNum == 20) {
				sum += dice20[dice[i]];
				flag = true;
			} else if (diceNum == 25) {
				sum += dice25[dice[i]];
				flag = true;
			} else if (diceNum == 30) {
				sum += dice30[dice[i]];
				flag = true;
			}
			if (!flag) {
				diceNum += dice[i] * 2;
				diceDir += dice[i];
				sum += diceNum;
			}
		}
	}
}
