/*
1423. Maximum Points You Can Obtain from Cards

Details
Runtime: 239 ms, faster than 6.53% of Java online submissions for Maximum Points You Can Obtain from Cards.
Memory Usage: 48.7 MB, less than 11.89% of Java online submissions for Maximum Points You Can Obtain from Cards.


 */
class MaxPointsWithKAlternateElementsFromEitherSide {
    public int maxScore(int[] cardPoints, int k) {

        if(k == 0 || cardPoints == null || cardPoints.length == 0){
            return 0;
        }

        if(k >= cardPoints.length){

            return getSum(cardPoints, 0, cardPoints.length -1);
        }


        int[] eleToConsider = new int[2 * k];
        int idx = 0;

        for(int i = k-1; i >= 0; i--){
            eleToConsider[idx++] = cardPoints[i];
        }

        for(int i = cardPoints.length - 1; i >= cardPoints.length - k; i--){
            System.out.println(idx+" "+i);
            eleToConsider[idx++] = cardPoints[i];
        }

        int maxScore = getSum(eleToConsider, 0 , k-1);
        int currentMaxScore = maxScore;

        for(int i = k; i < eleToConsider.length; i++){

            currentMaxScore -= eleToConsider[i-k];
            currentMaxScore += eleToConsider[i];

            maxScore = Math.max(maxScore, currentMaxScore);

        }


        return maxScore;
    }


    private int getSum(int[] cardPoints, int start, int end){
        int sum = 0;

        for(int i = start; i <= end; i++)
            sum += cardPoints[i];

        return sum;
    }
}