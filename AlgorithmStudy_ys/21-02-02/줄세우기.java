package solution99;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	
	static int arr[];
	static int dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];
		for(int i =0 ; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		
		for(int i = 1 ; i <n ;i++) {
			dp[i] = 1;
			for(int j = 0; j <i ; j++) {
				if(arr[j] < arr[i] && dp[j]+1 > dp[i]) {
					dp[i] = dp[j]+1;
				}
			}
		}
		int ans = 0 ;
		
		for(int i = 0 ; i < n; i++) {
			if(ans <dp[i]) {
				ans = dp[i]; 
			}
		}
		
		System.out.println(n-ans);
		
		
		System.out.println(Arrays.toString(dp));
		
		
	}
}
