package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 20165 인내의 도미노장인 호석 (구현)
public class 인내의도미노장인호석 {
    static int map[][];
    static int n;
    static int m;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r =  Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0 ; i < n;i++){
            String tmp[] = br.readLine().split(" ");
            for(int j = 0 ; j < m;j++){
               map[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        for(int i = 0 ; i < r ;i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            String way = st.nextToken();

            slide(row-1,col-1,way);

            st = new StringTokenizer(br.readLine());
            int upRow = Integer.parseInt(st.nextToken());
            int upCol = Integer.parseInt(st.nextToken());
            if(map[upRow-1][upCol-1]  < 0){
                map[upRow-1][upCol-1] *= -1;
            }
        }

        System.out.println(ans);
        for(int i = 0 ; i < n;i++){
            for(int j = 0 ; j < m;j++){
                if(map[i][j] <0){
                    System.out.print("F ");
                }else{
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }

    public static void slide(int row,int col,String way){
        int height = map[row][col];
        if(map[row][col] > 0){
            ans++;
            map[row][col] *= -1;
        }else{
            return ;
        }

        //북
        if(way.equals("N")){

            for(int i = row-1; i >= row-height+1 ;i--){
                if(i <0) return;
                if(map[i][col]  > 0){
                    slide(i,col,"N");
                }
            }
            //동
        }else if(way.equals("E")){
            for(int i = col+1; i <= col+height-1 ;i++){
                if(i >=m) return;
                if(map[row][i]  > 0){
                    slide(row,i,"E");
                }
            }

            //서
        }else if(way.equals("W")){


            for(int i = col-1; i >= col-height+1 ;i--){
                if(i <0) return;
                if(map[row][i]  > 0){
                    slide(row,i,"W");
                }
            }

            //남
        }else{
            for(int i = row+1; i <= row+height-1 ;i++){
                if(i >=n) return;
                if(map[i][col]  > 0){
                    slide(i,col,"S");
                }
            }

        }


    }


}


