import java.util.*;

class Solution {
    static HashSet<String> jew_set = new HashSet<>();
    static HashMap<String, Integer> jewCntMap = new HashMap<>();
    static int jew_len;
    public int[] solution(String[] gems) {
        int[] answer = {};

			jew_set.addAll(Arrays.asList(gems));
			jew_len = jew_set.size();

			int LEN = 100001;
			int LEFT = 1;
			Queue<String> queue = new LinkedList<>();

			for (String now_jew : gems) {
				if (!jewCntMap.containsKey(now_jew)) jewCntMap.put(now_jew, 1);
				else jewCntMap.put(now_jew, jewCntMap.get(now_jew) + 1);
				queue.add(now_jew);

				while (!queue.isEmpty()) {
					String now = queue.peek();
					if (jewCntMap.get(now) > 1) {
						jewCntMap.put(now, jewCntMap.get(now) - 1);
						queue.poll();
						LEFT++;
					} else break;
				}
				// 처음 발견
				if (jewCntMap.size() == jew_len && LEN > queue.size()) {
                    System.out.println(LEFT);
					LEN = queue.size();
					answer = new int[]{LEFT, LEFT + LEN - 1};
				}
			}
			return answer;
    }
}