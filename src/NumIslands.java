public class NumIslands {

    //https://leetcode.com/problems/number-of-islands/submissions/
    public int numIslands(char[][] grid) {
        int islandsCount = 0;

        if(grid.length == 0){
            return 0;
        }

        boolean [][] visited = new boolean[grid.length][grid[0].length];

        for(int row = 0; row < grid.length; row++){

            for(int col = 0; col < grid[0].length; col++){

                if(grid[row][col] == '1' && !visited[row][col]){
                    dfs(grid,visited,row,col);
                    islandsCount++;
                }

            }

        }


        return islandsCount;
    }


    private void dfs(char[][] grid, boolean [][] visited ,int startRow, int startCol){

        if(startRow < 0 || startRow >= grid.length || startCol >= grid[0].length || startCol < 0)           {
            return;
        }

        if(!visited[startRow][startCol]){
            visited[startRow][startCol] = true;


            //go in all 4 direction if only it is 1 and not visited

            if(startRow - 1 >=0 && grid[startRow-1][startCol] == '1' && !visited[startRow-1][startCol]){
                dfs(grid,visited,startRow-1,startCol);
            }


            if(startRow + 1 < grid.length && grid[startRow+1][startCol] == '1' && !visited[startRow+1][startCol]){

                dfs(grid,visited,startRow+1,startCol);

            }

            if(startCol - 1 >= 0 && grid[startRow][startCol-1] == '1' && !visited[startRow][startCol-1]){
                dfs(grid,visited,startRow,startCol-1);
            }


            if(startCol +1 < grid[0].length && grid[startRow][startCol+1] == '1' && !visited[startRow][startCol+1]){
                dfs(grid,visited,startRow,startCol+1);
            }
        }
    }

    public static void main(String[] args) {
        char [][] grid = {
                {'1','1','1','1','0'},
                {'1','1',0,'1',0},
                {'1','1',0,0,0},
                {0,0,0,0,0},
        };

        System.out.println(new NumIslands().numIslands(grid));
    }
}
