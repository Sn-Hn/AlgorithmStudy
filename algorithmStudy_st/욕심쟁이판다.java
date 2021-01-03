import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다 {

    static int[][] map;
    static int[][] dp;

    static int n;
    static int mx = 0;
    static int days;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        dp = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                mx = Math.max(mx,solve(i,j));
            }
        }
        System.out.println(mx);
    }

    static int solve(int x, int y){
        if(dp[x][y]!=0) return dp[x][y];

        int cnt = 1;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0&&ny>=0&&nx<n&&ny<n&&map[nx][ny]>map[x][y]){
                cnt = Math.max(solve(nx,ny)+1,cnt);
                dp[x][y] = cnt;
            }
        }
        return cnt;
    }
}
