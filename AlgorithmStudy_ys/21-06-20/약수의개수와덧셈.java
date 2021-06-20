package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//프로그래머스 약수의 개수와 덧셈
public class 약수의개수와덧셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solution(13,17);


    }

    public static int solution(int left, int right) {
        int answer = 0;

        int sum = 0;
        for(int i = left; i <= right ;i++){
            int cnt = divisor(i);
            if(cnt % 2 == 0){
                sum += i;
            }else{
                sum -= i;
            }

        }
            System.out.println(sum);
        return answer;
    }
    public static int divisor(int num){
        int cnt = 0;
        for(int i = 1 ; i <= num/2;i++){
            if(num%i == 0) cnt++;
        }

        return cnt+1;
    }


}
