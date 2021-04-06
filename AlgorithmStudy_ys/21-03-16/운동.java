package solution161;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] arr;
	static int INF = 10000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		arr = new int[v+1][v+1];
		
		for(int tmp[] : arr) {
			Arrays.fill(tmp, INF);
		}
		
		for(int i = 0 ;i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			arr[a][b] = Math.min(arr[a][b], d);
		}
		
		floyd(v);
		int ans = Integer.MAX_VALUE;
		for(int i = 1 ; i <=v;i++) {
			for(int j = 1 ; j <=v ;j++) {
				if(arr[i][j] >= 10000 || arr[j][i] >= 10000 )continue;
				ans = Math.min(ans, arr[i][j]+arr[j][i] );
				
				
			}
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		
	}
	
	public static void floyd(int n) {
		 // 기준이 되는 거쳐가는 노드 i
			for(int i = 1 ; i <=n ; i++) {
				// 출발하는 노드 j
				for(int j = 1; j <=n;j++) {
					// 도착하는 노드 k
					for(int k = 1 ; k <=n;k++) {
						//j에서 i를 거쳤다가 i에서 j까지 가는 거리와 j에서 k까지 가는 거리를 비교해서 작은 값이 최소거리이다.
						arr[j][k] = Math.min(arr[j][k], arr[j][i]+arr[i][k]);					
					}
				}
			}
	}
	
}
