package study_210801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 공정컨설턴트호석_22254 {
	private static int N;
	private static long X;
	private static long[] present;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Long.parseLong(st.nextToken());
		present = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			present[i] = Long.parseLong(st.nextToken());
		}
		
		int minLineCount = getMinLineCount();
		
		System.out.println(minLineCount);
		
		br.close();
	}
	
	private static int getMinLineCount() {
		int start = 1;
		int end = N;
		int mid = 0;
		
		while (start <= end) {
			mid = (start + end) / 2;
			
			long minTime = getMinTime(mid);
			
			if (minTime <= X) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		
		return start;
	}
	
	private static long getMinTime(int count) {
		PriorityQueue<Long> q = new PriorityQueue<Long>();
		int index = count;
		long firstTime = 0;
		long maxTime = 0;
		for (int i = 0; i < count; i++) {
			q.add(present[i]);
		}
		
		while (!q.isEmpty()) {
			firstTime = q.poll();
			
			if (index == N) {
				break;
			}
			
			firstTime += present[index++];
			maxTime = Math.max(maxTime, firstTime);
			q.add(firstTime);
		}
		
		
		return maxTime;
	}
}
