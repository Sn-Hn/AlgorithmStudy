package boj_20210406_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*

배 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	6294	1429	1057	25.191%
문제
지민이는 항구에서 일한다. 그리고 화물을 배에 실어야 한다. 
모든 화물은 박스에 안에 넣어져 있다. 
항구에는 크레인이 N대 있고, 1분에 박스를 하나씩 배에 실을 수 있다. 
모든 크레인은 동시에 움직인다.

각 크레인은 무게 제한이 있다. 
이 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다. 
모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는 프로그램을 작성하시오.
 

입력
첫째 줄에 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 
둘째 줄에는 각 크레인의 무게 제한이 주어진다. 
이 값은 1,000,000보다 작거나 같다. 
셋째 줄에는 박스의 수 M이 주어진다. 
M은 10,000보다 작거나 같은 자연수이다. 
넷째 줄에는 각 박스의 무게가 주어진다. 
이 값도 1,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 출력한다. 
만약 모든 박스를 배로 옮길 수 없으면 -1을 출력한다.

예제 입력 1 
3
6 8 9
5
2 5 2 4 7
예제 출력 1 
2

6 8 9
2 2 4 5 7 8 9 9

6 8 9
9 9 9 9

3
6 8 9
5
1 1 8 9 9

9 8 6
9 9 8 1 1
*/

public class 배_1092 {
	private static int N, M;
	private static List<Integer> crane;
	private static List<Integer> box;
	private static int cnt[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		crane = new ArrayList<>();
		cnt = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			crane.add(Integer.parseInt(st.nextToken()));
		}

		M = Integer.parseInt(br.readLine());
		box = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(crane, Collections.reverseOrder());
		Collections.sort(box, Collections.reverseOrder());

		if (crane.get(0) < box.get(0)) {
			System.out.println(-1);
			return;
		}
		int count = 0;
		int boxIndex;
		while (!box.isEmpty()) {
			boxIndex = 0;
			for (int i = 0; i < N; i++) {
				if (boxIndex == box.size()) {
					break;
				}
				System.out.println(boxIndex + ", " + i + " : " + box.get(boxIndex) + " , " + crane.get(i));
				if (box.get(boxIndex) <= crane.get(i)) {
					cnt[i]++;
					box.remove(boxIndex);
				} else {
					boxIndex++;
					i--;
				}
			}
			count++;
		}

		System.out.println(count);

		print();
		br.close();
	}

	private static void solve() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {

			}
		}
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(cnt[i] + " ");
		}
	}
}
