import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	
	static long number[];
	static long chk[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		number = new long[21];
	
		
		int n = Integer.parseInt(br.readLine());
		String tmp[] = br.readLine().split(" ");
		
		int arr[] = new int[n];
		for(int i = 0 ; i < n ;i++) {
			arr[i] = Integer.parseInt(tmp[i]);
		}
		
		
		number[arr[0]] = 1; 
		for(int i = 0 ; i < n-2 ;i++) {
			
			chk = new long[21];
			solution(arr,n,i+1);
			number = chk;
			
			
		}
		
		System.out.println(number[arr[n-1]]);
		
		
	}
	
	
	public static void solution(int arr[],int n,int next) {
		
		
		
		
		for(int i = 0 ; i <21 ; i++) {
			if(number[i] == 0) {
				continue;
			}else {
				if(i+arr[next] >=0 && i+arr[next] <=20) {
					chk[i+arr[next]] += number[i];
				}
				if(i-arr[next] >=0 && i-arr[next] <=20) {
					chk[i-arr[next]] += number[i];
				}
			}
		}
		System.out.println(Arrays.toString(chk));
		
	}
	
	
	
}