package solution88;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	
	
	
	static int arr[];
	static boolean visited[];
	static int n;
	static int num[];
	static boolean end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		num = new int[] {0,1,2,3};
		arr = new int[n];
		end = false;
		back("");
	}
	
	
	public static void back(String word) {
		
		if(end) {
			return ;
		}
		if(word.length() == n) {
			end = true;
			System.out.println(word);
		}
		
		
		for(int i = 1 ; i <= 3 ; i++) {
			if(istrue(word +i)) {
			
				back(word+i);
			}
		}
		
		
		
	}
	
	
	public static boolean istrue(String word) {
//		System.out.println("들어온단어"+word);

		for(int i = 1; i <=word.length()/2 ; i++) {
//			System.out.println("단어비교"+word.substring(word.length()-i*2,word.length()-i));
//			System.out.println("단어비교b"+word.substring(word.length()-i, word.length()));
			if(word.substring(word.length()-i*2,word.length()-i).equals(word.substring(word.length()-i, word.length()))){
				return false;
			}
		}
		return true;
		
	}
	
	
}
