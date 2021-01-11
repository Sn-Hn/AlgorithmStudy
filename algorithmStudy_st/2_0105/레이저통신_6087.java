import java.io.*;
import java.util.*;

/**
 *
 * 메모리 초과남.. 로직은 맞은 것 같음..이유는 모르겠움 
 *
 */
public class 레이저통신_6087 {

    static char[][] board;
    static int[] dx = {0,0,0,1,-1};
    static int[] dy = {0,1,-1,0,0};
    static int[][] visited;
    static int W;
    static int H;
    static Pair start;
    static Pair dest;
    static int answer = 1000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new char[W][H];
        visited = new int[W][H];
        boolean chk = true;

        for(int i=0; i<W; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j=0; j<H; j++){
                visited[i][j] = 1000;
                board[i][j] = str.charAt(j);
                if(board[i][j]=='C' && chk) {
                    start = new Pair(i,j,0,0); //처음에는 어느 방향도 노상관
                    chk = false;
                }else{
                    dest = new Pair(i,j,0,0);
                }

            }
        }

        bfs();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer);
    }
    static void bfs(){
        Queue<Pair> q = new LinkedList<>();
        visited[start.x][start.y] = 0;
        q.add(start);

        while(!q.isEmpty()){
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;
            int beforeDir = p.beforeDir;
            int cnt = p.cnt;

            if(x== dest.x && y== dest.y){
                answer = Math.min(cnt,answer);
                return;
            }
            for(int i=1; i<=4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && ny>=0 && nx<W && ny<H &&board[nx][ny]!='*'){
                    int tmp = cnt;
                    if(beforeDir!=i&&beforeDir!=0) cnt++;

                    if(visited[nx][ny]>=tmp){
                        q.add(new Pair(nx,ny,i,tmp));
                        visited[nx][ny] = tmp;
                    }
                }
            }
        }
    }
    static class Pair {
        int x;
        int y;
        int beforeDir;
        int cnt;
        public Pair(int x, int y, int beforeDir, int cnt){
            this.x = x;
            this.y = y;
            this.beforeDir = beforeDir;
            this.cnt = cnt;
        }
    }

}

