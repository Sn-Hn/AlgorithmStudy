package solution103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	
	static int num[];
	static int tree[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		while( (input = br.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(input);
				
				int n = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				num = new int[n];
				tree = new int[n*4];
				
				st = new StringTokenizer(br.readLine());
				for(int i = 0 ; i < n ; i++) {
					int t= Integer.parseInt(st.nextToken());
					
					num[i] = t == 0 ? 0 : t>0 ? 1: -1;
				}
				
				StringBuilder sb = new StringBuilder();
				init(0, n-1, 1);
				for(int i = 0 ; i < k; i++) {
					st = new StringTokenizer(br.readLine());
					String order = st.nextToken();
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken()); 
					
					if(order.equals("C")) {
						
						b = b == 0 ? 0 : b>0 ? 1: -1;
						
						update(0, n-1, 1, a-1, b);
						
					}else {
						int ans = mul(0, n-1, 1, a-1, b-1);
						
						if(ans >0 ) {
							sb.append("+");
						}else if (ans < 0) {
							sb.append("-");
						}else {
							sb.append("0");
						}
						
						
					}
					
				}
				System.out.println(sb.toString());
		
		}
			
		

	}
	
	
	public static int init(int start,int end,int node) {
		
		if(start == end	) return tree[node] = num[start];
		
		int mid = (start+end)/2;
		
		return tree[node] = init(start,mid,node*2) * init(mid+1,end,node*2+1);
	}
	
	
	
	public static int mul(int start,int end,int node,int left,int right) {
		
		if(left >end || right < start) {
			return 1;
		}
		
		if(left <=start && end <= right) {
			return tree[node];
		}
		int mid = (start+end)/2;
		
		return mul(start,mid,node*2,left,right) * mul(mid+1,end,node*2+1,left,right);
	}
	
	
	public static int update(int start,int end,int node,int idx,int val) {
		
//		if(idx >end || idx < start) return;
//		
//		tree[node] *= val;
//		
//		if(start == end	) return;
//		int mid = (start+end)/2;
//		
//		update(start,mid,node*2,idx,val);
//		update(mid+1,end,node*2+1,idx,val);
		
		
		if(idx >end || idx < start) return tree[node];
		
		if(start == end	) return tree[node] = val;
		
		int mid = (start+end)/2;
		
		return tree[node] = update(start,mid,node*2,idx,val) * update(mid+1,end,node*2+1,idx,val);
		
	}
	
	
	
	
}
