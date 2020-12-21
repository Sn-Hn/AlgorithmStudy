package boj_dfs_20201229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*

알파벳 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	40957	13056	8032	29.990%
문제
세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

입력
첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.

출력
첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

예제 입력 1 
2 4
CAAB
ADCB
예제 출력 1 
3

예제 입력 2
5 5
IEFCJ
FHFKC
FFALF
HFGCF
HMCHH
예제 출력 2
10

*/

// dfs
// List와 map을 String으로 받았었는데 시간 초과가 남
// List와 map을 char형으로 바꿔주니 통과는 하는데 시간이 오래 걸림
// 16848KB	3900ms

public class Alphabet_1987 {
	private static int R, C;
	private static char map[][];
	private static boolean visited[][];
	private static List<Character> alphabet = new ArrayList<Character>();
	private static int max = Integer.MIN_VALUE;
	
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = "";
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+1][C+1];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		visited[0][0] = true;
		alphabet.add(map[0][0]);
		move(0, 0);
		
		System.out.println(max);
		
		br.close();
	}
	
	private static void move(int x, int y) {
		max = Math.max(max, alphabet.size());
		
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if(X >= 0 && X < R && Y >= 0 && Y < C && !visited[X][Y]) {
				if(!alphabet.contains(map[X][Y])) {
					visited[X][Y] = true;
					alphabet.add(map[X][Y]);					
					move(X, Y);
					visited[X][Y] = false;
					alphabet.remove(alphabet.size()-1);
				}
			}
		}
		
	}
	
	private static void printMap() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
