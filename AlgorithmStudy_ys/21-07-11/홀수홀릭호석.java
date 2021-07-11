package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 20164 홀수홀릭 호석
public class 홀수홀릭호석 {

    static int cnt;
    static int max = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();


        cnt = 0;

        recursive(n,0);
        System.out.println(min+" "+max);


    }

    public static void recursive(String n,int cnt){
        char num[] = n.toCharArray();

        //홀수 개수 카운트
        for(int i = 0 ; i < num.length; i++){
            if(Character.getNumericValue(num[i]) %2 != 0){
                cnt++;
            }
        }

        if(num.length >= 3){
            for(int i = 0; i < num.length-2; i++){
                for(int j = i+1 ; j < num.length-1; j++){
                    int sum = sum(i,j,num);
                    recursive(Integer.toString(sum),cnt);
                }
            }
        }else if(num.length >= 2){
            for(int i = 0 ; i < num.length-1;i++){
                int sum = sum(i,num);
                recursive(Integer.toString(sum),cnt);
            }

        }else{
            max = Math.max(max,cnt);
            min = Math.min(min,cnt);

        }


    }

    public static int sum(int left,char num[]){

        StringBuilder leftSum = new StringBuilder();
        StringBuilder midSum = new StringBuilder();

        for(int i = 0; i < num.length; i++){

            if(i <=left){
                leftSum.append(num[i]);
            }else if ( i > left){
                midSum.append(num[i]);
            }

        }

        int sum = Integer.parseInt(leftSum.toString())+Integer.parseInt(midSum.toString());

        return sum;
    }


    public static int sum(int left,int mid,char num[]){

        StringBuilder leftSum = new StringBuilder();
        StringBuilder midSum = new StringBuilder();
        StringBuilder rightSum =new StringBuilder();

        for(int i = 0; i < num.length; i++){

            if(i <=left){
                leftSum.append(num[i]);
            }else if ( i <= mid){
                midSum.append(num[i]);
            }else if ( i > mid ){
                rightSum.append(num[i]);
            }

        }
        int sum = Integer.parseInt(leftSum.toString())+Integer.parseInt(midSum.toString())+Integer.parseInt(rightSum.toString());

        return sum;
    }




}
