import java.io.*;
import java.util.*;

public class 빙산 {
    static int row;
    static int col;

    static int year = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    static int[][] melt;
    static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        melt = new int[row][col];

        for(int idx = 0; idx<row; idx++){
            st = new StringTokenizer(br.readLine());
            for(int idx2=0; idx2<col; idx2++) {
                map[idx][idx2] = Integer.parseInt(st.nextToken());
            }
        }
       counting();
    }
    static void counting(){
        while(true){
            int cnt = 0;
            visited = new int[row][col];
            for(int idx = 0; idx<row; idx++){
                for(int idx2=0; idx2<col; idx2++){
                    if(map[idx][idx2]!=0&&visited[idx][idx2]==0){
                        dfs(idx,idx2);
                        cnt++;
                    }
                }
            }

            if(cnt == 0){
                System.out.println(0);
                break;
            }else if(cnt>=2){
                System.out.println(year);
                break;
            }
            year++;
            melting();
            melt = new int[row][col];
        }
    }

    static void dfs(int x, int y){
        visited[x][y] = 1;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0&&ny>=0&&nx<row&&ny<col&&map[nx][ny]>0){
                melt[x][y]++;
                if (visited[nx][ny] == 0) dfs(nx, ny);
            }
        }
    }

    static void melting(){
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                map[i][j] = map[i][j] - 4 + melt[i][j];
                if(map[i][j]<0) map[i][j] = 0;
            }
        }
    }
}
