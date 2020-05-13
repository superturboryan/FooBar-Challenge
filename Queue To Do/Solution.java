import java.util.Arrays;

/*
Thoughts: 
-There is a trick for getting the XOR of ranges faster than performing each operation, maybe a sub-problem of the bigger problem
*/

public class Solution {
    
    public static void main(String[] args) {
        int answer = solution(0, 3);
        System.out.printf("\nAnswer:%d \n",answer);
    }

    public static int solution(int start, int length) {
        
        // Get all ranges to be checked by helper
        int[][] ranges = getRangesToCheck(start, length);
        // Array to store XOR values of each line
        int[] lineXors = new int[length];
        // For each range...
        int i = 0;
        for (int[] range : ranges) {
            // Get the range XOR value using helper
            int rangeXor = getXorRange(range[0], range[1]);
            // Add it to array
            lineXors[i] = rangeXor;
            i++;
        }
        
        int answer = 0;
        // Using the XOR values computed for each line
        for (int num : lineXors) {
            // XOR all the values together one last time
            answer ^= num;
        }
        return answer;
    }

    // Returns a 2D array of two value array ranges [start,end] to be checked by range helper
    public static int[][] getRangesToCheck(int start, int length) {

        int[][] ranges = new int[length][2];

        int lineLength = length * length;
        int jump = length - 1;
        int i = start;
        int last = start + lineLength - length;
        int lineCount = 0;

        while(jump != 0) {
            int[] newRange = new int[2];
            // Find beginning and end of range to add based on jump to be taken
            int beg = i;
            int end = i + jump;
            newRange[0] = beg;
            newRange[1] = end;
            // Add the new range to the ranges to be returned
            ranges[lineCount] = newRange;
            // System.out.println(Arrays.toString(ranges[lineCount]));
            // Incrememnt to the beginning of the next range
            i += jump + lineCount + 1;
            jump -= 1;
            lineCount += 1;
        }
        // Add a final range of length 1 for the last number
        int[] lastRange = new int[2];
        int beg = last;
        int end = last;
        lastRange[0] = beg;
        lastRange[1] = end;
        ranges[lineCount] = lastRange;
        // System.out.println(Arrays.toString(ranges[lineCount]));
        // Return constructed ranges array
        return ranges;
    }

    public static int computeXOR(int n) {  
        // Modulus operator are expensive  
        // on most of the computers.  
        // n & 3 will be equivalent to n % 4  
        // n % 4  
        int x = n & 3;  
        switch (x) {  
            // If n is a multiple of 4  
            case 0:  
                return n;  
            // If n % 4 gives remainder 1  
            case 1:  
                return 1;  
            // If n % 4 gives remainder 2  
            case 2:  
                return n + 1;  
            // If n % 4 gives remainder 3  
            case 3:  
                return 0;  
        }  
        return 0;  
    }  
  

    public static int getXorRange(int l, int r){  
        return computeXOR(r)^computeXOR(l - 1);  
    }  

}