package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//프로그램스 N으로 표현 (dp) 꼭 복습
public class N으로표현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solution(5,12);

    }

    static int n;
    static int ans = Integer.MAX_VALUE;
    public static int solution(int N, int number) {

        n = N;

        recursive(0,0,number);

        if(ans >8){
            ans = -1;
        }

        return ans;
    }

    public static void recursive(int sum,int cnt,int number){
        if( cnt > 8){
            return;
        }
        if(sum == number){

            ans = Math.min(ans,cnt);
            return;
        }


        int plusNum = 0;
        for(int i = 0 ; i < 8-cnt ;i++){
            plusNum  += (int) (Math.pow(10,i)*n);
            recursive(sum+plusNum ,cnt+i+1,number);
            recursive(sum-plusNum,cnt+i+1,number);
            recursive(sum*plusNum,cnt+i+1,number);
            recursive(sum/plusNum,cnt+i+1,number);
        }



    }


}
