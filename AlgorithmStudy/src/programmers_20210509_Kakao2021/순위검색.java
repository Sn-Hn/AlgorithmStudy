package programmers_20210509_Kakao2021;

import java.util.*;

/*

[문제]
지원자가 지원서에 입력한 4가지의 정보와 획득한 코딩테스트 점수를 하나의 문자열로 구성한 값의 배열 info, 개발팀이 궁금해하는 문의조건이 문자열 형태로 담긴 배열 query가 매개변수로 주어질 때,
각 문의조건에 해당하는 사람들의 숫자를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.

[제한사항]
info 배열의 크기는 1 이상 50,000 이하입니다.
info 배열 각 원소의 값은 지원자가 지원서에 입력한 4가지 값과 코딩테스트 점수를 합친 "개발언어 직군 경력 소울푸드 점수" 형식입니다.
개발언어는 cpp, java, python 중 하나입니다.
직군은 backend, frontend 중 하나입니다.
경력은 junior, senior 중 하나입니다.
소울푸드는 chicken, pizza 중 하나입니다.
점수는 코딩테스트 점수를 의미하며, 1 이상 100,000 이하인 자연수입니다.
각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
query 배열의 크기는 1 이상 100,000 이하입니다.
query의 각 문자열은 "[조건] X" 형식입니다.
[조건]은 "개발언어 and 직군 and 경력 and 소울푸드" 형식의 문자열입니다.
언어는 cpp, java, python, - 중 하나입니다.
직군은 backend, frontend, - 중 하나입니다.
경력은 junior, senior, - 중 하나입니다.
소울푸드는 chicken, pizza, - 중 하나입니다.
'-' 표시는 해당 조건을 고려하지 않겠다는 의미입니다.
X는 코딩테스트 점수를 의미하며 조건을 만족하는 사람 중 X점 이상 받은 사람은 모두 몇 명인 지를 의미합니다.
각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
예를 들면, "cpp and - and senior and pizza 500"은 "cpp로 코딩테스트를 봤으며, 경력은 senior 이면서 소울푸드로 pizza를 선택한 지원자 중 코딩테스트 점수를 500점 이상 받은 사람은 모두 몇 명인가?"를 의미합니다.

50000 * 100000 = 5,000,000,000 (50억)

어차피 info에 조건은 5가지
java python -
frontend backend -
senior junior -
pizza chicken -
점수

*/

// https://programmers.co.kr/learn/courses/30/lessons/72412
public class 순위검색 {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        solution(info, query);
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = {};
        Map<String, List<Integer>> infoMap = initMap(info);
        String[][] splitQuery = initQuery(query);
        mapPrint(infoMap);
        answer = new int[info.length];
        for(int i = 0; i < splitQuery.length; i++) {
            if(infoMap.containsKey(splitQuery[i][0])) {
                answer[i] = findScore(infoMap.get(splitQuery[i][0]), Integer.parseInt(splitQuery[i][1]));
            }
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }


    private static int findScore(List<Integer> score, int minScore) {
    	int start = 0;
    	int end = score.size() - 1;
    	int mid = 0;
    	
    	while(start <= end) {
    		mid = (start + end) / 2;
    		
    		if(minScore > score.get(mid)) {
    			start = mid + 1;
    		}else {
    			end = mid - 1;
    		}
    	}
    	
    	return score.size() - start;
    }

    private static String[][] initQuery(String[] query) {
        String[][] querySplit = new String[query.length][2];
        for(int i = 0; i < query.length; i++) {
            querySplit[i] = query[i].replaceAll(" and ", "").split(" ");
        }

        return querySplit;
    }

    private static Map<String, List<Integer>> initMap(String[] info) {
        Map<String, List<Integer>> infoMap = new HashMap<>();
        String[] infoSplit;
        String strInfo = "";
        for(int i = 0; i < info.length; i++) {
            String infoStr = "";
            infoSplit = info[i].split(" ");
            setInfo(infoMap, infoSplit, 0, "");
        }

        return infoMap;
    }


    private static void setInfo(Map<String, List<Integer>> infoMap, String[] infoSplit, int depth, String resultInfo) {
        if(depth == 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("resultInfo : ").append(resultInfo);

//            System.out.println(sb.toString());

            if(infoMap.containsKey(resultInfo)) {
                infoMap.get(resultInfo).add(Integer.parseInt(infoSplit[4]));
                Collections.sort(infoMap.get(resultInfo));
                return;
            }

            List<Integer> scoreList = new ArrayList<>();
            scoreList.add(Integer.parseInt(infoSplit[4]));

            infoMap.put(resultInfo, scoreList);
            return;
        }

        setInfo(infoMap, infoSplit, depth + 1, resultInfo+infoSplit[depth]);
        setInfo(infoMap, infoSplit, depth + 1, resultInfo+"-");

    }

    private static void binarySearch() {
    }

    private static void mapPrint(Map<String, List<Integer>> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("key : ").append(map.keySet()).append("\n");
        sb.append("value : ").append(map.values()).append("\n");
        System.out.println(sb.toString());
    }
}