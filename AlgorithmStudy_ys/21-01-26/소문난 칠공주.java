package solution89;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static String map[][];
	
	static int a[] = {1,-1,0,0};
	static int b[] = {0,0,-1,1};
	static boolean visited[][];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map= new String[5][5];
		visited = new boolean[5][5];
		

		ans = 0;
		boolean num[] = new boolean[25];
		for(int i = 0 ; i < 5 ; i++) {
			String tmp[] = br.readLine().split("");
			for(int j = 0 ;j < tmp.length;j++) {
				map[i][j] = tmp[j];
			}
			
		}
		
		
		select(num,0,0,0);
	
		
		System.out.println(ans);
		
		
		
		
	}// main end
	
	
	
	
	// 7명의 숫자를 뽑는다.
	public static void select(boolean num[], int cnt,int start,int cnty) {
		
		
		if(cnty>=4) {
			return;
		}
		
		if(cnt == 7) {
			int point = 0 ;
			visited = new boolean[5][5];
			for(int i = 0 ; i < 25 ; i++) {
				if(num[i]) {
					point = i;
//					System.out.print(i/5+","+i%5+" " +map[i/5][i%5]);
					visited[i/5][i%5]= true;
				}
			}
			concnt= 1 ;
			connect(point/5,point%5);

		}
		for(int i = start ; i < 25 ; i++) {
			num[i] = true;
			if(map[i/5][i%5].equals("Y")) {
				select(num,cnt+1,i+1,cnty+1);
			}else {
				select(num,cnt+1,i+1,cnty);
			}
			num[i] = false;
			
		}
		
		
		
	}
	
	
	// 뽑은 7개의 숫자가 서로 연결되어 있는지
	static int concnt = 1 ;
	public static void connect(int x,int y) {
		//num을 몫과 나머지로 좌표를 구할 수 있다.

		boolean flag = false;
		visited[x][y] =false;
		
		for(int i = 0 ; i < 4 ; i++) {
			int mx = x+a[i];
			int my = y+b[i];
			if(mx >=0 && my >= 0 && mx <5 && my <5) {
				if(visited[mx][my]) {
					concnt++;
					connect(mx,my);
					flag = true;
				}
			}
			
			
		}
		if(!flag ) {
			if(concnt == 7) {
				ans +=1;
			}
			
			return ;
		}
		
		
	}
	
	
}
