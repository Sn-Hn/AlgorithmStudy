package boj_20210223_투포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*

소수의 연속합 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	13596	5786	4099	42.380%
문제
하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.

3 : 3 (한 가지)
41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
53 : 5+7+11+13+17 = 53 (두 가지)
하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 
7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다.
또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.

자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)

출력
첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.

예제 입력 1 
20
예제 출력 1 
0
예제 입력 2 
3
예제 출력 2 
1
예제 입력 3 
41
예제 출력 3 
3
예제 입력 4 
53
예제 출력 4 
2

*/

public class 소수의연속합_1644 {
	private static int N;
	private static boolean frimeNumber[];
	private static int arr[];
	private static int count = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		frimeNumber = new boolean[N+1];
		arr = new int[N];
		
		findFrimeNumber();
		
		System.out.println(solution());
		
		br.close();
	}
	
	private static int solution() {
		int start = 0;
		int end = 0;
		long sum = 0;
		int result = 0;
		
		while(end <= count && start <= count) {
			if(sum < N) {
				sum += arr[end];
				end++;
				continue;
			}else if(sum == N){
				result++;
			}
			sum -= arr[start];
			start++;
			
		}
		
		return result;
	}
	
	private static void findFrimeNumber() {
		frimeNumber[1] = false;
		if(N == 1) return;
		
		for(int i = 2; i <= N; i++) {
			frimeNumber[i] = true;
		}
		
		for(int i = 2; i*i <= N; i++) {
			for(int j = i*i; j <= N; j+=i) {
				frimeNumber[j] = false;
			}
		}
		
		int i = 0;
		int j = 1;
		
		while(j <= N) {
			if(frimeNumber[j]) {
				arr[i] = j;
				count++;
				i++;
			}
			j++;
		}
	}
}
