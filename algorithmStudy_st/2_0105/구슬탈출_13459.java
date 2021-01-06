import java.io.*;
import java.util.*;

public class 구슬탈출_13459 {

    static char[][] board;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][][][] visited;
    static int N,M;
    static int rx, ry, bx, by;
    static int answer=11;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j=0; j<M; j++){
                board[i][j] = str.charAt(j);
                if(str.charAt(j)=='R') {
                    rx = i; ry = j;
                }
                else if(str.charAt(j)=='B'){
                    bx = i; by = j;
                }
            }
        }

        visited = new boolean[11][11][11][11];
        visited[rx][ry][bx][by] = true;
        dfs(1,rx,ry,bx,by);
        if(answer>10) answer = 0;
        System.out.println(answer);

    }
    static void dfs(int cnt, int rx, int ry, int bx, int by){
        if(cnt>10) return;

        for(int i=0; i<4; i++){
            int mrx = rx, mry = ry, mbx = bx, mby = by;

            while(board[mrx][mry]!='O' && board[mrx+dx[i]][mry+dy[i]]!='#'){
                mrx+=dx[i];
                mry+=dy[i];
            }
            while(board[mbx][mby]!='O' && board[mbx+dx[i]][mby+dy[i]]!='#'){
                mbx+=dx[i];
                mby+=dy[i];
            }
            if(board[mbx][mby]=='O') continue;
            if(board[mrx][mry]=='O'){
                answer = 1;
                return;
            }
            if(mrx==mbx && mry==mby){
                if(Math.abs(mrx-rx) + Math.abs(mry-ry) < Math.abs(mbx-bx) + Math.abs(mby-by)){
                    mbx -=dx[i];
                    mby -=dy[i];
                }else{
                    mrx -=dx[i];
                    mry -=dy[i];
                }
            }
            if(visited[mrx][mry][mbx][mby]) continue;
            visited[mrx][mry][mbx][mby] = true;
            dfs(cnt+1, mrx, mry, mbx, mby);
            visited[mrx][mry][mbx][mby] = false;
        }

    }
}
