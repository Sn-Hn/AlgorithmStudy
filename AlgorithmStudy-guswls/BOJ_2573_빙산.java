package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*check point
 * 1. 높이 정보는 양수, 빙산 이외는 0
 * 2. 각 높이는 1년마다 4방향에 있는 0의 수만큼 줄어든다 (0보다는 줄어들지 x)
 * 3. 빙산이 두 덩어리 이상으로 분리되는 최초의 시간 구하기 
 * 4. 만약 다 녹을 때까지 두개 이상으로 분리되지 않는다면 0 출력*/

/* 구현 logic
 * 1. 입력 배열 저장
 * 2. 4방향 배열 생성 
 * 3. dfs 돌면서 0이 있나 확인 
 * 4. 0이 있다면 빙산에서 1씩 감소, 0이하면 감소 안하고 그냥 0
 * 5. 한바퀴 돌았을 때, 모든 빙산이 이어져있는지 확인
 * */
public class BOJ_2573_빙산 {
	static int [][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; //방향 탐색 배열
	static int [][] map;//전체
	static int [][] ice;//녹은 빙산 수 담는 배열 
	static int N, M;
	static boolean [][]visited;
	static int count,zerocount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);//행
		M = Integer.parseInt(str[1]);//열
		map = new int [N][M];
		ice = new int [N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++)
		{
			str =br.readLine().split(" ");
			for(int j =0; j<M; j++)
			{
				map[i][j] = Integer.parseInt(str[j]);
			}
		}//map에 입력값 저장 
		
		int year =0;
		
		while(true)
		{
			int count =0;
			for(int i=0; i<N; i++)
			{
				for(int j =0; j<M; j++)
				{
					if(map[i][j]!=0 && !visited[i][j])
					{
						dfs(i,j);	
						count++; //빙산 덩어리 
					}			
				}
			}
			if(count==0)
			{//다 녹을때까지 덩어리가 생기지 않는것
				System.out.println(0);
				break;
			}else if(count>=2){
				System.out.println(year);
				break;
			}
			else {
			icemelt();
			year++;
			}
		}
		
		
	}
	static void dfs(int a, int b)
	{
		visited[a][b] =true;
		for(int i =0; i<4;i++)
		{
			int dx = a+dir[i][0]; 
			int dy = b + dir[i][1];
			if (dx < N && dy< M && dx>= 0 && dy>= 0) {
				//map의 범위 체크 
				if (map[dx][dy] !=0 && visited[dx][dy] == false) {	
					//빙산의 높이가 0일때 dfs 실행
					dfs(dx, dy);
				}
				if(map[dx][dy]==0)//주변 0의 갯수 카운트
					ice[a][b]++;
			}
			
		}
		
	}
	static void icemelt() {//빙산 녹는 함수
		for(int i=0; i<N;i++) {
			for(int j =0; j<M; j++) {
				map[i][j]-= ice[i][j];
				
				if(map[i][j]<0)
					map[i][j]=0;
				visited[i][j]=false;
				ice[i][j]=0;
			}
		}
		
	}

}
