package solution92;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
//	10,20,30 이 파란색인데 이경우는 5의 배수 
//	파란색이면 중간길로 가야한다.
//  20인곳은 
	
	
	public static class Node {
		int score,blue,red;
		boolean isblue = false;
		
		Node(int score,int red){
			this.score = score;
			this.red = red;
		}
		
		Node(int blue,boolean isblue){
			this.blue = blue;
			this.isblue = isblue;
		}
		
		
	}
	
	
	static Node[] map;
	static int dice[];
	static int horsenum[];
	static int now[];
	static boolean existchk[];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp[] = br.readLine().split(" ");
		dice = new int[10];
		for(int i = 0 ; i< 10 ; i++) {
			dice[i] = Integer.parseInt(tmp[i]);
		}
		
		map = new Node[43];
		horsenum = new int[10];
		ans = 0;
		
		
		// 현재 노드와 다음 노드 
		for(int i = 0 ; i <=40;i++) {
			map[i] = new Node(i, i+2);
		}
		
		
		map[10].isblue = map[20].isblue = map[30].isblue = true;
		map[10].blue = 11;
		map[20].blue = 17;
		map[30].blue = 31;
		
//		map[10] = new Node(11, true);
		map[11] = new Node(13,13);
		map[13] = new Node(16,15);
		map[15] = new Node(19,25);
//		map[20] = new Node(17,true);
		map[17] = new Node(22,19);
		map[19] = new Node(24,25);
		map[25] = new Node(25,37);
//		map[30] = new Node(31,true);
		map[31] = new Node(28,33);
		map[33] = new Node(27,35);
		map[35] = new Node(26,25);
		map[37] = new Node(30,39);
		map[39] = new Node(35,40);
		map[42] = new Node(0,42);

		
		// 첫번째는 어떤 말이 움직이든 똑같으니 9번만
		combi(9,0);
		
		
		System.out.println(ans);
	}
	
	//주사위 10번의 움직일 말의 번호를 조합으로 뽑음
	private static void combi(int n, int k) {
		if (n == k) {
			now = new int[4];
			existchk = new boolean[43];
			move();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			horsenum[k] = i;
			combi(n, k + 1);
			
		}
	}
	
	public static void move() {
		int score = 0;
		
		// 주사위 이동 움직여야한다.
		for(int i = 0 ; i < 10 ; i++) {
			int end = horsemove(horsenum[i],dice[i]);
			
			//종료 자리에 다른 말이 있다.
			if(end == -1 ) {
				return;
			}
			
			now[horsenum[i]] = end; //말의 위치 변경
			score +=map[end].score;
			
			if((i == 9) && ( ans < score) ) {
				ans = score;
			}
			
		}
		
	}
	
	public static int horsemove(int horse,int step) {
		int tmp = now[horse]; // 현재 horse의 위치
		
		//주사위의 크기만큼 step만큼 1씩증가하면서(Node에 다음 노드 위치를 적어놨으니)
		for(int i = 0 ; i < step; i++) {
			if(i == 0 && map[tmp].isblue) {
				tmp = map[tmp].blue;
			}else {
				tmp = map[tmp].red;
			}
			
		}
		//종료 자리에 말이 있다.
		if(tmp <=40 && existchk[tmp] ) {
			return -1;
		}else {
			existchk[now[horse]]= false;//전에 있던 위치
			existchk[tmp] = true; // 이동된 위치
			return tmp;
		}
		
		
		
	
		
	}
	
	
	
	
	
}
