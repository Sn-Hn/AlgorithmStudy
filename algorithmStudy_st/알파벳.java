import java.util.*;
import java.io.*;


public class 알파벳 {

    static int[] alpha = new int[26];
    static char[][] map;
    static int row;
    static int col;
    static int mx = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];

        for(int i=0; i<row; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j=0; j<col; j++){
                map[i][j] = str.charAt(j);
            }
        }
        dfs(0,0,1);
        System.out.println(mx);
    }

    static void dfs(int x, int y, int cnt){
        alpha[map[x][y]-'A'] = 1;
        for(int i=0; i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0&&ny>=0&&nx<row&&ny<col){
                if(alpha[map[nx][ny]-'A']==0) dfs(nx,ny,cnt+1);
            }
        }
        alpha[map[x][y]-'A'] = 0;
        mx = Math.max(mx, cnt);
    }

}
