package solution95;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int map[][];
	static int dp[][];
	static int a[] = {1,-1,0,0};
	static int b[] = {0,0,1,-1};
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 현재 제일 왼쪽 위칸 -> 제일 오른쪽 아래칸  = 가능한 힘을 적게 높이가 너 낮은 지점으로만 이동
		// 항상 내리막길로면 이동하는 경로의 개수를 구해야 한다.
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		dp = new int[n][m];
		
		
		for(int i = 0 ; i < n ; i++) {
			String tmp[] = br.readLine().split(" ");
			for(int j = 0 ; j < m ;j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
			Arrays.fill(dp[i], -1);
		}
		dp[n-1][m-1] = 1;
		
		System.out.println(road(0,0));
		for(int i = 0 ; i < n; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		
		
	}
	
	
	public static int road(int r , int c) {
		
		
		if(dp[r][c] != -1) {
			return dp[r][c];
		}else {
			
			dp[r][c] = 0;
		}
		
		
		
		for(int i = 0 ; i < 4 ;i++) {
			int mr = r+a[i];
			int mc = c+b[i];
			
			
			if(mr >=0 && mc >= 0 && mr <n && mc<m) {
				
				if(map[mr][mc] < map[r][c]) {
					dp[r][c] += road(mr,mc);
				}
				
				
			}
			
		}
		
		
		
		return dp[r][c];
	}
	
	
	
}
