package programmers_20210509_Kakao2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/72411

public class 메뉴리뉴얼 {
	private static boolean[] visited;
	private static char[] order;
	private static Map<String, Integer> map = new HashMap<String, Integer>();
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		String[][] orders = { { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" },
				{ "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" }, { "XYZ", "XWY", "WXA" } };

		int[][] course = { { 2, 3, 4 }, { 2, 3, 5 }, { 2, 3, 4 } };
		String[] result = null;
		for (int i = 0; i < 3; i++) {
			result = solution(orders[i], course[i]);
			for(String res : result) {
				System.out.println(res);
			}
			System.out.println();
		}
		
	}

	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};

		List<String> result = new ArrayList<String>();

		// map에 전부 push
		for (int i = 0; i < course.length; i++) {
			int max = -1;
			int MAX = course[i];
			map.clear();
			for (int j = 0; j < orders.length; j++) {
				order = new char[MAX];
				visited = new boolean[orders[j].length()];
				combination(0, 0, MAX, orders[j]);
			}
			
			for(Map.Entry<String, Integer> entry : map.entrySet()) {
				max = Math.max(max, entry.getValue());
			}
			
			for(Map.Entry<String, Integer> entry : map.entrySet()) {
				if(max == entry.getValue() && entry.getValue() >= 2) {
					result.add(entry.getKey());
				}
			}
		}
		
		answer = new String[result.size()];
		
		for(int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}
		
		Arrays.sort(answer);

		return answer;
	}

	private static void combination(int index, int depth, int MAX_VALUE, String orders) {
		if (depth == MAX_VALUE) {
			sb.setLength(0);
			char[] ch = new char[MAX_VALUE];
			for (int i = 0; i < MAX_VALUE; i++) {
				ch[i] = order[i];
			}
			
			Arrays.sort(ch);
			
			for(char at : ch) {
				sb.append(at);
			}
			
			if (map.containsKey(sb.toString())) {
				map.put(sb.toString(), map.get(sb.toString()) + 1);
			} else {
				map.put(sb.toString(), 1);
			}

			return;
		}

		for (int i = index; i < orders.length(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[depth] = orders.charAt(i);
				combination(i + 1, depth + 1, MAX_VALUE, orders);
				visited[i] = false;
			}
		}
	}
}
