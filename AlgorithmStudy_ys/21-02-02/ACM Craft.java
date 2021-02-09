package solution97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	static int degree[];
	static int ans;
	static int dp[];
	public static void main(String[] args) throws IOException {
//		건물 개수 N
//		건설순서 규칙 K
//		
//		각 건물당 건설에 걸리는 시간 D
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		ans = 0;
		
		for(int i = 0 ;i < t ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			degree = new int[r+1];
			dp = new int[r+1];
			boolean map[][] = new boolean[r+1][r+1];
			int time[] = new int[r+1];
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1 ; j <= r ; j++) {
				time[j] = Integer.parseInt(st.nextToken()); 
				dp[j] = time[j];
			}
			
			for(int j = 0 ; j < c ; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = true;
				degree[b] +=1;
			}
			
			int target = Integer.parseInt(br.readLine());
			
			
			buildingtime(map, target, time);
			
		}

	}
	
	
	// 위상정렬을 한뒤에 순서대로 연결한뒤 최종값
	public static void buildingtime(boolean map[][],int target,int time[]) {
		Queue<Integer> que = new LinkedList<Integer>();
		for(int i = 1 ; i < degree.length;i++ ) {
			if(degree[i] == 0 ) {
				que.add(i);
			}
		}
		for(int i  = 1 ; i < degree.length; i++) {
			
			if(que.isEmpty()) {
				return ;
			}
			int x = que.poll();

			
			for(int j = 1 ; j < map[x].length; j++) {
				if(map[x][j]) {
					map[x][j] = false;
					dp[j] = Math.max(dp[x]+time[j],dp[j]); 
					if(--degree[j] == 0) {
						que.add(j);
					}
					
				}
			}
			
			if(x == target) {
				System.out.println(dp[x]);

			}
			
			
		}
		
		
		
	}
	
	
	
	
}
