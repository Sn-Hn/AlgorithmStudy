package boj_20210528_이분탐색_문자열;

/*

놀이 공원 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	6550	1476	1013	22.347%
문제
N명의 아이들이 한 줄로 줄을 서서 놀이공원에서 1인승 놀이기구를 기다리고 있다. 
이 놀이공원에는 총 M종류의 1인승 놀이기구가 있으며, 1번부터 M번까지 번호가 매겨져 있다.

모든 놀이기구는 각각 운행 시간이 정해져 있어서, 운행 시간이 지나면 탑승하고 있던 아이는 내리게 된다. 
놀이 기구가 비어 있으면 현재 줄에서 가장 앞에 서 있는 아이가 빈 놀이기구에 탑승한다. 
만일 여러 개의 놀이기구가 동시에 비어 있으면, 더 작은 번호가 적혀 있는 놀이기구를 먼저 탑승한다고 한다.

놀이기구가 모두 비어 있는 상태에서 첫 번째 아이가 놀이기구에 탑승한다고 할 때, 줄의 마지막 아이가 타게 되는 놀이기구의 번호를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 2,000,000,000)과 M(1 ≤ M ≤ 10,000)이 빈칸을 사이에 두고 주어진다. 
둘째 줄에는 각 놀이기구의 운행 시간을 나타내는 M개의 자연수가 순서대로 주어진다. 
운행 시간은 1 이상 30 이하의 자연수이며, 단위는 분이다.

출력
첫째 줄에 마지막 아이가 타게 되는 놀이기구의 번호를 출력한다.

예제 입력 1 
22 5
1 2 3 4 5
예제 출력 1 
4

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://dundung.tistory.com/56
// https://www.acmicpc.net/problem/1561
public class 놀이공원_1561 {
	private static long N;
	private static int M;
	private static long[] rides;
	private static final long MAX = 60000000000L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		rides = new long[M + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			rides[i] = Long.parseLong(st.nextToken());
		}
		
		if(N <= M) {
			System.out.println(N);
			return;
		}
		
		long lastMinute = binarySearch();
		long lastMinuteChildCount = N - getChildrenCount(lastMinute-1);
		
		System.out.println(getLastChildNumber(lastMinute, lastMinuteChildCount));
		
		br.close();
	}
	
	private static long binarySearch() {
		long start = 0;
		long end = MAX;
		long mid = 0;
		long result = 0;
		
		while(start <= end) {
			mid = (start + end) / 2;
			long count = getChildrenCount(mid);
			
			if(count < N) {
				start = mid + 1;
			}else {
				end = mid - 1;
				result = mid;
			}
		}
		
		return result;
	}
	
	private static long getChildrenCount(long minute) {
		long count = M;
		for (int i = 1; i <= M; i++) {
			count += minute / rides[i];
		}
		
		return count;
	}
	
	private static long getLastChildNumber(long lastMinute, long count) {
		long cnt = 0;
		long index = 1;
		for (int i = 1; i <= N; i++) {
			if(lastMinute % rides[i] == 0) {
				cnt++;
			}
			
			if(cnt == count) {
				index = i;
				break;
			}
		}
		
		return index;
	}
}
