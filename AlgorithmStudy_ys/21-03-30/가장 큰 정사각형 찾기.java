import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int map[][];

	static int n;
	static int m;
	static int a[]= {-1,-1,0};
	static int b[]= {0,-1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];

		
		for(int i = 0 ; i < n;i++) {
			String tmp[] = br.readLine().split("");
			for(int j = 0 ; j < tmp.length ;j++) {
				map[i][j] =	Integer.parseInt(tmp[j]);
			}
		}
		
		int ans = 0;
		for(int i = 0 ; i < n;i++) {
			for(int j = 0 ; j < m ;j++) {
				if(map[i][j] == 1) {
					
					int size = boxsize(i,j);
					ans = Math.max(ans, size);
				}
			}
		}
		
		for(int arr[] : map) {
			System.out.println(Arrays.toString(arr));
		}
		
		
		System.out.println(ans*ans);
	}
	public static int boxsize(int r, int c) {
		
		int min = 1001;
	
		for(int i = 0 ;i < 3; i++) {
			int mr = r+a[i];
			int mc = c+b[i];
			if(mr >=0 && mc >=0) {
				min = Math.min(min, map[mr][mc]);
			}else {
				min = 0;
				break;
			}
			
		}
		
		map[r][c] = min+1;
			
		return  min+1;
	}
	
	
}