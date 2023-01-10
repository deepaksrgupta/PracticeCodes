import java.util.ArrayList;
import java.util.List;

public class WordFinder {
    public static List<List<Pair<Integer,Integer>>> wordFinder3(char[][] grid, List<String> words){

        List<List<Pair<Integer,Integer>>> result  = new ArrayList<>();

        for (String word: words){
            List<Pair<Integer,Integer>> r = wordFinder2(grid, word);
            if(r != null){
                result.add(r);
            }
        }

        return result;
    }

    public static List<Pair<Integer,Integer>> wordFinder2(char[][] grid, String word){

        List<Pair<Integer,Integer>> result = null;

        for (int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){

                if(grid[i][j] == word.charAt(0)){

                    Pair<Boolean,List<Pair<Integer,Integer>>> currentResult = dfs(grid, word, i, j, 0);
                    if(currentResult.getKey()){
                        return currentResult.getValue();
                    }
                }

            }
        }

        return result;

    }

    public static Pair<Boolean,List<Pair<Integer,Integer>>> dfs(char[][] grid, String word, int i, int j, int k){

        if(k == word.length()){
            return new Pair<>(true, new ArrayList<>());
        }

        if(grid[i][j] != word.charAt(k)){
            return new Pair<>(false, null);
        }

        Pair<Boolean,List<Pair<Integer,Integer>>>  down  = dfs(grid, word, i + 1, j, k+1);
        Pair<Boolean,List<Pair<Integer,Integer>>>  right  = dfs(grid, word, i, j + 1, k+1);



        if(down.getKey()){
            down.getValue().add(0, new Pair<>(i,j));
            return down;
        }

        if(right.getKey()){
            right.getValue().add(0, new Pair<>(i,j));
            return right;
        }

        return new Pair<>(false, null);
    }
}
