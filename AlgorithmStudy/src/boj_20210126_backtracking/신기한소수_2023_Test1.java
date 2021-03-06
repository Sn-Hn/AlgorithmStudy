package boj_20210126_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

신기한 소수 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	4 MB	4241	1991	1528	46.275%
문제
수빈이가 세상에서 가장 좋아하는 것은 소수이고, 취미는 소수를 가지고 노는 것이다. 
요즘 수빈이가 가장 관심있어 하는 소수는 7331이다.

7331은 소수인데, 신기하게도 733도 소수이고, 73도 소수이고, 7도 소수이다. 
즉, 왼쪽부터 1자리, 2자리, 3자리, 4자리 수 모두 소수이다! 수빈이는 이런 숫자를 신기한 소수라고 이름 붙였다.

수빈이는 N자리의 숫자 중에서 어떤 수들이 신기한 소수인지 궁금해졌다. 
N이 주어졌을 때, 수빈이를 위해 N자리 신기한 소수를 모두 찾아보자.

입력
첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다.

출력
N자리 수 중에서 신기한 소수를 오름차순으로 정렬해서 한 줄에 하나씩 출력한다.

예제 입력 1 
4
예제 출력 1 
2333
2339
2393
2399
2939
3119
3137
3733
3739
3793
3797
5939
7193
7331
7333
7393

*/

// N자리의 숫자들을 순열로 뽑은 후 각 자리를 구하면서 소수인지 판별하면 된다
// 시간이 매우 오래걸린다.

public class 신기한소수_2023_Test1 {
	private static int N;
	private static int result[];
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = new int[N];
		
		permutation(0);
		
		System.out.println(sb);
		
		br.close();
	}
	
	private static void permutation(int depth) {
		if(depth == N) {
			if(isChecked(result)) {
				for(int i : result) {
					sb.append(i);
				}
				sb.append("\n");
			}
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			result[depth] = i+1;
			permutation(depth+1);
		}
	}
	
	private static boolean isChecked(int[] arr) {
		int ans = 0;
		for(int i : arr) {
			ans += i;
			if(!findFrimeNumber(ans)) {
				return false;
			}
			ans *= 10;
		}
		
		
		return true;
	}
	
	private static boolean findFrimeNumber(int a) {
		if(a == 1) return false;
		if(a == 2) return true;
		
		for(int i = 2; i <= Math.sqrt(a); i++) {
			if(a%i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
