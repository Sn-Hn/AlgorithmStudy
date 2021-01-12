package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제)
 * 1. i번째 적군의 통신탑은 설치 좌표로 부터 R만큼의 거리에포함된 지역을 연합
 * 2. i와 j 가 닿거나 겹치면 직접통신 가능
 * 3. 임의 지역만 닿아서 최종 통신가능하면 연결 가능
 * 
 * 풀이)
 * 1. 모든 입력 받기 
 * 2. 좌표에서 R만큼 거리에 있는 지역 좌표를 모두 한 집합에 넣기 
 * 3. 중복되지 않은 적 수 count 
 * */
public class BOJ_10216_CountCircleGroups {
	static int N,X,Y,R;
	static int [] map;
	static int [][] union;//좌표 거리 담는 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());	
		for(int i =0 ;i<T; i++)
		{		
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int [N];//root
			union = new int [N][3];// 연합
			
			for(int j =0; j<N; j++)
			{
				
				st = new StringTokenizer(br.readLine());
				union[j][0] =Integer.parseInt(st.nextToken());//x
				union[j][1] =Integer.parseInt(st.nextToken());//y
				union[j][2] =Integer.parseInt(st.nextToken());//z
				
				map[j] =j;
				
			}
			//입력 완료
			
			// 범위 체크 후 결과 출력 
			int count =N; // 1부터 시작하니까 
			for(int a= 0; a<N; a++)
			{
				for(int b=0; b<N; b++)
				{// 두 좌표의 거리 계산 
					int x = union[a][0]-union[b][0];
					int y = union[a][1]-union[b][1];
					int r = union[a][2]+union[b][2];
					
					double dist =Math.sqrt(x*x +y*y) ;
					if(dist<=r)
					{// 범위 겹치는 경우 
						if(find(a)!=find(b))
						{// 합쳐지면 적의 수는 감소 
							merge(a,b);
							count--;
						}
						
					}
				}
			}
			System.out.println(count);
		}
	}
	static int find(int x)
	{
		if(map[x]==x)
			return x;
		else
			return map[x] = find(map[x]);
	}
	
	static void merge(int x,int y)
	{
		x = find(x);
		y = find(y);
		if(x!=y)
		{
			if(x<y)
			{
				map[y] =x;
			}else
			{
				map[x]=y;
			}
		}
	}
}
