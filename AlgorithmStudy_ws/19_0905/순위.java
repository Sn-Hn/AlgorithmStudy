import java.util.*;

class Solution {
    static boolean[][] win;
    static boolean[][] loss;
    
    public int solution(int n, int[][] results) {
        
        win = new boolean[n+1][n+1];
        loss = new boolean[n+1][n+1];
        
        for (int[] result : results) {
            int winner = result[0];
            int losser = result[1];
            
            win[winner][losser] = true;
            loss[losser][winner] = true;
        }
        
        int answer = solve(n);
        return answer;
    }
    
    private static int solve(int n)
    {
        int count = 0;
        
        win = floyd(win, n);
        loss = floyd(loss, n);
        
        int check;
        for (int i = 1; i <= n; i++) {
            check = 0;
            for (int j = 1; j <= n; j++) {
                if (win[i][j] || loss[i][j]) check++;
            }
            if (check == n - 1) count++;
        }
        return count;
    }
    
    private static boolean[][] floyd(boolean[][] winLoss, int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (winLoss[i][k] && winLoss[k][j]) {
                        winLoss[i][j] = true;
                    }
                }
            }
        }
        return winLoss;
    }
}