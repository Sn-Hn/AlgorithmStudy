package solution113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());

		int[] arr = getsosu(n);
		
		
		if(n !=1) {
			int start = 0;
			int end = 0;
			long sum = arr[0];
			int cnt = 0;
			
			while(true) {
				if(sum >= n) {
					if(sum== n) cnt++;
					sum-=arr[start];
					start++;
				}else if(end == arr.length-1) {
					break;
				}else {
					end++;
					sum+=arr[end];
				}
				
			}
			System.out.println(cnt);
			
		}else {
			System.out.println(0);
		}
		

	}
	
	public static int[] getsosu(int num) {
		boolean arr[] = new boolean[num+1];
		ArrayList<Integer> sosu = new ArrayList<Integer>();
		for(int i = 2; i <= num ;i++) {
			if(arr[i] == false) {
				for(int j = i*2 ; j <=num; j+=i) {
					arr[j] = true;
				}
			}
			
		}

		for(int i = 2; i <= num;i++) {
			if(arr[i] ==false) {
				sosu.add(i);
				System.out.println("소수"+i);
			}
		}
		
		int tmp[] = new int[sosu.size()];
		
		for(int i = 0 ;i < sosu.size();i++) {
			tmp[i]= sosu.get(i);
		}

		return tmp;
		
		
	}
	
	
}
