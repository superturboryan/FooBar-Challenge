/*
Thoughts:
-Find terminal levels
-Find probabilities for each terminal level one by one
-Find common denominator for all numbers
-Construct answer array
*/

import java.util.ArrayList;

public class Solution {
        
    public static void main(String[] args) {
        System.out.println("Good luck!");

        int[][] input = {
            {0,1,0,0,0,1},  // s0, the initial state, goes to s1 and s5 with equal probability
            {4,0,0,3,2,0},  // s1 can become s0, s3, or s4, but with different probabilities
            {0,0,0,0,0,0},  // s2 is terminal, and unreachable (never observed in practice)
            {0,0,0,0,0,0},  // s3 is terminal
            {0,0,0,0,0,0},  // s4 is terminal
            {0,0,0,0,0,0}};  // s5 is terminal
          
        int[] answer = solution(input);
    }

    public static int[] solution(int[][] m) {
        
        ArrayList terms = getTerminalLevels(m); 

        System.out.println("\n\n"+terms.toString());
        
        int[] ret = new int[0];
        return ret;
    }

    public static ArrayList<Integer> getTerminalLevels(int[][] m) {
        ArrayList<Integer> terms = new ArrayList<Integer>();
        
        for (int i = 1; i < m.length; i++) {
            boolean foundOther = false;
            for (int num : m[i]) {
                if (num != 0) {foundOther = true; break;}
            }
            if (!foundOther) terms.add(i);
        }
        
        return terms;
    }

}