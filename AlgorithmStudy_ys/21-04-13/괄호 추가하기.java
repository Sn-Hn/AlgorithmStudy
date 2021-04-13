import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int ans = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		

		char input[] = br.readLine().toCharArray();
		
		bind(input, n, 0, 0);
		System.out.println(ans);
		
	}
	
	public static void bind(char[] input,int n,int idx,int cur) {
		//괄호안에 한가지 수식만 들어갈 수 있으니까 숫자 2개씩
		//n은 무조건 홀수
		//3개씩 자르면 두개의 수를 묶을 수 있다.
		
		//탐색하면서 묶는경우와 안묶는 경우
//		System.out.println("인덱스"+idx);
//		System.out.println("cur"+cur);
		if(idx > n-1) {
//			System.out.println(cur);
			ans = Math.max(cur, ans);
			return ;
		}
		
		char beforeoper = idx == 0 ? '+' : input[idx-1];
		
		
		//묶는경우
		if(idx+2 < n) {
			
			int a = Character.getNumericValue(input[idx]);
			int b = Character.getNumericValue(input[idx+2]);
			char operator = input[idx+1];
			
//			System.out.println("cur"+cur+" = "+	beforeoper+"  "+a+""+operator+""+b);
			
			int num = cal(a,b,operator);
			bind(input, n, idx+4, cal(cur,num , beforeoper));
			
		}
		//안묶는경우

		bind(input, n, idx+2,cal(cur,Character.getNumericValue(input[idx]), beforeoper));
		
		
	}
	
	public static int cal (int a, int b,char operator) {
		
		if(operator == '+') {
			return a+b;
		}else if(operator == '-') {
			return a-b;
		}else {
			return a*b;
		}
		
	}
	
	
	
}
