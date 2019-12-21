import java.util.*;
public class FindSingleElementWithMultiElementsOfSameCount {


    public static int findSingleElementInMultiple(int[] a,int repetatingElementCount)
    {
        int result = 0;
        int bitPosition = 1;

        for(int i=0;i<Integer.SIZE;i++)
        {
            int count = 0;


            for(int j=0;j<a.length;j++)
            {
                int isZero = a[j]&bitPosition;

                if(isZero != 0)
                {
                    count++;
                }
            }

            if(count%repetatingElementCount != 0)
                result = result | bitPosition;

            bitPosition = bitPosition<<1;

        }


        return result;

    }


    public static void main(String[] args) {

        //int[] arr = {5,2,2,5,5,2,4};
        //int repetatingElementCount = 3;
        int[] arr = {5,2,2,5,1};
        int repetatingElementCount = 2;


        System.out.println(findSingleElementInMultiple(arr,repetatingElementCount));
    }
}
