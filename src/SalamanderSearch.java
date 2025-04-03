import java.util.ArrayList;
import java.util.List;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     */
    public static boolean canReach(char[][] enclosure) {
        int[] start = salamanderLocation(enclosure);
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        return canReach(enclosure, start, visited);
    }

    // Helper Method
    public static boolean canReach(char[][] enclosure, int[] current, boolean[][] visited) {
        int curR = current[0];
        int curC = current[1];

        if (visited[curR][curC]) return false;
        if (enclosure[curR][curC] == 'f') return true;

        visited[curR][curC] = true;

        List<int[]> moves = possibleMoves(enclosure, current);
        for(int[] move : moves) {
            if(canReach(enclosure, move, visited)) return true;
        }
        return false;
    }

    // Helper Method
    public static List<int[]> possibleMoves(char[][] enclosure, int[] current) {
        int curR = current[0]; // row
        int curC = current[1]; // column

        List<int[]> moves = new ArrayList<>();

        // UP
        int newR = curR - 1; 
        int newC = curC;
        if(newR >= 0 && enclosure[newR][newC] != 'W') {
            moves.add(new int[]{newR, newC});
        } 

        // DOWN
        newR = curR + 1;
        newC = curC;
        if(newR < enclosure.length && enclosure[newR][newC] != 'W') {
            moves.add(new int[]{newR, newC});
        }

        // LEFT
        newR = curR;
        newC = curC - 1;
        if(newC >= 0 && enclosure[newR][newC] != 'W') {
            moves.add(new int[]{newR, newC});
        }

        // RIGHT
        newR = curR;
        newC = curC + 1;
        if(newC < enclosure[0].length && enclosure[newR][newC] != 'W') {
            moves.add(new int[]{newR, newC});
        }

        return moves;
    }

    // Helper Method - That holds a {row, column} as an index
    public static int[] salamanderLocation(char[][] enclosure) {
        for(int r = 0; r < enclosure.length; r++) {      // iterating through the 'row'
            for(int c = 0; c < enclosure[0].length; c++) {   // iterating through the 'column'
                if(enclosure[r][c] == 's') {                    // if that location of iteration finds the salamander,
                    return new int[]{r, c};                          // return the new int[]{r, c} at the location indecies of the salamander
                }
            }
        }
        throw new IllegalArgumentException("No salamander present");
    }

    
}
