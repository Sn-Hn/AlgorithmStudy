import java.util.*;
import java.io.*;



public class 치즈 {

    static class Pair{
        Integer x;
        Integer y;
        public Pair(Integer x, Integer y){
            this.x = x;
            this.y = y;
        }
    }

    static int row;
    static int col;

    static int hour = 0;
    static int remain = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] map;
    static boolean[][] visited;

    static ArrayList<Pair> meltingList;

    public static void main(String[] args) throws Exception {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row+2][col+2];

        for(int idx = 0; idx<row; idx++){
            st = new StringTokenizer(br.readLine());
            for(int idx2=0; idx2<col; idx2++) {
                map[idx][idx2] = Integer.parseInt(st.nextToken());
            }
        }
        //가장자리 구분
        outside();
        //멜팅될 애들
        meltingCheck();

        while(meltingList.size()>0){
            hour++;
            melting();
            outside();
            meltingCheck();
        }

        System.out.println(hour);
        System.out.println(remain);

    }

    static void outside(){
        Queue<Pair> q = new LinkedList<>();
        visited = new boolean[row+2][col+2];
        q.add(new Pair(0,0));
        visited[0][0] = true;
        while(!q.isEmpty()){
            int x = q.peek().x;
            int y = q.peek().y;
            q.remove();
            map[x][y] = -1;

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0&&ny>=0&&nx<row&&ny<col){
                    if(map[nx][ny]<=0&&!visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }
    }

    static void melting(){
        for(int i=0; i<meltingList.size(); i++){
            int x = meltingList.get(i).x;
            int y = meltingList.get(i).y;
            map[x][y] = -1;
        }
    }
    static void meltingCheck(){
        meltingList = new ArrayList<>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(map[i][j]==1&&edge(i,j)) meltingList.add(new Pair(i,j));
            }
        }
        if(meltingList.size()!=0) remain = meltingList.size();

    }
    static boolean edge(int x, int y){
        for(int i=0; i<4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(nx>=0&&ny>=0&&nx<row&&ny<col){
                if(map[nx][ny]==-1) return true;
            }
        }
        return false;
    }
}

