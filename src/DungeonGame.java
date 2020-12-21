/*
https://leetcode.com/problems/dungeon-game/submissions/
Runtime: 0 ms, faster than 100.00% of Java online submissions for Dungeon Game.
Memory Usage: 39 MB, less than 25.29% of Java online submissions for Dungeon Game.
 */
class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return 0;
        final int n = dungeon.length;
        final int m = dungeon[0].length;
        Integer[][] dag = new Integer[n][m];
        dfs(dungeon, 0, 0, dag);

        return dag[0][0];
    }

    private int dfs(int[][] dungeon, int x, int y, Integer[][] dag) {
        //DAG
        if (dag[x][y] != null) {
            return dag[x][y];
        }
        //base case
        if (x == dungeon.length - 1 && y == dungeon[0].length - 1) {
            dag[x][y] = dungeon[x][y] >= 1 ? 1 : 1 - dungeon[x][y];

            return dag[x][y];
        }
        //general case
        if (x == dungeon.length - 1) {
            int requiredByNextStep = dfs(dungeon, x, y + 1, dag);
            int ans = dungeon[x][y] >= requiredByNextStep ? 1 : requiredByNextStep - dungeon[x][y];
            dag[x][y] = ans;

            return ans;
        }

        if (y == dungeon[0].length - 1) {
            int requiredByNextStep = dfs(dungeon, x + 1, y, dag);
            int ans = dungeon[x][y] >= requiredByNextStep ? 1 : requiredByNextStep - dungeon[x][y];
            dag[x][y] = ans;

            return ans;
        }

        int childOne = dfs(dungeon, x + 1, y, dag);
        int childTwo = dfs(dungeon, x, y + 1, dag);
        int requiredByNextStep = Math.min(childOne, childTwo);
        int ans = dungeon[x][y] >= requiredByNextStep ? 1 : requiredByNextStep - dungeon[x][y];

        dag[x][y] = ans;

        return ans;
    }
}
