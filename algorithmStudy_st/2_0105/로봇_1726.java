import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇_1726 {
    static class Pair {
        Integer x;
        Integer y;
        Integer dir;
        Integer cnt;
        public Pair(Integer x, Integer y, Integer dir, Integer cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static int[][] board;
    static Pair robot;
    static Pair dest;
    //동 서 남 북
    static int[] dx = {0,0,0,1,-1};
    static int[] dy = {0,1,-1,0,0};

    static boolean[][][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M][5];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        robot = new Pair(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()),0);

        st = new StringTokenizer(br.readLine());
        dest = new Pair(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()),0);

        bfs();
    }
    static void bfs(){
        Queue<Pair> q = new LinkedList<>();
        visited[robot.x][robot.y][robot.dir] = true;
        q.add(robot);

        while(!q.isEmpty()){
            int x = q.peek().x;
            int y = q.peek().y;
            int dir = q.peek().dir;
            int cnt = q.peek().cnt;
            q.remove();

            if(x== dest.x && y== dest.y && dir == dest.dir){
                System.out.println(cnt);
                return;
            }
            for(int i=1; i<=3; i++){
                int nx = x + dx[dir]*i;
                int ny = y + dy[dir]*i;
                if(nx>=0 && ny>=0 && nx<N && ny<M){
                    if(board[nx][ny]==0){
                        if(!visited[nx][ny][dir]){
                            visited[nx][ny][dir] = true;
                            q.add(new Pair(nx,ny,dir,cnt+1));
                        }
                    }else break;
                }
            }

            for(int i=1; i<=4; i++){
                if(!visited[x][y][i] && dir!=i){
                    visited[x][y][i] = true;
                    if(Math.abs(dir-i)>1){
                        q.add(new Pair(x,y,i,cnt+1));
                    }else{
                        if((dir==2&&i==3)||(dir==3&&i==2)) q.add(new Pair(x,y,i,cnt+1));
                        else q.add(new Pair(x,y,i,cnt+2));
                    }
                }
            }

        }
    }

}
