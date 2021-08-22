//22236: 가희와 비행기
import java.io.*;
import java.util.*;
 
public class Main {

    static int INF = 4001;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int d = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        //입력 끝

        long[][] dp = new long[INF + 1][INF + 1];
        dp[1][1] = 1;
        for(int i = 2; i < d; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) continue;
                else dp[i][j] += (dp[i - 1][j - 1] + dp[i - 1][j + 1] % m);
            }
        }
        System.out.println(dp[d - 1][1] % m);
    }
}