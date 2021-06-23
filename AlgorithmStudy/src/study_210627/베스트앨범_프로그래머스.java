package study_210627;

/*

베스트앨범
문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
입출력 예
genres   plays   return
["classic", "pop", "classic", "classic", "pop"]   [500, 600, 150, 800, 2500]   [4, 1, 3, 0]
입출력 예 설명
classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

고유 번호 3: 800회 재생
고유 번호 0: 500회 재생
고유 번호 2: 150회 재생
pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

고유 번호 4: 2,500회 재생
고유 번호 1: 600회 재생
따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.

*/

import java.util.*;

public class 베스트앨범_프로그래머스 {

    private static class Music implements Comparable<Music> {
        String genre;
        int plays;
        int totalPlays;
        int index;

        public Music(String genre, int plays, int totalPlays, int index) {
            this.genre = genre;
            this.plays = plays;
            this.totalPlays = totalPlays;
            this.index = index;
        }

        @Override
        public int compareTo(Music o) {
            if (totalPlays == o.totalPlays) {
                if (plays == o.plays) {
                    return index - o.index;
                }
                return o.plays - plays;
            }

            return o.totalPlays - totalPlays;
        }
    }
    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = getGenreAndPlayMap(genres, plays);
        List<Music> musicList = getMusicList(genres, plays, map);

        List<Integer> result = getBestMusic(musicList);

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private static List<Integer> getBestMusic(List<Music> musicList) {
        List<Integer> resultList = new ArrayList<>();
        String genre = "";
        int count = 0;
        for (Music music : musicList) {
            System.out.println(music.genre + ", " + music.plays + ", " + count + ", " + music.index);
            if (genre.equals(music.genre)) {
                count ++;
                if (count >= 2) {
                    continue;
                }
            } else {
                genre = music.genre;
                count = 0;
            }

            resultList.add(music.index);
        }

        return resultList;
    }

    private static List<Music> getMusicList(String[] genres, int[] plays, Map<String, Integer> map) {
        List<Music> musicList = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            musicList.add(new Music(genres[i], plays[i], map.get(genres[i]), i));
        }

        Collections.sort(musicList);

//        for (Music music : musicList) {
//            System.out.println(music.genre + ", " + music.plays);
//        }

        return musicList;
    }

    private static Map<String, Integer> getGenreAndPlayMap(String[] genres, int[] plays) {
        Map<String, Integer> genreAndPlay = new HashMap<String, Integer>();
        for (int i = 0; i < genres.length; i++) {
            if (genreAndPlay.containsKey(genres[i])) {
                genreAndPlay.put(genres[i], genreAndPlay.get(genres[i]) + plays[i]);
                continue;
            }
            genreAndPlay.put(genres[i], plays[i]);
        }

        return genreAndPlay;
    }

    public static void main(String[] args) {
        String[] genres = {"pop", "classic", "pop", "classic", "classic", "pop", "pop"};
        int[] plays = {600, 500, 600, 150, 800, 2500, 600};
        // return {4, 1, 3, 0}

        System.out.println(Arrays.toString(solution(genres, plays)));
    }
}