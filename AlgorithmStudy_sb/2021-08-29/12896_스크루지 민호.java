//12896: 스크루지 민호
import java.io.*;
import java.util.*;
 
public class Main {
    
    static int n, max, node;
    static ArrayList<Node>[] list;
    static boolean[] visited;
 
    public static void main(String[] args) throws IOException {
 
        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
 
        list = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }
 
        for(int i = 0; i < n - 1; i++) {
            String str = bf.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, 1));
            list[e].add(new Node(s, 1));
        }
        //입력 끝
 
        //임의의 노드(1)에서 다른 노드로 가는 최대 거리와 그 거리에 있는 노드를 구한다.
        max = -1;
        visited = new boolean[n + 1];
        dfs(1, 0);
 
        //위에서 찾은 노드에서 가장 먼 노드로 가는 거리를 구한다.
        max = -1;
        visited = new boolean[n + 1];
        dfs(node, 0);
 
        System.out.println((1 + max) / 2);
    }
 
    public static void dfs(int x, int len) {
        if(max < len) {
            max = len;
            node = x;
        }
        visited[x] = true;
 
        for(int i = 0; i < list[x].size(); i++) {
            Node next = list[x].get(i);
            if(!visited[next.end]) {
                dfs(next.end, len + next.cost);
                visited[x] = true;
            }
        }
    }
 
    public static class Node {
        int end, cost;
 
        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
