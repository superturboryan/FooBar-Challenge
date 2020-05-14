/*
Thoughts:
-Find terminal levels
-Init array to hold probability for each level
-Going line by line, adjust probabilities for each level
-Construct answer array

-STOCHASTIC MATRIX + MARKOV CHAINS!

-The problem is an Absorbing Markov Chain
-The solution uses Matrix Inverse using Gaussian Elimination
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.*;
import java.util.Queue;

public class Solution {
        
    public static void main(String[] args) {
        System.out.println("Good luck!");

        int[][] input = {
            {0,1,0,0,0,1},  // s0, the initial state, goes to s1 and s5 with equal probability
            {4,0,0,3,2,0},  // s1 can become s0, s3, or s4, but with different probabilities
            {0,0,0,0,0,0},  // s2 is terminal, and unreachable (never observed in practice)
            {0,0,0,0,0,0},  // s3 is terminal
            {0,0,0,0,0,0},  // s4 is terminal
            {0,0,0,0,0,0}}; // s5 is terminal
            
            // s2 has probability 0     = 0.000
            // s3 has probability 3/14  = 0.214
            // s4 has probability 1/7   = 0.142
            // s5 has probability 9/14  = 0.643

        int[] answer = answer(input);
        System.out.println("\n"+answer);
    }

    public static int[] solution(int[][] m) {
        
        ArrayList<Integer> levelSums = getLevelSums(m);
        ArrayList<Integer> terminalLevels = getTerminalLevels(m); 

        System.out.println("\n\n"+levelSums.toString());
        System.out.println("\n\n"+terminalLevels.toString());
        
        double[] probabilityForEachLevel = new double[m.length];

        // For each level
        for (int i = 0; i < m.length; i++) {
            
            // If level is terminal, continue
            if (terminalLevels.contains(i)) continue;
            
            // For each digit in level
            for (int j = 0; j < m.length; j++) {
                
                int digit = m[i][j];
                // Looking for digits that aren't 0...
                if (digit == 0) continue;

                // For the first row we can set prob for each level accordingly
                if (i == 0) {

                    // If target level is not terminal
                    // if (!terminalLevels.contains(j)) 
                    //     continue;
                    
                    double totalProb = (double)digit / (double)levelSums.get(i);
                    probabilityForEachLevel[j] = totalProb;
                    continue; // Move to next digit
                }

                double totalProb = (((double)digit / (double)levelSums.get(i)) * (probabilityForEachLevel[i] == 0 ? 1 : probabilityForEachLevel[i]));

                // Otherwise...
                // Check if target level is before current level
                // If so, go back through previous level with new multiplier, 
                if (j <= i) {
            
                    for (int x = 0; x < m.length; x++) {
                        
                        int otherDig = m[j][x];

                        // Skipping any numbers pointing to current number's row
                        if (otherDig == 0) continue;
                        
                        double otherProb = ((double)otherDig / (double)levelSums.get(i));

                        // probabilityForEachLevel[x] += otherProb;
                    }
                }
                else {
                    probabilityForEachLevel[j] += totalProb;
                }
            }
        }

        System.out.println("");
        for (int i = 0; i < terminalLevels.size(); i++) {
            System.out.printf("%.3f, ",probabilityForEachLevel[terminalLevels.get(i)]);
        }
        System.out.println("\n" + Arrays.stream(probabilityForEachLevel).sum());

        int[] ret = {1};
        return ret;
    }

    // Convenience
    public static ArrayList<Integer> getLevelSums(int[][] m) {
        ArrayList<Integer> levelSums = new ArrayList<Integer>();
        for (int[] level : m) {
            levelSums.add(Arrays.stream(level).sum());
        }
        return levelSums;
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