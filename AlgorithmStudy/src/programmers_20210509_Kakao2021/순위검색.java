package programmers_20210509_Kakao2021;

// https://programmers.co.kr/learn/courses/30/lessons/72412

public class 순위검색 {
	public static void main(String[] args) {
		String[] info = {
				"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"
		};
		
		String[] query = {
				"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"
		};
		
		int[] result = {1,1,1,1,2,4};
	}
	
	private static int[] solution(String[] info, String[] query) {
		int[] answer = {};
        String[][] people = new String[info.length][5];
        String[] queryStr = {};
        boolean flag = false;
        answer = new int[query.length];
        
        for(int k = 0; k < info.length; k++) {
        	people[k] = info[k].split(" ");
        }
        	
    	for(int i = 0; i < query.length; i++) {
    		query[i] = query[i].replaceAll(" and ", " ");
    		queryStr = query[i].split(" ");
    		int count = 0;
    		for(int k = 0; k < info.length; k++) {
        		flag = true;
        		for(int j = 0; j < queryStr.length; j++) {
        			if(j == queryStr.length-1 && Integer.parseInt(people[k][4]) >= Integer.parseInt(queryStr[4])) ]{
        				flag = true;
        				break;
        			}
        			if(queryStr[j].equals("-")) {
        				flag = true;
        				continue;
        			}else if(!queryStr[j].equals(people[k][j])) {
        				flag = false;
        				break;
        			}
        		}
        		
        		if(flag) {
        			count++;
        		}
        		
        	}
    		answer[i] = count;
        }
		
		
		return answer;
	}
}
