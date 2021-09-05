import java.util.*;

class Solution {
    // ! 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다 !

    static PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1.start == o2.start) 
            return Integer.compare(o1.taskTime, o2.taskTime);
        else return Integer.compare(o1.start, o2.start);
    });
    
    static PriorityQueue<Job> innerPq = new PriorityQueue<>(Comparator.comparingInt(o -> o.taskTime));
    
    public int solution(int[][] jobs) {
        for (int[] job : jobs) {
            pq.add(new Job(job[0], job[1]));
        }
        
        int nowTime = 0;
        int totalTime = 0;
        
        while (!pq.isEmpty())
        {
            // LAST TIME 이내 job
            while (!pq.isEmpty() && pq.peek().start <= nowTime) {
                innerPq.add(pq.poll());
            }
            // LAST TIME 바로 다음 job
            if (innerPq.isEmpty()) {
                nowTime = pq.peek().start;
                innerPq.add(pq.poll());
            }
            // 소요시간 가장 짧은거 수행
            Job job = innerPq.poll();
            nowTime += job.taskTime;
            totalTime += nowTime - job.start;
        }
        // 나머지 job들 수행
        while (!innerPq.isEmpty()) {
                Job job = innerPq.poll();
                nowTime += job.taskTime;
                totalTime += nowTime - job.start;
        }
        return totalTime / jobs.length;
    }
    
    public static class Job {
        int start;
        int taskTime;
        
        public Job(int s, int t) {
            this.start = s;
            this.taskTime = t;
        }
    }
}