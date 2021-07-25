package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 창영이와퇴근_이분탐색 {

    static int map[][];
    static int a[] = {0,0,-1,1};
    static int b[] = {1,-1,0,0};
    static boolean visited[][];
    static int n;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0 ; i < n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 접근
         * 경로의 최대값중 최소값을 뽑아야하는데
         * 가능한 최대값을 이분탐색으로 값을 찾은뒤
         * 가능 여부를 bfs를 돌려 확인한다.
         * lower bound를 통해 가장 작은값을 찾는다.
         * 그냥 visited에 최소값을 저장하여 활용하는 bfs보다는 느리다.
         *
         */
        ans = bs();
        System.out.println(ans);


    }

    public static boolean bfs(int max){


        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{0,0});

        while(!que.isEmpty()){
            int cur[] = que.poll();

            int r = cur[0];
            int c = cur[1];

            if(r == n-1 && c == n-1){
                return true;
            }


            for(int i =0  ;i < 4 ;i++){
                int mr = r + a[i];
                int mc = c + b[i];

                if(mr < 0 || mc <0 || mr >=n || mc >=n) continue;
                if(!visited[mr][mc] && Math.abs(map[r][c]-map[mr][mc]) <= max){
                    visited[mr][mc] = true;
                    que.add(new int[] {mr,mc});
                }


            }

        }



        return false;

    }


    public static int bs(){
        int left = 0;
        int right = 1000000000;

        while(left < right){
            int mid = (left+right)/2;
            visited = new boolean[n][n];


            if(bfs(mid)){

                right = mid;
            }else{

                left = mid+1;

            }
        }

        return left;



    }


}
