package solution141;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	static class Node{
		int a;
		int b;
		int dis;
		public Node(int a, int b, int dis) {
			super();
			this.a = a;
			this.b = b;
			this.dis = dis;
		}
	}
	
	static int n;
	static int num[];
	static Node info[];
	static ArrayList<Node> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<Node>();
		num = new int[n];
		for(int i = 0 ; i < n;i++) {
			num[i] = i;
		}
		
		int total = 0;
		for(int i = 0 ;i < n ;i++) {
			String tmp[] = br.readLine().split("");
			for(int j = 0 ; j < tmp.length;j++) {
				int num = tmp[j].codePointAt(0);
				System.out.println(num);
				if(num == 48)continue;
				
				if(num <= 96) {	
					num -=38;
				}else {
					num -=96;
				}
				total += num;
				list.add(new Node(i, j, num));
				
			}
		}
		
		Collections.sort(list,new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				if(o1.dis>o2.dis) {
					return 1;
				}else if(o2.dis> o1.dis) {
					return -1;
				}else {
					return 0;
				}
			}
		});
		
		
		int sum  = 0;
		int cnt = 0;
		for(int i = 0 ; i < list.size();i++) {
			Node lan = list.get(i);
			
			if(!find(lan.a, lan.b)) {
				union(lan.a, lan.b);
				sum += lan.dis;
				cnt++;
			}
		}
		if(cnt == n-1) {
			System.out.println(total-sum);
		}else {
			System.out.println(-1);
		}
		
		
		
		
		
	}
	
	public static int getParent(int x) {
		if(num[x]== x) return x;
		
		
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
