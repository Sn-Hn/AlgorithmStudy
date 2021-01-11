package algostudy06_Baekjoon_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
	
	static int map[][];
	static boolean visited[][];
	static int a[] = {-1,0,1,0};
	static int b[] = {0,-1,0,1};
	static int n;
	static int eat;
	static ArrayList<int[]> list;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		int shark[] = new int[2];
		
		for(int i = 0 ; i < n ; i++) {
			String tmp[] =  br.readLine().trim().split(" ");
			for(int j = 0 ; j < tmp.length ; j++) {
				int num = Integer.parseInt(tmp[j]);
				map[i][j] = num;
				
				//아기 상어의 초기 위치 저장
				if(num == 9) {
					shark[0] = i;
					shark[1] = j;
				}
			}
		}
		
		
		
		eat = 0;
		size = 2;
		int time = 0;
		while(true){
			list = new ArrayList<int[]>();
			visited = new boolean[n][n];
			bfs(shark[0],shark[1]);
			
			//상어 이동 했으니 이전 자리를 0
			map[shark[0]][shark[1]] = 0;
			
			//더이상 먹을 수 있는 물고기가 공간에 없다면 
			// bfs를 돌렸는데 size보다 작은 숫자가 없다.
			if(list.size() == 0) {
				break;
			}
			// 물고기가 있다면 거리가 가장 가까운 물고기(최소 거리를 알아야한다)
			if(list.size() ==1) { // 한마리이면 먹으러 간다
				eat += 1;
				
				int x = list.get(0)[0];
				int y = list.get(0)[1];
				int dis = list.get(0)[2];
				//이동한 자리를 9로 
				map[x][y] = 9;
				shark[0] = x;
				shark[1] = y;
				time  += dis;
			}else if(list.size() > 1) { // 한마리 이상이면 정렬 후 먹으러간다
				eat += 1;
				list.sort(new Comparator<int[]>() { 
					@Override
					public int compare(int[] o1, int[] o2) {
						// 정렬 맨위쪽 값을 찾기위해 맨위쪽 값이 많으면 제일 왼쪽 값
						return o1[2]-o2[2] != 0 ? o1[2]-o2[2] : o1[0]-o2[0] == 0 ? o1[1]-o2[1] : o1[0]-o2[0] ;
					}
				});
				

				int x = list.get(0)[0];
				int y = list.get(0)[1];
				int dis = list.get(0)[2];
				//이동한 자리를 9로 
				map[x][y] = 9;
				shark[0] = x;
				shark[1] = y;
				time  += dis;
			}
			
			//물고기 먹은수와 사이즈 비교 같으면 사이즈 증가시키고 먹은수 0 으로 초기화
			
			if(eat == size) {

				size += 1;
				eat = 0;
			}
			
			
			
		}// while end
		
		
		System.out.println(time);
		
	}
	
	public static void bfs(int x,int y) {
		Queue<int[]> que = new LinkedList<int[]>();
		
		que.add(new int[] {x,y,0});
		
		visited[x][y] = true;
		while(!que.isEmpty()) {
			int	shark[] = que.poll();
		
			int dis = shark[2];
			for(int i = 0 ; i< 4 ; i++) {
				int mx = shark[0]+a[i]; 
				int my = shark[1]+b[i];

				if(mx>=0 && my>=0 && mx <n && my<n) {
					if(!visited[mx][my] && map[mx][my] !=0 && map[mx][my] <size) {
						visited[mx][my] = true;
						list.add(new int[] {mx,my,dis+1});
					
					}else if(!visited[mx][my] && (map[mx][my] == 0 || map[mx][my] == size)) {
						visited[mx][my] = true;
						que.add(new int[] {mx,my,dis+1});
						
						
					}
				}
				
			
			}

		}
		
	}
	
	
}
