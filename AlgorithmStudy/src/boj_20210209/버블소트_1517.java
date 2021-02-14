package boj_20210209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

버블 소트 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	11100	2720	1815	28.324%
문제
N개의 수로 이루어진 수열 A[1], A[2], …, A[N]이 있다. 
이 수열에 대해서 버블 소트를 수행할 때, Swap이 총 몇 번 발생하는지 알아내는 프로그램을 작성하시오.

버블 소트는 서로 인접해 있는 두 수를 바꿔가며 정렬하는 방법이다. 
예를 들어 수열이 3 2 1 이었다고 하자. 이 경우에는 인접해 있는 3, 2가 바뀌어야 하므로 2 3 1 이 된다. 
다음으로는 3, 1이 바뀌어야 하므로 2 1 3 이 된다. 
다음에는 2, 1이 바뀌어야 하므로 1 2 3 이 된다. 그러면 더 이상 바꿔야 할 경우가 없으므로 정렬이 완료된다.

입력
첫째 줄에 N(1≤N≤500,000)이 주어진다. 
다음 줄에는 N개의 정수로 A[1], A[2], …, A[N]이 주어진다. 각각의 A[i]는 0≤|A[i]|≤1,000,000,000의 범위에 들어있다.

출력
첫째 줄에 Swap 횟수를 출력한다

예제 입력 1 
3
3 2 1
예제 출력 1 
3

*/

// https://m.blog.naver.com/PostView.nhn?blogId=fhskf94kr&logNo=221370457426&categoryNo=13&proxyReferer=https:%2F%2Fwww.google.com%2F
// 일반 버블소트로는 시간 초과 O(n^2)

public class 버블소트_1517 {
	private static int N;
	private static int arr[];
	private static int sort[];
	private static long cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sort = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		mergeSort(0, N-1);
		System.out.println(cnt);
		
		br.close();
	}
	
	// 재귀를 돌면서 계속 분할한다.
	// 한개만 남을 때 까지 (start >= end)
	private static void mergeSort(int start, int end) {	
		if(start < end) {
			int middle = (start + end)/2;
			mergeSort(start, middle);
			mergeSort( middle+1, end);
			merge(start, middle, end);
		}
	}
	
	private static void merge(int start, int middle, int end) {
		int s = start;
		int m = middle + 1;
		int e = start;
		
		while(s <= middle && m <= end) {
			if(arr[s] <= arr[m]) {
				sort[e++] = arr[s++];
			}else {
				sort[e++] = arr[m++];
				cnt += (middle + 1 - s);
			}
		}
		if(s > middle) {
			for(int i = m; i <= end; i++, e++) {
				sort[e] = arr[i];
			}
		}else {
			for(int i = s; i <= middle; i++, e++) {
				sort[e] = arr[i];
			}
		}
		
		for(int i = start; i <= end; i++) {
			arr[i] = sort[i];
		}
		
	}
	
}
