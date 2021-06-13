import java.util.*;

/*
3 [1, 1, 1] 1
3 [1, 2, 3] 2
*/

class Solution {
    static long TIME_MAX;
    public long solution(int n, int[] times) {
        
        TIME_MAX = (long) times.length * 1_000_000_000;
        
        long left = 0;
        long right = TIME_MAX;
        long mid;
        
        while (left <= right)
        {
            mid = (left + right) / 2;
            long div = 0;
            for (int i = 0; i < times.length; i++)
            {
                div += mid / times[i]; 
            }
            
            // less
            if (div < n)
            {
                left = mid + 1;
            } else if (div >= n)
            {
                right = mid - 1;
            }
        }
        
        return left;
    }
}