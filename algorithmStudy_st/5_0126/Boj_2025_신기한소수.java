package BackTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_2025_신기한소수 {

    static int N;
    static LinkedList<Integer> answer = new LinkedList<>();
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        //무조건 시작은 2,3,5,7
        dfs(1,2);
        dfs(1,3);
        dfs(1,5);
        dfs(1,7);

        for(Integer i : answer){
            System.out.println(i);
        }
    }
    static void dfs(int len,int x){
        if(!check(x)) return;
        if(len==N){
            answer.add(x);
            return;
        }
        //안에 원소는 홀수여야함
        for(int i=1; i<10; i+=2){
            dfs(len+1, x*10+i);
        }
    }

    static boolean check(int x){
        if(x==2) return true;
        for(int i=2; i<=Math.sqrt(x); i++){
            if(x%i==0) return false;
        }
        return true;
    }
}
