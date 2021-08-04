package study_210808;

/*

과제 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	3422	1705	1395	50.912%
문제
웅찬이는 과제가 많다. 하루에 한 과제를 끝낼 수 있는데, 과제마다 마감일이 있으므로 모든 과제를 끝내지 못할 수도 있다.
과제마다 끝냈을 때 얻을 수 있는 점수가 있는데, 마감일이 지난 과제는 점수를 받을 수 없다.

웅찬이는 가장 점수를 많이 받을 수 있도록 과제를 수행하고 싶다. 웅찬이를 도와 얻을 수 있는 점수의 최댓값을 구하시오.

입력
첫 줄에 정수 N (1 ≤ N ≤ 1,000)이 주어진다.

다음 줄부터 N개의 줄에는 각각 두 정수 d (1 ≤ d ≤ 1,000)와 w (1 ≤ w ≤ 100)가 주어진다. d는 과제 마감일까지 남은 일수를 의미하며, w는 과제의 점수를 의미한다.

출력
얻을 수 있는 점수의 최댓값을 출력한다.

예제 입력 1 
7
4 60
4 40
1 20
2 50
3 30
4 10
6 5
예제 출력 1 
185
힌트
예제에서 다섯 번째, 네 번째, 두 번째, 첫 번째, 일곱 번째 과제 순으로 수행하고, 세 번째, 여섯 번째 과제를 포기하면 185점을 얻을 수 있다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 과제_13904 {
	private static int N;
	private static PriorityQueue<Report> q = new PriorityQueue<Report>();
	private static boolean[] checked;
	
	private static class Report implements Comparable<Report>{
		int deadline;
		int score;
		
		public Report(int deadline, int score) {
			this.deadline = deadline;
			this.score = score;
		}
		
		@Override
		public int compareTo(Report o) {
			// TODO Auto-generated method stub
			return o.score - score;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		checked = new boolean[N + 1];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			q.add(new Report(deadline, score));
		}
		
		int result = getMaxScore();
		
		System.out.println(result);
		
		br.close();
	}
	
	private static int getMaxScore() {
		int sum = 0;
		
		while(!q.isEmpty()) {
			Report report = q.poll();
			int deadline = report.deadline;
			int score = report.score;
			
			if (N <= deadline) {
				sum += score;
				continue;
			}
			
			for (int i = deadline; i > 0; i--) {
				if (isChecked(i)) {
					sum += score;
					break;
				}
			}
		}
		
		return sum;
	}
	
	private static boolean isChecked(int index) {
		if (!checked[index]) {
			checked[index] = true;
			return true;
		}
		
		return false;
	}
}
