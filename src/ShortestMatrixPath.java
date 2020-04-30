import java.util.LinkedList;
import java.util.Queue;

public class ShortestMatrixPath {
    private static class Pair{
        int row;
        int col;
        int d;

        Pair(int row,int col,int d) {
            this.row = row;
            this.col = col;
            this.d = d;
        }
    }

    static int shortestCellPath(int[][] grid, int sr, int sc, int tr, int tc) {
        // your code goes here


        Queue<Pair> queue = new LinkedList<>();


        boolean visited[][] = new boolean[grid.length][grid[0].length];


        int dirs[][] = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

        queue.offer(new Pair(sr,sc,0));
        visited[sr][sc] = true;

        while(!queue.isEmpty()){

            Pair currentNode = queue.poll();

            if(currentNode.row == tr && currentNode.col == tc) {
                return currentNode.d;
            }




            for(int[] dir : dirs) {


                int row = currentNode.row + dir[0];
                int col = currentNode.col + dir[1];

                if(row >= 0  && row < grid.length && col >= 0 && col < grid[0].length && !visited[row][col] && grid[row][col] == 1) {
                    queue.offer(new Pair(row,col,currentNode.d+1));
                    visited[row][col] = true;
                }
            }



        }

        return -1;
    }

    public static void main(String[] args) {
        int grid[][] = {{1,1,1},{0,0,0},{0,0,0}};

        System.out.println(shortestCellPath(grid,0, 1, 0, 0));
    }
}
