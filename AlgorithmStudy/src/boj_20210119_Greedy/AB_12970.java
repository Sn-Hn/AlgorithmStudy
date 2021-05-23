package boj_20210119_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

AB 스페셜 저지분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	961	384	306	40.691%
문제
정수 N과 K가 주어졌을 때, 다음 두 조건을 만족하는 문자열 S를 찾는 프로그램을 작성하시오.

문자열 S의 길이는 N이고, 'A', 'B'로 이루어져 있다.
문자열 S에는 0 ≤ i < j < N 이면서 s[i] == 'A' && s[j] == 'B'를 만족하는 (i, j) 쌍이 K개가 있다.
입력
첫째 줄에 N과 K가 주어진다. (2 ≤ N ≤ 50, 0 ≤ K ≤ N(N-1)/2)

출력
첫째 줄에 문제의 조건을 만족하는 문자열 S를 출력한다. 가능한 S가 여러 가지라면, 아무거나 출력한다. 만약, 그러한 S가 존재하지 않는 경우에는 -1을 출력한다.

예제 입력 1 
3 2
예제 출력 1 
ABB
예제 입력 2 
2 0
예제 출력 2 
BA
예제 입력 3 
5 8
예제 출력 3 
-1
예제 입력 4 
10 12
예제 출력 4 
BAABBABAAB

*/

public class AB_12970 {
	private static int N, K;
	private static String str[];
	private static int A_Index[];
	private static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		str = new String[N];

		Arrays.fill(str, "B");

		findString();

		br.close();
	}

	private static void findString() {
		int a = 0;
		int b = 0;
		int sub = 0;
		for (int i = 1; i < N; i++) {
			if (i * (N - i) >= K) {
				// 조건을 만족
				flag = true;
				a = i;
				b = N - i;
				break;
			}
		}

		sub = a * b - K;

		// A의 위치들이 들어갈 배열
		A_Index = new int[a];

		// A의 위치를 구하는 반복문
		for (int i = a - 1; i >= 0; i--) {
			// AB 쌍의 최대 개수에서 K를 뺸값이 b보다 크다면
			// b보다 크다는 것은 마지막A의 인덱스가 N을 넘어선다는 뜻이므로
			// 마지막 A가 배열의 끝에 올 수 있도록 구현
			// 그런 후 sub에서 b를 뺸다
			if (sub > b) {
				A_Index[i] = b + i;
				sub -= b;

				// sub가 b보다 작다면 마지막 A만 움직여서 K를 만들 수 있다는 뜻
			} else {
				A_Index[i] = sub + i;
				sub = 0;
			}
		}

		// A의 위치를 구했으니 str배열에 A를 넣음
		for (int i = 0; i < a; i++) {
			str[A_Index[i]] = "A";
		}

		if (flag) {
			for (int i = 0; i < N; i++) {
				System.out.print(str[i]);
			}
			// !flag -> 조건을 만족하는 문자열이 없다는 뜻
		} else {
			System.out.println(-1);
		}

	}
}
