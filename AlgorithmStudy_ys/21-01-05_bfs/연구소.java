package Solution83;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	
	static boolean visited[];
	static ArrayList<int[]> virus;
	static int map[][];
	static int copy[][];
	static int n;
	static int m;
	static int a[] = {1 ,-1, 0, 0};
	static int b[] = {0 ,0, 1, -1};
	static int max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		max = 0;
		map = new int[n][m];
		virus = new ArrayList<int[]>();
		
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] ==2) {
					virus.add(new int[] {i,j});
				}
			}
		}


		combi(0,3,n*m);
		
		System.out.println(max);
		
	}
	
	// 조합공식(백트래킹)을 사용하여  벽을 세울수 있는 모든 위치를 뽑기
	public static void combi(int start,int r,int size) {
		if(r == 0) {
			
			copy = Arraycopy();
			for(int i = 0 ; i < virus.size();i++) {
				infection(virus.get(i)[0], virus.get(i)[1]);
			}
			max = Math.max(max, safezone());
			return;
		}
		
		for(int i = start;i<size;i++ ) {
			if(map[i/m][i%m] == 0) {
				map[i/m][i%m] = 1;
				combi(i+1,r-1,size);
				map[i/m][i%m] = 0;
			}
		}
		
		
	}
	
	
	//bfs를 돌려서 감염된 공간 찾기
	public static void infection(int x, int y) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {x,y});
		
		
		while(!que.isEmpty()) {
			int tmp[] = que.poll();
			for(int i = 0 ; i < 4; i++) {
				int mx = tmp[0]+a[i];
				int my = tmp[1]+b[i];
				if(mx >=0 && my >=0 && mx <n && my <m) {
					if(copy[mx][my] ==0) {
						que.add(new int[] {mx,my});
						copy[mx][my]=2;
					}
				}
				
				
			}

		}
	
	}
	
	public static int[][] Arraycopy() {
		int copy[][] = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ;  j++) {
				copy[i][j]=map[i][j];
			}
		}
		
		return copy;
	}
	
	
	// 안전 영역의 크기 계산
	public static int safezone() {
		int cnt = 0;
		for(int i = 0 ;i <n ; i++) {
			for(int j = 0 ; j < m; j ++) {
				if(copy[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	
	
	
}
