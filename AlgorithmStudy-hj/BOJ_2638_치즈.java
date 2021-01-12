package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*외부 -1 치즈 1 내부 0
 * 외부 / 내부 구분 하기 
 * 4변 중 2변 이상이 접촉 되면 녹는다 
 * -> 외부랑 2변 이상 접촉하는지 파악하기
 * */
public class BOJ_2638_치즈 {
	static int [][] map;
	static boolean [][] visited;
	static int [][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		
		
		for(int i =0; i<N; i++)
		{
			str=br.readLine().split(" ");
			for(int j =0; j<M; j++)
			{
				map[i][j] = Integer.parseInt(str[j]);
			}
		}//입력 데이터 배열에 저장
		
		int result =0;
		while(true) {
			visited = new boolean[N][M];
			boolean check = false;
			dfs(0,0);
			
			for(int i =0;i<N; i++)
			{
				for(int j =0; j<M;j++)
				{
					if(map[i][j] ==1 && cheese(i,j))
					{//치즈 있을 때, 외부에 공기 2변이상
						check = true;
					}
				}
			}
			if(!check)
			{
				break;
			}
			result++;
		}
		System.out.println(result);
	}
	
	static void dfs(int a, int b)
	{
		map[a][b] =-1;// 녹은 부분 -1
		visited[a][b]=true;
		for(int i=0; i<4;i++) {
			int dx= a+ dir[i][0];
			int dy = b+ dir[i][1];
			
			if(dx>=0 && dy>=0 && dx<N && dy<M) {
				if(map[dx][dy]!=1 && !visited[dx][dy] )
					dfs(dx,dy);			
			}
		}
	}
	
	static boolean cheese(int a, int b)
	{// 치즈 두변이상 닿는지 확인하는 것
		int count =0;
		for(int i=0; i<4;i++) {
			int dx= a+ dir[i][0];
			int dy = b+ dir[i][1];
			
			if(dx>=0 && dy>=0 && dx<N && dy<M) {
				if(map[dx][dy]==-1  )
					count++;			
			}
		}
		if(count>=2)
		{//2개 이상일때는 치즈가 녹는다.
			map[a][b]=0;
			return true;
		}
		return false;
	}

}
