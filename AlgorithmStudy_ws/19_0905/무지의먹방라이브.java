import java.util.*;

class Solution {
    static int LEN;
    static PriorityQueue<Food> pq = new PriorityQueue<>(Comparator.comparingLong(f -> f.remainFood));
    static ArrayList<Food> remainFoods = new ArrayList<>();
    
    public int solution(int[] food_times, long k) {
        LEN = food_times.length;
        
        for (int i = 0; i < LEN; i++) {
            pq.add(new Food(i+1, food_times[i]));
        }
        
        long beforeFoodTime = 0;
        long nowFoodTime = 0;
        
        while (!pq.isEmpty())
        {
            Food food = pq.peek();
            
            nowFoodTime = food.remainFood;
            long diff = nowFoodTime - beforeFoodTime;
            
            if (diff != 0) 
            {
                long totalDiff = diff * LEN;
                if (totalDiff <= k) {
                    k -= totalDiff;
                    beforeFoodTime = nowFoodTime;
                } 
                else {
                    remainFoods.addAll(pq);
                    Collections.sort(remainFoods, Comparator.comparingInt(f -> f.idx));
                    return remainFoods.get((int) (k % LEN)).idx;
                }
            }
            
            LEN--;
            pq.poll();
        }
        return -1;
    }
    
    private static class Food {
        int idx;
        long remainFood;
        
        public Food(int i, long r) {
            this.idx = i;
            this.remainFood = r;
        }
    }
}