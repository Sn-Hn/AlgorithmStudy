package solution291;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 프로그래머스 입국심사 (이분탐색)
public class Main {
    public static void main(String[] args) {

        System.out.println(solution(6,new int[] {7,10}));

    }

    public static long solution(int n, int[] times) {
        long answer = 0;


        Arrays.sort(times);
        long max = times[times.length-1]*(long)n;

        long left = 1;
        long right = max;


        while(left <= right){
            long mid = (left+right)/2;

            long sum = 0;
            for(int i = 0 ; i < times.length;i++){
                sum += mid / times[i];
            }

            if(sum >= n){
                answer = mid;
                // 간격을 좁힌다.
                right = mid-1;
            }else{
                left = mid+1;
            }




        }


        return answer;
    }
}
