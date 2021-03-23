import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int num[];
	static ArrayList<Integer> list[];
	static int time[];
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		time = new int[n+1];
		num = new int[n+1];
		
		for(int i = 1; i<=n;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		
		
		for(int i = 1 ; i <= n;i++) {
			String tmp[] = br.readLine().split(" ");
			for(int j = 0 ; j < tmp.length;j++) {
				if(j==0) {
					time[i] = Integer.parseInt(tmp[j]);
				}else if (Integer.parseInt(tmp[j]) == -1) {
					break;
				}else{
					
					list[Integer.parseInt(tmp[j])].add(i);
					num[i]++;
				}
				
			}
		}
	
		topology();
		
	}
	public static void topology() {
		Queue<Integer> que = new LinkedList<Integer>();
		int dp[] = new int[n+1];
		
		for(int i = 1 ; i <=n ;i++) {
			if(num[i] == 0) {
				que.add(i);
				dp[i] = time[i];
			}
		}
		
		for(int i = 1 ; i <=n ;i++) {
		
			if(que.isEmpty())return;
			
			int cur = que.poll();
			
			
			
			for(int j = 0 ; j <list[cur].size() ;j++) {
				int get = list[cur].get(j);
				
			
				
				if(dp[get] < time[get] + dp[cur]) {
					dp[get] = time[get]+dp[cur];
				
				}
				
				
				num[get]--;
				
				if(num[get] == 0) {
					que.add(get);
				}
			}
		}
		
		
		for(int i = 1 ; i <=n;i++) {
			System.out.println(dp[i]+" ");
		}
		
	}
	
	
	
}
