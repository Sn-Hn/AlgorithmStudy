package solution114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int A[] = new int[n];
		int B[] = new int[n];
		int C[] = new int[n];
		int D[] = new int[n];
		for(int i = 0 ;i < n; i++) {
			String tmp[] = br.readLine().split(" ");
			A[i] = Integer.parseInt(tmp[0]);
			B[i] = Integer.parseInt(tmp[1]);
			C[i] = Integer.parseInt(tmp[2]);
			D[i] = Integer.parseInt(tmp[3]);
		}

		long AB[] = new long[n*n];
		long CD[] = new long[n*n];
		int cnt = 0;
		for(int i = 0 ; i < n;i++) {
			for(int j = 0 ; j < n ;j++) {
				AB[cnt] = A[i]+B[j];
				CD[cnt] = C[i]+D[j];
				cnt++;
			}
		}
		
	
		
		int start = 0;
		int end = n*n-1;
		cnt = 0;
		Arrays.sort(AB);
		Arrays.sort(CD);
		long ans = 0;
		while(start <= n*n-1 && end >= 0 ) {
			
			long sum = AB[start]+CD[end];
			long ABsum = AB[start];
			long CDsum = CD[end];
			long ABcnt = 0;
			long CDcnt = 0;
			
			if(sum == 0) {
				
				while(start <= n*n-1 && AB[start] == ABsum ) {
					start++;
					ABcnt++;
				}
				
				while(end >= 0 && CD[end] == CDsum ) {
					end--;
					CDcnt++;
				}
				
				
				ans += ABcnt*CDcnt;
				
			}else if(sum < 0) {
				start++;
			}else {
				end--;
			}
			

		}
		
		System.out.println(ans);
		
		
		

	}
}
