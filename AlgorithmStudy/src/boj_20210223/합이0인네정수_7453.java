package boj_20210223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
	private static long arr[][];
	private static long A[], B[], C[], D[];
	private static long AB[], CD[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new long[N];
		B = new long[N];
		C = new long[N];
		D = new long[N];		
		AB = new long[N*N];
		CD = new long[N*N];		
		
		for(int i = 0; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		int idx = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx] = C[i] + D[j];
				idx++;
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		System.out.println(solution());
		
		br.close();
	}
	
	private static long solution() {
		int start = 0;
		int end = N*N-1;
		long result = 0;
		int s = 0;
		int e = 0;
		long sum = 0;
		
		while(end > 0 && start < N*N) {
			sum = AB[start] + CD[end];
			if(sum > 0) {
				end--;
			}else if(sum < 0) {
				start++;
			}else {
				s = start + 1;
				e = end - 1;
				while(e > 0 && AB[start] + CD[e] == 0) {
					e--;
				}
				while(start < N*N && AB[s] + CD[end] == 0) {
					s++;
				}
				result += (s-start) * (end-e);
				start = s;
				end = e;
				
			}
		}
		
		return result;
	}
}
