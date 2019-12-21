import java.util.Random;

public class HeapSort
{

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i < n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    //takes an input specified array and currentIndex at which heapify should be performed
    //takes log(n) to execute
    static void heapify(int a[],int currentIndex,int length){

        //get root, left and right index
        int largeElementIndex = currentIndex;
        int left = 2*currentIndex+1;
        int right = 2*currentIndex+2;

        //check if left child is greater
        if(left < length && a[left] > a[largeElementIndex])
            largeElementIndex = left;

        //check if right child is greater; it will override changes made by previous if
        if(right < length && a[right] > a[largeElementIndex])
            largeElementIndex = right;

        //check if large element index has changed or not
        //swap and heapfiy the unbalanced node for now
        if(largeElementIndex != currentIndex)
        {
            //swap
            int temp = a[currentIndex];
            a[currentIndex] = a[largeElementIndex];
            a[largeElementIndex] = temp;

            //call heapify for child node which is not yet balanced
            heapify(a,largeElementIndex,length);
        }

    }

    //main heap sort driver function
    static void heapSort(int a[]){

        //build heap from half array because for left and right child we will heapify recursively
        for(int i = a.length/2 - 1; i>=0;i--)
            heapify(a,i,a.length-1);

        for(int i = a.length-1;i>=0;i--)
        {
            //get max from root node and add it to last position
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            //heapify root node to balance the max heap
            heapify(a,0,i-1);
        }
    }

    public static void main(String args[])
    {
        int arr[] = {1,12,9,11,14,12,15,5,6,10};
        HeapSort m = new HeapSort();
        m.heapSort(arr);
        m.printArray(arr);
    }
}
