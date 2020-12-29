import java.io.*;
import java.util.*;

class Pair{
    Integer x;
    Integer y;
    public Pair(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
}

public class 미네랄 {

    static int row;
    static int col;
    static int stickCnt;
    static char[][] map;
    static int[][] visited;
    static int[] stick;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];

        //map 입력
        for(int i=0; i<row; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j=0; j<col; j++){
                map[i][j] = str.charAt(j);
            }
        }

        //방향 입력
        st = new StringTokenizer(br.readLine());
        stickCnt = Integer.parseInt(st.nextToken());
        stick = new int[stickCnt];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<stickCnt; i++){
            stick[i] = Integer.parseInt(st.nextToken());
        }

        //동작
        for(int i=0; i<stickCnt; i++){
            visited = new int[row][col];
            if(i%2==0) throwing(row-stick[i],1);
            else throwing(row-stick[i],0);

            down();
        }

        //출력
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static void throwing(int height , int dir){
        if(dir==1){//왼->오
            for(int i=0; i<col; i++){
                if(map[height][i]=='x'){
                    map[height][i] = '.';
                    break;
                }
            }
        }else{//오->왼
            for(int i=col-1; i>=0; i--){
                if(map[height][i]=='x'){
                    map[height][i] = '.';
                    break;
                }
            }
        }
    }

    static void down(){
        ArrayList<Pair> list = new ArrayList<>();
        //Find cluster
        Queue<Pair> q = new LinkedList<>();
        for(int i=0; i<col; i++){
            if(map[row-1][i]=='.'|| visited[row-1][i]==1) continue;
            q.add(new Pair(row-1,i));
            visited[row-1][i] = 1;

            while(!q.isEmpty()){
                int x = q.peek().x;
                int y = q.peek().y;
                q.remove();

                for(int j=0; j<4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx>=0&&ny>=0&&nx<row&&ny<col){
                        if(map[nx][ny]=='x'&&visited[nx][ny]==0){
                            visited[nx][ny] = 1;
                            q.add(new Pair(nx,ny));
                        }
                    }
                }
            }
        }
        //cluster -> .
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(visited[i][j]==0 && map[i][j]=='x'){
                    list.add(new Pair(i,j));
                    map[i][j] = '.';
                }
            }
        }
        if(list.size()==0) return;

        boolean check = true;

        while(check){
            for(Pair i : list){
                int x = i.x;
                x++;
                int y = i.y;
                if(x>=row || map[x][y]=='x'){
                    check = false; break;
                }
            }
            if(check){
                for(int i=0; i<list.size(); i++){
                    list.get(i).x++;
                }
            }
        }
        for(Pair i : list){
            int x = i.x;
            int y = i.y;
            map[x][y] = 'x';
        }
    }
}
