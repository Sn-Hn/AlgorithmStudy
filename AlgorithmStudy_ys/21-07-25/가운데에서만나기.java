package 플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 가운데에서만나기 {
    static int road[][];
    static int m;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        road = new int[n+1][n+1];
        for(int a[] : road){
            Arrays.fill(a,Integer.MAX_VALUE);
        }

        for(int i = 0 ; i < m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            road[a][b] = time;

        }

        int k = Integer.parseInt(br.readLine());

        String tmp[] = br.readLine().split(" ");

        int home[] = new int[k];
        for(int i = 0 ; i < k ;i++){
            int num = Integer.parseInt(tmp[i]);
            home[i] = num;
        }

        for(int i = 0 ; i <= n;i++){
            road[i][i] = 0;
        }

        /**
         * 왕복 시간중 최대
         * 왕복시간을 구하려면 다익스트라 or 플로이드
         * 그럼 다익스트라 -> k번 만큼 다익스트라를 돌려야한다.
         * 그대신 플로이드 와샬로 구현
         *
         * 왕복시간들중 최대가 최소가 되는 도시 -> 거주 도시별 최대값중 최소값
         */
        floyd();


        ArrayList<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        //도시 개수
        for(int i = 1 ; i <=n;i++){
            //친구 도시 개수
            boolean flag = true;

            int max = 0;
            for(int j =0 ; j < k ;j ++){
               int time =road[home[j]][i];
               int comback = road[i][home[j]];

               if(time != Integer.MAX_VALUE  && comback != Integer.MAX_VALUE){
                   max = Math.max(max,time+comback);
               }else{
                   flag = false;
                   break;
               }
            }

            if(flag && min >= max){
                if(min > max){
                    min = max;
                    list = new ArrayList<>();
                }
                list.add(i);
            }


        }



        list.stream().forEach(num -> System.out.print(num+" ") );


    }


    public static void floyd(){
        for(int k = 1 ; k <= n; k++){
            for(int i = 1 ; i <= n ;i++){
                for(int j = 1 ; j <= n; j++){
                    if (k == i || i == j || j == k)	continue;
                    if(road[i][k] == Integer.MAX_VALUE || road[k][j] == Integer.MAX_VALUE) continue;

                    road[i][j] = Math.min(road[i][j], road[i][k] + road[k][j]);


                }
            }
        }
    }

}
