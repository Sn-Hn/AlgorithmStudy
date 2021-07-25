package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 얼음미로 {


    static class Info  {
        int r;
        int c;
        int time;


        public Info(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    static String map[][];
    static int a[] = {-1,1,0,0};
    static int b[] = {0,0,-1,1};
    static int visited[][];
    static int w;
    static int h;
    static int end[];
    static int ans = Integer.MAX_VALUE;
    static Queue<Info> que;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new String[h][w];
        visited = new int[h][w];

        int start[] = new int[2];
        end = new int[2];

        for(int i = 0 ; i < h ; i++){
            String tmp[] = br.readLine().split("");
            for(int j = 0 ; j < w ; j++){
                map[i][j] = tmp[j];

                if(tmp[j].equals("T")){
                    start[0] = i;
                    start[1] = j;
                    map[i][j] = "0";
                }else if (tmp[j].equals("E")){
                    end[0] = i;
                    end[1] = j;
                }

            }
        }


        /**
         * 접근
         * 4방향 탐색 그리고 탈출구까지
         * 무조건 바위나 탈출구를 만나야한다.(끝은 낭떠러지)
         *
         *
         */

        bfs(start[0],start[1]);



        if(visited[end[0]][end[1]] == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(visited[end[0]][end[1]]);
        }




    }

    public static void bfs(int sr,int sc){
        que = new LinkedList<>();

        for(int a[]:visited){
            Arrays.fill(a,Integer.MAX_VALUE);
        }
        que.add(new Info(sr,sc,0));
        visited[sr][sc] = 0;


        while(!que.isEmpty()){
            Info info = que.poll();
            int r = info.r;
            int c = info.c;
            int time = info.time;


            //  visited[r][c] 가아니라는 것은 r,c위치가 최소 값으로 다시 초기화 되었다. (이전 최소값이다.)
            if(visited[r][c] != time) continue;

            //4방향 탐색
            for(int i = 0 ; i < 4;i++){
                sliding(r,c,i,time);
            }

        }




    }

    public static void sliding(int r,int c,int way,int curTime){
        // 0:상 1:하 2:좌 3:우
        int time = 0;


        while(true){
            r +=a[way];
            c +=b[way];

            if(r < 0 || c <0 || r >=h || c >= w ){
                return ;
            }
            if(map[r][c].equals("H")) return ;

            if (map[r][c].equals("R") ){
                 r-=a[way];
                 c-=b[way];
                 break;
            }
             if (map[r][c].equals("E") ){
                 break;
            }
             time +=Integer.parseInt(map[r][c]);

        }

        int nextTime = time+curTime;
        if(visited[r][c] > nextTime){
            visited[r][c] = nextTime;
            que.add(new Info(r,c,nextTime));
        }





    }


}
