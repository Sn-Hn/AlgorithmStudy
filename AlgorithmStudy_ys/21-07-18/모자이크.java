package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 모자이크 {

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    static Point space[];
    static int target ;
    static int cols;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 접근
         * 색종이는 모두 크기가 같고 정사각형 모양이다
         * 모든 색종이는 반드시 도화지의 밑변에 맞추어 붙인다. 겹처서 붙일 수 있다.
         * 유추 -> 색종이를 가장많이 쓰려면 크기는 최대 높이의 값 가장 적게 쓰려면 주어진 col의 크기로 지정
         * 잘못된 공간을 저장한 배열을 만들고 저장한뒤 col기준으로 덮는것을 계산할수 있음으로 col기준으로 정렬
         * mid를 덮는 색종이의 크기를 기준으로 하여 이분탐색 주어진 색종이 개수보다 적게 사용하거나 같으면 ans갱신한뒤 색종이 크기가 가장 작아야하니까 rigth = mid-1;
         *
         */

        int rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(br.readLine().trim());

        int wrongAmt = Integer.parseInt(br.readLine().trim());


        space = new Point[wrongAmt];
        int min = 0;
        for(int i = 0 ; i < wrongAmt; i++){
            st= new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            min = Math.max(min,r);
            space[i] = new Point(r,c);
        }

        Arrays.sort(space, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.c,o2.c);
            }
        });

        System.out.println(bs(min));

    }


    // 덮을 색종이의 크기를 이분탐색
    public static int bs(int min){
        int left = min;
        int right = cols;

        int ans = 0 ;
        while(left <= right){
            int mid = (left + right)/2;

            //조각수가 많다 (색종이 크기가 작다)
            //mid 는 색종이의 크기
            int cnt = cover(mid);
            if(cnt > target){
                //조각 개수가 너무 많으니까 크기를 키워야함;
                left = mid + 1;
            }else if( cnt <= target){
                //주어진 장수를 다 안써도 되나?
                ans = mid;
                right = mid-1;
            }


        }

        return ans;

    }

    public static int cover(int size){

        int paperCnt = 1;
        int limit = space[0].c+size-1;
        for(int i = 1 ; i < space.length;i++){
            //색종이오른쪽이 잘못된 공간보다 크다 (덮혀있다)
            if(space[i].c > limit){
                paperCnt++;
                limit = space[i].c+size-1;
            }

        }

       return paperCnt;
    }

}
