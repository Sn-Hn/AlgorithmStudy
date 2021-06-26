import java.util.*;
import java.io.*;

class Solution {
    static final int PRICE = 100;
    static HashMap<String, String> parentMap = new HashMap<>();
    static HashMap<String, Integer> moneyMap = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer;
        ArrayList<Integer> moneyList = new ArrayList<>(); 
        
        makeTree(enroll, referral);
        saleTree(seller, amount);
        
        for (String name : enroll)
        {
            if (moneyMap.get(name) == null)
                moneyList.add(0);
            else
                moneyList.add(moneyMap.get(name));
        }
        
        answer = new int[moneyList.size()];
        int index = 0;
        for (int val : moneyList)
            answer[index++] = val;
        return answer;
    }
    
    private static void makeTree(String[] enroll, String[] referral)
    {
        for (int seller = 0; seller < enroll.length; seller++) 
        {
            String parent = referral[seller];
            
            if (parent.equals("-"))
                parentMap.put(enroll[seller], "center");
            else
                parentMap.put(enroll[seller], parent);   
        }
    }
    
    private static void saleTree(String[] seller, int[] amount)
    {
        int idx = 0;
        for (String name : seller)
        {
            int money = amount[idx++] * PRICE;
            distributeMoney(name, money);
        }
    }
    
    private static void distributeMoney(String name, int money)
    {
        if (money < 1) return;
        
        int change = money / 10;
        int mine = money - change;
        int give = change;
            
        if (give < 1) mine = money;
        
        if (moneyMap.get(name) ==  null)
            moneyMap.put(name, mine);
        else 
            moneyMap.put(name, moneyMap.get(name) + mine);
        
        distributeMoney(parentMap.get(name), give);
    }
}