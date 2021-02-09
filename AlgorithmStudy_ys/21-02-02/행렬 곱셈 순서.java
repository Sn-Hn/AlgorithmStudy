package solution98;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
		
	
    static int[][] matrix;
    static int[][] dp;

    public static int cal(int i1, int j1, int i2, int j2) {
        return matrix[i1-j1+1][0] * matrix[i1][1] * matrix[i2][1] + dp[i1][j1] + dp[i2][j2];
    }
	
	  public static void main(String[] args) throws IOException {
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int n = Integer.parseInt(br.readLine());

	        matrix = new int[n+1][2];
	        for (int i = 1; i <= n; i++) {
	            String[] s = br.readLine().split(" ");
	            matrix[i][0] = Integer.parseInt(s[0]);
	            matrix[i][1] = Integer.parseInt(s[1]);
	        }

	       
	        dp = new int[n+1][n+1];
	        for (int i = 1; i <= n; i++)
	            dp[i][1] = 0;
	        for (int i = 2; i <= n; i++) {
	            for (int j = 2; j <= i; j++) {
	                int min = Integer.MAX_VALUE;
	                for (int t = 1; t <= j-1; t++) {
	                    int cur = cal(i - j + t, t, i, j - t);
	                    if (cur < min)
	                        min = cur;
	                }
	                dp[i][j] = min;
	            }
	        }

	        System.out.println(dp[n][n]);
	    }
}
