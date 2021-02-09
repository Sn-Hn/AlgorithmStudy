package solution94;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static class Item{
		int weight;
		int value;
		
		public Item(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
			
			
		}
		
		
		
	}
	
	public static int dp[][];
	public static Item item[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		dp = new int[n+1][k+1];
		
		item = new Item[n+1];
		item[0] = new Item(0, 0);
		
		for(int i = 1 ; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			item[i] = new Item(w, v);
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(backpack(n,k));
		
		for(int i = 0 ; i <= n ; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		
		
	}
	
	public static int backpack(int r, int c) {
		System.out.println("r" + r);
		if(r ==-1 || c == -1) return 0;
		
		
		if(dp[r][c] == -1) {
			dp[r][c] = 0;
			
			
			if(item[r].weight > c) {
				dp[r][c] = backpack(r-1,c);
			}else {

				dp[r][c] = Math.max(backpack(r-1,c), backpack(r-1,c-item[r].weight)+item[r].value);
				
				
			}
			
		}
		
		
		return dp[r][c];
	}
	
	
	
	
	
}
