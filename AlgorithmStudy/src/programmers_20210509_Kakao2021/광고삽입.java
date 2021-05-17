package programmers_20210509_Kakao2021;

/*

[문제]
"죠르디"의 동영상 재생시간 길이 play_time, 공익광고의 재생시간 길이 adv_time, 시청자들이 해당 동영상을 재생했던 구간 정보 logs가 매개변수로 주어질 때,
시청자들의 누적 재생시간이 가장 많이 나오는 곳에 공익광고를 삽입하려고 합니다.
이때, 공익광고가 들어갈 시작 시각을 구해서 return 하도록 solution 함수를 완성해주세요.
만약, 시청자들의 누적 재생시간이 가장 많은 곳이 여러 곳이라면, 그 중에서 가장 빠른 시작 시각을 return 하도록 합니다.

[제한사항]
play_time, adv_time은 길이 8로 고정된 문자열입니다.
play_time, adv_time은 HH:MM:SS 형식이며, 00:00:01 이상 99:59:59 이하입니다.
즉, 동영상 재생시간과 공익광고 재생시간은 00시간 00분 01초 이상 99시간 59분 59초 이하입니다.
공익광고 재생시간은 동영상 재생시간보다 짧거나 같게 주어집니다.
logs는 크기가 1 이상 300,000 이하인 문자열 배열입니다.

logs 배열의 각 원소는 시청자의 재생 구간을 나타냅니다.
logs 배열의 각 원소는 길이가 17로 고정된 문자열입니다.
logs 배열의 각 원소는 H1:M1:S1-H2:M2:S2 형식입니다.
H1:M1:S1은 동영상이 시작된 시각, H2:M2:S2는 동영상이 종료된 시각을 나타냅니다.
H1:M1:S1는 H2:M2:S2보다 1초 이상 이전 시각으로 주어집니다.
H1:M1:S1와 H2:M2:S2는 play_time 이내의 시각입니다.
시간을 나타내는 HH, H1, H2의 범위는 00~99, 분을 나타내는 MM, M1, M2의 범위는 00~59, 초를 나타내는 SS, S1, S2의 범위는 00~59까지 사용됩니다.
잘못된 시각은 입력으로 주어지지 않습니다. (예: 04:60:24, 11:12:78, 123:12:45 등)

return 값의 형식

공익광고를 삽입할 시각을 HH:MM:SS 형식의 8자리 문자열로 반환합니다.

*/

public class 광고삽입 {
    private static int play_time_sec = 0;
    private static int adv_time_sec = 0;
    private static int[] logs_start_sec;
    private static int[] logs_end_sec;
    public static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        answer = initTime(play_time, adv_time, logs);

        System.out.println(answer);

        return answer;
    }

    private static long getCount() {
        long[] total_time = new long[play_time_sec+1];
        for (int i = 0; i < logs_start_sec.length; i++) {
            total_time[logs_start_sec[i]] += 1;
            total_time[logs_end_sec[i]] -= 1;
        }

        for (int i = 1; i < play_time_sec; i++) {
            total_time[i] = total_time[i - 1] + total_time[i];
        }

        for (int i = 1; i < play_time_sec; i++) {
            total_time[i] = total_time[i-1] + total_time[i];
        }

        long maxTime = total_time[adv_time_sec - 1];
        long maxStartTime = 0;
        for (int i = 0; i < play_time_sec - adv_time_sec; i++) {
            long temp = total_time[i + adv_time_sec] - total_time[i];

            if(temp > maxTime) {
                maxTime = temp;
                maxStartTime = i + 1;
            }
        }

        return maxStartTime;
    }

    private static String initTime(String play_time, String adv_time, String[] logs) {
        play_time_sec = getSecTime(play_time);
        adv_time_sec = getSecTime(adv_time);
        logs_start_sec = new int[logs.length];
        logs_end_sec = new int[logs.length];
        String[] logsTime = new String[2];

        for (int i = 0; i < logs.length; i++) {
            logsTime = logs[i].split("-");
            logs_start_sec[i] = getSecTime(logsTime[0]);
            logs_end_sec[i] = getSecTime(logsTime[1]);
        }

        return parseDateString(getCount());
    }

    private static int getSecTime(String time) {
        String[] times = time.split(":");
        int sec = 0;
        int stdTime = 3600;
        for (int i = 0; i < times.length; i++) {
            sec += Integer.parseInt(times[i]) * stdTime;
            stdTime /= 60;
        }
        return sec;
    }

    private static String parseDateString(long time) {
        String timeStr = "";
        if(time / 3600 < 10) {
        	timeStr += "0";
        }
        timeStr += time / 3600 + ":";
        time %= 3600;
        
        if(time / 60 < 10) {
        	timeStr += "0";
        }
        timeStr += time / 60 + ":";
        time %= 60;
        
        if(time < 10) {
        	timeStr += "0";
        }
        timeStr += time;

        return timeStr;
    }

    public static void main(String[] args) {
        String[] play_time = {"02:03:55", "99:59:59", "50:00:00"};
        String[] adv_time = {"00:14:15", "25:00:00", "50:00:00"};
        String[][] logs = {
                {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"},
                {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"},
                {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}

        };

        for (int i = 0; i < 3; i++) {
            solution(play_time[i], adv_time[i], logs[i]);
        }
    }
}