public class ShiftedSortArraySearch {
    static int shiftedArrSearch(int[] shiftArr, int num) {
        // your code goes here
        if(shiftArr.length == 0) return -1;

        if(shiftArr.length == 1) return shiftArr[0] == num ? 0 : -1;



        int pivot = findRotatedPivot(shiftArr);

        if(shiftArr[pivot] == num){
            return pivot;
        }


        if(shiftArr[pivot] < num && shiftArr[shiftArr.length-1] >= num){

            return binarySearch(shiftArr,pivot,shiftArr.length-1,num);
        }else{

            return binarySearch(shiftArr,0,pivot,num);
        }

    }


    static int binarySearch(int []arr, int start, int end, int num){

        while(start <= end){

            int mid = start + (end-start)/2;

            if(arr[mid] == num){
                return mid;
            } else if (num > arr[mid]){
                start = mid+1;
            }else {
                end = mid-1;
            }

        }


        return -1;

    }

    /*

      [9,12,17,2,4,5,6], 4
       0, 1, 2,3,4,5,6
  */

    static int findRotatedPivot(int [] arr){

        int start = 0;
        int end = arr.length -1;


        while(start < end){

            int mid = start + (end-start)/2;

            if(mid-1 >=0 && mid+1 < arr.length){
                if(arr[mid] < arr[mid+1] && arr[mid-1] > arr[mid]){
                    return mid;
                }
            }


            if(arr[mid] > arr[end])
                start = mid;
            else end = mid;
        }

        return start;
    }
}
