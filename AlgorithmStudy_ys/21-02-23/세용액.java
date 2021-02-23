package solution112;

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
		min = Long.MAX_VALUE;
		val = new long[3];
		
		
		String tmp[] = br.readLine().split(" ");
		for(int i = 0 ;i < n ; i++) {
			num[i] = Long.parseLong(tmp[i]);
		}
		
		Arrays.sort(num);
		
		for(int i = 0 ; i < n-2; i++) {
			int pick = i;
			int start = i+1;
			int end = n-1;
			
			while(start<end) {

				long sum = num[start]+num[end]+num[pick];
				
				if(sum < 0) {
					if(min > Math.abs(sum)) {
						min = Math.abs(sum);
						val[0] = num[pick];
						val[1] = num[start];
						val[2] = num[end];
						
					}
					
					start++;
				}else {
					if(min > Math.abs(sum)) {
						min = Math.abs(sum);
						val[0] = num[pick];
						val[1] = num[start];
						val[2] = num[end];
						
					}
					end--;
				}

			}
			
			
		}
		
		System.out.println(val[0]+" "+val[1]+" "+val[2]);
		
		
	}
}
