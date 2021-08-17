package study_210822;
/*

가희와 은행 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1.5 초	512 MB	142	44	37	33.036%
문제
가희는 창구가 하나인 은행을 운영하고 있습니다. 가희의 은행이 영업을 시작했을 때, 대기 줄에는 손님이 N명 있습니다.

[그림 1] 카운터 직원과 N명의 손님

x번 손님에 대한 정보는 x번 손님의 id 값인 Px와 업무를 처리하는 데 필요한 시간인 tx초로 정보가 주어지게 됩니다.

은행이 영업을 시작하고 난 후에 들어오는 손님은 M명 있습니다. 이 손님들은 입력을 받은 순서대로 각각 N+1, N+2, ..., N+M번 손님이 됩니다.

이 손님들에 대한 정보는 x번 손님의 id 값인 Px와 업무를 처리하는 데 필요한 시간인 tx초, 영업 시작 cx초 후에 들어왔다는 정보가 주어지게 됩니다.

손님은 은행에 들어옴과 동시에, 대기 큐의 맨 뒤에 서게 됩니다. N+1번 손님이 은행을 영업을 시작하고 cN+1초 후에 들어왔다고 생각해 보겠습니다.

 



[그림 2] 은행이 영업을 시작하고 cN+1초 후 상황

N+1번 손님은 은행에 들어오자 마자 대기 큐의 맨 뒤에 줄을 서게 되므로, 영업을 시작하고 cN+1초 후에 대기 큐의 상태는 위와 같습니다.

창구에 있는 직원과 고객들은 아래와 같은 알고리즘으로 업무를 처리합니다.

대기 큐의 맨 앞에 있는 고객이 x번 손님이라고 하면, 창구에 있는 직원은
tx가 T보다 크다면, x번 손님의 업무를 T초동안 처리합니다. 그 후, x번 손님의 업무가 끝나는 데 필요한 시간인 tx는 T만큼 감소합니다.
그렇지 않으면, x번 손님의 업무를 tx초 동안 처리합니다. 이후에, x번 손님의 업무가 끝나는 데 필요한 시간인 tx는 은 0이 됩니다.
대기 큐의 맨 앞에 있는 고객인 x번 손님은
업무가 끝나는 데 필요한 시간인 tx가 0이 되었다면, 은행 바깥으로 나가게 됩니다.
그렇지 않으면 대기 큐의 맨 뒤로 이동하게 됩니다. 만약에 이 때 도착한 손님이 있다면, 도착한 손님 뒤로 가게 됩니다.
대기 큐에 고객이 남았다면 1로 돌아갑니다.
은행이 영업을 시작할 때 부터 창구에 있는 직원은 일을 시작합니다.

은행이 영업을 시작한 시점으로부터 0초가 지났을 때 부터 W-1초가 지날 때 까지 창구에 있는 직원이 어떤 고객의 업무를 처리하는지 알려주세요.

입력
첫 번째 줄에 N과 T, W가 공백을 구분으로 해서 주어집니다.

두 번째 줄 부터 N개의 줄에는 0초일 때, 대기 큐의 앞에 있는 고객부터, Px와 고객이 일을 처리하는 데 필요한 시간 tx가 공백으로 구분되어 주어집니다.

N+2번째 줄에는, 1초 이후에 은행에 들어온 고객 수 M이 주어집니다.

N+3번째 줄부터 M개의 줄에 걸쳐서, Px, tx, cx가 공백으로 구분되어 주어집니다. 입력된 순서대로 각각 N+1, ..., N+M번 고객입니다.

이는 고객 id가 Px인 고객은 일을 처리하는 데 필요한 시간이 tx초이고, 영업 시작 시간으로부터 cx초가 지났을 때 은행에 들어왔다는 것을 의미합니다.

출력
i번째 줄에는 은행이 영업을 시작한 시점으로부터 i-1초가 지났을 때 은행 직원이 처리하고 있는 고객 id를 출력해 주세요.

제한
N, T, W, M는 구간 [1, 2×105]에 속하는 정수입니다.
0초부터 W-1초까지 모든 순간에 대기 큐가 비어 있는 경우는 존재하지 않습니다.
고객이 일을 처리하는 데 걸리는 시간은 구간 [1, 109]에 속하는 정수입니다.
고객 id는 구간 [1, 109]에 속하는 정수이고, 중복되지 않습니다.
[N+1, N+M]에 속하는 임의의 정수 x에 대해, cx는 구간 [1, 109]에 속하는 정수이며, 중복되지 않습니다.
즉, 영업을 시작하고 난 후에는 같은 시간에 2명 이상이 동시에 들어오지 않습니다.
예제 입력 1 
1 5 7
1 6
1
3 1 5
예제 출력 1 
1
1
1
1
1
3
1

[그림 3] 예제 1번 상황의 처음 대기 큐

처음에 대기 큐에는 id가 1인 1번 손님만 있었습니다. T가 5이므로, 5초 동안 고객 1번의 업무를 처리합니다.

영업을 시작하고 나서 5초 후에, id가 3인 2번 손님이 들어오게 됩니다. 그와 동시에 1번 손님은 대기 큐의 맨 뒤로 가게 됩니다.

대기 큐의 맨 뒤로 가는 id가 1인 1번 손님은, 은행에 들어온 id가 3인 2번 손님의 뒤로 가게 됩니다.

 


[그림 4] 5초가 지난 후 예제 1의 대기 큐

id가 3인 손님이 업무를 마치는 데 필요한 시간 1초는 T값인 5초 보다 작거나 같으므로, 카운터의 직원은 1초 동안 2번 손님의 업무를 처리합니다.

 


[그림 5] 6초가 지난 후 예제 1의 대기 큐

다음에, 6초일 때, id가 1인 손님의 일을 처리하게 됩니다.

예제 입력 2 
1 3 10
1 6
2
3 4 5
2 4 2
예제 출력 2 
1
1
1
2
2
2
1
1
1
3

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 가희와은행_22234 {
	private static int N;
	private static int T;
	private static int W;
	private static int M;
	private static Queue<Task> waitPeople = new LinkedList<Task>();
	private static PriorityQueue<Task> latePeople = new PriorityQueue<Task>();
	private static int cnt = 0;
	private static StringBuilder result = new StringBuilder();
	
	private static class Task implements Comparable<Task> {
		long index;
		long time;
		long lateTime;
		
		public Task(long index, long time, long lateTime) {
			this.index = index;
			this.time = time;
			this.lateTime = lateTime;
		}
		
		@Override
		public int compareTo(Task o) {
			// TODO Auto-generated method stub
			return (int) (lateTime - o.lateTime);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int id= Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			waitPeople.add(new Task(id, time, 0));
		}
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int lateTime = Integer.parseInt(st.nextToken());
			
			latePeople.add(new Task(id, time, lateTime));
		}
		
		getId();
		
		System.out.println(result.toString().trim());
		
		br.close();
	}

	private static void getId() {
		while (!waitPeople.isEmpty() && cnt < W) {
			Task now = waitPeople.poll();
			long index = now.index;
			long time = now.time;
			
			if (T < time) {
				time = T;
			}
			setResult(index, time);
			
			while (!latePeople.isEmpty() && latePeople.peek().lateTime <= cnt) {
				waitPeople.add(latePeople.poll());
			}
			
			if (T < now.time) {
				waitPeople.add(new Task(index, now.time - T, 0));				
			}
		}
	}
	
	private static void setResult(long value, long n) {
		for (int i = 0; i < n; i++) {
			result.append(value).append("\n");
			cnt++;
			if (cnt >= W) {
				return;
			}
		}
	}
}
