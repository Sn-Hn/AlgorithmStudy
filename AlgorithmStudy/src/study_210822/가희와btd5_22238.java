package study_210822;

/*

가희와 btd5 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2.5 초	512 MB	211	34	24	20.339%
문제
btd5에는 Darting Gun Tower가 있습니다. Darting Gun Tower는 아래의 알고리즘으로 풍선을 공격합니다.

공격하고자 하는 목표물의 방향으로 공격 방향을 바꿉니다.
공격 방향에 있는 풍선들의 체력을 d씩 낮춥니다.
Darting Gun Tower는 좌표 (0, 0)에 하나 있습니다.
Darting Gun Tower가 공격을 하게 되면, 공격하는 방향에 놓인 모든 풍선들은 동일한 수치의 피해를 입히게 됩니다.
초기에 풍선은 N개 있고, Darting Gun Tower는 공격을 M번 하였습니다. 공격을 끝낼 때 마다, 남은 풍선의 수를 세어 주세요.
초기 상태에 Darting Gun Tower가 특정 방향으로 데미지가 109 이상의 공격을 했을 때, 모든 풍선을 제거할 수 있는 방법이 존재합니다.

입력
첫 번째 줄에 N, M이 주어집니다.

2번째 줄부터 N+1번째 줄까지 풍선이 있는 x좌표, y좌표, 체력이 주어집니다.

N+2번째 줄부터 N+M+1번째 줄까지 Darting Gun Tower의 공격 방향 (x, y)와, Darting Gun Tower가 준 데미지 d가 주어집니다.

출력
x번째 줄에 x번째 공격이 끝났을 때 남은 풍선의 개수를 출력해 주세요.

제한
N과 M은 구간 [1, 2×105]에 속하는 정수입니다.
풍선들의 x 좌표와 y 좌표는 [-109, 109]에 속하는 정수입니다.
풍선들의 위치는 고정되어 있으며, 풍선이 있는 위치에 Darting Gun Tower가 있지 않습니다.
두 개 이상의 풍선이 같은 위치에 있지 않습니다.
풍선들의 hp와 Darting Gun Tower가 주는 데미지는 구간 [1, 109]에 속하는 정수입니다.
예제 입력 1 
3 1
1 1 3
3 3 4
2 2 2
1 1 3
예제 출력 1 
1
 첫 번째 공격은 개틀링 거너가 좌표 (0,0)에서 (1,1)방향에 있는 풍선들의 체력을 3 감소 시키는 공격을 하는 것입니다.

 



[그림1] 개틀링 거너의 첫 공격

첫 공격 후, (1, 1)에 있었던 체력이 3이였던 풍선과 (2, 2)에 있었던 체력이 2였던 풍선은 데미지가 3인 공격을 받아서 사라집니다.

(3, 3)에 있는 풍선은 체력이 1이 됩니다. 



[그림2] 첫 공격 후 상황

5 3
1 1 3
3 3 4
2 2 2
1 2 5
1 3 4
1 1 3
2 6 2
1 2 5


2 4
999999999 1000000000 10
999999998 999999999 21
999999998 999999999 20
999999997 999999998 30
-999999998 -999999999 1
-999999995 999999996 50


*/

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class 가희와btd5_22238 {
	private static final int MAX_DAMAGE = 1000000000;
	private static final int INF = 1000000001;
	
	private static int N;
	private static int M;
	private static Map<BigDecimal, Queue<Integer>> balloon = new HashMap<>();
	private static Queue<Integer> balloons = new LinkedList<>();
	private static Map<BigDecimal, Long> damages = new HashMap<>();
	private static BigDecimal slope;
	private static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		BigDecimal balloonX = null;
		BigDecimal balloonY = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Integer.parseInt(st.nextToken());
			double y = Integer.parseInt(st.nextToken());
			int blood = Integer.parseInt(st.nextToken());
			
			BigDecimal divide = null;
			if (x == 0) {
				divide = new BigDecimal(INF);
				slope = new BigDecimal(INF);
			}else if (y == 0) {
				divide = new BigDecimal(0L);
				slope = new BigDecimal(0L);
			}else {
				balloonX = new BigDecimal(x);
				balloonY = new BigDecimal(y);
				
				divide = balloonX.divide(balloonY, 20, BigDecimal.ROUND_HALF_UP);				
				slope = balloonX.divide(balloonY, 20, BigDecimal.ROUND_HALF_UP);				
			}
			
//			if (balloon.containsKey(divide)) {
//				balloon.get(divide).add(blood);
//				continue;
//			}
			
//			Queue<Integer> bloodList = new LinkedList<Integer>();
//			bloodList.add(blood);
			balloons.add(blood);
			
//			balloon.put(divide, bloodList);
		}
		BigDecimal damageX = null;
		BigDecimal damageY = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Integer.parseInt(st.nextToken());
			double y = Integer.parseInt(st.nextToken());
			long damage = Long.parseLong(st.nextToken());
			
			if (damage >= MAX_DAMAGE) {
				flag = true;
				break;
			}
			
			BigDecimal divide = null;
			if (x == 0 && y == 0) {
				continue;
			}else if (x == 0) {
				divide = new BigDecimal(INF);
			}else if (y == 0) {
				divide = new BigDecimal(0L);
			}else {
				damageX = new BigDecimal(x);
				damageY = new BigDecimal(y);
				
				divide = damageX.divide(damageY, 20, BigDecimal.ROUND_HALF_UP);				
			}
			
			
			if (damages.containsKey(divide)) {
				damage += damages.get(divide);
			}
			damages.put(divide, damage);				
		}
		
		if (!flag) {
			attack2();
		System.out.println(balloons.size());
//			System.out.println(getCount());			
		}else {
			System.out.println(0);
		}

//		double q = -11111;
//		double w = -2;
//		
//		BigDecimal a = new BigDecimal(q);
//		BigDecimal b = new BigDecimal(w);
//		
//		BigDecimal c = a.divide(b, 20, BigDecimal.ROUND_HALF_UP);
//		
//		System.out.println(c);
	
		br.close();
	}
	
	private static void attack2() {
		for (BigDecimal s : damages.keySet()) {
			if (slope.equals(s)) {
				long damage = damages.get(s); 
				int size = balloons.size();
				for (int i = 0; i < size; i++) {
					int blood = balloons.poll();
					if (blood > damage) {
						balloons.add((int) (blood - damage));
					}
				}
			}
		}
	}
	 
	private static void attack() {
		for (BigDecimal s : damages.keySet()) {
			if (balloon.containsKey(s)) {
				Queue<Integer> q = balloon.get(s);
				long damage = damages.get(s); 
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int blood = q.poll();
					if (blood > damage) {
						q.add((int) (blood - damage));
					}
				}
			}
		}
	}
	
	private static int getCount() {
		int count = 0;
		for (BigDecimal s : balloon.keySet()) {
			count += balloon.get(s).size();
		}
		
		return count;
	}
}
