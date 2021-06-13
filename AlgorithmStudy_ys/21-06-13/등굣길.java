package solution87;

import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		
		solution(4,3,new int[][] {{2,2}});
		solution(5, 3, new int[][]{{3,2}});
		
	}

    public static int solution(int m, int n, int[][] puddles) {
        
        int map[][] = new int[n+1][m+1];
        
        

        for(int i  = 0 ; i < puddles.length; i++) {
        	int x = puddles[i][0];
        	int y = puddles[i][1];
        	map[x][y] = -1;	
        }
        
        map[1][1] = 1;
        for(int i = 1 ; i <= n ;i++) {
        	for(int j = 1 ; j <= m; j++) {
        		
        		if(map[i][j]==-1) {
        			map[i][j] = 0;
        			continue;
        		}
        		
        		
        		if(i!=1) {
        			map[i][j] += map[i-1][j] %1000000007;
        		}
        		
        		if(j!=1) {
        			map[i][j] +=map[i][j-1] %1000000007;
        									
        		}
        		
        	}
        }
        
        
        for(int i = 0 ; i < map.length;i++) {
        	System.out.println(Arrays.toString(map[i]));
        }
     
        return map[n][m]%1000000007;
    }
    
   

    
	
}
