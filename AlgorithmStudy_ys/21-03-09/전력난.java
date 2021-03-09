package solution134;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static class node{
		int a;
		int b;
		int distance;
		
		public node(int a, int b, int distance) {
			super();
			this.a = a;
			this.b = b;
			this.distance = distance;
		}

	}
	
	static node[] info;
	static int num[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 집의수
			int m = Integer.parseInt(st.nextToken());
			// 길의 수
			int n = Integer.parseInt(st.nextToken());
			
			if(m ==0 && n ==0) {
				break;
			}
			
			
			info = new node[n];
			num = new int[m];
			
			int all = 0;
			for(int i = 0 ; i < n;i++) {
				st = new StringTokenizer(br.readLine());
				// 집의수
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
			
				info[i] = new node(a, b, c);
				all +=c;
			}
			
			Arrays.sort(info,new Comparator<node>() {
		
				@Override
				public int compare(node o1, node o2) {
					// TODO Auto-generated method stub
					return o1.distance-o2.distance;
				}
			});
			
			for(int i = 0 ; i <m;i++ ) {
				num[i] = i;
			}
			
			int sum = 0;
			for(int i = 0 ; i < info.length;i++) {
				int a = info[i].a;
				int b = info[i].b;
				
				if(!find(a, b)) {
					union(a, b);
					sum +=info[i].distance;
					
				}
				
			}
			
			System.out.println(all-sum);
			
		}
	}
	
	public static int getParent(int x) {
		if(num[x] == x)return x;
		
		
		return num[x] = getParent(num[x]);
		
	}
	
	public static void union(int a,int b) {
		a = getParent(a);
		b = getParent(b);
		
		if(a<b) {
			num[b] = a;
		}else {
			num[a] = b;
		}
		
	}
	
	public static boolean find(int a,int b) {
		a = getParent(a);
		b = getParent(b);
		
		
		if(a == b) {
			return true;	
		}else {
			return false;
		}
		
		
	}
	
	
	
	
}
