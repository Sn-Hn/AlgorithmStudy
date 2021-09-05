class Solution {
    static int h, w;
    public int solution(int[][] triangle) {
        
        h = triangle.length;
        w = triangle[h-1].length;
        
        return go(triangle);
    }
    
    private static int go(int[][] triangle)
    {
        for (int y = h - 1; y > 0; y--) {
            for (int x = 0; x < y; x++) {
                triangle[y-1][x] += Math.max(triangle[y][x], triangle[y][x+1]);
            }
        }
        return triangle[0][0];
    }
}