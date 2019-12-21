import java.util.*;
public class TotalWaterInHistogram {

    public static int findTotalWater(int[] histogram,int start,int end)
    {
        int[] leftMax = new int[histogram.length];
        int[] rightMax = new int[histogram.length];

        int index = -1;

        for(int i=0;i<leftMax.length;i++){
            if(index == -1)
            {
                leftMax[i] = i;
                index = i;
            }
            else if(histogram[i] > histogram[index]){
                leftMax[i] = i;
                index = i;
            }
            else {
                leftMax[i] = index;
            }
        }

        index = rightMax.length-1;
        rightMax[index] = index;
        for(int i=rightMax.length-2;i>=0;i--){
            if(histogram[index]<histogram[i])
            {
                index = i;
                rightMax[i] = index;
            }
            else
            {
                rightMax[i] = index;
            }
        }


        int totalWater = 0;
        displayArray(leftMax,0,leftMax.length-1);
        displayArray(rightMax,0,rightMax.length-1);


        for(int i=0;i<histogram.length;i++)
        {
            totalWater += Math.min(histogram[leftMax[i]],histogram[rightMax[i]]) - histogram[i] ;
        }


        return totalWater;
    }

    public static void displayArray(int[] arr,int start,int end)
    {
        int index = start;

        for(int i=start+1;i<=end;i++)
        {
            System.out.print(arr[i]+" ");
        }

        System.out.println("");
    }

    public static void main(String[] args) {

        int[] histogram = {0,0,4,0,0,6,0,0,3,0,8,0,2,0,5,2,0,3,0,0};

        System.out.println("Total water of the histogram is "+findTotalWater(histogram,0,histogram.length-1));

    }
}
