package solution116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		int start = 0;
		int end = 0;
		int max = 0;
		int cnt = 0 ;
		

		Map<Integer, String> map = new HashMap<Integer, String>();

		
		
		String arr[] = br.readLine().split("");

		
		while(start <= end && end <arr.length) {
			
			if(map.containsValue(arr[end]) ) {
				map.put(end, arr[end]);
				max = Math.max(max, end-start+1);
				end++;
			}else if(cnt < n) {
				cnt++;
				map.put(end, arr[end]);
				max = Math.max(max, end-start+1);
				end++;
			}else {
				
				map.remove(start);
				if(!map.containsValue(arr[start])) {
					cnt--;
				}
				start++;
				
			}

			
		}
		System.out.println(max );
		
		
	}
}
