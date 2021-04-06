package boj_20210406_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/*

수 묶기 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	14378	3933	3178	26.864%
문제
길이가 N인 수열이 주어졌을 때, 그 수열의 합을 구하려고 한다. 
하지만, 그냥 그 수열의 합을 모두 더해서 구하는 것이 아니라, 수열의 두 수를 묶으려고 한다. 
어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다. 
하지만, 같은 위치에 있는 수(자기 자신)를 묶는 것은 불가능하다. 
그리고 어떤 수를 묶게 되면, 수열의 합을 구할 때 묶은 수는 서로 곱한 후에 더한다.

예를 들면, 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때, 그냥 이 수열의 합을 구하면 0+1+2+4+3+5 = 15이다.
하지만, 2와 3을 묶고, 4와 5를 묶게 되면, 0+1+(2*3)+(4*5) = 27이 되어 최대가 된다.

수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.

수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.

입력
첫째 줄에 수열의 크기 N이 주어진다. N은 10,000보다 작은 자연수이다. 
둘째 줄부터 N개의 줄에, 수열의 각 수가 주어진다. 수열의 수는 -10,000보다 크거나 같고, 10,000보다 작거나 같은 정수이다.

출력
수를 합이 최대가 나오게 묶었을 때 합을 출력한다. 정답은 항상 231보다 작다.

예제 입력 1 
4
-1
2
1
3
예제 출력 1 
6

*/

public class 수묶기_1744 {
	private static int N;
	private static Integer arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new Integer[N];
		int positiveCount = 0;
		boolean isZero = false;
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			if(a > 0) positiveCount++;
			if(a == 0) isZero = true;
			arr[i] = a;
		}
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		int sum = 0;
		
		// 양수들의 합
		for(int i = 0; i < positiveCount-1; i+=2) {
			if(arr[i+1] == 1) {
				sum += arr[i] + arr[i+1];
				continue;
			}
			sum += arr[i]*arr[i+1];
		}
		if(positiveCount%2 == 1) 
			sum += arr[positiveCount-1];
		
		
		// 음수들의 합
		int negativeCount = N - positiveCount;
		for(int i = N-1; i >= positiveCount+1; i-=2) {
			sum += arr[i]*arr[i-1];
		}

		if(negativeCount%2 == 1)
			sum += arr[positiveCount];
		
//		for(int i : arr) System.out.print(i + " ");
		System.out.println(sum);
		br.close();
	}
}
