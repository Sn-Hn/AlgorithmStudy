import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		

		int maxdp[] = new int[3];
		int mindp[] = new int[3];
		for(int i = 0 ;i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			
			if(i == 0) {
				maxdp[0] = mindp[0]= a;
				maxdp[1] = mindp[1]= b;
				maxdp[2] = mindp[2]= c;
			}else {
				int fir = Math.max(maxdp[0], maxdp[1]) + a;
				int sec = Math.max(maxdp[0], Math.max(maxdp[2], maxdp[1])) + b;
				int thr= Math.max(maxdp[1], maxdp[2]) + c;
				
				maxdp[0] = fir;
				maxdp[1] = sec;
				maxdp[2] = thr;
				
				fir = Math.min(mindp[0], mindp[1]) + a;
				sec = Math.min(mindp[0], Math.min(mindp[2], mindp[1])) + b;
				thr= Math.min(mindp[1], mindp[2]) + c;
				
				mindp[0] = fir;
				mindp[1] = sec;
				mindp[2] = thr;
			}
		}
		
		int max = Math.max(maxdp[0], Math.max(maxdp[2], maxdp[1]));
		int min = Math.min(mindp[0], Math.min(mindp[2], mindp[1]));

		System.out.println(max+" "+min);
		
	}
}