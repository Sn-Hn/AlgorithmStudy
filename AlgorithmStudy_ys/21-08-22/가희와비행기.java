import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가희와비행기 {

    static long dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dp = new long[4002][4002];

        /**
         * 김포 공항에서 이륙하면 김해 공항에 착륙할 때 까지 다른 어떠한 지점에도 착륙하지 않습니다. 고도가 0이 되었을 때, 착륙하였다고 합니다.
         * 비행기는 수평 거리 d만큼 이동합니다.  수평거리 d만큼 이동했을 때 고도가 0인 지점에 김해 공항이 있습니다.
         * 비행기는 이륙할 때부터 착륙할 때 까지 비행 방향을 바꾸지 않습니다. 즉, 김포 공항에서부터 김해 공항까지 일직선으로 날아갑니다.
         * 비행기는 지구를 한 바퀴 이상 돌지 않으며, 김해 공항이 있는 방향 반대편(북서쪽)으로 비행하지 않습니다.
         * 김포 공항과 김해 공항의 고도는 0이며, 비행기가 날아가는 경로 상에는 장애물이 존재하지 않습니다.
         */




        dp[0][0] = 1;

        for(int i = 1 ; i <= d ; i++){
            for(int j = 0 ; j <=i ;j++){
                //j-1 이 0이면 착륙한것을 더해주는 것 이므로 d일때만 더해줌
                if(j == 0 && i == d){
                    dp[i][j] = dp[i-1][j+1];
                }else if ( j >= 1){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
                }
                dp[i][j] %= m ;
//                System.out.println("아이"+i+"제이"+j+"="+dp[i][j]);

            }
        }

        System.out.println(dp[d-1][1]);


    }
}
