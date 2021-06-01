package boj_20210528_이분탐색_문자열;

/*

휴게소 세우기 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	2589	1081	790	41.843%
문제
다솜이는 유료 고속도로를 가지고 있다.
다솜이는 현재 고속도로에 휴게소를 N개 가지고 있는데, 휴게소의 위치는 고속도로의 시작으로부터 얼만큼 떨어져 있는지로 주어진다. 
 다솜이는 지금 휴게소를 M개 더 세우려고 한다.

다솜이는 이미 휴게소가 있는 곳에 휴게소를 또 세울 수 없고, 고속도로의 끝에도 휴게소를 세울 수 없다. 
휴게소는 정수 위치에만 세울 수 있다.

다솜이는 이 고속도로를 이용할 때, 모든 휴게소를 방문한다. 
다솜이는 휴게소를 M개 더 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소로 하려고 한다. (반드시 M개를 모두 지어야 한다.)

예를 들어, 고속도로의 길이가 1000이고, 현재 휴게소가 {200, 701, 800}에 있고, 휴게소를 1개 더 세우려고 한다고 해보자.

일단, 지금 이 고속도로를 타고 달릴 때, 휴게소가 없는 구간의 최댓값은 200~701구간인 501이다. 
하지만, 새로운 휴게소를 451구간에 짓게 되면, 최대가 251이 되어서 최소가 된다.

입력
첫째 줄에 현재 휴게소의 개수 N, 더 지으려고 하는 휴게소의 개수 M, 고속도로의 길이 L이 주어진다. 
N은 100보다 작거나 같으며, M도 100보다 작거나 같다. 
L은 100보다 크거나 같고, 1000보다 작거나 같은 정수이다. 
모든 휴게소의 위치는 중복되지 않으며, N+M은 L보다 작다. 
둘째 줄에, 휴게소의 위치가 공백을 사이에 두고 주어진다.

출력
첫째 줄에 M개의 휴게소를 짓고 난 후에 휴게소가 없는 구간의 최댓값의 최솟값을 출력한다.

예제 입력 1 
6 7 800
622 411 201 555 755 82
예제 출력 1 
70

*/

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class 휴게소세우기_1477 {
	private static int N;
	private static int M;
	private static int L;
	private static int[] restArea;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		restArea = new int[N + 2];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			restArea[i] = Integer.parseInt(st.nextToken());
		}
		
		restArea[0] = 0;
		restArea[N + 1] = L;
		
		Arrays.sort(restArea);
		
		int result = binarySearch();
		
		System.out.println(result);

		br.close();
	}
	
	private static int binarySearch() {
		int start = 0;
		int end = L;
		int mid = 0;
		
		while(start <= end) {
			mid = (start + end) / 2;
			int count = getCountRestArea(mid);
			
			if(M < count) {
				start = mid + 1;
			}else {
				end = mid - 1;
			}
		}
		
		return start;
	}

	private static int getCountRestArea(int maxDistance) {
		int sum = 0;
		
		for (int i = 1; i <= N + 1; i++) {
			sum += (restArea[i] - restArea[i - 1] - 1) / maxDistance;
			
		}
		
		return sum;
	}

}
