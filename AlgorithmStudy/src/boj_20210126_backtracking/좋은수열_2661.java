package boj_20210126;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

좋은수열 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	6560	3381	2608	52.976%
문제
숫자 1, 2, 3으로만 이루어지는 수열이 있다. 
임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면, 그 수열을 나쁜 수열이라고 부른다. 
그렇지 않은 수열은 좋은 수열이다.

다음은 나쁜 수열의 예이다.

33
32121323
123123213
다음은 좋은 수열의 예이다.

2
32
32123
1232123
길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램을 작성하라. 
예를 들면, 1213121과 2123212는 모두 좋은 수열이지만 그 중에서 작은 수를 나타내는 수열은 1213121이다.

입력
입력은 숫자 N하나로 이루어진다. N은 1 이상 80 이하이다.

출력
첫 번째 줄에 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서 가장 작은 수를 나타내는 수열만 출력한다. 
수열을 이루는 1, 2, 3들 사이에는 빈칸을 두지 않는다.

예제 입력 1 
7
예제 출력 1 
1213121

*/

// 123중에 중복을 허용하여 N까지 뽑는 것
// 하나의 값이 그 전의 값과 같다면 X
public class 좋은수열_2661 {
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		permutation("");
		
		br.close();
	}
	
	private static void permutation(String str) {
		if(str.length() == N) {
			System.out.println(str);
			System.exit(0);
		}
		
		for(int i = 0; i < 3; i++) {
			if(isCheck(str + (i+1))) {
				permutation(str + (i+1));
			}
		}
	}
	
	private static boolean isCheck(String str) {
		int len = str.length()/2;
		// Str의 끝에서부터 하나씩 증가하여 절반까지 간 후 대칭을 비교
		// i == 1 ) str의 맨 끝의 자리와 그 대칭 자리  검사
		// i == 2 ) str의 맨 끝에서부터 2자리와 그 대칭 2자리 검사
		// ...
		// i == len ) str의 끝에서 절반 그 대칭 절반을 검사
		for(int i = 1; i <= len; i++) {
			String front = str.substring(str.length() - 2*i, str.length() - i);
			String back = str.substring(str.length() - i);
			if (back.equals(front)) {
                return false;
            }
		}
		return true;
	}
}
