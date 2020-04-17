import java.util.*;
public class StrPermutations {



        //https://leetcode.com/problems/permutations/

        private void swap(char nums[], int i,int j){
            char temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        private List<Character> getFinalList(char[] nums){
            List<Character> intList = new ArrayList<>(nums.length);
            for (char i : nums)
            {
                intList.add(i);
            }

            return intList;
        }


        private void permuteHelper(char nums[],int start,List<List<Character>> output){

            if(start == nums.length) {
                output.add(getFinalList(nums));
            }else{

                for(int i=start; i< nums.length;i++){

                    swap(nums,i,start);
                    permuteHelper(nums,start+1,output);
                    swap(nums,start,i);

                }
            }


        }


        public List<List<Character>> permute(char[] nums) {
            List<List<Character>> output = new ArrayList<>();

            permuteHelper(nums,0,output);

            return output;
        }


        /*

            public static void main(String[] args) {
        List<List<Character>> output  = new StrPermutations().permute("123456789".toCharArray());

        for (List<Character> result: output) {

            for (Character c:
                 result) {
                System.out.print(c);
            }

            System.out.println();
        }


        System.out.println("total permutations are "+output.size());

    }
         */

}
