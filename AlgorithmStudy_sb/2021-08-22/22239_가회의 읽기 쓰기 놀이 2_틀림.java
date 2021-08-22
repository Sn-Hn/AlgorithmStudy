//22239: 가희와 읽기 쓰기 놀이 2
import java.io.*;
import java.util.*;
 
public class Main {

    static int n, c;
    static ArrayList<Integer> card_list = new ArrayList<>();
    static LinkedList<Integer>[] people;
    static ArrayList<Integer> cal_list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
       
        str = bf.readLine();
        st = new StringTokenizer(str);
        for(int i = 0; i < c; i++) {
            card_list.add(Integer.parseInt(st.nextToken()));
        }

        people = new LinkedList[n + 1];
        for(int i = 1; i <= n; i++) {
            people[i] = new LinkedList<>();
            str = bf.readLine();
            st = new StringTokenizer(str);
            int n = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++) {
                people[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 0; i < c; i++) {
            str = bf.readLine();
            cal_list.add(Integer.parseInt(str.split(" ")[1]));
        }   
        //입력 끝
        backtracking(0, new ArrayList<Integer>());
    }

    public static void backtracking(int idx, ArrayList<Integer> result) {
        if(idx == c) {
            print_result(result);
            System.exit(0);
        }

        int current_card = cal_list.get(idx);
        int card_idx = card_list.indexOf(current_card) + 1;
        boolean isContain = false;
        for(int i = 1; i <= n; i++) {
            if(!people[i].isEmpty() && people[i].peek() == card_idx) {
                result.add(i);
                people[i].poll();
                backtracking(idx + 1, result);
                result.remove((Integer) i);
                people[i].addFirst(card_idx);
            }
        }
        if(!isContain) {
            System.out.println("-1");
            System.exit(0);
        }
    }

    public static void print_result(ArrayList<Integer> result) {
        for(int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}