/*
Don't Get Volunteered!
======================

As a henchman on Commander Lambda's space station, you're expected to be resourceful, smart, and a quick thinker. 
It's not easy building a doomsday device and capturing bunnies at the same time, after all! 

In order to make sure that everyone working for her is sufficiently quick-witted, 
Commander Lambda has installed new flooring outside the henchman dormitories. 

It looks like a chessboard, and every morning and evening you have to solve a new movement puzzle in order to cross the floor. 

That would be fine if you got to be the rook or the queen, but instead, you have to be the knight. 

Worse, if you take too much time solving the puzzle, you get "volunteered" as a test subject for the LAMBCHOP doomsday device!

To help yourself get to and from your bunk every day, write a function called solution(src, dest) which takes in two parameters: 
-the source square, on which you start, 
-and the destination square, which is where you need to land to solve the puzzle.  

The function should return an integer representing the smallest number of moves it will take for you to travel from 
the source square to the destination square using a chess knight's moves 
(that is, two squares in any direction immediately followed by one square perpendicular to that direction, or vice versa, in an "L" shape). 

Both the source and destination squares will be an integer between 0 and 63, inclusive, and are numbered like the example chessboard below:
-------------------------
| 0| 1| 2| 3| 4| 5| 6| 7|
-------------------------
| 8| 9|10|11|12|13|14|15|
-------------------------
|16|17|18|19|20|21|22|23|
-------------------------
|24|25|26|27|28|29|30|31|
-------------------------
|32|33|34|35|36|37|38|39|
-------------------------
|40|41|42|43|44|45|46|47|
-------------------------
|48|49|50|51|52|53|54|55|
-------------------------
|56|57|58|59|60|61|62|63|
-------------------------

-- Python cases --
Input:
solution.solution(0, 1)
Output:
    3

Input:
solution.solution(19, 36)
Output:
    1

-- Java cases --
Input:
Solution.solution(19, 36)
Output:
    1

Input:
Solution.solution(0, 1)
Output:
    3
*/

import java.util.*;

public class Solution {
    
    public static void main(String[] args) {

        solution(4, 61);

    }

    public static int solution(int src, int dest) {
        int[][] board = getChessBoard();

        Tile start = getTileForNumber(src);
        Tile end = getTileForNumber(dest);

        System.out.printf("\nStart: %d,%d \n",start.x,start.y);
        System.out.printf("End: %d,%d \n\n",end.x,end.y);

        return shortestPath(start.x, start.y, end.x, end.y, board);
    }

    public static int shortestPath(int startX, int startY, int endX, int endY, int[][] board) {



        return 0;
    }

    public static Tile getTileForNumber(int num) {

        int x = num % 7;
        int y = Math.max(0 , (num/7)-1);

        Tile pos = new Tile(x,y);

        return pos;
    }

    public static int[][] getChessBoard() {
        int[][] board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int val = (i*8) + j;
                board[i][j] = val;
            }
        }
        return board;
    }

    static class Tile {
        int x;
        int y;
        // Init
        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return x == tile.x &&
                    y == tile.y;
        }
        
        @Override public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}