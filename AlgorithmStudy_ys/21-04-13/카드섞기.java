import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Main {

	static String input;
	static String[] target;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());

		target = br.readLine().split(" ");
		String vs = Arrays.toString(target);

		int k = 1;
		
		while(true) {
			int num =  (int) Math.pow(2, k);
			if(num < n) {
				k++;
			}else {
				k--;
				break;
			}
			
		}
		ArrayList<String> num = new ArrayList<String>();
		
		for(int i = 1 ; i <= n ;i++) {

			num.add(Integer.toString(i));
		}
	
		

		ArrayList<String> ans = new ArrayList<String>();
		ArrayList<String> last = new ArrayList<String>();
		
		loop:
		for(int i = k ; i > 0 ; i--) {
			ans = suffle(num, i);
			for(int j = k ; j > 0;j--) {
				last = suffle(ans, j);
				
				String word = last.toString();


				if(word.equals(vs)) {

					System.out.println(i+" "+j);
					
					break loop;
				}
				
			}
		}
		

		

	}
	
	public static ArrayList<String> suffle(ArrayList<String> num,int k) {

		ArrayList<String> word =new ArrayList<String>();
		List<String> card = num;


		for(int i = 1 ; i<= k+1;i++) {
			int slice = (int) (Math.pow(2, k-i+1));
			word.addAll(0,card.subList(0, card.size()-slice));
			card =card.subList(card.size()-slice, card.size());
		}
		word.add(0,card.get(0));


		return word;
	}

	
	
}
