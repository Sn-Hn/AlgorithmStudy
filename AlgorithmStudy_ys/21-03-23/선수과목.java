import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int num[];
	static ArrayList<Integer> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		num = new int[n+1];
		
		list = new ArrayList[n+1];
		for(int i = 1 ; i <=n;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		
		for(int i = 0 ; i < m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			num[b]++;
			list[a].add(b);
			
		}
		
		topology(n);

	}
	
	public static void topology(int n) {
		Queue<Integer> que = new LinkedList<Integer>();
		int ans[] = new int[n+1];

		for(int i = 1 ; i <= n;i++) {
			if(num[i] == 0) {
				que.add(i);
				ans[i] = 1;
			}
		}

	
		
		for(int i = 1 ; i <=n;i++) {
			if(que.isEmpty()) {
				return ;
			}
			
			int cur = que.poll();
			
			
			for(int j = 0; j <list[cur].size();j++) {
				int get = list[cur].get(j);
				num[get]--;
				
				
				if(num[get] == 0) {
					que.add(get);
					ans[get] = ans[cur]+1;
				
				}
				
			}
		
			
		}
		
		
	
		for(int j = 1 ; j <=n;j++) {
			System.out.print(ans[j]+" ");
		}
		
		
	}
}
