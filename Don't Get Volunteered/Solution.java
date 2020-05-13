import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution {
    
    public static void main(String[] args) {

        int solution = solution(0, 1);

        System.out.printf("\nAnswer: Min knight moves is %d \n\n",solution);
    }

    public static int solution(int src, int dest) {

        Tile start = getTileForNumber(src);
        Tile end = getTileForNumber(dest);

        // System.out.printf("\nStart %d: %d,%d \n",src,start.x,start.y);
        // System.out.printf("\nEnd %d: %d,%d \n\n",dest,end.x,end.y);

        return minKnightMoves(start.x, start.y, end.x, end.y);
    }

    public static int minKnightMoves(int currX, int currY, int x, int y) {
        
        int moves = 0;
        
        // OPTIONAL brute force optimization to get closer to goal
        // while(Math.abs(currX - x) > 7 || (Math.abs(currY - y) > 7)) {
        //     
        //     int deltaX = x - currX;
        //     int deltaY = y - currY;
        //     int absDx = Math.abs(deltaX);
        //     int absDy = Math.abs(deltaY);
        //
        //     // Determine if we have to move more in vertical or horizontal
        //     if(absDx <= absDy) {
        //         currY += ((deltaY >= 0)? 1 : -1) * 2; // Move left or right two moves
        //         currX += ((deltaX >= 0)? 1 : -1) * 1; // Move up or down one move
        //         moves++; // Count one move taken
        //     } else {
        //         currX += ((deltaX >= 0)? 1 : -1) * 2;
        //         currY += ((deltaY >= 0)? 1 : -1) * 1;
        //         moves++;
        //     }
        // }

        // BFS checking all possible moves from every tile 
        Map<Tile, Integer> path = new HashMap<>();
        Queue<Tile> tilesToCheck = new LinkedList<>();
        tilesToCheck.add(new Tile(currX, currY));
        path.put(tilesToCheck.peek(), 0);
        
        // While we still have tiles left in the queue
        while(tilesToCheck.size() > 0) {
            
            // Get head of queue
            Tile t = tilesToCheck.poll();
            
            // If head is equal to target
            if(t.equals(new Tile(x,y))) {
                // Return right away, this is our answer
                return moves + path.get(t);
            }
            
            int[][] paths = getPossiblePaths();
            
            // Check all possible paths
            for(int[] p : paths) { 
                // Next tile from this path
                Tile next = new Tile(t.x + p[0], t.y + p[1]);
                // If the tile is not already on our path
                if(!path.containsKey(next)) {
                    // Put new tile in path and increment length of path taken
                    path.put(next, path.get(t) + 1);
                    tilesToCheck.add(next);
                }
            }
        }

        return -1;
    }

    public static int[][] getPossiblePaths() {
        int[][] moves = new int[][]{{1, 2}, {-1, 2}, {1, -2}, {-1, -2},{2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        return moves;
    }

    public static Tile getTileForNumber(int num) {
        int x = num % 8;
        int y = num / 8;
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
    }
}