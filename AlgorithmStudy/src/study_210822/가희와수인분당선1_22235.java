package study_210822;
/*

가희와 수인 분당선 1 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	54	23	17	35.417%
문제
하행은 서울을 시점으로 종점 방향으로 운행하는 방향을 의미합니다.

수인 분당선은 K209(청량리)역이 서울에 있고, K272(인천)역이 인천에 있으므로, K209역에서 K272역으로 가는 방향이 하행이 됩니다. 

 



[그림 1] 수인 분당선의 하행

수인 분당선의 역 번호는 Kddd로 정의됩니다. 이 때 ddd는 209 이상 272 이하의 정수입니다. 이는 K209가 청량리역이고 K272가 인천역이기 때문입니다.

하행에서의 역 번호가 Kddd인 역의 다음 역은, 역 번호가 K272인 인천역을 제외하고, 정수 ddd + 1의 값이 DDD일 때 KDDD가 됩니다. 

하행 열차는 아래와 같은 알고리즘으로 운행하게 됩니다.

시발역에서 HH시 mm분에 출발해서, 다음역으로 이동하게 됩니다.
열차가 역에 도착했다면 20초 동안 역에 머무르게 됩니다. 20초가 지난 후에는 문을 닫고 출발하게 됩니다.
만약에 종착역이라면 열차는 더 이상 운행하지 않습니다. 그렇지 않으면 다음역으로 이동한 후에 2번을 수행합니다.
예를 들어 하행 열차가 역 번호가 K210인 역에서 운행을 시작해서, K233 역에서 운행을 종료한다고 해 보겠습니다.

 



[그림 2] K233이 종착역인 하행 열차가 K232 역에 도착한 경우

그림 2는 K210 역이 시발역이고, K233역이 종착역인 하행 열차가 K232 역에 도착한 상황과, K232 역에 도착하고 20초 후 상황을 도식화 한 그림입니다. 

K232은 종착역이 아니므로, K232역에 도착하고 20초 후에는 K232 역을 출발해서 K233역 방향으로 가게 됩니다.

 



[그림 3] K233이 종착역인 하행 열차가 K233 역에 도착한 경우

그림 3은 K210 역이 시발역이고, K233역이 종착역인 하행 열차가 K233에 도착한 상황과, K233에 도착하고 나서 20초 후의 상황을 나타냅니다. 

K233은 종착역이므로 K233역에 도착한 열차는 K233에 도착하고 20초 후에 운행을 종료하게 됩니다. 운행이 종료된 열차는 그림에 표시되지 않습니다.

한 역에서 다음 역을 이동하는 시간은 표 1에 있는 경우를 제외하고 2분입니다.

구간	소요 시간	구간	소요시간
현 역	다음 역	현 역	다음 역
K210	K211	3	K220	K221	4
K221	K222	4	K222	K223	3
K225	K226	3	K238	K239	3
K245	K246	4	K247	K248	5
K249	K250	4	K250	K251	3
K256	K257	3	K266	K267	3
[표 1] 인접 역간 소요 시간이 3분 이상인 경우

가희는 역 번호가 K225인 모란역에서 수인 분당선 열차만 타고 역 번호가 K272인 인천역에 가려고 합니다.

인천역에 가장 빠르게 도착했을 때 시각을 구해주세요.

입력
첫 번째 줄에는 가희가 모란역에 도착한 시각이 HH:mm:ss 형식으로 주어집니다. 항상 올바른 형식의 시각으로 주어집니다.

두 번째 줄에 분당선 하행 열차의 운행 정보 갯수 N이 주어집니다.

세 번째 줄부터 N+2번째 줄까지, 열차가 운행을 시작하는 시발역의 역 번호, 운행을 마치는 종착역의 역 번호, 시발역 에서의 출발 시각이 주어집니다.

시발역에서의 출발 시각은 HH:mm 형식으로 주어집니다. 항상 올바른 형식의 시각으로 주어집니다.

예를 들어, 운행 정보가 K210 K272 11:20으로 주어지면, 이는 K210(왕십리)역을 11시 20분에 출발하여, K272(인천)역에서 운행을 종료하는 열차임을 의미합니다. 

출력
인천역에 도착하는 가장 빠른 시각을 HH:mm:ss 형식으로 출력합니다.

만약, HH, mm, ss가 10보다 작다면 앞에 0을 붙여서 출력하세요. 예를 들어, 답이 9시 1분 0초라면 9:1:1이 아닌 09:01:00으로 출력해 주세요.

금일 23:59:59 까지 수인 분당선만 타고 역 번호가 K272인 인천역에 도착할 수 없다면 -1을 출력합니다.

제한
1 ≤ N ≤ 400
열차의 시발역과 종착역은 K210, K233, K246, K258, K272 중 하나이며, 시발역은 종착역보다 사전순으로 앞에 있습니다.
K268 (학익)역에도 열차가 멈춥니다.
시발 역에서 첫차는 04:45 이후에 출발하고, 막차는 23:45 이전에 출발합니다.
즉, 하행 열차의 운행 정보에 있는 시각은 04:45 이후이고 23:45 이전입니다.
열차의 조착이나 지연은 없습니다.
하행 열차 둘 이상이 같은 역에 있는 경우는 존재하지 않습니다.
HH:mm:ss 형식과 HH:mm 형식에서 HH는 구간 [0, 23]에 속하는 정수이고, mm과 ss는 구간 [0, 59]에 속하는 정수입니다.
HH, mm, ss는 10보다 작은 경우, 앞에 0이 붙습니다.
예제 입력 1 
13:13:00
5
K210 K272 11:00
K210 K258 12:00
K210 K272 14:00
K210 K246 14:52
K210 K233 22:00
예제 출력 1 
16:42:20
예제 입력 2 
05:06:00
6
K210 K233 05:00
K233 K246 06:30
K246 K258 08:00
K258 K272 10:00
K210 K272 09:40
K210 K272 11:15
예제 출력 2 
10:33:20
힌트
시발역은 열차가 운행을 시작하는 역이고, 종착역은 열차가 운행을 종료하는 역입니다.
예를 들어 시발역이 K258이고, 종착역이 K272라면, 열차는 K258 (오이도)역을 출발하여 아래와 같은 순서대로 역에 멈추게 됩니다.
K259(달월), K260(월곶), K261(소래포구), K262(인천논현), K263(호구포), K264(남동인더스파크), K265(원인재), K266(연수), K267(송도),
K268(학익), K269(인하대), K270(숭의), K271(신포), K272(인천)

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 처음에는
// 출발역 -> 도착역 에 대한 정보만 초기화 시켜주었다.
// 즉, 210에서 258로 갈때 258에 도착 정보만 넣어주었는데
// 그렇게 된다면 233에서는 도착 정보를 가지고 있지 않다.
// 210 -> 258로 한번에 가는게 아니라
// 210 -> 233 -> 246 -> 258 -> 272을 거쳐서 가는 것이기 때문에
// 해당 역에 대해서도 처리를 해주면 된다.

public class 가희와수인분당선1_22235 {
	private static final int[] train = new int[273];
	private static final int MORAN = 225;
	
	private static int N;
	private static int[] arrivalTime = new int[273];
	private static PriorityQueue<Train> trainTime = new PriorityQueue<Train>();
	private static List<Integer> station = Arrays.asList(210, 233, 246, 258, 272);
	
	private static class Train implements Comparable<Train>{
		int start;
		int end;
		int time;
		
		public Train(int start, int end, int time) {
			this.start = start;
			this.end = end;
			this.time = time;
		}
		
		@Override
		public int compareTo(Train o) {
			// TODO Auto-generated method stub
			if (start == o.start) {
				return time - o.time;
			}
			
			return start - o.start;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(":");
		
		int gahee = timeToSeconds(input);
		init();
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken().substring(1));
			int end = Integer.parseInt(st.nextToken().substring(1));
			String[] startTime = st.nextToken().split(":");
			int startSecondsTime = timeToSeconds(startTime);
			trainTime.add(new Train(start, end, startSecondsTime));
		}
		
		Arrays.fill(arrivalTime, Integer.MAX_VALUE);
		
		arrive(gahee);
		
		int result = arrivalTime[272] - 20;
		
		// 24:00:00 -> 
		String[] limitTime = {"24", "00", "00"};
		if (result >= timeToSeconds(limitTime)) {
			System.out.println(-1);
		}else {
			System.out.println(secondsToTime(result));			
		}
		
		br.close();
	}
	
	private static void arrive(int gahee) {
		while (!trainTime.isEmpty()) {
			Train train = trainTime.poll();
			int start = train.start;
			int end = train.end;
			int time = train.time;
			int startIndex = station.indexOf(start);
			int endIndex = station.indexOf(end);
			
			if (start == 210) {
				int moranTrainArrivalSeconds = arriveTime(start, MORAN, time) - 20;
				if (moranTrainArrivalSeconds < gahee) {
					continue;
				}
				
				for (int i = startIndex + 1; i <= endIndex; i++) {
					int arrival = arriveTime(start, station.get(i), time);
					arrivalTime[station.get(i)] = Math.min(arrivalTime[station.get(i)], arrival);					
				}
				continue;
			}
			
			if (time < arrivalTime[start] - 20) {
				continue;
			}
			
			for (int i = startIndex + 1; i <= endIndex; i++) {
				int arrival = arriveTime(start, station.get(i), time);
				arrivalTime[station.get(i)] = Math.min(arrivalTime[station.get(i)], arrival);				
			}
		}
	}
	
	private static int arriveTime(int start, int end, int startSeconds) {
		int time = startSeconds;
		for (int i = start; i < end; i++) {
			time += train[i];
		}
		
		return time;
	}
	
	private static int timeToSeconds(String[] input) {
		int hours = Integer.parseInt(input[0]);
		int minutes = Integer.parseInt(input[1]);
		int seconds = 0;
		if (input.length == 3) {
			seconds = Integer.parseInt(input[2]);			
		}
		
		int time = hours * 60 * 60 + minutes * 60 + seconds;
		
		return time;
	}
	
	private static String secondsToTime(int seconds) {
		int hour = seconds/60/60;
		int minute = seconds/60%60;
		int second = seconds%60;
		
		return setTime(hour) + ":" + setTime(minute) + ":" + setTime(second);
	}
	
	private static String setTime(int time) {
		if (time < 10) {
			return "0" + time;
		}
		
		return Integer.toString(time);
	}
	
	// 각 역당 소요 시간 (입력으로 주어진게 아니라 표로 주어짐)
	private static void init() {
		for (int i = 210; i < 272; i++) {
			train[i] = 2 * 60 + 20;
		}
		
		train[210] = 3 * 60 + 20;
		train[220] = 4 * 60 + 20;
		train[221] = 4 * 60 + 20;
		train[222] = 3 * 60 + 20;
		train[225] = 3 * 60 + 20;
		train[238] = 3 * 60 + 20;
		train[245] = 4 * 60 + 20;
		train[247] = 5 * 60 + 20;
		train[249] = 4 * 60 + 20;
		train[250] = 3 * 60 + 20;
		train[256] = 3 * 60 + 20;
		train[266] = 3 * 60 + 20;
	}
}
