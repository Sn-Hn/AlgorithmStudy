import java.io.*;
import java.util.*;

public class 연구소_14502 {
    static int W;
    static int H;
    static int answer = 0;
    static int[][] board;
    static int[][] cp_board;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Pair> virusList;

    static class Pair{
        Integer x;
        Integer y;
        public Pair(Integer x, Integer y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[W][H];
        virusList = new ArrayList<>();

        for(int i=0; i<W; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<H; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==2) virusList.add(new Pair(i,j));
            }
        }

        comb(0,0,0);

        System.out.println(answer);
    }

    static void comb(int x, int y, int cnt){
        if(cnt==3){
            cp_board = new int[W][H];
            for(int i=0; i<W; i++){
                cp_board[i] = board[i].clone();
            }
            spread();
            answer = Math.max(answer,remain());
            return;
        }
        for(int i=x; i<W; i++){
            for(int j=0; j<H; j++){
                if(i==x && j<y) continue;

                if(board[i][j]==0){
                    board[i][j] = 1;
                    comb(i,j+1, cnt+1);
                    board[i][j] = 0;
                }
            }
        }
    }

    static void spread(){
        Queue<Pair> q = new LinkedList<>();
        for(int i=0; i<virusList.size(); i++){
            q.add(virusList.get(i));
        }
        while(!q.isEmpty()){
            int x = q.peek().x;
            int y = q.peek().y;
            q.remove();

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0&&ny>=0&&nx<W&&ny<H&&cp_board[nx][ny]==0){
                    cp_board[nx][ny] = 2;
                    q.add(new Pair(nx,ny));
                }
            }
        }
    }

    static int remain(){
        int cnt = 0;
        for(int i=0; i<W; i++){
            for(int j=0; j<H; j++){
                if(cp_board[i][j]==0) cnt++;
            }
        }
        return cnt;
    }



}