import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//크레인이 N대있고 1분에 박스를 하나씩 배에 실을 수 있다.
		// 크레인은 동시에 움직인다. 크레인은 무게 제한이 있다. 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다. 
//		모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는것
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); //크레인 대수
		
		String tmp[] = br.readLine().split(" ");
		ArrayList<Integer> crane = new ArrayList<Integer>();
		for(int i = 0 ; i < n;i++) {
			crane.add(Integer.parseInt(tmp[i]));
		}
		
		int m = Integer.parseInt(br.readLine()); //박스 개수
		
		tmp = br.readLine().split(" ");
		ArrayList<Integer> box = new ArrayList<Integer>();
		
		for(int i = 0 ; i < m;i++) {
			box.add(Integer.parseInt(tmp[i]));
		}
		
		Collections.sort(box,Collections.reverseOrder());
		Collections.sort(crane,Collections.reverseOrder());
		
		int time = 0;
		if(box.get(0)>crane.get(0)) {
			System.out.println(-1);
		}else {
			while(true) {
				int idx = 0;
				int cidx = 0 ;
				
				while(cidx < n) {
					if(idx== box.size()) {
					
						break;
					}
					
					if(box.get(idx) <= crane.get(cidx)) {
						box.remove(idx);
						cidx++;
					}else {
						idx++;
						
					}
				}
				time++;
			
				if(box.size() == 0) {

					break;
				}
				
			}
			
		}
			
		
		System.out.println(time);
		
		
		
	}
}