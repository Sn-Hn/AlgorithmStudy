package programmers_20210509_Kakao2021;

import java.util.regex.Pattern;

// https://programmers.co.kr/learn/courses/30/lessons/72410

public class 신규아이디추천 {
	public static void main(String[] args) {

		String[] new_id = { "...!@BaT#*..y.abcdefghijklm", "z-+.^.", "=.=", "123_.def", "abcdefghijklmn.p" };
		String[] answer = { "bat.y.abcdefghi", "z--", "aaa", "123_.def", "abcdefghijklmn" };

		for (int i = 0; i < 5; i++) {
			System.out.println(solution(new_id[i]));
		}
	}

	public static String solution(String new_id) {
		String answer = "";
		// 아이디는 3자 이상 15자 이하
		// 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)
		// 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용 금지

		answer = level1(new_id);
		answer = level2(answer);
		answer = level3(answer);
		answer = level4(answer);
		answer = level5(answer);
		answer = level6(answer);
		answer = level7(answer);

		return answer;
	}

	private static String level1(String new_id) {
		return new_id.toLowerCase();
	}

	private static String level2(String new_id) {
		String pattern = "[^a-z0-9-_.]";
		return new_id.replaceAll(pattern, "");
	}

	private static String level3(String new_id) {
		String pattern = "\\.+";
		return new_id.replaceAll(pattern, ".");
	}

	private static String level4(String new_id) {
		String pattern = "^[.]|[.]$";
		return new_id.replaceAll(pattern, "");
	}

	private static String level5(String new_id) {
		if ("".equals(new_id)) {
			new_id += "a";
		}
		
		return new_id;
	}
	
	private static String level6(String new_id) {
		String pattern = "[.]$";
		if(new_id.length() >= 16) {
			return new_id.substring(0, 15).replaceAll(pattern, "");
		}
		
		return new_id;
	}
	
	private static String level7(String new_id) {
		while(new_id.length() <= 2) {
			new_id += new_id.charAt(new_id.length()-1);
		}
		
		return new_id;
	}
}
