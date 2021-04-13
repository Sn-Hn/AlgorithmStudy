import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main {
	static boolean connect[][];
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		connect = new boolean[h+2][n+1];
		
		for(int i = 0 ; i < m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//a번 가로선에 b와 b+1이 연결되어있다.
			connect[a][b] = true;
		}
		
		search(m, h, n, 0,1);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		
	}
	
	public static void search(int m,int h,int n,int cnt,int idx) {

		if(cnt > 3) {
			return;
		}
		if(ladder(n, h)) {
			
			ans = Math.min(ans, cnt);
			return;
		}

		
			for(int col = 1 ; col < n ;col++ ) {
				for(int row = idx ; row <=h; row++) {
				if(connect[row][col-1] ||  connect[row][col+1] )continue;

				if(!connect[row][col]) {
					connect[row][col] =true;
				
					search(m, h, n, cnt+1,row);
					connect[row][col] = false;
			
				}
				
				
			}
		}
		
		
	}
	

	
	
	public static boolean ladder(int n,int h) {


		for(int col =1  ; col <= n;col++) {
			int laddernum = col;
			for(int row = 1; row <=h ;row++) {
				
				if(connect[row][laddernum]) {
					//col랑 col+1이 연결되어있기 때문에
					laddernum +=1;
					
				}else if(connect[row][laddernum-1]) {
					laddernum -=1;
				}
				
			}
			if(laddernum != col) {
			
				return false;
			}
			
		}

		return true;
		
	}
	
	
	
}
