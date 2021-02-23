package solution109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static long min;
	static long val[];
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		long num[] = new long[n];
		
		int start = 0;
		int end = n-1;
		min = Integer.MAX_VALUE;
		val = new long[2];
		String tmp[] = br.readLine().split(" ");
		for(int i = 0 ;i < n ; i++) {
			num[i] = Integer.parseInt(tmp[i]);
		}
		
		Arrays.sort(num);
		
		boolean flag = false;
		while(start < end) {
			if(num[start]+num[end] < 0) { //0 보다 작으니까 0에 가까워지려면 더 커져야 한다.
				if(judge(start,end,num)) {
					min = Math.abs(num[start]+num[end]);
					val[0] = num[start];
					val[1] = num[end];
				}
				start++;
			}else if(num[start]+num[end] > 0 ) { // 0보다 작으니까 가까워지려면 작아져야한다.
				if(judge(start,end,num)) {
					min = Math.abs(num[start]+num[end]);
					val[0] = num[start];
					val[1] = num[end];
				}
				end--;
			}else {
				System.out.println(num[start]+" "+num[end]);
				flag = true;
				break;
			}
			
		}
		
		if(!flag) {
			System.out.println(val[0]+" "+val[1]);
		}
		
	}
	
	 public static boolean judge(int a,int b,long num[]) {
		 if(Math.abs(num[a]+num[b]) <min) {
			 return true;
		 }
		return false;
		 
	 }
	
	
	
}
