package BackTracking;

import java.io.*;
import java.util.*;

public class Boj_1941_소문난칠공주 {

    static char[][] map;
    static boolean[] visited;
    static int answer = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[26][26];
        visited = new boolean[26];

        for(int i=0; i<5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j=0; j<5; j++){
                map[i][j] = str.charAt(j);
            }
        }
        dfs(-1,0);

        System.out.println(answer);
    }


    //BackTracking
    static void dfs(int cnt, int tCnt){
        if(tCnt==7){
            if(check()) answer++;
            return;
        }

        for(int i=cnt+1; i<25; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, tCnt+1);
                visited[i] = false;
            }
        }


    }

    static boolean check(){
        int start = 0;
        int sCnt = 0;
        boolean[][] chk = new boolean[5][5];

        for(int i=0; i<25; i++){
            if (visited[i]) {
                start = i;
                chk[i / 5][i % 5] = true;

                if (map[i / 5][i % 5] == 'S') {
                    ++sCnt;
                }
            }
        }

        if(sCnt < 4){
            return false;
        }

        int count = 0;

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start / 5, start % 5));

        while (!q.isEmpty()) {
            Point temp = q.poll();

            if (count == 7) {
                return true;
            }
            count++;

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && chk[nx][ny]) {
                    q.add(new Point(nx, ny));
                    chk[nx][ny] = false;
                }
            }
        }

        return false;
    }

}
