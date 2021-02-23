package solution111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		long s = Integer.parseInt(st.nextToken());
		int arr[] = new int[n];
		
		String tmp[] = br.readLine().split(" ");
		
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
		}
		int min = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;
		long sum = arr[0] ;
		
		
		while(true) {

//			System.out.println(sum);
			if(sum >= s) {
//				System.out.println(end+"검증"+start+"합"+sum);
				min = Math.min(min, end-start+1);
				sum -= arr[start];
				start++;
			}else if(end == n-1) {
				break;
			}else {
				end++;
				sum += arr[end];
			}
			
			
		}
			
		System.out.println(min == Integer.MAX_VALUE ?0 : min);
			
		
	}
}
