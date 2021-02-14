package solution101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int num[];
	static int min[][];
	static int tree[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		num = new int[n];
		tree = new int[n*4];
		for(int i = 0 ; i< n ; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		
		min = new int[m][2];
		init(0,n-1,1);
		for(int i = 0 ; i < m ;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			System.out.println(min(0,n-1,1,a-1,b-1));
		}
		
	

	}
	
	
	public static int init(int start,int end,int node) {
		if(start == end) {
			
			return tree[node] = num[start];
		}
		
		int mid = (start+end)/2;

		return tree[node] = Math.min(init(start,mid,node*2),init(mid+1,end,node*2+1));
	}
	
	// start: 시작 인덱스, end: 끝 인덱스
	// left, right: 구간 합을 구하고자 하는 범위 
	public static int min(int start, int end, int node,int left,int right) {
		// 범위 밖에 있는 경우
		if(left > end || right <start) return Integer.MAX_VALUE;
		// 범위 안에 있는 경우
		if(left <=start && end <=right) {
			return tree[node];
		}
		// 그렇지 않다면 두 부분으로 나누어 합을 구하기
		int mid = (start+end)/2;
		
		return Math.min(min(start,mid,node*2,left,right),min(mid+1,end,node*2+1,left,right));
	}
	
	
	
	
}



