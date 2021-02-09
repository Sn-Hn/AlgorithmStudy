package solution90;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Review {
	
	static int map[][];
	static int ans;
	static int papercnt[] = {0,5,5,5,5,5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[10][10];
		ans = Integer.MAX_VALUE;
		//입력
		for(int i = 0 ; i < 10 ;i++){
			String tmp[] = br.readLine().split(" ");
			for(int j = 0 ; j< 10 ; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		dfs(0,0,0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		
	}
	
	public static void dfs(int r,int c,int cnt) {
		
		// 탐색 끝
		if(r >= 9 && c >9) {
		
			ans = Math.min(ans,cnt);
			return ; 
		}
		
		//다음 행으로 
		if(c >9) {
			dfs(r+1,0,cnt);
			return;
		}
		
		if(cnt >= ans) return ;
		 
		if(map[r][c] == 1) {
			//5부터 색종이를 채워본다.
			for(int i = 5 ; i >=1 ; i--) {
				if(papercnt[i] >0 &&chk(r,c,i)) {
					onpaper(r,c,i,0);
					papercnt[i]--;
					
					dfs(r,c+1,cnt+1);
					
					onpaper(r,c,i,1);
					papercnt[i]++;
					
				}
			}
		}else {
			dfs(r,c+1,cnt);
		}
	}
	
	
	public static boolean chk(int r,int c,int psize) {
		for(int i = r ; i < r+psize;i++) {
			for(int j = c; j < c+psize; j++) {
				if(i >=0 && j >=0 && i <10 && j <10 ) {
					// 0이 한개라도 있으면 색종이를 못붙인다.
					if(map[i][j] == 0) {
						return false;
					}
				}else {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public static void onpaper(int r,int c,int psize,int status) {
		for(int i = r ; i < r+psize;i++) {
			for(int j = c; j < c+psize; j++) {
				map[i][j] = status;
			}
		}

	}
	
	
}
