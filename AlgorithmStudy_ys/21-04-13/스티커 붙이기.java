import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<int[][]> sticker;
	static int map[][];
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		int k = Integer.parseInt(st.nextToken());
		
		sticker = new ArrayList<int[][]>();
		for(int i = 0 ;i < k;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int arr[][] = new int[r][c];
			for(int j = 0 ; j < r ;j++) {
				st = new StringTokenizer(br.readLine());
				for(int z = 0 ; z< c ;z++) {
					arr[j][z] = Integer.parseInt(st.nextToken());
					
				}
			}
			sticker.add(arr);
		}
	
		for(int i = 0 ; i < sticker.size();i++) {
			
			for(int j = 0 ; j < 4;j++) {
				if( search(n,m,i) ) {
					break;
				}else {
				
					rotate(i);
				}
			}
		}

//		for(int a[] : map) {
//			System.out.println(Arrays.toString(a));
//		}
		int cnt = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m; j++) {
				
				if(map[i][j] == 1) {
					cnt++;
				}
					
					
			}
		}
		
		
		System.out.println(cnt);

	}
	
	public static boolean search(int n,int m,int stickernum) {
	

		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m; j++) {
				
				//붙일수 있나 ?
				if(iSstick(i, j, stickernum)) {
					//있으면 붙이고.
					stick(i, j, stickernum);
			
					
					return true;
				}
				
					
					
			}
		}
		return false;
			
		
		
		
	}
	public static void rotate(int stickernum) {
		
		int tmp[][] = sticker.get(stickernum);
		
		int newSticker[][] = new int[tmp[0].length][tmp.length];
		
			for(int i = 0 ; i < tmp[0].length;i++) {
				for(int j  = 0 ; j < tmp.length;j++) {
					newSticker[i][j]+=tmp[tmp.length-1-j][i];
				}
			}
		
			
	
		sticker.set(stickernum, newSticker);
	
		
		return;
	}
	
	public static void stick(int r,int c,int stickernum) {
		
		int newSticker[][] = sticker.get(stickernum);
		
		for(int i = 0; i < newSticker.length;i++) {
			for(int j = 0 ; j < newSticker[i].length ;j++) {
				map[i+r][j+c]  = map[i+r][j+c] +newSticker[i][j];
			}
		}
//		for(int a[] : map) {
//			System.out.println(Arrays.toString(a));
//		}
//		System.out.println("-------------------------------");

		
		
	}
	
	
	public static boolean iSstick(int r,int c,int stickernum) {
		
		int newSticker[][] = sticker.get(stickernum);
		
		for(int i = 0; i < newSticker.length;i++) {
			for(int j = 0 ; j < newSticker[i].length ;j++) {
				if(i+r >=n || j+c>=m) {
					
					return false;
				}
				
				if(map[i+r][j+c] +newSticker[i][j] ==2) {
					
					return false;
				}
			
			}
		}
		
		return true;
		
	}
	
	
}
