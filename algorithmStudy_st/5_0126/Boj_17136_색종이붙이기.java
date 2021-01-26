package BackTracking;

import java.io.*;
 import java.util.*;

public class Boj_17136_색종이붙이기 {

    static int[][] map = new int[10][10];
    static int mn = 1000000000;
    static int[]paper = new int[5]; //1 , 22, 33, 44, 55
    static int cnt = 0;
    static int attachCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) cnt++;
            }
        }

        int[] use = new int[5];
        dfs();

        if (mn == 1000000000) System.out.println(-1);
        else System.out.println(mn);
    }

    static void dfs(){
        if(attachCnt==cnt){//다 채웠으면
            //사용한 종이 비교
            int total = 0;
            for(int i=0; i<5; i++){
                total += paper[i];
            }
            if(mn>total) mn = total;
            return;
        }
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(map[i][j]==1){
                    for(int k=4; k>=0; k--){
                        if(paper[k]<5 && check(i,j,k)) {//5개 안넘었으면
                            attach(i, j, k, 0);
                            paper[k]++;
                            attachCnt += (k + 1) * (k + 1);
                            dfs();
                            paper[k]--;
                            attach(i, j, k, 1);
                            attachCnt -= (k + 1) * (k + 1);
                        }
                    }
                    return;
                }
            }
        }
    }

    static boolean check(int i, int j, int k){
        for(int x = 0; x <=k; x++){
            for(int y=0; y<=k; y++){
                int nx = i + x;
                int ny = j + y;
                if(nx<0||ny<0||nx>=10||ny>=10) return false;
                if(map[nx][ny]==0) return false;
            }
        }
        return true;
    }
    static void attach(int i, int j, int k, int l){
        for(int x = 0; x <=k; x++){
            for(int y=0; y<=k; y++){
                map[i+x][i+y] = l;
            }
        }
    }
}