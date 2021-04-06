import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> list[];
	static int num[];
	
	static int n;
	static int result[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		result = new int[n+1];
		num = new int[n+1];
		
		list = new ArrayList[n+1];
		for(int i = 1 ; i<= n ;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		
		
		
		for(int i = 0 ; i < m ;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			num[b]++;
		}
		
		topology();
	}
	
	
	public static void topology() {
		Stack<Integer> que = new Stack<Integer>();
		
		for(int i = 1 ; i <= n;i++) {
			if(num[i] == 0) {
				que.add(i);

			}
		}

		
		for(int  i = 1 ; i <= n ;i++) {
			if(que.isEmpty()) {
				return ;
			}
			int x = que.pop();
			
			result[i] = x;

			for(int j = 0 ; j<list[x].size();j++) {
				int get =list[x].get(j);
				num[get]--;
				if(num[get] == 0) {
					que.add(get);
				}
				
			}
			
			
		}
		
		
		
		for(int i = 1 ; i <=n;i++) {
			System.out.print(result[i]+" ");
		}
		
		
		
	}
	
	
	
}
