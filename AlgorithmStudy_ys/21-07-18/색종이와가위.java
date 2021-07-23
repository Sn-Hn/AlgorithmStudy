package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이와가위 {

    static int n;
    static long k;
    static String ans = "NO";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        /**
         * 백준 20444 색종이와 가위
         * 접근법.
         * 접근 색종이를 자를수 있는 방법은 가로,세로
         * 가로 세로 자르는 방법을 정해진 가위질 만큼 모든 경우에수를 해본다 (시간초과를 생각못함)
         *
         * 2번째
         * 최대로 많이 조각을 낼 수있는 경우는 가로n/2 세로n/2 번으로 자르는것
         * 그래서 최대 경우의 수를 n/2+1번으로 지정하고 mid를 기준으로 가로 자르는 개수 세로 자르는 개수를 정한다.
         * int col = mid+1;
         * int row = n-mid+1;
         *
         * long pieces = col* row; -> 조각 개수
         * 그리고 기준과 비교하면서 값을 도출
         */

        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());


        int left = 0 ;
        int right = n/2+1; // 홀수일 경우는 +1 해줘야 하므로

        while(left <= right){
            int mid = (left + right)/2;

            int col = mid+1;
            int row = n-mid+1;

            long pieces = col* row;

            if(pieces < k){
                left = mid+1;
            }else if (pieces > k){
                right = mid-1;
            }else{
                ans = "YES";
                break;
            }

        }

        System.out.println(ans);

    }



}
