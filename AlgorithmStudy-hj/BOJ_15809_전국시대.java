package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 문제)
 * 1. 동맹 - 병력 합
 * 2. 전쟁 - 1) 더 많은 병력을 가진 국가 승리, 남은 병력 수 = 승리 병력 - 패배병력
 * 		   2) 같은 병력이면 둘다 망함
 * 3. 조건 - 내 동맹국의 동맹은 모두 동맹 속국도 동맹, 
 * 		 - 전쟁에서 진 국가 동맹국은 이긴 국가의 속국이 됨
 * 4. 여러국가가 동맹이거나 속국이면 한국가로 취급
 * 
 * 입력)
 * N : 국가 수 , M : 기록 수
 * N개의 국가 병력
 * O: 1일 경우 P-Q 동맹 , 2: P!= 전쟁
 * 출력)
 * 남아있는 국가 수 \n 각 국가의 병력의 수 
 * 
 * 풀이) 
 * 1. 배열 생성, 배열에 병력수 넣기 
 * 2. O가 1인 경우, 국가 연결 및 병력 합치기 새로운 배열에 넣기  
 * 3. 전쟁 국가 표시 하고 전쟁 병력 비교 
 * */
public class BOJ_15809_전국시대 {
	static int N, M;
	static int [] country;//나라 번호
	static int [] countrymem;// 나라 병력
	static int [] union;// 결과
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String [] str = br.readLine().split(" ");
		N= Integer.parseInt(str[0]);
		M= Integer.parseInt(str[1]);
		country = new int[N+1];
		countrymem = new int[N+1];
		union = new int[N+1];
		
		for(int i =1; i<=N; i++)
		{// 나라별 병력 수 
			String num = br.readLine();
			country[i]=i;
			countrymem[i] = Integer.parseInt(num); 
		}
		//
		for(int i =0; i<M; i++)
		{
			str = br.readLine().split(" ");
			if(Integer.parseInt(str[0])==1) {
				//동맹 -> 연합 군
				merge(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			}
			else {
				war(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			}
		}
		
		//결과 출력
		int a =1;
		for(int i =1 ;i<=N; i++)
		{
			if(countrymem[i]>0) {//0이아니면 나라 유지 되었기 때문에 결과 배열에 넣어줌
				union[a] =countrymem[i];
				a++;
			}
		}
		System.out.println(a-1);
		Arrays.sort(union);
		for(int i=1; i<union.length; i++)
		{
			if(union[i]!=0)
				System.out.print(union[i]+ " ");
		}
		
	}

	static int findset(int x) {// 
		if(x == country[x] ) {// 현재 노드가 집합의 루트 일 경우 반환
			return x;
		}
		else {
			return country[x]=findset(country[x]);
		}
	}
	
	
	static void merge(int a, int b) {//동맹일 경우 
		a = findset(a);
		b = findset(b);
		if(countrymem[a]<countrymem[b])
		{//큰쪽으로 합치기
			country[a] =b;
			countrymem[b]+=countrymem[a];
			countrymem[a] =0;
		}
		else {
			country[b] =a;
			countrymem[a]+=countrymem[b];
			countrymem[b] =0;
		}	
	}
	static void war(int a, int b) {//동맹이 아닐 경우 
		a = findset(a);
		b = findset(b);
		if(countrymem[a]<countrymem[b])
		{//승리 병력 계산
			country[a] =b;
			countrymem[b]-=countrymem[a];
			countrymem[a] =0;
		}
		else if(countrymem[a]>countrymem[b]){
			countrymem[a]-=countrymem[b];
			country[b] =a;
			countrymem[b]=0;
		}
		else {
			// 병력이같을 경우 
			countrymem[a] = 0;
			countrymem[b] = 0;
		}
	}

}
