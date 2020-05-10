class FindSingleFromThriceArray {
    public int singleNumber(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }


        int[] result = new int[32];


        for(int n : nums){

            for(int i = 0; i <= 31; i++)
            {
                int x = 1 << i;
                if((n & x) == x){

                    result[i]++;


                    if(result[i] == 3){
                        result[i] = 0;
                    }
                }

            }

        }


        int finalValue = 0;
        for(int i = 31; i>=0; i--){
            if(result[i] == 1) {
                finalValue = finalValue | (1 << i);
            }
        }

        return finalValue;
    }
}
