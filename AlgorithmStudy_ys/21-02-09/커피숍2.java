package solution104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	
	static int num[];
	static long tree[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		
		num = new int[n];
		tree = new long[n*4];
		
		String tmp[] = br.readLine().split(" ");
		for(int i = 0 ; i < n ;i++) {
			num[i] = Integer.parseInt(tmp[i]);
		}
		
		init(0, n-1, 1);
		for(int i = 0 ; i< q ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(a> b) {
				System.out.println(sum(0, n-1, 1, b-1, a-1));

			}else {
				
				System.out.println(sum(0, n-1, 1, a-1, b-1));
			}
			
			
			
				update(0, n-1, 1, c-1, d);

		}

	}

	
	public static long init(int start,int end, int node) {
		if(start == end) return tree[node] = num[start];
		
		int mid = (start+end)/2;
		
		return tree[node] = init(start,mid,node*2) + init(mid+1,end,node*2+1);
		
	}
	
	
	public static long sum(int start,int end, int node ,int left,int right) {
		
		if(left> end || right < start) return 0;
		
		if(left <= start && end <=right) return tree[node];
		
		int mid = (start+end)/2;
		
		return sum(start,mid,node*2,left,right) + sum(mid+1,end,node*2+1,left,right);
	}
	
	public static long update(int start,int end,int node,int idx,int value) {
		
		if(idx> end || idx < start) return tree[node];
		
		tree[node] += value;
		
		if(start == end )return tree[node] = value;
		int mid = (start+end)/2;
		
		
		return tree[node] = update(mid+1,end,node*2+1,idx,value)+ update(start,mid,node*2,idx,value);

	}
	
	
	
	
}
