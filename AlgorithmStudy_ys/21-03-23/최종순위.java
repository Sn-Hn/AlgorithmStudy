import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int degree[];
	static int arr[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			degree = new int[n+1];
			arr = new int[n+1][n+1];
			String tmp[] = br.readLine().split(" ");
			for(int j = 0 ; j < tmp.length;j++) {
				int num = Integer.parseInt(tmp[j]);
				for(int k = j+1;k <n;k++) {
					arr[num][Integer.parseInt(tmp[k])] =1;
					degree[Integer.parseInt(tmp[k])]++;
				}
				
			}
			
			//바뀐 등수
			int m = Integer.parseInt(br.readLine());
			
			for(int j = 0 ; j < m;j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(arr[a][b] == 1) {
					arr[a][b] = 0;
					arr[b][a] = 1;
					
					degree[b]--;
					degree[a]++;
					
				}else {
					arr[b][a] = 0;
					arr[a][b] = 1;
					
					degree[a]--;
					degree[b]++;
				}

			}
	
			topology(n);
		}
		
		
		
		
	}
	
	public static void topology(int n) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		Queue<Integer> que = new LinkedList<Integer>();
		for(int i = 1 ; i <= n;i++) {
			if(degree[i] == 0) {
				ans.add(i);
				que.add(i);
			}
			
		}
		
		
		for(int i = 1 ; i <= n;i++) {
			if(que.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
			int cur = que.poll();
			
			
			for(int j = 0 ; j < arr[cur].length;j++) {
				if(arr[cur][j] == 1) {
					
					 degree[j]--;
					 
					 
					 if(degree[j] ==0) {
						 que.add(j);
						 ans.add(j);
					 }
					
				}
				
			}
			
			
			
			
		}
		
		if(ans.size()<n) {
			System.out.println("?");
		}else {
			for(int number : ans) {
				System.out.print(number+" ");
			}
			System.out.println();
		}
		
		
		
	}
	
	
	
	
}
