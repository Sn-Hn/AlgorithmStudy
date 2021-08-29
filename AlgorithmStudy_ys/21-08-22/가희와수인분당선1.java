import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가희와수인분당선1 {


    static class Section{
        int arr[] = new int [300];
        int totalTime[] = new int[300];

        Section(String data[],int stopTime,int defaultTime){
            for(int i = 210 ; i < 273;i++){
                arr[i] = stopTime + defaultTime;
            }
            for(String input : data){
                String []tokens = input.split("\\s+");
                fill(tokens[0], tokens[2], 20);
                fill(tokens[3], tokens[5], 20);

            }

            for(int i = 210 ; i < 273 ; i++){
                totalTime[i] = totalTime[i-1] + arr[i];
            }
        }

        public int getDuration(int s, int e){
            return totalTime[e-1] - totalTime[s-1];
        }

        private void fill(String stationNum, String time, int stopTime){
            int loc = Integer.parseInt(stationNum.substring(1));
            int minute = Integer.parseInt(time);
            this.arr[loc] = 60*minute + stopTime;
        }



    }


    static class Train{
        int start;
        int end;
        int startSecond;


        public Train(int start, int end, int startSecond) {
            this.start = start;
            this.end = end;
            this.startSecond = startSecond;
        }


        @Override
        public String toString() {
            return "Train{" +
                    "start=" + start +
                    ", end=" + end +
                    ", startSecond=" + startSecond +
                    '}';
        }
    }

    static Train info[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //주어진 역마다 이동시간 input
        String []data = {
                "K210	K211	3	K220	K221	4",
                "K221	K222	4	K222	K223	3",
                "K225	K226	3	K238	K239	3",
                "K245	K246	4	K247	K248	5",
                "K249	K250	4	K250	K251	3",
                "K256	K257	3	K266	K267	3"
        };
        Section section = new Section(data, 120, 20);


        String inputTime[] = br.readLine().split(":");

        //처음 주어진 시간.
        int curSec = (Integer.parseInt(inputTime[0])*3600)
                +(Integer.parseInt(inputTime[1])*60)
                +(Integer.parseInt(inputTime[2]));

        int n = Integer.parseInt(br.readLine());


        //열차 운행 정보
        info = new Train[n];
        for(int i = 0 ; i < n;i++){
            String input[] = br.readLine().split(" ");
            String startTime[] = input[2].split(":");
            int startSec = (Integer.parseInt(startTime[0])*3600) + (Integer.parseInt(startTime[1])*60);
            info[i] = new Train(Integer.parseInt(input[0].substring(1)),Integer.parseInt(input[1].substring(1)),startSec);
        }

        /**
         * 시발역에서 HH시 mm분에 출발해서, 다음역으로 이동하게 됩니다.
         * 열차가 역에 도착했다면 20초 동안 역에 머무르게 됩니다. 20초가 지난 후에는 문을 닫고 출발하게 됩니다.
         * 만약에 종착역이라면 열차는 더 이상 운행하지 않습니다. 그렇지 않으면 다음역으로 이동한 후에 2번을 수행합니다.
         */

        //열차의 시발역 210은 가희가 225에서 출발한다고 했으니 제외
        int station[] = {225,233,246,258,272};


        //열차의 출발점마다 비교
        for(int i = 0 ; i < 4 ;i++){
            int cur = station[i];
            int next = station[i+1];
            int min = Integer.MAX_VALUE;

            for(Train t : info){
                if(t.end <= cur || t.start >cur ) continue;

                int timeStart = t.startSecond;
                int leaveTime = timeStart + section.getDuration(t.start,cur);

                if(curSec < leaveTime && leaveTime < min){
                    min = leaveTime;
                }
            }


            //인천역에 도착할 수 없다.
            if(min == Integer.MAX_VALUE){

                System.out.println("-1");
                return;
            }
            curSec = min + section.getDuration(cur,next) - 20;
        }

        if(curSec >= 86400){
            System.out.println(-1);
        }else{
            printTime(curSec);
        }




    }
    public static void printTime(int sec){
        int h = sec/3600;
        int m = (sec%3600)/60;
        int s = (sec%3600)%60;
        System.out.println(String.format("%02d",h)+":"
                +String.format("%02d",m)+":"+String.format("%02d",s));
    }


    public static int timeToSecond(String input){
        int sec = 0;
        String time[] = input.split(":");
            sec += Integer.parseInt(time[0])*3600;
            sec += Integer.parseInt(time[1])*60;
            sec += Integer.parseInt(time[2]);

        return sec;

    }

}
