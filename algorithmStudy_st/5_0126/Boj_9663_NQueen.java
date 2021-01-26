package BackTracking;

import java.io.*;
import java.util.*;

public class Boj_9663_NQueen {
    static int N;
    static int[] C; //row 배열 -> 값은 Column 원래 2차원으로 풀었었음..
    static int answer = 0;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = new int[N+1];
        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int r){
        if(r==N) answer++;
        else {
            for(int i=1; i<=N; i++){
                C[r+1] = i;
                if(check(r+1)){
                    dfs(r+1);
                }
            }
        }
    }

    static boolean check(int r){
        for(int i=1; i<r; i++){
            if(C[i]==C[r] || (r-i)== Math.abs(C[r]-C[i]))return false;
        }
        return true;
    }


}
