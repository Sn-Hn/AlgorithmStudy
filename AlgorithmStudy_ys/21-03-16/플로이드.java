package solution159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {
	static int arr[][];
	static int n;
	public static final int INF = 1000000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		
		for(int i = 1 ; i <= n ;i++) {
			for(int j = 1 ; j <=n;j++) {
				if(i==j)continue;
				arr[i][j] = INF;
			}
		}

		for(int i = 0 ; i < m;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());


			arr[a][b] = Math.min(arr[a][b], c);
		}
		
		
		floyd();

		
		for(int i = 1 ; i <= n ;i++) {
			for(int j = 1 ; j <=n;j++) {
				if(arr[i][j] >=INF) System.out.print("0 ");
				else System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
		

		
	}
	
	public static void floyd() {
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
