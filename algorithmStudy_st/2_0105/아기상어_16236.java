import java.io.*;
import java.util.*;

public class 아기상어_16236 {
    static class Pair implements Comparable<Pair>{
        Integer x;
        Integer y;
        Integer w;
        public Pair(Integer w, Integer x, Integer y ){
            this.w = w;
            this.x = x;
            this.y = y;
        }
        public int compareTo(Pair pair){
            if(this.x == pair.x) return this.y.compareTo(pair.y);
            return this.x.compareTo(pair.x);
        }
    }

    static int[][] board;
    static int[][] dist;
    static boolean[] visited = new boolean[404];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int W;
    static int tim = 0;
    static int sharkX, sharkY;
    static int sharkSize = 2;
    static int cnt = 0;
    static boolean chk = true;
    static ArrayList<Pair> fish = new ArrayList<>();


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());

        board = new int[W][W];
        dist = new int[W][W];

        for(int i=0; i<W; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]>0){
                    if(board[i][j]==9){
                        board[i][j] = 0;
                        sharkX = i;
                        sharkY = j;
                    }else{
                        fish.add(new Pair(board[i][j],i,j));
                    }
                }
            }
        }
        if(fish.size()==0){
            System.out.println(0);
            return;
        }

        Collections.sort(fish);

        while(chk){
            move();
        }
        System.out.println(tim);
    }


    static void move(){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,sharkX,sharkY));
        for(int i=0; i<W; i++){
            for(int j=0; j<W; j++){
                dist[i][j] = 1000000;
            }
        }
        dist[sharkX][sharkY] = 0;
        while(!q.isEmpty()){
            int x = q.peek().x;
            int y = q.peek().y;
            q.remove();
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0&&ny>=0&&nx<W&&ny<W&&board[nx][ny]<=sharkSize){
                    if(dist[nx][ny] > dist[x][y]+1){
                        dist[nx][ny] = dist[x][y]+1;
                        q.add(new Pair(0,nx,ny));
                    }
                }
            }
        }
        int mx = 100000;
        int mi = 0;
        for(int i=0; i<fish.size(); i++){
            if(visited[i]) continue;
            if(fish.get(i).w >= sharkSize) continue;
            if(mx > dist[fish.get(i).x][fish.get(i).y]){
                mx = dist[fish.get(i).x][fish.get(i).y];
                mi = i;
            }
        }
        if(mx == 100000){
            chk = false;
            return;
        }
        sharkX = fish.get(mi).x;
        sharkY = fish.get(mi).y;
        visited[mi] = true;
        int spend = dist[sharkX][sharkY];
        tim += spend;
        cnt++;
        if(cnt==sharkSize){
            sharkSize++;
            cnt = 0;
        }
    }
}
