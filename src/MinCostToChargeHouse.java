class MinCostToChargeHouse {

    private int maxRef = 1_000_000_000;

    public int solution(int[] A, int X, int Y) {
        // write your code in Java SE 8

        //take non using second ka balance
        return solve(A, 0, 0, 0, X, Y);
    }


    public int solve(int[] a, int i, int chargeLeft, int chargeNeed, int x, int y){

        if(i == a.length && chargeNeed == 0){
            return 0;
        }

        if (i == a.length && chargeNeed == chargeLeft){
            return 0;
        }

        if (i == a.length && chargeLeft > 0 ){
            return maxRef;
        }

        if(i == a.length && chargeNeed > 0){
            return maxRef;
        }

        int firstType  = x + solve(a, i + 1, chargeLeft, chargeNeed, x, y);

        int secondType = y + solve(a, i +1, chargeLeft + a[i], chargeNeed, x, y);

        int unitliseExiting = maxRef;

        if(a[i] <= chargeLeft){

            unitliseExiting = solve(a, i + 1, chargeLeft - a[i], chargeNeed, x, y);
        }

        int fillOver = solve(a, i + 1, chargeLeft, chargeNeed + a[i], x, y);


        int r =  Math.min(firstType, Math.min(secondType, Math.min(unitliseExiting, fillOver)));
        return r;

    }

    public static void main(String[] args) {
        System.out.println(new MinCostToChargeHouse().solution(new int[]{5, 3, 8, 3, 2}, 2, 5));
    }
}

