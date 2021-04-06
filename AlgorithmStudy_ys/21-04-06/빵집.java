import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char map[][];
	static int a[] = {-1,0,1};
	static int b[] = { 1,1,1};
	static boolean visited[][];
	static int r;
	static int c;
	static int ans = 0;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visited = new boolean[r][c];
		for(int i = 0 ; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0 ; i < r ;i++) {
			
			flag = false;
	
			dfs(i,0);
			
			
		}
		
		
		System.out.println(ans);
		
	}
	public static void dfs(int nr,int nc) {
		
		
		for(int i = 0 ; i < 3 ;i++) {
			
			int mr = nr + a[i];
			int mc = nc + b[i];

			
			if(mc == c-1 ) {
		
				flag = true;
				
				ans++;
				return ;
			}
			
			
			if(mr >= 0 && mc >=0 && mr <r && mc <c) {
				
				if(!visited[mr][mc] && map[mr][mc]=='.') {
					visited[mr][mc] =true;
					dfs(mr,mc);
					if(flag)return ;
				}
				
				
			}
			
		}
		
		
	}
	
	
	
}