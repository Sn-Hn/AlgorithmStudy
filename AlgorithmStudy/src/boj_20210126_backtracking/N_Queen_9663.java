package boj_20210126;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

N-Queen 성공분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
10 초	128 MB	32297	17517	11511	53.817%
문제
N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. (1 ≤ N < 15)

출력
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

예제 입력 1 
8
예제 출력 1 
92

*/


// 기본적인 백트래킹
// 퀸이 놓아질 수 있는 자리는 Row당 한 개씩 올 수 있다.
// 또한, Col당 한 개씩 올 수 있다.
// Row당 하나 씩이지만 Col과도 겹치지 않게
// 퀸이므로 대각선도 올 수 없다.
// 대각선은 queen의 인덱스의 차이와 queen의 값이 같다면 대각선이다.
// 즉 queen배열의 인덱스는 Row이고 queen의 값은 Col이다.

public class N_Queen_9663 {
	private static int N;
	private static int queen[];
	private static boolean visited[];
	private static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		queen = new int[N];
		visited = new boolean[N];
		
		permutation(0);
		System.out.println(count);
		
		br.close();
	}
	
	// 순열을 뽑음
	private static void permutation(int depth) {
		if(depth == N) {
			count += 1;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				queen[depth] = i;
				// 대각선 체크
				if(isCheck(depth)) {
					permutation(depth+1);					
				}
				visited[i] = false;
			}
		}
	}
	
	private static boolean isCheck(int row) {
		for(int i = 0; i < row; i++) {
			// 대각선
			// 서로의 인덱스의 차이와 값의 차이가 같다면 대각선
			// ex)  0, 2  <=>  queen[0], queen[2]
			// - 0번째 row, 2번째 row <=> queen[0] = 2, queen[2] = 4
			// 두칸 <=> 두칸 이므로 대각선
			if(Math.abs(row - i) == Math.abs(queen[row] - queen[i])) {
				return false;
			}
		}
		return true;
	}
}
