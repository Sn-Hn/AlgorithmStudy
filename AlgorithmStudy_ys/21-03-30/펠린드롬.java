import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int arr[];
	static boolean dp[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1];
		dp = new boolean[n+1][n+1];
		
		
		String tmp[] = br.readLine().split(" ");
		for(int i = 1 ; i <= n;i++) {
			arr[i] = Integer.parseInt(tmp[i-1]);
		}
		
		int m = Integer.parseInt(br.readLine());
		
		
		
		// 1일때 2일때 초기화
		for(int i = 1 ; i <= n ;i++ ) {
			dp[i][i] = true;
			if(arr[i] == arr[i-1]) {
				dp[i-1][i] = true;
			}
		}

		// 왼쪽 끝과 오른쪽 끝이 같다면 그 사이에 있는 숫자가 펠린드롬이라면 이 숫자는 펠린드롬이다.
		for(int i = 2 ; i <=n -1 ;i++) {
			for(int j  = 1 ; j <= n-i ;j++) {
				if(arr[j] == arr[i+j] && dp[j+1][i+j-1]) {
					dp[j][i+j] = true;
				}
			}
			
			
		}
		
		StringBuilder sb = new  StringBuilder();
		
		for(int i = 0 ;i < m;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(dp[a][b]) {
				sb.append(1);
			}else {
				sb.append(0);
			}
			sb.append("\n");
		}
	
		System.out.println(sb.toString());
		
		
	}
	

	
	
}