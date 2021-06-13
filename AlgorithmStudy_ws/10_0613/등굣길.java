import java.util.*;

class Solution {
    static int[] dy = {1, 0};
    static int[] dx = {0, 1};
    static int[][] map;
    static int[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        map = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        
        for (int i = 0; i < puddles.length; i++)
        {
            int y = puddles[i][1];
            int x = puddles[i][0];
            map[y][x] = 1;
        }
        
        BFS(n, m);
        
        return dp[n][m] % 1_000_000_007;
    }
    
    private static void BFS(int n, int m)
    {
        Queue<Node> queue = new LinkedList<>();
        for (int d = 0; d < 2; d++)
        {
            int ny = 1 + dy[d];
            int nx = 1 + dx[d];
            if (map[ny][nx] == 0) {
                queue.add(new Node(ny, nx));
                dp[ny][nx] = 1;
            }
        }
        
        while (!queue.isEmpty())
        {
            Node node = queue.poll();
            
            if (node.y == n && node.x == m) break;
            
            for (int d = 0; d < 2; d++)
            {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];
                
                if (ny <= 0 || ny > n || nx <= 0 || nx > m) continue;
                if (map[ny][nx] == 1) continue;
                
                if (dp[ny][nx] == 0) {
                    dp[ny][nx] += dp[node.y][node.x] % 1_000_000_007;
                    queue.add(new Node(ny, nx));
                } else {
                    dp[ny][nx] += dp[node.y][node.x];
                    dp[ny][nx] %= 1_000_000_007;
                }
            }
        }
    }
    
    private static class Node
    {
        int y, x;
        public Node(int y, int x)
        {
            this.y = y;
            this.x = x;
        }
    }
}