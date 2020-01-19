import java.util.Arrays;
import java.util.HashMap;

public class FormDietWithIngredients {

    public static boolean shouldConsiderNSubtract(int[] test, int[] ingredient) {

        for (int i = 0; i < test.length; i++) {
            test[i] = test[i] - ingredient[i];
            if (test[i] < 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isArrayZero(int[] test) {

        for (int s : test) {
            if (s != 0) {
                return false;
            }
        }
        return true;
    }

    static class SubProblemResult {
        boolean isFound;
        String value;

        SubProblemResult() {
            isFound = false;
            value = "";
        }
    }

    static int countR = 0;

    public static SubProblemResult solveDietIngredientsR(int[][] ingredients, int[] test, int currentIngredient, String output) {
        countR++;
        if (currentIngredient >= ingredients.length) {
            SubProblemResult s = new SubProblemResult();
            s.isFound = false;
            return s;
        }


        int modifiedTest[] = new int[test.length];
        System.arraycopy(test, 0, modifiedTest, 0, test.length);

        boolean isConsider = shouldConsiderNSubtract(modifiedTest, ingredients[currentIngredient]);

        if (isConsider && isArrayZero(modifiedTest)) {
            SubProblemResult s = new SubProblemResult();
            s.isFound = true;
            s.value = output + " " + currentIngredient;
            return s;
        }

        SubProblemResult taken = new SubProblemResult();
        if (isConsider) {
            taken = solveDietIngredientsR(ingredients, modifiedTest, currentIngredient + 1, output + " " + currentIngredient);
        }


        SubProblemResult notTaken = solveDietIngredientsR(ingredients, test, currentIngredient + 1, output);


        if (taken.isFound && notTaken.isFound) {
            return taken.value.length() > notTaken.value.length() ? notTaken : taken;
        }

        if (taken.isFound) {
            return taken;
        } else if (notTaken.isFound) {
            return notTaken;
        } else {
            return new SubProblemResult();
        }
    }

    public static void main(String args[]) {

        int[][] ingredients = {
                {1, 2, 3}
                , {2, 3, 4}
                , {4, 5, 6}
                , {5, 6, 7}
                , {6, 7, 8}
                , {11, 13, 15}
        };

        //int[] test = {6, 8, 10};
        //int[] test  = {9,11,13};
        int[] test = {12, 15, 18};

        /*
                        6,8,10 --> 0,""
                        /                                                                   \
                 5,6,7-->1,"0"                                                              6,8,10-->1,""
                 /           \                                                              /           \
           3,3,3-->2,"0 1"      5,6,7-->2,"0"                                          4,5,6-->2,"2"    6,8,10-->2,"" , false
/       \                       /                   \                                   /                           /           \
false    3,3,3-->3,"0 1"       1,1,1-->3, "0 2"     5,6,7-->3,"0"                   0,0,0-->"2 3",true          2,3,4-->3,"2"+false      false
        ""                     ""                     ""
         */

        SubProblemResult diet = solveDietIngredientsR(ingredients, test, 0, "");
        System.out.println(diet.isFound + " " + diet.value);
        System.out.println("Total func calls in recursive is " + countR);

        diet = solveDietIngredientsTD(ingredients, test, 0, "");
        System.out.println(diet.isFound + " " + diet.value);
        System.out.println("Total func calls in top down is " + countTD);
    }

    static int countTD = 0;
    static HashMap<String, SubProblemResult> subProblems = new HashMap<>();

    public static SubProblemResult solveDietIngredientsTD(int[][] ingredients, int[] test, int currentIngredient, String output) {
        countTD++;
        if (currentIngredient >= ingredients.length) {
            SubProblemResult s = new SubProblemResult();
            s.isFound = false;
            return s;//or nothing
        }

        //if sub problem is found
        if (subProblems.containsKey(currentIngredient + " " + Arrays.hashCode(test))) {
            return subProblems.get(currentIngredient + " " + Arrays.hashCode(test));
        }

        int modifiedTest[] = new int[test.length];
        System.arraycopy(test, 0, modifiedTest, 0, test.length);

        boolean isConsider = shouldConsiderNSubtract(modifiedTest, ingredients[currentIngredient]);

        if (isConsider && isArrayZero(modifiedTest)) {
            SubProblemResult s = new SubProblemResult();
            s.isFound = true;
            s.value = output + " " + currentIngredient;
            subProblems.put(currentIngredient + " " + Arrays.hashCode(test), s);

            return s;
        }

        SubProblemResult taken = new SubProblemResult();
        if (isConsider) {
            if (subProblems.containsKey((currentIngredient + 1) + " " + Arrays.hashCode(modifiedTest))) {
                taken = subProblems.get((currentIngredient + 1) + " " + Arrays.hashCode(modifiedTest));
            } else {
                taken = solveDietIngredientsTD(ingredients, modifiedTest, currentIngredient + 1, output + " " + currentIngredient);
                subProblems.put((currentIngredient + 1) + " " + Arrays.hashCode(modifiedTest), taken);
            }
        }

        SubProblemResult notTaken;
        if (subProblems.containsKey((currentIngredient + 1) + " " + Arrays.hashCode(test))) {
            notTaken = subProblems.get((currentIngredient + 1) + " " + Arrays.hashCode(test));
        } else {
            notTaken = solveDietIngredientsTD(ingredients, test, currentIngredient + 1, output);
            subProblems.put((currentIngredient + 1) + " " + Arrays.hashCode(test), notTaken);
        }


        if (taken.isFound && notTaken.isFound) {
            return taken.value.length() > notTaken.value.length() ? notTaken : taken;
        }

        if (taken.isFound) {
            return taken;
        } else if (notTaken.isFound) {
            return notTaken;
        } else {
            return new SubProblemResult();
        }
    }
}
