public class GetSameEleNIndexInSortedArr {

    // log n time complexity
    static int indexEqualsValueSearch(int[] arr) {
        // your code goes here

        int result = -1;
        if(arr == null || arr.length == 0) return result;


        int start = 0;
        int end = arr.length -1;

        while(start <= end) {

            int mid = start + (end-start)/2;

            if(mid == arr[mid]){

                result = mid;
                end = mid - 1;

            } else if(arr[mid] > mid) {

                end = mid - 1 ;

            } else {

                start = mid + 1;

            }

        }


        return result;

    }
}
