import java.util.*;

// key 조합을 만든다
        
// 0 1 , 0 2, 0 3, ... 2 3 4, 2 3 5 ..

// 특정 키를 가지고 -> uniqeness & minimality 체크

// 되면 해당 키를 제거

// 다시 조합 시작

// 0 1 2 3 4 -> 1개, 2개, 3개, 4개, 5개

// ckey : 2 3 // ckey 1 3 4 (가능)

class Solution {
    
    static ArrayList<ArrayList<Integer>> keylist = new ArrayList<>();
    static boolean findKey = false;
    static String[][] table;
    static StringBuilder keystr = new StringBuilder();
    
    static ArrayList<Integer> tmpkey = new ArrayList<>();
    static ArrayList<Integer> indexlist = new ArrayList<>();
    
    public int solution(String[][] relation) {
        table = relation;
        
        combinationKey();
        
        return keylist.size();
    }
    
    private static void combinationKey()
    {
        for (int i = 0; i < table[0].length; i++)
            indexlist.add(i);
        
        int max = 0;
        boolean[] visited = new boolean[indexlist.size()];
        while (true)
        {
            max++;
            
            Arrays.fill(visited, false);

            comb(0, 0, max, visited);
            
            if (findKey) {
                findKey = false;
                max--;
            }
            
            if (max > indexlist.size() - 1) 
                break;
        }
    }
    
    private static void comb(int start, int select, int max, boolean[] visited)
    {
        if (findKey) return;
        
        if (select == max)
        {
            // key
            boolean flag = true;
            HashSet<String> set = new HashSet<>();
            
            // 유일성 체크
            for (int i = 0; i < table.length; i++) 
            {
                StringBuilder key = new StringBuilder();
             
                for (int k = 0; k < visited.length; k++) 
                {
                    if (visited[k]) {
                        int col_obj = indexlist.get(k);
                        key.append(table[i][col_obj]).append(" ");
                    }
                }
                
                if (set.contains(key.toString())) return;
                else set.add(key.toString());
            }
        
            // 최소성을 위해 이전 키와 비교
            if (flag) 
            {
                tmpkey = new ArrayList<>();
                
                for (int k = 0; k < visited.length; k++) {
                    if (visited[k]) {
                        int col_obj = indexlist.get(k);
                        tmpkey.add(col_obj);
                    }
                }
                
                for (ArrayList<Integer> key : keylist) {
                    if (tmpkey.containsAll(key))
                        return;
                }
                
                keylist.add(tmpkey);
                findKey = true;
            }
            return;
        }
        
        for (int i = start; i < visited.length; i++)
        {
            if (!visited[i])
            {
                visited[i] = true;
                comb(i + 1, select + 1, max, visited);
                if (findKey) return;
                visited[i] = false;
            }
        }
    }
}