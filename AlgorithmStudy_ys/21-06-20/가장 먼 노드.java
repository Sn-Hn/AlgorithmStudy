import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	
	
	public static void main(String[] args) {
		
		solution(6,new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
		
	}
	

	static boolean node[][];
	static int visited[];
	static int ans = 0;
    public static int solution(int n, int[][] edge) {
        int answer = 0;
        
        node = new boolean[n+1][n+1];
        
        for(int i = 0 ; i < edge.length; i++) {
	        node[edge[i][0]][edge[i][1]] = true;
	        node[edge[i][1]][edge[i][0]] = true;
        }
        
       
        Queue<Integer> que = new LinkedList<Integer>();
        
        que.add(1);
        int degree[] = new int[n+1];
        
        degree[1] =1;
        int max = 1;
        while(!que.isEmpty()) {
        	
        	int now = que.poll();
        	for(int i = 1 ; i<= n;i++) {
        		if(node[now][i] && degree[i]== 0) {
        			degree[i] = degree[now]+1;
        			node[i][now] = false;
        			max = Math.max(max, degree[i]);
        			que.add(i);
        		}
        	}

        	
        }
        
        
        for(int i = 1 ; i <= n ; i++ ) {
        	if(max == degree[i]) {
        		answer++;
        	}
        }
        
       
        System.out.println(answer);
        return answer;
    }
    
 
    
    
}