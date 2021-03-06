package boj_20210223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

세 용액 스페셜 저지출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	7113	1841	1321	24.887%
문제
KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다. 
각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다. 
산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고, 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.

같은 양의 세 가지 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다. 
이 연구소에서는 같은 양의 세 가지 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다. 

예를 들어, 주어진 용액들의 특성값이 [-2, 6, -97, -6, 98]인 경우에는 특성값이 -97와 -2인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고, 이 용액이 특성값이 0에 가장 가까운 용액이다. 
참고로, 세 종류의 알칼리성 용액만으로나 혹은 세 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.

산성 용액과 알칼리성 용액이 주어졌을 때, 이 중 같은 양의 세 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 세 용액을 찾는 프로그램을 작성하시오.

입력
첫째 줄에는 전체 용액의 수 N이 입력된다. N은 3 이상 5,000 이하의 정수이다. 
둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다. 
이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다. 
N개의 용액들의 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.

출력
첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 세 용액의 특성값을 출력한다. 
출력해야하는 세 용액은 특성값의 오름차순으로 출력한다. 
특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.

예제 입력 1 
5
-2 6 -97 -6 98
예제 출력 1 
-97 -2 98

*/

public class 세용액_2473 {
	private static int N;
	private static long arr[];
	private static long a, b, c;
	private static long result = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		solution();
		System.out.println(a + " " + b + " " + c);
		
		br.close();
	}
	
	private static void solution() {
		int start = 0;
		int end = N-1;
		int middle = start + 1;
		long sum = 0;
		
		while(true) {
			sum = arr[start] + arr[middle] + arr[end];
			
			if(result > Math.abs(sum)) {
				result = Math.abs(sum);
				a = arr[start];
				b = arr[middle];
				c = arr[end];
			}
			if(sum > 0) {
				end--;
			}else if(sum < 0) {
				middle++;
			}else {
				a = arr[start];
				b = arr[middle];
				c = arr[end];
				break;
			}
			
			if(middle == end) {
				start++;
				middle = start + 1;
			}
			
			if(start+1 >= end) {
				break;
			}
		}
		
	}
}
