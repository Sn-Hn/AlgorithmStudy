package study_210829;

/*

좋다
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	4342	1024	701	22.511%
문제
N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 “좋다(GOOD)”고 한다.

N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개인지 출력하라.

수의 위치가 다르면 값이 같아도 다른 수이다.

입력
첫째 줄에는 수의 개수 N(1 ≤ N ≤ 2,000), 두 번째 줄에는 i번째 수를 나타내는 Ai가 N개 주어진다. (|Ai| ≤ 1,000,000,000, Ai는 정수)

출력
좋은 수의 개수를 첫 번째 줄에 출력한다.

예제 입력 1 
10
1 2 3 4 5 6 7 8 9 10
예제 출력 1 
8
힌트
3,4,5,6,7,8,9,10은 좋다.



*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 좋다_1253 {
	private static int N;
	private static int[] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		int goodNumberCnt = 0;
		for (int i = 0; i < N; i++) {
			if (isFindGoodNumber(input[i], i)) {
				goodNumberCnt ++;
			}
		}
		
		System.out.println(goodNumberCnt);
		
		br.close();
	}
	
	private static boolean isFindGoodNumber(int findNumber, int index) {
		int start = 0;
		int end = N - 1;
		
		while (start < end) {
			int sum = input[start] + input[end];
			
			if (start == index) {
				start ++;
				continue;
			}
			
			if (end == index) {
				end --;
				continue;
			}
			
			if (findNumber < sum) {
				end --;
			}else if (findNumber > sum) {
				start ++;
			}else {
				return true;
			}
		}
		
		return false;
	}
}
