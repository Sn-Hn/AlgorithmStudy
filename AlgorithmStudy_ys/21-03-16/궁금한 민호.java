package solution162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int arr[][];
	static boolean chk[][];
	static int sum = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		chk = new boolean[n+1][n+1];
		for(int i = 1 ; i <=n;i++) {
			String tmp[] = br.readLine().split(" ");
			for(int j = 1; j <=n ; j++) {
				arr[i][j] = Integer.parseInt(tmp[j-1]);
		
			}
		}
		
		
		
		floyd(n);
		
		if(sum != -1) {
			
			for(int i = 1 ; i<=n;i++) {
				for(int j = i+1; j <=n;j++) {
					if(chk[i][j] == false){
						sum+=arr[i][j];
					}
				}
			}
			
			
		}
		System.out.println(sum);
		
	}
	
	public static void floyd(int n) {
		 // 기준이 되는 거쳐가는 노드 i
			for(int i = 1 ; i <=n ; i++) {
				// 출발하는 노드 j
				for(int j = 1; j <=n;j++) {
					// 도착하는 노드 k
					for(int k = 1 ; k <=n;k++) {
						if(i != j && i !=k && j != k) {
							if(arr[j][k] == arr[j][i]+arr[i][k]) {
								
								chk[j][k] = true;
							}else if(arr[j][k] > arr[j][i]+arr[i][k]) {
								sum = -1;
								break;
							}
						}
						
						
					}
				}
			}
	}
	

	
	
}
