package solution136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static class node{
		int a;
		int b;
		double dis;
		
		public node(int a, int b, double dis) {
			super();
			this.a = a;
			this.b = b;
			this.dis = dis;
		}

		
	}
	

	static int n;
	static int m;
	static int[] num;
	static ArrayList<node> list;
	static node[] info;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = new int[n+1];
		info = new node[n+1];
		
		list = new ArrayList<node>();
		
		for(int i = 1 ; i <=n;i++) {
			num[i]= i;
		}

		//우주신들의 좌표
		for(int i = 1 ; i <=n;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			info[i] = new node(a, b, 0);
		}
		//이미 연결
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		for(int i = 1 ; i <= n-1 ; i++) {
			node a = info[i];
			for(int j = i+1; j<=n ; j++) {
				node b = info[j];
				double dis = Math.sqrt(Math.pow(a.a-b.a, 2) + Math.pow(a.b-b.b, 2));
				list.add(new node(i, j, dis));
			}
		}
		
		Collections.sort(list, new Comparator<node>() {
			@Override
			public int compare(node o1, node o2) {
				// TODO Auto-generated method stub
				if(o1.dis>o2.dis) {
					return 1;
				}else {
					return -1;
				}
			}
		});
		
		double sum = 0;
		for(int i = 0 ; i < list.size();i++) {
			node n = list.get(i);
			if(!find(n.a, n.b)) {
				union(n.a, n.b);
				sum +=n.dis;
			}
			
		}
		
		System.out.printf("%.2f",sum);
		
	}
	public static int getParent(int x) {
		if(num[x] == x) return x;
		
		return getParent(num[x]);
	}
	
	public static void union(int a,int b) {
		a = getParent(a);
		b = getParent(b);
		
		if(a < b) {
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
