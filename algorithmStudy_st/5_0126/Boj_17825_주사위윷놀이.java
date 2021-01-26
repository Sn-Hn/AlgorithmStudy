package BackTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17825_주사위윷놀이 {

    static int[] map = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0, //19
            10,13,16,19,25,30,35,40,0, //27
            20,22,24,25,30,35,40,0, //34
            30,28,27,26, 25,30,35,40,0 //42
    };

    static boolean[] visited;
    static int total = 0;
    static int[] dice = new int[10];
    static int[]m = new int[4]; //말
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<10; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }
        visited= new boolean[50];

        dfs(0,0);
        System.out.println(total);
    }

    static void dfs(int cnt, int sum){
        if(cnt==10){
            if(sum>total) total = sum;
            return;
        }

        for(int i=0; i<4; i++){
            int prev = m[i];
            int current = prev;
            int mv = dice[cnt];
            int addSum = sum;

            if(map[current]==10){
                current = 21;
                if((current+mv)<30&&visited[current+mv]) continue;
                while(mv>0){
                    if(map[current]==0){
                        break;
                    }
                    addSum += map[current++];
                    mv--;
                }
                current--;
                addSum-=10;
            }else if(map[current]==20){
                current = 30;
                if((current+mv)<38&&visited[current+mv]) continue;
                while(mv>0){
                    if(map[current]==0){
                        break;
                    }
                    addSum += map[current++];
                    mv--;
                }
                current--;
                addSum-=20;
            }else if((current+mv)<28&&map[current]==30){
                current = 38;
                if(visited[current+mv]) continue;
                while(mv>0){
                    if(map[current]==0){
                        break;
                    }
                    addSum += map[current++];
                    mv--;
                }
                current--;
                addSum-=30;
            }else{
                if(visited[current+mv]) continue;
                while(mv>0){
                    if(map[current]==0){
                        break;
                    }
                    addSum += map[current++];
                    mv--;
                }
                current--;
            }

            visited[prev] = false;
            if(map[current]!=0) visited[current] = true;
            m[i] = current;

            dfs(cnt+1, addSum );

            //말
            m[i] = prev;
            visited[prev] = true;
            visited[current] = false;
        }
    }
}
