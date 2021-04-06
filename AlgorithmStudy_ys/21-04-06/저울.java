import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		무게가 양의 정수인 N개의 저울추가 주어질 때, 이 추들을 사용하여 측정할 수 없는 양의 정수 무게 중 최솟값을 구하는 프로그램을 작성하시오.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		
		String tmp[] = br.readLine().split(" ");
		
		int num[] = new int[n];
		
		for(int i = 0 ; i < n; i++) {
			num[i] = Integer.parseInt(tmp[i]);
		}
		
		Arrays.sort(num);
		int sum = 0;
		for(int i = 0 ; i < n; i++) {
			if(num[i] <= sum+1) {
				sum += num[i];
			}else break;
			
			
		}
		
		System.out.println(sum+1);
		
		
	}
}