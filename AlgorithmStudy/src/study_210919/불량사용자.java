package study_210919;

import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/64064
public class 불량사용자 {
    private static Set<Set<String>> results = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        int bannedLen = banned_id.length;
        List<String>[] bannedIdList = new List[bannedLen + 1];
        for (int i = 0; i <= bannedLen; i++) {
            bannedIdList[i] = new ArrayList<>();
        }

        for (int i = 0; i < bannedLen; i++) {
            String banned = banned_id[i];

            for (int j = 0; j < user_id.length; j++) {
                String user = user_id[j];

                if (!includeBannedId(banned, user)) {
                    continue;
                }
                bannedIdList[i].add(user);
            }
        }

        for (List<String> strings : bannedIdList) {
            System.out.println(strings);
        }

        findBannedList(bannedIdList, new HashSet<>(), 0, bannedLen);

        answer = results.size();

        return answer;
    }

    private static void findBannedList(List<String>[] bannedIdList, Set<String> resultSet, int depth, int bannedLen) {
        if (depth == bannedLen) {
            if (resultSet.size() == bannedLen) {
                results.add(new HashSet<>(resultSet));
            }
            return;
        }

        for (int i = 0; i < bannedIdList[depth].size(); i++) {
            if (resultSet.contains(bannedIdList[depth].get(i))) {
                continue;
            }

            resultSet.add(bannedIdList[depth].get(i));
            findBannedList(bannedIdList, resultSet, depth + 1, bannedLen);
            resultSet.remove(bannedIdList[depth].get(i));
        }

    }

    private static boolean includeBannedId(String banned, String user) {
        if (banned.length() != user.length()) {
            return false;
        }

        for (int i = 0; i < banned.length(); i++) {
            char bannedChar = banned.charAt(i);
            char userChar = user.charAt(i);

            if (bannedChar == '*' || bannedChar == userChar) {
                continue;
            }

            return false;
        }

        return true;
    }

    public static void main(String[] args) {
//        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banner_id = {"fr*d*", "abc1**"};
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banner_id = {"fr*d*", "*rodo", "******", "******"};

        System.out.println(new 불량사용자().solution(user_id, banner_id));
    }
}
