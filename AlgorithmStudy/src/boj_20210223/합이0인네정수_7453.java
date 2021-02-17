package boj_20210223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

합이 0인 네 정수 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
12 초 (추가 시간 없음)	1024 MB	18261	4431	2421	23.062%
문제
정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.

A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 배열의 크기 n (1 ≤ n ≤ 4000)이 주어진다. 
다음 n개 줄에는 A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다. 
배열에 들어있는 정수의 절댓값은 최대 228이다.

출력
합이 0이 되는 쌍의 개수를 출력한다.

예제 입력 1 
6
-45 22 42 -16
-41 -27 56 30
-36 53 -37 77
-36 30 -75 -46
26 -38 -10 62
-32 -54 -6 45
예제 출력 1 
5

*/

public class 합이0인네정수_7453 {
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N ; i++) {
			
		}
		
		
		br.close();
	}
}
