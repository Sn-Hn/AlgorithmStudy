package solution292;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



//백준 14938 서강 그라운드( 플로이드,다익스트라)
public class Main {

    static int items[];
    static int map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        //지역의 개수
        int n = Integer.parseInt(st.nextToken());
        //수색 범위
        int m = Integer.parseInt(st.nextToken());
        //길의 개수
        int r = Integer.parseInt(st.nextToken());


        items = new int[n+1];
        map = new int[n+1][n+1];


        String tmp[] = br.readLine().split(" ");
        for(int i = 1 ;i <= tmp.length;i++){
            items[i] = Integer.parseInt(tmp[i-1]);
        }

        for(int i = 1 ; i< map.length;i++){
            Arrays.fill(map[i],1501);
        }


        for(int i = 0 ; i < r ;i++){
            st=  new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b]= c;
            map[b][a]= c;
        }

        for(int i = 1 ; i <=n ;i++){
            map[i][i] = 0;
        }


        floyd(n);


        int ans = 0;
        for(int i = 1 ; i <= n;i++){
            int sum = 0;
            for(int j = 1 ; j <= n;j++){
                if(map[i][j] != Integer.MAX_VALUE && map[i][j] <=m){
                    sum +=items[j];
                }
            }
            ans = Math.max(ans,sum);
        }


        System.out.println(ans);


    }

    public static void floyd(int n){

        for(int k = 1 ;k < n ;k++){
            for(int i = 1 ;i <=n; i++){
                for(int j = 1 ; j <=n ;j++){
                    if(i == j || j== k|| i == k) continue;
                    map[i][j] = Math.min(map[i][j],map[i][k] + map[k][j]);
                }
            }
        }

    }


}
