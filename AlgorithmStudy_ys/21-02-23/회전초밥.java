package solution110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int arr[];
	static int eat[];
	static int max;
	public static void main(String[] args) throws IOException {
		
//		접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		arr = new int[n];
		eat = new int[d+1];
		max = 0;
		int cnt = 0 ;
		
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		
		//처음 연속해서 먹는 것 
		for(int i = 0 ; i < k; i++) {
			if(eat[arr[i]] == 0 ) cnt++; //처음먹으면 cnt++;
			eat[arr[i]]++; // 먹은거 개수 ++;
		}
		
		max = cnt;
		
		for(int i = 1 ; i < n ; i++) {
			if(max <= cnt) {
				if(eat[c] == 0) { //쿠폰이 없다면 +1
					max = cnt +1;
				}else { //쿠폰을 이미 먹었으니
					max = cnt;
				}
			}
			
			eat[arr[i-1]]--; //start부분 먹고
			if(eat[arr[i-1]] == 0)cnt--;   //먹은거 개수 --
			
			
			if(eat[arr[(i+k-1)%n]] == 0)cnt++; //end 부분 먹고
			eat[arr[(i+k-1)%n]]++; //end부분 ++;
			
		}
		
		
		System.out.println(max);

	}
}
