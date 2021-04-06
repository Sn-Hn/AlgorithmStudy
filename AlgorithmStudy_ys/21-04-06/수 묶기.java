import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Integer num[] = new Integer[n];
		
		for(int i = 0 ; i < n;i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
	
		Arrays.sort(num, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2, o1);
			}
		});
		
		
		int plus = 0;
		int minus = num.length-1;
		int sum = 0;
		
		
		while(plus<minus) {
			int a = num[plus];
			int b = num[plus+1];
			
			if(a >1 && b >1) {
				sum += a*b;
				plus +=2;
			}else {
				break;
			}
		}
		
		while(0<minus) {
			int a = num[minus];
			int b = num[minus-1];
			
			if(a <1 && b <1) {
				sum += a*b;
				minus -=2;
			}else {
				break;
			}
		}
		
		while(plus <=minus) {
			sum += num[plus];
			plus++;
		}
		
		
		
		
		System.out.println(sum);
		
		
	}
}