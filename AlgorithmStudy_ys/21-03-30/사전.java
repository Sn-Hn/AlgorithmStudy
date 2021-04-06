import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	

	static int arr[];
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int z = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int len = a+z;
		dp = new int[201][201];
		
		arr = new int[len];
		
		for(int i = 0 ; i < a; i++) {
			arr[i] = "a".codePointAt(0);
		}
		for(int i = a; i < len;i++) {
			arr[i] = "z".codePointAt(0);
		}
		
		StringBuilder sb = new StringBuilder();
		dp[0][0] = 1;
		
		for(int i = 1 ; i <=len ;i++) {
			dp[i][0] = 1;
			for(int j = 1 ;j <=z && j<=i; j++) {
				if(i==j) {
					dp[i][j] = 1;
					continue;
				}
				// 조합 공식 C(N, M) = C(N - 1, M - 1) + C(N - 1, M)
				dp[i][j]= dp[i-1][j] +dp[i-1][j-1];
				if(dp[i][j] >= 1000000000) dp[i][j] = 1000000000;
				
			}
			
		}
	
		
		k--;
		
		if(dp[a+z][z] <= k) {
			System.out.println(-1);
		}else {
			for(int i = a+z-1; i>= 0 ;i--) {
				if(i >= z&& dp[i][z] >k) {
					sb.append("a");
				}else {
					sb.append("z");
					k-=dp[i][z];
					z--;
					
				}
			}
		}
		
		
		System.out.println(sb.toString());
		
	}
	
	

	
	
	
}