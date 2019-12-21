import java.util.Random;

public class MinModforAlternate01
{

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i < n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static int matchSequence(int[] a,int currrent)
    {
        int modified = 0;

        for(int i=0;i<a.length;i++)
        {
            if(currrent != a[i])
                modified++;

            currrent = currrent==1?0:1;
        }

        return modified;
    }


    public static void main(String args[])
    {
        //int arr1[] = {1,12,9,11,14,12,15,5,6,10};

        while(true) {

            Random r = new Random();
            if(r.nextInt(100)==90)
                break;

            System.out.println("New run");

            int size = r.nextInt(10);

            int arr[] = new int[size];

            for (int i = 0; i < size; i++)
                arr[i] = r.nextInt(2);


            printArray(arr);
            System.out.println(Math.min(matchSequence(arr, 1), matchSequence(arr, 0)));
        }
    }
}